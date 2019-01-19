package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private App app;
	
	@Before
	public void setUp()
	{
		app = new App();
	}
	
    @Test
    public void additionOfTwoPositiveNumbersTest()
    {
    	int actual = app.addPositiveNumbers(4,5);
    	int expected = 9;
        assertEquals( expected, actual );
    }
    
    @Test
    public void additionOfTwoNegativeNumbersTest()
    {
    	int actual = app.addNegativeNumbers(-4,-5);
    	int expected = -9;
        assertEquals( expected, actual );
    }
    
    @Test
    public void additionOfOnePositiveNumberAndOneNegativeNumberTest()
    {
    	int actual = app.addOnePositiveAndOneNegativeNumber(5,-4);
    	int expected = 1;
        assertEquals( expected, actual );
    }
    

    @Test
    public void subtractionOfTwoPositiveNumbersTest()
    {
    	int actual = app.subtractPositiveFromPositiveNumber(5,4);
    	int expected = 1;
        assertEquals( expected, actual );
    }
    
    @Test
    public void subtractionOfTwoNegativeNumbersTest()
    {
    	int actual = app.subtractNegativeNumberFromNegativeNumber(-10,-5);
    	int expected = -5;
        assertEquals( expected, actual );
    }
    
    @Test
    public void subtractionOfPositiveNumberFromNegativeNumberTest()
    {
    	int actual = app.subtractPositiveFromNegativeNumber(-5,4);
    	int expected = 1;
        assertEquals( expected, actual );
    }
    
    @Test
    public void subtractionOfNegativeNumberFromPositiveNumberTest()
    {
    	int actual = app.subtractNegativeFromPositiveNumber(-5,4);
    	int expected = 1;
        assertEquals( expected, actual );
    }
    

}
