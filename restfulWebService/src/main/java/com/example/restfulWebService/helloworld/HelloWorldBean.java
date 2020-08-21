package com.example.restfulWebService.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok => 자동으로 Getter, Setter 생성함.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
	// ctrl + space => 생성자, import? 자동 생성
	private String message;
	
}
