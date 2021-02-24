package org.webrtc.kite.kurentomcu.pages;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static io.cosmosoftware.kite.util.WebDriverUtils.loadPage;

public class MainPage extends BasePage {

  public MainPage(Runner runner) {
    super(runner);
  }

  public void open(String url) {
    loadPage( url, 20);
  }
  
}
