package com.example.restfulWebService.users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//게시물 관리를 위한 Post Entity 추가
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	// 기본 키에 대한 정보를 저장하기 위한 id 값
	@Id
	@GeneratedValue
	private Integer id;
	
	// 게시물의 내용에 해당하는 field
	private String description;
	
	// User : Post -> 1 : (0~N), Main : Sub -> Parent : Child
	// 어떤 사용자가 작성했는지 알려주는 field
	// 외부에 공개되지 않기 위해 @JsonIgnore라는 annotation 추가
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
}
