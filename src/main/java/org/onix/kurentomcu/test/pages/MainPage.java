package org.onix.kurentomcu.test.pages;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    private static final int TIMEOUT_IN_SECONDS = 20;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "join")
    private WebElement joinButton;

    public MainPage(final Runner runner) {
        super(runner);
    }

    public void open(final String url) {
        this.loadPage(url, TIMEOUT_IN_SECONDS);
    }

    public void clickLogin() {
        this.loginButton.click();
    }

    public void clickJoin() {
        this.joinButton.click();
    }

    public void enterUsername(final String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

}
