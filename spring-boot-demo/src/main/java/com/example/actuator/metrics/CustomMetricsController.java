package com.example.actuator.metrics;

import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomMetricsController {

	@RequestMapping(value = "/counter-test", method = RequestMethod.GET)
	public void counterTest() {
		// Counter的使用方式1：tag必须成对出现，也就是偶数个（测试不起作用）
//		Counter counter = Counter.builder("counterName1103v1")
//				.tag("counterTagKey1103v1", "counterTagVal1103v1")
//				.description("counterDescription1103v1")
//				.register(new SimpleMeterRegistry());
//		counter.increment();
//		counter.increment(2D);
//		System.out.println(counter.count());
//		System.out.println(counter.measure());
		// Counter的使用方式2：全局静态方法
		Metrics.addRegistry(new SimpleMeterRegistry());
		Counter counter = Metrics.counter("counterName1103v2", "counterTagVal1103v2", "counterDescription1103v2");
		counter.increment(100D);
		counter.increment(10D);
		System.out.println(counter.count());
		System.out.println(counter.measure());
	}
	
	@RequestMapping(value = "/gauge-test", method = RequestMethod.GET)
	public void gaugeTest() {
		// 使用方式1（测试不起作用）
        AtomicInteger atomicInteger = new AtomicInteger();
//        Gauge gauge = Gauge.builder("gaugeName1103v1", atomicInteger, AtomicInteger::get)
//                .tag("gaugeTagKey1103v1", "gaugeTagVal1103v1")
//                .description("gaugeDescription1103v1")
//                .register(new SimpleMeterRegistry());
//        atomicInteger.addAndGet(5);
//        System.out.println(gauge.value());
//        System.out.println(gauge.measure());
//        atomicInteger.decrementAndGet();
//        System.out.println(gauge.value());
//        System.out.println(gauge.measure());
        //使用方式2：全局静态方法，返回值竟然是依赖值，有点奇怪，暂时不选用
        Metrics.addRegistry(new SimpleMeterRegistry());
        AtomicInteger other = Metrics.gauge("gaugeName1103v2", atomicInteger, AtomicInteger::get);
        System.out.println("gaugeTest测试");
	}
	

}
