package ink.zhongshao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ink.zhongshao.util.JsonResult;
import ink.zhongshao.util.ResultCode;


/**
 * @author Minbo
 */
@RestController
@RefreshScope
public class InitController {

	//默认值123
	@Value("${config.appKey:123}")
	private String appKey;

	@GetMapping("/hello")
	public String hello() {
		System.out.println("microservice-alibaba-nacos-config");
		return "Hello, greetings from microservice-alibaba-nacos-config";
	}

	@GetMapping("/getAppKey")
	public JsonResult getAppKey() {
		return new JsonResult(ResultCode.SUCCESS, this.appKey);
	}
}