/**
 * 
 */
package ink.zhongshao.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 
 * 每个请求的耗时 (GatewayFilter:自定义局部过滤器)
 * 
 * @author zs
 * @date 2020年12月11日
 */
public class ElapsedFilter implements GatewayFilter, Ordered {

	private static final Log log = LogFactory.getLog(GatewayFilter.class);
	private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
			if (startTime != null) {
				//.getRawPath()
				log.info(exchange.getRequest().getURI() + ": " + (System.currentTimeMillis() - startTime)
						+ "ms");
			}
		}));
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}