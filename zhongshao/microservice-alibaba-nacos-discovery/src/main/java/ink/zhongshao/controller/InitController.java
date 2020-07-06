package ink.zhongshao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangfan
 */
@RestController
public class InitController {

	/**
	 * http://localhost:9090/hello
	 * 
	 * test
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		System.out.println("microservice-alibaba-nacos-discovery");
		return "Hello, greetings from microservice-alibaba-nacos-discovery";
	}

}
