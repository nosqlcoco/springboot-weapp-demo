package com.weapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ApiAspect {
	
	@Pointcut(value="@annotation(com.weapp.common.annotation.Api)")
	public void apiAspect(){
		
	}
	
	@Around("apiAspect()")
	public Object doAround(ProceedingJoinPoint pjp) throws Exception{
		Object result = null;
		
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			sw.start(pjp.getSignature().getName());
			result = pjp.proceed();
			
		} catch(Throwable e){
			e.printStackTrace();
		}finally {
			sw.stop();
		}
		return result;
		
		
	}
}
