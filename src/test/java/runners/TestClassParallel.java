package runners;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestClassParallel {

    @Test
    public void test() {
        Class[] cls = {
                UsersAccountTest.class,
                ShoppingProcessTest.class,
                PaymentMethodAdministrationTest.class,
                OrdersFulfillmentTest.class
        };

        Result result = JUnitCore.runClasses(ParallelComputer.methods(), cls);

        System.out.println("Run count: " + result.getRunCount());
        System.out.println("Failure count: " + result.getFailureCount());
        System.out.println("Ignore count: " + result.getIgnoreCount());

        if (result.getFailures().size() > 0) {
            System.out.println("Failed tests:");

            for (int i = 0; i < result.getFailures().size(); i++) {
                System.out.println("-------------------");
                System.out.println(result.getFailures().get(i));
            }
        }
    }
}
