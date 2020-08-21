package com.example.restfulWebService.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
// prefix로 /jpa 사용
@RequestMapping("/jpa")
public class UserJpaController {

	//UserRepository라는 interface의 의존성 주입을 받아서 사용
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	// http://localhost:8088/jpa/users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
//		findById는 Optional을 반환한다.
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID{%s} not found", id));
		}
		
		Resource<User> resource = new Resource<>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
	
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	// test
	// Postman에 "POST" 형태로 http://localhost:8088/jpa/users 에 접속
	// Body -> raw -> text를 JSON으로 변경하면 415 Unsupported Media Type Error 문제 해결
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	// Post method를 사용할 수 있는 API method를 생성
	// http://localhost:8088/jpa/users/90001/posts
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPostsByUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID{%s} not found", id));
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
		
		// 사용자 id 검색
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID{%s} not found", id));
		}
		
		// 사용자가 있다면 id값 반환
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedPost.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
