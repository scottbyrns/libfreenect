package com.libfreenect;

/**
 * Implementation of the Led class to wrap the Kinect led.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class KinectLed implements Led {

    private LEDStatus ledStatus;

    /**
     * Create a new Kinect LED object.
     */
    public KinectLed () {
        setStatus(LEDStatus.GREEN);
    }

    /**
     * Get the current status of the Kinect LED
     *
     * @return current status
     */
    public LEDStatus getStatus() throws LedConnectionIssue {
        return ledStatus;
    }

    /**
     * Set the LED status for the Kinect
     *
     * @param status new LED status
     */
    public void setStatus(LEDStatus status) {
        ledStatus = status;
    }
}
