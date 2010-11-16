package com.libfreenect;

/**
 * Class to encapsulate the functionality of the Kinect
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public interface Kinect {

    /**
     * Get the Kinect's motor.
     * @return motor
     */
    public Motor getMotor();

    /**
     * Set the Kinect's motor.
     *
     * @param motor new motor to assign to the Kinect
     */
    public void setMotor(Motor motor);

    /**
     * Get the Kinect's LED
     *
     * @return led
     */
    public Led getLed ();

    /**
     * Set the Kinect's LED
     * 
     * @param led new led to assign to the Kinect
     */
    public void setLed (Led led);

}
