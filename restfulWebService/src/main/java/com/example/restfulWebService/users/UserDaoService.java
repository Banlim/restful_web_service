package com.example.restfulWebService.users;



import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

// 사용자 전체 목록 조회, 정보 추가, 상세보기 등 사용자 관리하는 class
@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	// 사용자 수
	private static int usersCount = 3;
	
	
	static {
		users.add(new User(90001, "Kim", new Date(), "pass1", "701010-1111111"));
		users.add(new User(90002, "Lee", new Date(), "pass2", "801010-2111111"));
		users.add(new User(90003, "Park", new Date(), "pass3", "901010-1231111"));
	}
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	public List<User> findAll(){
		return users;
	}
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
