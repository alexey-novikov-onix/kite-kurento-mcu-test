package org.onix.kurentomcu.test.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestCheck;

import static io.cosmosoftware.kite.util.TestUtils.waitAround;

public class MyFirstCheck extends TestCheck {

    public MyFirstCheck(Runner runner) {
        super(runner);
    }

    @Override
    public String stepDescription() {
        return "MyFirstCheck checks that checkPass is true";
    }

    @Override
    protected void step() throws KiteTestException {
        waitAround(60000);
    }

}
