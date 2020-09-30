package ink.zhongshao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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

		// for循环生成数据
		List<UserPo> users = new ArrayList<>();
		for (int i = 1; i <= 60000; i++) {
			users.add(new UserPo(Long.parseLong(String.valueOf(i)), i+"nax水电费阿的说法第三方阿电饭锅第三方斯蒂芬me", i+"s是的冯绍东方故事峰发地方广东省多少奥德赛ex", i+"axvxcv是的冯绍峰东方故事发多少奥德赛ge"));
		}
		
		Long start = System.currentTimeMillis();
		List<UserPo> newUsers = 
		users.stream().map((user) -> {
			UserPo userp = new UserPo(9L, "水电费阿的说法第三方阿斯蒂芬", "是的冯绍峰发多少奥德赛", "文化双方都快捷方式");
			BeanUtils.copyProperties(user, userp);
			//System.out.println(userp );
			return user;
		} ).collect(Collectors.toList()) ;
		System.out.println(System.currentTimeMillis() -   start     +"      newUsers.size() = " + newUsers.size() );
		
		return "Hello, greetings from microservice-alibaba-nacos-discovery";
	}

}

class UserPo {

	public UserPo(Long id, String name, String sex, String age) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	private Long id;
	private String name;
	private String sex;
	private String age;
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;
	private String j;
	private String k;
	private String l;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	@Override
	public String toString() {
		return "UserPo [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", a=" + a + ", b=" + b
				+ ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + ", i=" + i + ", j=" + j
				+ ", k=" + k + ", l=" + l + "]";
	}

}
