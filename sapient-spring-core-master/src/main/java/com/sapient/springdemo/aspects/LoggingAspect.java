package com.sapient.springdemo.aspects;

import com.sapient.springdemo.service.EmployeeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.sapient.springdemo.service.EmployeeServiceImpl.saveEmployee(..))")
    public void logBefore(JoinPoint target) {
        System.out.println("------------logging before the method is getting executed-------------------");
        System.out.println(target.getArgs()[0]);
        System.out.println(target.getTarget() instanceof EmployeeService);
    }
    
    @After("execution(* com.sapient.springdemo.service.EmployeeServiceImpl.saveEmployee(..))")
    public void logAfter(JoinPoint target) {
        System.out.println("------------logging after the method is getting executed-------------------");
        System.out.println(target.getArgs()[0]);
        System.out.println(target.getTarget() instanceof EmployeeService);
    }
    
    //Both functionality of before and after
    @Around("execution(* com.sapient.springdemo.service.EmployeeServiceImpl.saveEmployee(..))")
    public void logAround(ProceedingJoinPoint target) {
        System.out.println("------------logging before the method is getting executed in around-------------------");
        if(target.getArgs()[0] instanceof EmployeeService){
        	
        	try {
        		target.proceed();
        	} 
        	catch(Throwable throwable) {
        		throwable.printStackTrace();
        	}
        }
        System.out.println("------------logging after the method is getting executed in around-------------------");
    }
    
}