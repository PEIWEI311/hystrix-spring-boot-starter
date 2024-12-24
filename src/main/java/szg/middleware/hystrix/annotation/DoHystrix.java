package szg.middleware.hystrix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoHystrix {

    String returnJson() default "";  // 降级逻辑返回的 JSON 数据

    int timeoutValue() default 1000; // 超时时间（毫秒）

}
