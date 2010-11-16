package com.libfreenect;

/**
 * General connection exception.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class KinectConnectionIssue extends Throwable {
    public KinectConnectionIssue (String message) {
        super(message);
    }
}
