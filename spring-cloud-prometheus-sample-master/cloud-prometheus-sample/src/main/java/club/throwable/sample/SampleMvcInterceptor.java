package club.throwable.sample;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/21 9:41
 */
@Component
public class SampleMvcInterceptor extends HandlerInterceptorAdapter {

	private static final Counter COUNTER = Counter.builder("xiao.http.request.count")
			.tag("HttpCount", "HttpCount")
			.description("Http请求统计")
			.register(Metrics.globalRegistry);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object handler, Exception ex) throws Exception {
		COUNTER.increment();
	}
}
