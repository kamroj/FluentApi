package com.rojek.kamil.fluentconditionals;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class OrElse {
    private Runnable runnableTrue;
    private boolean condition;

    OrElse(boolean condition, Runnable runnable) {
        this.condition = condition;
        this.runnableTrue = runnable;
    }

    public void orElse(Runnable runableParameter) {
        if (condition)
            runnableTrue.run();
        else
            runableParameter.run();
    }

    public <ExceptionType extends Throwable> void orElseThrowE(ExceptionType exceptionType) throws ExceptionType {
        orElseThrow(() -> exceptionType);
    }

    public <ExceptionType extends Throwable> void orElseThrow(Function<String, ExceptionType> exceptionType, String message) throws ExceptionType {
        orElseThrow(() -> exceptionType.apply(message));
    }

    public <ExceptionType extends Throwable> void orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if(!condition)
            throw exceptionType.get();
        else
            runnableTrue.run();
    }
}
