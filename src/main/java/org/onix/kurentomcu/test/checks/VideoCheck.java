package org.onix.kurentomcu.test.checks;

import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.interfaces.Runner;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestCheck;
import io.cosmosoftware.kite.util.TestUtils;
import org.onix.kurentomcu.test.pages.MainPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VideoCheck extends TestCheck {

    private static final int TIMEOUT_IN_MILLISECONDS = 5000;
    private static final int DURATION_IN_MILLISECONDS = 500;
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
        try {
            waitForVideoToLoad();
            this.logger.info("Looking for video objects");
            final List<WebElement> videos = this.mainPage.getVideoElements();
            if (this.mainPage.getVideoElements().size() != 2) {
                throw new KiteTestException("Unable to find video elements on the page", Status.FAILED);
            }
            final String videoCheckOutput = TestUtils.videoCheck(this.webDriver, 0);
            final String videoCheckInput = TestUtils.videoCheck(this.webDriver, 1);
            this.logger.error("Checks - " + videoCheckOutput + " - " + videoCheckInput);
            if (!"video".equalsIgnoreCase(videoCheckOutput)) {
                this.reporter.textAttachment(report, "Sent Video", videoCheckOutput, "plain");
                throw new KiteTestException("The first video is " + videoCheckOutput, Status.FAILED);
            }
            if (!"video".equalsIgnoreCase(videoCheckInput)) {
                this.reporter.textAttachment(report, "Sent Video", videoCheckInput, "plain");
                throw new KiteTestException("The first video is " + videoCheckInput, Status.FAILED);
            }
        } catch (KiteTestException e) {
            throw e;
        } catch (Exception e) {
            throw new KiteTestException("Error looking for the video", Status.BROKEN, e);
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
