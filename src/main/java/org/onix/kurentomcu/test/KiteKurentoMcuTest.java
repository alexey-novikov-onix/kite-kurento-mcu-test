package org.onix.kurentomcu.test;

import org.onix.kurentomcu.test.checks.VideoCheck;
import org.onix.kurentomcu.test.steps.JoinRoomStep;
import org.onix.kurentomcu.test.steps.LeaveRoomStep;
import org.webrtc.kite.tests.KiteBaseTest;
import org.webrtc.kite.tests.TestRunner;

public class KiteKurentoMcuTest extends KiteBaseTest {

    private String url;
    private int roomCount;

    @Override
    protected void payloadHandling() {
        this.url = this.payload.getString("url");
        this.roomCount = this.payload.getInt("roomCount");
    }

    @Override
    public void populateTestSteps(final TestRunner runner) {
        final int userId = Integer.parseInt(runner.getClientName()) + 1;
        int roomId = 1;
        if (this.roomCount > 1) {
            roomId = userId > this.roomCount ? userId - this.roomCount : userId;
        }

        final int timeout = this.tupleSize * 6 * 1000;

        runner.addStep(new JoinRoomStep(runner, this.url, userId, roomId));
        runner.addStep(new VideoCheck(runner, timeout, userId));
        runner.addStep(new LeaveRoomStep(runner, timeout, userId, roomId));
    }

}
