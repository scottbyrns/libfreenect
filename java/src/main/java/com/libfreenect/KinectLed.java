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
//        setStatus(LEDStatus.BLINKING_YELLOW);
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
