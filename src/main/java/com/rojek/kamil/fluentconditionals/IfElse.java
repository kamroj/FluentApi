package com.rojek.kamil.fluentconditionals;

import static com.rojek.kamil.fluentconditionals.FluentConditionals.*;

/**
 * @author Kamil Rojek
 */
public class IfElse {

    public static void main(String[] args) {

        FluentConditionals.when(TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console

        when(TestHelper::somethingIsTrue)
                .then(TestHelper::printBar)
                .orElse(TestHelper::printFoo);
        //'Bar' printed to console


        when(!TestHelper.somethingIsTrue())
                .then(TestHelper::printBar)
                .orElse(doNothing);
//        //nothing printed to console
    }
}
