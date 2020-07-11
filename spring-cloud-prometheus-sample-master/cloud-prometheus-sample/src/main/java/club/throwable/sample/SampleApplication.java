package club.throwable.sample;

import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import club.throwable.sample.aspect.MethodMetric;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/21 9:13
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
	
	@MethodMetric(name="xiao_hello", description="xiao_description", tags={"xiao_tag_v1","val_v1","xiao_tag_v2","val_v2"})
	@GetMapping(value = "/hello")
	public String hello(@RequestParam(name = "name", required = false, defaultValue = "doge") String name) {
		System.out.println("开始hello请求");
		try {
			TimeUnit.SECONDS.sleep(5);
		}catch (InterruptedException e){
			//ignore
		}
		return String.format("%s say hello!", name);
	}
}
