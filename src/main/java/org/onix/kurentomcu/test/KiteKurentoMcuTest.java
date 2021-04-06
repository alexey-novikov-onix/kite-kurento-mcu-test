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
        for (int roomId = 1; roomId <= this.roomCount; roomId++) {

        }
        runner.addStep(new JoinRoomStep(runner, this.url));
        runner.addStep(new VideoCheckStep(runner, this.videoDurationInSeconds));
    }

}
