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
 * Enumeration of commands that can be issued to the Kinect JNI
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public enum KinectCommands {

    INITIALIZE_KINECT(0),
    DISSCONNECT_KINECT(1),

    TURN_RGB_CAMERA_ON(2),
    TURN_RGB_CAMERA_OFF(3),

    TURN_DEPTH_CAMERA_ON(4),
    TURN_DEPTH_CAMERA_OFF(5),

    UPDATE_MOTOR_POSITION(6),
    UPDATE_LED_STATE(7),

    TURN_ACCELEROMETER_ON(8),
    TURN_ACCELEROMETER_OFF(9);

    private int command;
    KinectCommands(int command) {
        this.command = command;
    }
    public int command() {
        return command;
    }
}
