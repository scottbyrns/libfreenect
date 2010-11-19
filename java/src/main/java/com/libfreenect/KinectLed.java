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
 * Implementation of the Led class to wrap the Kinect led.
 *
 * @author Scott Byrns
 * @version 0.2a
 */
public class KinectLed implements Led {

    private LEDStatus ledStatus;
    private KinectDevice hostDevice;

    /**
     * Create a new Kinect LED object.
     *
     * @param hostDevice host device
     */
    public KinectLed (KinectDevice hostDevice) {
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
     * Get the current status of the Kinect LED
     *
     * @return current status
     */
    public LEDStatus getStatus() {
        return ledStatus;
    }

    /**
     * Set the LED status for the Kinect
     *
     * @param status new LED status
     */
    public void setStatus(LEDStatus status) {
        ledStatus = status;
        hostDevice.issueCommand(KinectCommands.UPDATE_LED_STATE);
    }
    
}
