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

import com.wapmx.nativeutils.jniloader.NativeLoader;

/**
 * Implementation of the Kinect interface to wrap the
 * functions of the Kinect in a simple portable object.
 *
 * @author Scott Byrns
 * @version 0.1a
 * TODO actually write this code
 */
public class KinectDevice implements Kinect {

    static {
        try {
            NativeLoader.loadLibrary("com_libfreenect_KinectDriver");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Open a connection to the Kinect device.
     */
    public native void connect ();

    /**
     * Get the Kinect's motor.
     *
     * @return motor
     */
    public Motor getMotor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Set the Kinect's motor.
     *
     * @param motor new motor to assign to the Kinect
     */
    public void setMotor(Motor motor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Get the Kinect's LED
     *
     * @return led
     */
    public Led getLed() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Set the Kinect's LED
     *
     * @param led new led to assign to the Kinect
     */
    public void setLed(Led led) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
