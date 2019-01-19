package com.cg.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.app.calculator.Calculator;


public class App 
{
    public static void main( String[] args )
    {
		/* Logger logger = Logger.getLogger(Calculator.class.getName()); */
    	ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    	Calculator calculator = context.getBean(Calculator.class);
    	calculator.add(5, 200);
    }
}
