package com.libfreenect;

import com.wapmx.nativeutils.jniloader.NativeLoader;

/**
 * Implementation of the Motor class to wrap the Kinect motor.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class KinectMotor implements Motor {
    
    static {
        try {
            NativeLoader.loadLibrary("com_libfreenect_KinectMotor");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private double position;

    private static final double minimumPositionBoundary = 0.0;
    private static final double maximumPositionBoundary = 1.0;

    public KinectMotor () throws MotorConnectionIssue {
            try {
                setPosition(minimumPositionBoundary);
            }
            catch (MotorPositionOutOfBounds e) {
                /* Ignore this one since we are providing a default valid value */
            }
    }

    /**
     * Set the position of the camera.
     * The input value must fall in the range of 0.0 to 1.0 where 0.0 is level to the horizon.
     *
     * @param position to set the camera to
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     * @throws MotorPositionOutOfBounds if the provided position is greater than maximumPositionBoundary or less than minimumPositionBoundary
     */
    public void setPosition(double position) throws MotorConnectionIssue, MotorPositionOutOfBounds {
        ensurePositionIsInBounds(position);
        this.position = position;
        commitPositionToDevice((int)(getPostion() * 100));
    }

    /**
     * Get the current position of the camera.
     *
     * @return position of the camera.
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     */
    public double getPostion() throws MotorConnectionIssue {
        return position;
    }

    /**
     * Ensure the provided position is in bounds.
     *
     * @param position target position
     * @throws MotorPositionOutOfBounds if the provided position is greater than maximumPositionBoundary or less than minimumPositionBoundary
     */
    private void ensurePositionIsInBounds (double position) throws MotorPositionOutOfBounds {
        if (position > maximumPositionBoundary || position < minimumPositionBoundary) {
            throw new MotorPositionOutOfBounds();
        }
    }

    private native void commitPositionToDevice(int position);
}
