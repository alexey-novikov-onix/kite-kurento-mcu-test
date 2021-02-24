package org.webrtc.kite.kurentomcu;

import org.webrtc.kite.kurentomcu.checks.MyFirstCheck;
import org.webrtc.kite.kurentomcu.steps.OpenUrlStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class KiteKurentoMcuTest extends KiteBaseTest {
  
  private String url = "https://google.com";
  
  @Override
  protected void payloadHandling() {
    if (this.payload != null) {
      url = payload.getString("url", url);
    }
  }

  @Override
  public void populateTestSteps(TestRunner runner) {
    runner.addStep(new OpenUrlStep(runner, url));
    runner.addStep(new MyFirstCheck(runner));
  }

}
