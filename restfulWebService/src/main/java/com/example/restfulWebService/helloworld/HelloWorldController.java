package com.example.restfulWebService.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

// DispatcherServlet
//	- client의 모든 요청을 한 곳으로 받아서 처리
//	- 요청에 맞는 Handler로 요청을 전달
//	- Handler의 실행 결과를 Http Response 형태로 만들어서 반환


// RestController
//	- Spring4 부터 annotation으로 @RestController 지원
//	- @Controller + @ResponseBody
//	- View를 갖지 않는 REST Data(JSON/XML)를 반환
@RestController
public class HelloWorldController {
	// GET
	// /hello-world (endPoint)
	// @RequestMapping(method=RequestMethod.GET, path="/hello-world(endPoint)") -> 이전에는 이걸 사용하기도 했음.
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	// alt + enter => 오류 해결 방안 나오게 하는 단축키
	@GetMapping(path ="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}
	
	// Path Variable : URL에 가변 data를 client에게 전달할 수 있다.
	@GetMapping(path ="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World!, %s", name));
	}
	
	@GetMapping(path ="/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("greeting.message", null, locale);
	}
}
