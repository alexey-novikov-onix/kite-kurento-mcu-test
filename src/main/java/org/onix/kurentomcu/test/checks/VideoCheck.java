package org.onix.kurentomcu.test.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestCheck;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;
import org.openqa.selenium.JavascriptExecutor;

public class VideoCheck extends TestCheck {

    private static final int TIMEOUT_IN_MILLISECONDS = 7000;
    private static final int DURATION_IN_MILLISECONDS = 800;
    private static final String VIDEO_CURRENT_TIME_SCRIPT = "videos=document.getElementsByTagName('video');" +
            "return videos[1].currentTime;";

    private final MainPage mainPage;

    public VideoCheck(final Runner runner) {
        super(runner);
        this.mainPage = new MainPage(runner);
    }

    @Override
    public String stepDescription() {
        return "VideoCheck checks that checkPass is true";
    }

    @Override
    protected void step() throws KiteTestException {
        waitForVideoToLoad();

        this.logger.info("Looking for video objects");

        try {
            if (this.mainPage.getVideoElements().size() != 2) {
                throw new KiteTestException("Unable to find video elements on the page", Status.FAILED);
            }

            final String videoLocalCheck = TestUtils.videoCheck(this.webDriver, 0);
            final String videoRemoteCheck = TestUtils.videoCheck(this.webDriver, 1);

            if (!"video".equalsIgnoreCase(videoLocalCheck)) {
                this.reporter.textAttachment(report, "Sent Video", videoLocalCheck, "plain");
                throw new KiteTestException("Local video is " + videoLocalCheck, Status.FAILED);
            }

            if (!"video".equalsIgnoreCase(videoRemoteCheck)) {
                this.reporter.textAttachment(report, "Sent Video", videoRemoteCheck, "plain");
                throw new KiteTestException("Remote video is " + videoRemoteCheck, Status.FAILED);
            }
        } catch (KiteTestException kiteTestException) {
            throw kiteTestException;
        } catch (Exception exception) {
            throw new KiteTestException("Error looking for the video", Status.BROKEN, exception);
        }
    }

    private void waitForVideoToLoad() {
        final long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        while (elapsedTime < TIMEOUT_IN_MILLISECONDS || this.getVideoCurrentTime() < 3) {
            elapsedTime = System.currentTimeMillis() - startTime;

            TestUtils.waitAround(DURATION_IN_MILLISECONDS);
        }
    }

    private double getVideoCurrentTime() {
        return (double) ((JavascriptExecutor) this.webDriver).executeScript(VIDEO_CURRENT_TIME_SCRIPT);
    }

}
