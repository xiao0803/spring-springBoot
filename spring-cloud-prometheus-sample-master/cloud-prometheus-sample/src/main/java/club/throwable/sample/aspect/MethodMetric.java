package club.throwable.sample.aspect;

import java.lang.annotation.*;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2018/7/21 12:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodMetric {

	String name() default "";

	String description() default "";

	String[] tags() default {};
}
