package org.onix.kurentomcu.test.checks;

import io.cosmosoftware.kite.entities.VideoQuality;
import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;

public class VideoCheck extends TestStep {

    private final MainPage mainPage;
    private final int userId;
    private final int timeout;

    public VideoCheck(final Runner runner, final int timeout, final int userId) {
        super(runner);
        this.mainPage = new MainPage(runner);
        this.userId = userId;
        this.timeout = timeout;
    }

    @Override
    public String stepDescription() {
        return "Step: user " + this.userId + " videos check";
    }

    @Override
    protected void step() throws KiteTestException {
        TestUtils.waitAround(this.timeout);

        this.logger.info("Start looking for user " + this.userId + " video objects");

        try {
            if (this.mainPage.getVideoElements().size() != 2) {
                throw new KiteTestException("Unable to find video elements on the page for user " + this.userId, Status.FAILED);
            }

            final String videoLocalCheck = TestUtils.videoCheck(this.webDriver, 0);
            final String videoRemoteCheck = TestUtils.videoQualityCheck(this.webDriver, 1);

            if (VideoQuality.BLANK.toString().equalsIgnoreCase(videoLocalCheck)) {
                this.reporter.textAttachment(
                        this.report,
                        "Local video for user " + this.userId,
                        videoLocalCheck,
                        "plain"
                );
                throw new KiteTestException(
                        "Local video is " + videoLocalCheck + " for user " + this.userId,
                        Status.FAILED
                );
            }

            if (VideoQuality.BLANK.toString().equalsIgnoreCase(videoRemoteCheck)) {
                this.reporter.textAttachment(
                        this.report,
                        "Remote video for user " + this.userId,
                        videoRemoteCheck,
                        "plain"
                );
                throw new KiteTestException(
                        "Remote video is " + videoRemoteCheck + " for user " + this.userId,
                        Status.FAILED
                );
            }
        } catch (KiteTestException kiteTestException) {
            throw kiteTestException;
        } catch (Exception exception) {
            throw new KiteTestException("Error looking for the video for user " + this.userId, Status.BROKEN, exception);
        }
    }

}
