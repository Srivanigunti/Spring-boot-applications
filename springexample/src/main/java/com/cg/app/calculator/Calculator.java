package com.cg.app.calculator;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
	
	public Integer add(int numberOne, int numberTwo)
	{
		Logger logger = Logger.getLogger(Calculator.class.getName());
		logger.info("Sum: " + (numberOne + numberTwo));
		return numberOne + numberTwo;
	}

}
