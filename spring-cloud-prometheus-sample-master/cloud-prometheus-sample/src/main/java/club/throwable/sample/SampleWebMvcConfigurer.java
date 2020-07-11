package club.throwable.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/21 9:40
 */
@Component
public class SampleWebMvcConfigurer implements WebMvcConfigurer {

	@Autowired
	private SampleMvcInterceptor sampleMvcInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sampleMvcInterceptor);
	}
}
