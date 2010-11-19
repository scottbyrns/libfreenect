/*
 * This file is part of the OpenKinect Project. http://www.openkinect.org
 *
 * Copyright (c) 2010 individual OpenKinect contributors. See the CONTRIB file
 * for details.
 *
 * This code is licensed to you under the terms of the Apache License, version
 * 2.0, or, at your option, the terms of the GNU General Public License,
 * version 2.0. See the APACHE20 and GPL2 files for the text of the licenses,
 * or the following URLs:
 * http://www.apache.org/licenses/LICENSE-2.0
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * If you redistribute this file in source form, modified or unmodified, you
 * may:
 *   1) Leave this header intact and distribute it under the same terms,
 *      accompanying it with the APACHE20 and GPL20 files, or
 *   2) Delete the Apache 2.0 clause and accompany it with the GPL2 file, or
 *   3) Delete the GPL v2 clause and accompany it with the APACHE20 file
 * In all cases you must keep the copyright notice intact and include a copy
 * of the CONTRIB file.
 *
 * Binary distributions must follow the binary distribution requirements of
 * either License.
 */

package com.libfreenect;

/**
 * Class to encapsulate the functionality of the Kinect
 *
 * @author Scott Byrns
 * @version 0.2a
 */
public interface Kinect extends Runnable {

    /**
     * Issue a command to the Kinect JNI driver.
     * Any commands issued will be placed in a queue and executed in the order they were received.
     *
     * @param command command to be issued to the Kinect
     */
    public void issueCommand (KinectCommands command);

    /**
     * Close the connection to the Kinect Device
     */
    public void close();

    /**
     * Get the Kinect's RGB Camera
     *
     * @return RGB Camera
     */
    public Camera getRGBCamera ();

    /**
     * Get the Kinect's Depth Camera
     *
     * @return Depth Camera
     */
    public Camera getDepthCamera ();

    /**
     * Get the Kinect's motor.
     * 
     * @return motor
     */
    public Motor getMotor();

    /**
     * Get the Kinect's LED
     *
     * @return led
     */
    public Led getLed ();

    /**
     * Get the Kinect's Accelerometer
     *
     * @return accelerometer
     */
    public Accelerometer getAccelerometer ();

}
