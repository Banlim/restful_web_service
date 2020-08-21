package com.example.restfulWebService.users;



import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonFilter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//JsonIgnoreProperties -> password 정보는 나타나지 않음.
//@JsonIgnoreProperties(value= {"password"})
//@JsonFilter("UserInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
//해당하는 class의 이름을 가진 DB table을 자동으로 생성해줌.
@Entity
public class User {
	
	
	
	public User(int id, String name, Date joinDate, String password, String ssn) {
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.password = password;
		this.ssn = ssn;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	// name의 길이가 2글자 이내이면 유효성 검사를 통해 Exception 처리 됨
	@Size(min=2, message = "Name은 2글자 이상 입력해주세요.")
	@ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "사용자 등록일을 입력해주세요.")
	private Date joinDate;
	
	@ApiModelProperty(notes = "사용자 패스워드을 입력해주세요.")
	private String password;
	
	@ApiModelProperty(notes = "사용자 주민번호을 입력해주세요.")
	private String ssn;
	
	// user data와 mapping이 되도록 함.
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	// 노출하고 싶지 않은 데이터 값을 무시해주세요.
	// http://localhost:8088/users 에서 password, ssn 필드가 나타나지 않음.
	// @JsonIgnore
	// private String password;
	// @JsonIgnore
	// private String ssn; // 주민등록번호
}
