package org.onix.kurentomcu.test.pages;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage extends BasePage {

    private static final int TIMEOUT_IN_SECONDS = 20;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "join")
    private WebElement joinButton;

    @FindBy(id = "leave")
    private WebElement leaveButton;

    @FindBy(tagName = "video")
    private List<WebElement> videos;

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

    public void clickLeave() {
        this.leaveButton.click();
    }

    public void enterUsername(final String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterRoom(final int roomId) {
        new Select(this.webDriver.findElement(By.id("room"))).selectByValue(Integer.toString(roomId));
    }

    public List<WebElement> getVideoElements() {
        return this.videos;
    }

}
