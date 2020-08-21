package com.example.restfulWebService.users;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

// HATEOAS : 현재 리소스와 연관된(호출 가능한) 자원 상태 정보를 제공

@RestController
@RequestMapping("/admin")
public class AdminUserController {
private UserDaoService service;
	
	public AdminUserController(UserDaoService service) {
		this.service = service;
	}
	
	@GetMapping("/users")
	public MappingJacksonValue retrieveAllUsers(){
		List<User> users = service.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "joinDate", "password");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	// 일반 client와 달리 관리자의 경우 user 정보를 다 볼 수 있도록 하는 것.
	// GET /admin/users/1 -> /admin/v1/users/1 : version 관리도 포함.
//	@GetMapping("/v1/users/{id}")
//	@GetMapping(value = "/users/{id}/", params = "version=1")
//	@GetMapping(value = "/users/{id}", headers="X-API-VERSION=1")
	@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
	public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "password", "ssn");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
//	@GetMapping("/v2/users/{id}")
//	파라미터로 버전 관리
//	@GetMapping(value = "/users/{id}/", params = "version=2")
//	Header로 버전 관리
//	@GetMapping(value = "/users/{id}", headers="X-API-VERSION=2")
//	MimeType을 이용하여 버전 관리
	@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
	public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		
		// User -> User2로 변환
		UserV2 userV2 = new UserV2();
		BeanUtils.copyProperties(user, userV2); // id, name, joinDate, pw, ssn
		userV2.setGrade("VIP");
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("id", "name", "joinDate", "grade");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(userV2);
		mapping.setFilters(filters);
		
		return mapping;
	}


}
