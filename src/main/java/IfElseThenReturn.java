import com.rojek.kamil.fluentconditionals.FluentConditionals;

/**
 * @author Kamil Rojek
 */
public class IfElseThenReturn {

    public static void main(String[] args) {
        int result1 = FluentConditionals.when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);
        System.out.println(result1);//1000

        int result2 = FluentConditionals.when(!TestHelper.somethingIsTrue())
                .thenReturn(TestHelper::getHighNumber)
                .orElse(0);
        System.out.println(result2);//0
    }
}
