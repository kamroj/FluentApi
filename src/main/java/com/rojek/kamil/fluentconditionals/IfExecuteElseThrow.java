package com.rojek.kamil.fluentconditionals;

import static com.rojek.kamil.fluentconditionals.FluentConditionals.when;

//Task 2
public class IfExecuteElseThrow {

    public static void main(String[] args) {

        FluentConditionals.when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElseThrow(new RuntimeException());
        //'Bar' printed to console

        FluentConditionals.when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElseThrow(RuntimeException::new);
        //'Bar' printed to console

        FluentConditionals.when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElseThrow(TestHelper::createException);
        //'Bar' printed to console

        FluentConditionals.when(!TestHelper.somethingIsTrue())
                .then(TestHelper::printFoo)
                .orElseThrow(TestHelper::createException);
        //exception thrown
    }
}