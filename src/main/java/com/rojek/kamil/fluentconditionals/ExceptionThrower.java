package com.rojek.kamil.fluentconditionals;

import java.util.function.Function;

/**
 * @author Kamil Rojek
 */
public class ExceptionThrower<Type, ExceptionThrow extends Throwable> {
    private Type type;
    private Throwable exceptionThrow;
    private boolean condition;
    private String message;

    ExceptionThrower(Type type, ExceptionThrow exceptionThrow, boolean condition, String message) {
        this.type = type;
        this.exceptionThrow = exceptionThrow;
        this.condition = condition;
        this.message = message;
    }

    public Type orElseThrow(Function<String, ExceptionThrow> e) throws ExceptionThrow {
        return orElseThrow(e.apply(message));
    }


    public Type orElseThrow(ExceptionThrow e) throws ExceptionThrow {
        if(!condition)
            throw e;
        return type;
    }
}
