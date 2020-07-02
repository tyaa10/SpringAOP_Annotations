package org.tyaa.spring.aop.logging;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogger {

	@Pointcut("execution(* *(..)) && within(org.tyaa.spring.aop.objects.*)")
	private void allMethods() {
	};

	@Around("allMethods()")
	public Object watchTime(ProceedingJoinPoint joinpoint) {
		long start = System.currentTimeMillis();
		System.out.println("method begin: " + joinpoint.getSignature().toShortString() + " >>");
		Object output = null;

		/* for (Object object : joinpoint.getArgs()) {
			System.out.println("Param : " + object);
		} */

		try {
			output = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long time = System.currentTimeMillis() - start;
		System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
		System.out.println();

		return output;
	}

	@AfterReturning(pointcut = "allMethods()", returning = "obj")
	public void print(Object obj) {

		System.out.println("Print info begin >>");

		if (obj instanceof Set) {
			Set set = (Set) obj;
			for (Object object : set) {
				System.out.println(object);
			}

		} else if (obj instanceof Map) {
			Map map = (Map) obj;
			/* for (Object object : map.keySet()) {
				System.out.println("key=" + object + ", " + map.get(object));
			} */
			map.entrySet().forEach((entry) -> {
				System.out.println("key=" + ((Entry<String, Integer>) entry).getKey() + ", " + ((Entry<String, Integer>) entry).getValue());
			});
		}

		System.out.println("Print info end <<");
		System.out.println();

	}

}
