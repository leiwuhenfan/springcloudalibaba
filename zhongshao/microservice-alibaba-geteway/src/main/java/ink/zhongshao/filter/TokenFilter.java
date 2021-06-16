
package ink.zhongshao.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * 
 * 
 * 
 * 
 * @author zs
 * @date 2020年12月11日
 */
@Component(value = "globalTokenFilter")
public class TokenFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		System.out.println("token=" + token);
		if (token == null || token.isEmpty()) {
			ServerHttpResponse reponse = exchange.getResponse();
			HttpHeaders header = reponse.getHeaders();
			header.add("token", token);
			reponse.setStatusCode(HttpStatus.UNAUTHORIZED);
			return reponse.setComplete();
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -100;
	}
}