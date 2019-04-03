package com.rojek.kamil.fluentconditionals;

import static com.rojek.kamil.fluentconditionals.FluentConditionals.*;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Kamil Rojek
 */
public class FluentConditionalsTest {
    @Test
    public void testFluentConditional() {
        //FluentConditionals mock = mock(FluentConditionals.class);
        assertNotNull(when(false));
    }

    @Test
    public void testBooleanFunctionInterface(){
        assertNotNull(when(()-> true));
    }

    @Test
    public void testThenFunction(){
        Runnable firstRunnable = mock(Runnable.class);

        FluentConditionals.when(true).then(firstRunnable).orElse(firstRunnable);
        verify(firstRunnable).run();
    }

    @Test
    public void testThenMethod(){
        Runnable firstRunnable = mock(Runnable.class);
        Runnable secondRunnable = mock(Runnable.class);

        FluentConditionals.when(()->false).then(firstRunnable).orElse(firstRunnable);

        verify(firstRunnable).run();
        verify(secondRunnable, never()).run();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionHandling() throws RuntimeException{
        //Exception exception = mock(RuntimeException.class);
        Runnable runnable = mock(Runnable.class);

        FluentConditionals.when(false).then(runnable).orElseThrowE(TestHelper.createException());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionHandlingNoSupplier() throws RuntimeException{
        //Exception exception = mock(RuntimeException.class);
        Runnable runnable = mock(Runnable.class);
        FluentConditionals.when(false).then(runnable).orElseThrow(TestHelper::createException);
    }

    @Test
    public void testExceptionHandlingNoThrowing() throws RuntimeException{
        //Exception exception = mock(RuntimeException.class);
        Runnable runnable = mock(Runnable.class);
        FluentConditionals.when(true).then(runnable).orElseThrow(TestHelper::createException);
        FluentConditionals.when(true).then(runnable).orElseThrowE(TestHelper.createException());
    }

    @Test
    public void testReturn(){
        int result = FluentConditionals.when(true)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(0);

        int result2 = FluentConditionals.when(false)
                .thenReturn(TestHelper.getHighNumber())
                .orElse(0);

        assertEquals(result, 1000);
        assertEquals(result2, 0);
    }

    @Test
    public void testOrElseReturn(){
        int result = FluentConditionals.when(()->false)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);

        assertEquals(result, 1);
    }

    @Test
    public void testGiven(){
        FluentConditionals.given("this").when(true)
                .then(TestHelper::printFirstChar)
                .orElse(TestHelper::printLastChar);

        FluentConditionals.given("this").when(false)
                .then(TestHelper::printFirstChar)
                .orElse(TestHelper::printLastChar);

        FluentConditionals.given(() -> "this")
                .when(() -> false)
                .then(TestHelper::printLastChar)
                .orElse(TestHelper::printFirstChar);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void thenThrowTest(){
        FluentConditionals.when(true).thenThrow(RuntimeException::new, "asd");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testOrElseThrow(){
        FluentConditionals.when(false)
                .then(TestHelper::printBar)
                .orElseThrow(RuntimeException::new);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testThenReturnR(){
        FluentConditionals.given("Greetings")
                .when(() -> true)
                .thenReturn(String::hashCode)
                .orElseThrow(IllegalAccessError::new, "Exception message");

        FluentConditionals.given("Greetings")
                .when(() -> true)
                .thenReturn(() -> "asd")
                .orElseThrow(IllegalAccessError::new, "Exception message");

        FluentConditionals.given("Greetings")
                .when(() -> true)
                .then(TestHelper::printFirstChar)
                .orElseThrow(RuntimeException::new, "Exception message");

        FluentConditionals.given("Greetings")
                .when(() -> false)
                .then(TestHelper::printFirstChar)
                .orElseThrow(RuntimeException::new, "Exception message");
    }

    @Test
    public void testExceptionThrower(){


    }

}