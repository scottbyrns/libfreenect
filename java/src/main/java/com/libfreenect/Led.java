package com.libfreenect;

/**
 * Kinect's status LED
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public interface Led {

    /**
     * Get the current status of the Kinect LED
     *
     * @return current status
     * @throws LedConnectionIssue if communication with the Kinect's led fails.
     */
    public LEDStatus getStatus () throws LedConnectionIssue;

    /**
     * Set the LED status for the Kinect
     *
     * @param status new LED status
     */
    public void setStatus (LEDStatus status);

}
