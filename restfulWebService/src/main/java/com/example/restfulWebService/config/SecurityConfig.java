package com.example.restfulWebService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// JPA
// Java Persistence API
// Java ORM 기술에 대한 API 표준 명세
// Java Application에서 관계형 DB를 사용하는 방식을 정의한 Interface
// EntityManager를 통해 CRUD 처리

// Hibernate
// JPA의 구현체, Interface를 직접 구현한 library
// 생산성, 유지보수, 비종속성

// Spring Data JPA
// Spring Module
// JPA를 추상화한 Repository Interface 제공

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	인증 절차 없이 h2-console 바로 접속
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
		throws Exception {
		auth.inMemoryAuthentication()
			.withUser("kim")
			.password("{noop}test1234")
			.roles("USER");
	}

}
