package org.onix.kurentomcu.test.steps;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;

public class JoinRoomStep extends TestStep {

    private final MainPage mainPage;
    private final String url;
    private final int userId;
    private final int roomId;

    public JoinRoomStep(final Runner runner, final String url, final int userId, final int roomId) {
        super(runner);
        this.mainPage = new MainPage(runner);
        this.url = url;
        this.userId = userId;
        this.roomId = roomId;
    }

    @Override
    public String stepDescription() {
        return "Open " + url;
    }

    @Override
    protected void step() {
        TestUtils.waitAround(this.userId * 1000);

        this.mainPage.open(url);

        this.mainPage.enterUsername("user" + this.getClientID());
        this.mainPage.clickLogin();

        TestUtils.waitAround(2000);

        this.mainPage.enterRoom(this.roomId);
        this.mainPage.clickJoin();
    }

}
