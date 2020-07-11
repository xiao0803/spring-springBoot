package club.throwable.sample.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/21 12:31
 */
@Aspect
@Component
public class HttpMethodCostAspect {

	AtomicInteger atomicInteger = new AtomicInteger();
	
	@Autowired
	private MeterRegistry meterRegistry;

	@Pointcut("@annotation(club.throwable.sample.aspect.MethodMetric)")
	public void pointcut() {
	}

	@Around(value = "pointcut()")
	public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
		Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		//这里是为了拿到实现类的注解
		Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
				.getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());
		if (currentMethod.isAnnotationPresent(MethodMetric.class)) {
			MethodMetric methodMetric = currentMethod.getAnnotation(MethodMetric.class);
			return processMetric(joinPoint, currentMethod, methodMetric);
		} else {
			return joinPoint.proceed();
		}
	}

	private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
								 MethodMetric methodMetric) throws Throwable {
		String name = methodMetric.name();
		if (!StringUtils.hasText(name)) {
			name = currentMethod.getName();
		}
		String desc = methodMetric.description();
		if (!StringUtils.hasText(desc)) {
			desc = name;
		}
		String[] tags = methodMetric.tags();
		if (tags.length == 0) {
			tags = new String[2];
			tags[0] = name;
			tags[1] = name;
		}
		System.out.println("name打印：" + name);
		System.out.println("desc打印：" + desc);
		System.out.println("tags打印：" + Lists.newArrayList(tags));
		
		Counter counter = Counter.builder(name + "_counter")
				.tags(tags)
				.description(desc + "_counter")
				.register(meterRegistry);
		counter.increment(10D);
		
		Gauge gauge = Gauge.builder(name + "_gauge", atomicInteger, AtomicInteger::get)
				.tags(tags)
				.description(desc + "_gauge")
				.register(meterRegistry);
		atomicInteger.addAndGet(1000);
		
		Timer timer = Timer.builder(name + "_timer").tags(tags)
				.description(desc + "_timer")
				.register(meterRegistry);
		
		DistributionSummary summary = DistributionSummary.builder(name + "_summary")
				.tags(tags)
				.description(desc + "_summary")
				.register(meterRegistry);
		summary.record(LocalDateTime.now().getSecond());
		
		return timer.record(() -> {
			try {
				return joinPoint.proceed();
			} catch (Throwable throwable) {
				throw new IllegalStateException(throwable);
			} finally {
				summary.record(LocalDateTime.now().getSecond());
			}
		});
	}
}
