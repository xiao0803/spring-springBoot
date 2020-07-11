package club.throwable.sample;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/19 23:10
 */
public class CounterSample {

	public static void main(String[] args) throws Exception {
		//tag必须成对出现，也就是偶数个
		Counter counter = Counter.builder("counter")
				.tag("counter", "counter")
				.description("counter")
				.register(new SimpleMeterRegistry());
		counter.increment();
		counter.increment(2D);
		System.out.println(counter.count());
		System.out.println(counter.measure());
		//全局静态方法
		Metrics.addRegistry(new SimpleMeterRegistry());
		counter = Metrics.counter("counter", "counter", "counter");
		counter.increment(10001D);
		counter.increment(10002D);
		System.out.println(counter.count());
		System.out.println(counter.measure());
	}
}
