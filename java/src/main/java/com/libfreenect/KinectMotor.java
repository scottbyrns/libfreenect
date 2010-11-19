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
 * Implementation of the Motor class to wrap the Kinect motor.
 *
 * @author Scott Byrns
 * @version 0.2a
 */
public class KinectMotor implements Motor {

    private double position;

    private KinectDevice hostDevice;

    private static final double minimumPositionBoundary = 0.0;
    private static final double maximumPositionBoundary = 1.0;

    public KinectMotor (KinectDevice hostDevice) {
        setHostDevice(hostDevice); 
    }

    /**
     * Set the host Kinect device for this function
     * @param hostDevice host device
     */
    private void setHostDevice(KinectDevice hostDevice) {
        this.hostDevice = hostDevice; 
    }

    /**
     * Set the position of the camera.
     * The input value must fall in the range of 0.0 to 1.0 where 0.0 is level to the horizon.
     *
     * @param position to set the camera to
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     * @throws MotorPositionOutOfBounds if the provided position is greater than maximumPositionBoundary or less than minimumPositionBoundary
     */
    public void setPosition(double position) throws MotorPositionOutOfBounds {
        ensurePositionIsInBounds(position);
        this.position = position;
        hostDevice.issueCommand(KinectCommands.UPDATE_MOTOR_POSITION);
//        commitPositionToDevice((int)(getPostion() * 100));
    }

    /**
     * Get the current position of the camera.
     *
     * @return position of the camera.
     * @throws MotorConnectionIssue if there is an issue communicating with the camera.
     */
    public double getPostion() {
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

}
