package ink.zhongshao.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangfan
 */
@RestController
public class InitController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

		Map<String, Object> result = this.jdbcTemplate.queryForMap("select * from user u limit 1");

		if (!CollectionUtils.isEmpty(result)) {
			Set<String> sets = result.keySet();
			sets.forEach((key) -> {
				System.out.println(key + ":" + result.get(key));
			});
		}

		return "Hello, greetings from microservice-alibaba-nacos-discovery";
	}

}
