package com.rojek.kamil.fluentconditionals;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class OrElseGivenReturn<GivenType, ReturnType> {
    private boolean condition;
    private GivenType givenType;
    private Function<GivenType, ReturnType> ifTrueFunction;

    OrElseGivenReturn(boolean condition, GivenType givenType, Function<GivenType, ReturnType> function) {
        this.condition = condition;
        this.givenType = givenType;
        this.ifTrueFunction = function;
    }

    public ReturnType orElse(ReturnType returnType) {
        return orElse(() -> returnType);
    }

    public ReturnType orElse(Supplier<ReturnType> supplier) {
        return orElse(givenType-> supplier.get());
    }

    public ReturnType orElse(Function<GivenType, ReturnType> functionWhenFalse) {
        if(condition)
            return ifTrueFunction.apply(givenType);
        return functionWhenFalse.apply(givenType);
    }

    public <ExceptionType extends Throwable> ReturnType orElseThrowE(ExceptionType exceptionType) throws ExceptionType {
       return  orElseThrow(() -> exceptionType);
    }

    public <ExceptionType extends Throwable> ReturnType orElseThrow(Function<String, ExceptionType>  exceptionType, String message) throws ExceptionType {
        return orElseThrow(() -> exceptionType.apply(message));
    }

    public <ExceptionType extends Throwable> ReturnType orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if(!condition)
            throw exceptionType.get();
        return ifTrueFunction.apply(givenType);
    }
}
