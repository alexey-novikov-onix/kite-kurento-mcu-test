package org.onix.kurentomcu.test;

import org.onix.kurentomcu.test.checks.MyFirstCheck;
import org.onix.kurentomcu.test.steps.OpenUrlStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class KiteKurentoMcuTest extends KiteBaseTest {

    private String url;

    @Override
    protected void payloadHandling() {
        this.url = this.payload.getString("url");
    }

    @Override
    public void populateTestSteps(final TestRunner runner) {
        runner.addStep(new OpenUrlStep(runner, this.url));
        runner.addStep(new MyFirstCheck(runner));
    }

}
