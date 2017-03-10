package reflection.test;

import static reflection.testrunner.Assertions.assertEquals;

import reflection.calculator.Calculator;
import reflection.testrunner.annotations.Before;
import reflection.testrunner.annotations.Test;


public class CalculatorTest {

    private int a;
    private int b;
    private Calculator calc;

    @Before
    public void setUp() {
        a = 1;
        b = 2;
        calc = new Calculator();
    }

    @Test
    public void failingTest() {
        int actual = calc.add(a,b);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void passingTest() {
        int actual = calc.add(a,b);
        int expected = 3;
        assertEquals(expected, actual);
    }

}