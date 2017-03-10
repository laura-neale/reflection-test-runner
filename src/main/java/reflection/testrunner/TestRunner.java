package reflection.testrunner;

import static reflection.testrunner.AnnotationScanner.getAnnotatedMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import reflection.test.CalculatorTest;
import reflection.testrunner.annotations.Before;
import reflection.testrunner.annotations.Test;

public class TestRunner {

    public void runTests() {
        runTests(CalculatorTest.class);
    }

    private void runTests(Class testClass) {
        List<Method> befores = getAnnotatedMethods(Before.class, testClass);
        List<Method> tests = getAnnotatedMethods(Test.class, testClass);
        tests.forEach(test -> runTest(testClass, befores, test));
    }

    private void runTest(Class testClass, List<Method> befores, Method test) {
        try {
            Object testInstance = testClass.newInstance();
            setup(befores, testInstance);
            System.out.println(String.format("Running test %s.%s", testClass.getSimpleName(), test.getName()));
            invoke(test, testInstance);
            System.out.println("  > PASS");
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof AssertionError) {
                System.out.println("  > FAIL: " + e.getCause().getMessage());
            } else {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(String.format("Error running test %s.%s", testClass.getSimpleName(), test.getName()));
            e.printStackTrace();
        }
    }

    private void setup(List<Method> befores, Object testInstance) throws InvocationTargetException, IllegalAccessException {
        for (Method before : befores) {
            invoke(before, testInstance);
        }
    }

    private void invoke(Method method, Object testInstance) throws InvocationTargetException, IllegalAccessException {
        method.invoke(testInstance, new Object[0]);
    }

}
