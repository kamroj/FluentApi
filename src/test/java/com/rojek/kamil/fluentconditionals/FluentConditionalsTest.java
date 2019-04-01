package com.rojek.kamil.fluentconditionals;

import static com.rojek.kamil.fluentconditionals.FluentConditionals.when;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

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

        FluentConditionals fc = FluentConditionals.when(true).then(firstRunnable);
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

        FluentConditionals.when(false).then(runnable).orElseThrow(TestHelper.createException());
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
        FluentConditionals.when(true).then(runnable).orElseThrow(TestHelper.createException());
    }

    @Test
    public void testReturn(){
        int result = FluentConditionals.when(true)
                                    .thenReturn(TestHelper::getHighNumber)
                                    .orElse(0);

        int result2 = FluentConditionals.when(false)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(0);

        assertTrue(result == 1000);
        assertTrue(result2 == 0);
    }

    @Test
    public void testOrElseReturn(){
        int result = FluentConditionals.when(()->false)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);

        assertTrue(result == 1);
    }
}