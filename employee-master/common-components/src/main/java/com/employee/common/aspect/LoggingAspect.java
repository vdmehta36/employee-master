package com.employee.common.aspect;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger;

	public LoggingAspect() {
		logger = LoggerFactory.getLogger(getClass());

	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}

	@Pointcut("execution(* com.employee.controller.*Controller.*(..))")
	public void methodPointcut() {
	}
	

	@Around("requestMapping() || methodPointcut()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable { 
		Calendar start = null;
		String methodSignature = pjp.getSignature().toLongString();

		try {
			start = Calendar.getInstance();
			logger.info("++++++++Entering: Method signature++++++++ \n" + methodSignature + " at: " + start.getTime());

			return pjp.proceed();

		} finally {
			Calendar stop = Calendar.getInstance();
			logger.info("++++++++ Exiting : method signature++++++++  \n" + methodSignature + "  at: " + stop.getTime());
			long startMillis = start.getTimeInMillis();
			long stopMillis = stop.getTimeInMillis();

			double durationMillis = stopMillis - startMillis;
			logger.info("++++++++ method signature " + methodSignature + " took " + (durationMillis / 1000.0) + " seconds");

		}
	}
	
}
