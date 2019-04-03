package com.rojek.kamil.fluentconditionals;

import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class ThenReturn<ReturnType> {
    private boolean condition;
    private Supplier<ReturnType> supplierIsTrue;


    public ThenReturn(boolean condition, Supplier<ReturnType> supplier) {
        this.condition = condition;
        this.supplierIsTrue = supplier;
    }

    public ReturnType orElse(ReturnType returnType) {
        return orElse(() -> returnType);
    }

    public ReturnType orElse(Supplier<ReturnType> supplierParameter) {
        if(condition)
            return supplierIsTrue.get();
        return supplierParameter.get();
    }

    public <ExceptionType extends Throwable> ReturnType orElseThrowE(ExceptionType exceptionType) throws ExceptionType {
        return orElseThrow(() -> exceptionType);
    }

    public <ExceptionType extends Throwable> ReturnType orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if(!condition)
            throw exceptionType.get();
        return supplierIsTrue.get();
    }
}
