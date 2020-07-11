package club.throwable.sample;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/19 23:30
 */
public class GaugeSample {

	public static void main(String[] args) throws Exception {
		AtomicInteger atomicInteger = new AtomicInteger();
		Gauge gauge = Gauge.builder("gauge", atomicInteger, AtomicInteger::get)
				.tag("gauge", "gauge")
				.description("gauge")
				.register(new SimpleMeterRegistry());
		atomicInteger.addAndGet(5);
		System.out.println(gauge.value());
		System.out.println(gauge.measure());
		atomicInteger.decrementAndGet();
		System.out.println(gauge.value());
		System.out.println(gauge.measure());
		//全局静态方法，返回值竟然是依赖值，有点奇怪，暂时不选用
		Metrics.addRegistry(new SimpleMeterRegistry());
		AtomicInteger other = Metrics.gauge("gauge", atomicInteger, AtomicInteger::get);
	}
}
