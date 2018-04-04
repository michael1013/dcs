package cn.gnetop.dcs.console.system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DaoAspectManager {

	// @Around("execution(* cn.gnetop.dcs.dao.impl.*Impl.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}
}
