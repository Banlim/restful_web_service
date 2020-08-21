package com.example.restfulWebService;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


// 전체 코드 : Github
// https://github.com/edowon/restful-web-service
@SpringBootApplication
public class RestfulWebServiceApplication {
	
	// GET : 주로 select(조회)를 위한 목적
	// POST : 주로 insert를 위한 목적
	// PUT : 주로 modify/update를 위한 목적
	// DELETE : 주로 delete/remove를 위한 목적
	
	// Leonard Richardson Maturity Model
	// REST API를 개발할 때 확인해야 할 REST 방식의 주요 요소를 3단계로 나눈 Model
	// level 0
	// 기존의 resource를  web service 형태로 제공해서 단순히 URI만 mapping한 형태
	// 동작 값을 URI에 표현하고 있음
	
	// level 1
	// 일정한 패턴을 가지고 작성되어있지만, HTTP의 method별로 서비스를 구분하여 사용하지는 않음.
	
	// level 2
	// level 1 + HTTP Methods 
	
	// level 3
	// level 2 + HATEOAS
	// DATA + Next Possible Actions
	
	// REST API 설계 시 고려해야 할 사항
	// 1. 해당 API를 사용하는 Consumer 입장에서  명료하고 직관적으로 설계해야 한다.
	// 2. HTTP의 method와 Request, Response Type, Header 값 등과 같이 HTTP의 장점을 최대한 살려 설계
	// 3. 각 resource별로 적절한 HTTP의 Method를 사용해야 한다.
	// 4. 각 API 요청에 따른 적절한 HTTP의 status를 결정해야 한다.
	// 5. URI에 직접 private한 정보를 표현하지 말아야한다.
	// 6. resource는 동사형보다는 명사형으로 표시해주는 게 좋다.
	// 7. 일괄된 접근 "endpoint"를 사용하는 것이 좋다.

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREA);
		return localeResolver;
	}

}
 