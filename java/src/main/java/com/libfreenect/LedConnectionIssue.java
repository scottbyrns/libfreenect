package com.libfreenect;

/**
 * Led communications exception
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class LedConnectionIssue extends KinectConnectionIssue {
    public static final String defaultMessage = "There was an issue connecting to the Kinect's LED.";
    public LedConnectionIssue () {
        super(defaultMessage);
    }
}
