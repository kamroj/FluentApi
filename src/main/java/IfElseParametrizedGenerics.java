import com.rojek.kamil.fluentconditionals.FluentConditionals;

/**
 * @author Kamil Rojek
 */
public class IfElseParametrizedGenerics {
    public static void main(String[] args) {
        String message = FluentConditionals.given(SomeClass::new)
                .when(TestHelper::somethingIsTrue)
                .thenReturn(SomeClass::getMessageForLowNumber)
                .orElse(SomeClass::getMessageForHighNumber);
        System.out.println(message);//I'm so low

        AnotherClass object = FluentConditionals.given(SomeClass::new)
                .when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::extractMessageForHighNumber)
                .orElse(TestHelper::extractMessageForLowNumber);
        System.out.println(object.message);//I'm so high
    }
}
