package com.example.restfulWebService.users;



import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//default 생성자 자동 생성 annotation
@NoArgsConstructor
//@JsonFilter("UserInfoV2")
public class UserV2 extends User{
	private String grade; // 고객의 등급 지정
}
