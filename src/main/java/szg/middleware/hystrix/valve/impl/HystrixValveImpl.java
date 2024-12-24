package szg.middleware.hystrix.valve.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import szg.middleware.hystrix.annotation.DoHystrix;
import szg.middleware.hystrix.valve.IValveService;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;


public class HystrixValveImpl extends HystrixCommand<Object> implements IValveService {

    private ProceedingJoinPoint jp;
    private Method method;
    private DoHystrix doHystrix;

    public HystrixValveImpl() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("DefaultGroup")));
    }

    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoHystrix doHystrix, Object[] args) {
        this.jp = jp;
        this.method = method;
        this.doHystrix = doHystrix;


        Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CustomGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(doHystrix.timeoutValue())
                );

        return this.execute();
    }

    @Override
    protected Object run() throws Exception {
        try {
            return jp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Object getFallback() {
        return JSON.parseObject(doHystrix.returnJson(), method.getReturnType()); // 执行降级逻辑
    }
}

