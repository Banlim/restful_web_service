package com.example.restfulWebService.users;




import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	private UserDaoService service;
	
	public UserController(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	// GET /users/1 or users/10 -> String
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		// HATEOAS : spring 2.2 이상
		// Resource -> EntityModel
		// ControllerLinkBuilder -> WebMvcLinkBuilder
		
		// HATEOAS : spring 2.1.8 Release
		// "all-users", SERVER_PATH + "/users"
		// retrieveAlUsers
		Resource<User> resource = new Resource<>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
	
		return resource;
	}
	

	// CREATED
	// input - details of user
	// output - CREATED & Return the created URI
	// Status : 201 Created 
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
	}
}
