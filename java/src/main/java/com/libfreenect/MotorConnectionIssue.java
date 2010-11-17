package com.libfreenect;

/**
 * Exception to throw when a communications error arises when attempting to access the
 * motor in the Kinect.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class MotorConnectionIssue extends KinectConnectionIssue {
    public static final String defaultMessage = "There was an issue connecting to the Kinect's motor.";
    MotorConnectionIssue () {
        super(defaultMessage);
    }
}
