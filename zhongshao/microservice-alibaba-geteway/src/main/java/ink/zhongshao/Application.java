package ink.zhongshao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import ink.zhongshao.filter.ElapsedFilter;
import ink.zhongshao.filter.TokenFilter;


/**
 * 网关服务
 * 
 * @author Wangfan
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 全局过滤器
	 * @return
	 */
	/*
	 * @Bean public TokenFilter tokenFilter(){ return new TokenFilter(); }
	 */
	
	/**
	 * 设置 路由走指定的自定义过滤器
	 * @param builder
	 * @return
	 */
	//@Bean
	public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
	    // @formatter:off
	    return builder.routes()
	            .route(r -> r.path("/sentinel/**")
	                         .filters(f -> f.stripPrefix(2)
	                                        .filter(new ElapsedFilter())
	                                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
	                         .uri("lb://microservice-alibaba-sentinel")
	                         .order(0)
	                         .id("microservice-alibaba-sentinel2")
	            )
	            .build();
	    // @formatter:on
	}
}
