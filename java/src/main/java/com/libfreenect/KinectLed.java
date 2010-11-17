package com.libfreenect;

import com.wapmx.nativeutils.jniloader.NativeLoader;

/**
 * Implementation of the Led class to wrap the Kinect led.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class KinectLed implements Led {

    static {
        try {
            NativeLoader.loadLibrary("com_libfreenect_KinectLed");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

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
        try {
            commitLEDToDevice(getStatus().status());
        }
        catch (LedConnectionIssue e) {
            /**
             * TODO do something meaningful with this exception
             */
        }
    }

    /**
     * Natively implemented method to commit the led status to the device.
     *
     * @param value led status from 0 - 7
     */
    private native void commitLEDToDevice(int value);
}
