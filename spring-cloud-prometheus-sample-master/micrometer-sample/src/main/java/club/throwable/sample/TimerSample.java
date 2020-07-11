package club.throwable.sample;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/19 23:44
 */
public class TimerSample {

	public static void main(String[] args) throws Exception{
		Timer timer = Timer.builder("timer")
				.tag("timer","timer")
				.description("timer")
				.register(new SimpleMeterRegistry());
		timer.record(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			}catch (InterruptedException e){
				//ignore
			}
		});
		System.out.println(timer.count());
		System.out.println(timer.measure());
		System.out.println(timer.totalTime(TimeUnit.SECONDS));
		System.out.println(timer.mean(TimeUnit.SECONDS));
		System.out.println(timer.max(TimeUnit.SECONDS));
	}
}
