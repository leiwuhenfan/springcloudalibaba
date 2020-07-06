package org.microservice.alibaba.sentinel.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Wangfan 
 */
@RestController
public class InitController {

	//http://localhost:9092/hello
	@GetMapping("/hello")
	public String hello() {
		System.out.println("microservice-alibaba-sentinel");
		return "Hello, greetings from microservice-alibaba-sentinel";
	}
}
