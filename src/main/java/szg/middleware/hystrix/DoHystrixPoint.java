package szg.middleware.hystrix;

import szg.middleware.hystrix.annotation.DoHystrix;
import szg.middleware.hystrix.valve.IValveService;
import szg.middleware.hystrix.valve.impl.HystrixValveImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class DoHystrixPoint {

    @Pointcut("@annotation(szg.middleware.hystrix.annotation.DoHystrix)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(doGovern)")
    public Object doRouter(ProceedingJoinPoint jp, DoHystrix doGovern) throws Throwable {
        IValveService valveService = new HystrixValveImpl();
        return valveService.access(jp, getMethod(jp), doGovern, jp.getArgs());
    }

    private Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}

