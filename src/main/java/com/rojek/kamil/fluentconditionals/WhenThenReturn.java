//package com.rojek.kamil.fluentconditionals;
//
//import java.util.function.Function;
//import java.util.function.Supplier;
//
///**
// * @author Kamil Rojek
// */
//public class WhenThenReturn<GivenType, ReturnType> {
//
//    private boolean condition;
//    private GivenType givenType;
//    private Function<GivenType, ReturnType> function;
//
//    public WhenThenReturn(boolean condition, GivenType givenType) {
//        this.condition = condition;
//        this.givenType = givenType;
//    }
//
//    public WhenThenReturn(boolean condition, GivenType givenType, Function<GivenType, ReturnType> function) {
//        this.condition = condition;
//        this.givenType = givenType;
//        this.function = function;
//    }
//
//
//    public ReturnType orElse(Function<GivenType, ReturnType> functionWhenFalse) {
//        if(condition)
//            return function.apply(givenType);
//        return functionWhenFalse.apply(givenType);
//    }
//
//    public <ExceptionType extends Throwable> void orElseThrow(ExceptionType exceptionType) throws Throwable {
//        if(!condition)
//            throw exceptionType;
//    }
//
//    public <ExceptionType extends Throwable> void orElseThrow(Supplier<ExceptionType> exceptionType) throws Throwable {
//        orElseThrow(exceptionType.get());
//    }
//
//    public <ExceptionType extends Throwable> void orElseThrow(Function<String, ExceptionType>  exceptionType, String message) throws Throwable {
//        if(!condition)
//            throw exceptionType.apply(message);
//    }
//}
