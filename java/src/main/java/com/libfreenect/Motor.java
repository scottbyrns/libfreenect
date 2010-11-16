package com.libfreenect;

/**
 * Kinect's horizontal position motor.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public interface Motor {

    /**
     * Set the position of the camera.
     * The input value must fall in the range of 0.0 to 1.0 where 0.0 is level to the horizon.
     *
     * @param position to set the camera to
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     * @throws MotorPositionOutOfBounds if the provided position is greater than 1.0 or less than 0.0
     */
    public void setPosition (double position) throws MotorConnectionIssue, MotorPositionOutOfBounds;

    /**
     * Get the current position of the camera.
     *
     * @return position of the camera.
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     */
    public double getPostion () throws MotorConnectionIssue;

}
