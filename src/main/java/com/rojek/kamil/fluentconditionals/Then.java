package com.rojek.kamil.fluentconditionals;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class Then {
    private boolean condition;

    public Then(boolean isTrue) {
        this.condition = isTrue;
    }

    public OrElse then (Runnable runnable) {
        return new OrElse(condition, runnable);
    }

    public <ReturnType>ThenReturn <ReturnType> thenReturn (ReturnType returnType) {
        return new ThenReturn<>(condition, () -> returnType);
    }

    public <ReturnType>ThenReturn <ReturnType> thenReturn (Supplier<ReturnType> supplier) {
        return new ThenReturn<>(condition, supplier);
    }

    public <ExceptionType extends Throwable> void thenThrowE (ExceptionType exceptionType) throws ExceptionType {
        thenThrow(() -> exceptionType);
    }

    public <ExceptionType extends Throwable> void thenThrow (Supplier<ExceptionType> exceptionType) throws ExceptionType {
        throw exceptionType.get();
    }

    public <ExceptionType extends Throwable> void thenThrow (Function<String, ExceptionType> exceptionType, String message) throws ExceptionType {
        throw exceptionType.apply(message);
    }
}
