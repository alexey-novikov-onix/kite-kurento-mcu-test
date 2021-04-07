package org.onix.kurentomcu.test.checks;

import io.cosmosoftware.kite.entities.VideoQuality;
import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestStep;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;

public class VideoCheckStep extends TestStep {

    private final MainPage mainPage;
    private final int videoDurationInSeconds;
    private final int userId;

    public VideoCheckStep(final Runner runner, final int videoDurationInSeconds, final int userId) {
        super(runner);
        this.mainPage = new MainPage(runner);
        this.videoDurationInSeconds = videoDurationInSeconds;
        this.userId = userId;
    }

    @Override
    public String stepDescription() {
        return "VideoCheck checks that checkPass is true";
    }

    @Override
    protected void step() throws KiteTestException {
        TestUtils.waitAround((this.videoDurationInSeconds + this.userId) * 1000);

        this.logger.info("Looking for video objects");

        try {
            if (this.mainPage.getVideoElements().size() != 2) {
                throw new KiteTestException("Unable to find video elements on the page", Status.FAILED);
            }

            final String videoLocalCheck = TestUtils.videoCheck(this.webDriver, 0);
            final String videoRemoteCheck = TestUtils.videoQualityCheck(this.webDriver, 1);

            if (!VideoQuality.VIDEO.toString().equalsIgnoreCase(videoLocalCheck)) {
                this.reporter.textAttachment(report, "Sent Video", videoLocalCheck, "plain");
                throw new KiteTestException("Local video is " + videoLocalCheck, Status.FAILED);
            }

            if (!VideoQuality.VIDEO.toString().equalsIgnoreCase(videoRemoteCheck)) {
                this.reporter.textAttachment(report, "Sent Video", videoRemoteCheck, "plain");
                throw new KiteTestException("Remote video is " + videoRemoteCheck, Status.FAILED);
            }
        } catch (KiteTestException kiteTestException) {
            throw kiteTestException;
        } catch (Exception exception) {
            throw new KiteTestException("Error looking for the video", Status.BROKEN, exception);
        }
    }

}
