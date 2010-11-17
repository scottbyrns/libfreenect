package com.libfreenect;

/**
 * Exception to throw when a communications error arises when attempting to access the
 * RGB camera in the Kinect.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class RGBCameraConnectionIssue extends KinectConnectionIssue {

    public static final String message = "There was an issue communicating with the Kinect's rgb camera.";

    public RGBCameraConnectionIssue () {
        super(message);
    }
}