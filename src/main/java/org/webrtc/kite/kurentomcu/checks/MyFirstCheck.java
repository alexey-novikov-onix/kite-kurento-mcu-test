package org.webrtc.kite.kurentomcu.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Reporter;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestCheck;
import org.openqa.selenium.WebDriver;

import static io.cosmosoftware.kite.util.ReportUtils.saveScreenshotPNG;

public class MyFirstCheck extends TestCheck {
  
  private final boolean checkPass = Math.random() < 0.5;

  public MyFirstCheck(Runner runner) {
    super(runner);
  }


  @Override
  public String stepDescription() {
    return "MyFirstCheck checks that checkPass is true";
  }
  
  @Override
  protected void step() throws KiteTestException {
    reporter.screenshotAttachment(report, saveScreenshotPNG(webDriver));
    if (!checkPass) {
      throw new KiteTestException("MyFirstCheck failed", Status.FAILED);
    }
  }
}
