package org.webrtc.kite.kurentomcu.steps;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import org.webrtc.kite.kurentomcu.pages.MainPage;

public class OpenUrlStep extends TestStep {
  
  private final String url;
  final MainPage searchPage;

  public OpenUrlStep(Runner runner, String url) {
    super(runner);
    searchPage = new MainPage(runner);
    this.url = url;
  }

  @Override
  public String stepDescription() {
    return "Open " + url;
  }
  
  @Override
  protected void step() {
    searchPage.open(url);
  }
}
