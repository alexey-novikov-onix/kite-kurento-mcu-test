package org.onix.kurentomcu.test;

import org.onix.kurentomcu.test.checks.VideoCheckStep;
import org.onix.kurentomcu.test.steps.JoinRoomStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class KiteKurentoMcuTest extends KiteBaseTest {

    private String url;
    private int videoDurationInSeconds;
    private int roomCount;

    @Override
    protected void payloadHandling() {
        this.url = this.payload.getString("url");
        this.videoDurationInSeconds = this.payload.getInt("videoDurationInSeconds");
        this.roomCount = this.payload.getInt("roomCount");
    }

    @Override
    public void populateTestSteps(final TestRunner runner) {
        int userId = Integer.parseInt(this.runner.getClientName());
        int roomId = userId > this.roomCount ? userId - this.roomCount : userId;

        runner.addStep(new JoinRoomStep(runner, this.url, userId, roomId));
        runner.addStep(new VideoCheckStep(runner, this.videoDurationInSeconds));
    }

}
