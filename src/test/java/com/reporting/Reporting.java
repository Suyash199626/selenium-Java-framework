package com.reporting;

import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Reporting {

    public static void logResult(
            String action,
            String actual,
            String expected,
            String status
    ) throws IOException {
        logResult(action, actual, expected, status, null);
    }

    public static void logResult(
            String action,
            String actual,
            String expected,
            String status,
            String screenshotPath
    ) throws IOException {

        String message =
                "Action : " + action
                + "<br>Expected : " + expected
                + "<br>Actual : " + actual;

        if (screenshotPath != null && !screenshotPath.isBlank()) {
		    if (status.equalsIgnoreCase("PASS")) {
		        ExtentTestManager.getTest()
		                .log(Status.PASS,
		                        message,
		                        MediaEntityBuilder
		                                .createScreenCaptureFromPath(screenshotPath)
		                                .build());
		        return;
		    }

		    ExtentTestManager.getTest()
		            .log(Status.FAIL,
		                    message,
		                    MediaEntityBuilder
		                            .createScreenCaptureFromPath(screenshotPath)
		                            .build());
		    return;
		}

        if(status.equalsIgnoreCase("PASS")) {
            ExtentTestManager.getTest()
                    .log(Status.PASS, message);
        } else {
            ExtentTestManager.getTest()
                    .log(Status.FAIL, message);
        }
    }
}