package org.onix.kurentomcu.test.steps;

import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;

public class LeaveRoomStep extends TestStep {

    private final MainPage mainPage;
    private final int userId;
    private final int roomId;
    private final int videoDurationInSeconds;

    public LeaveRoomStep(final Runner runner, final int videoDurationInSeconds, final int userId, final int roomId) {
        super(runner);
        this.mainPage = new MainPage(runner);
        this.videoDurationInSeconds = videoDurationInSeconds;
        this.userId = userId;
        this.roomId = roomId;
    }

    @Override
    public String stepDescription() {
        return "User " + userId + " leave room " + roomId;
    }

    @Override
    protected void step() {
        TestUtils.waitAround(this.videoDurationInSeconds / 3 * 1000);
        this.mainPage.clickLeave();
    }

}
