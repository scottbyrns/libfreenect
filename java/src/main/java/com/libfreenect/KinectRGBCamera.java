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
 * Implementation of the RGBCamera class to wrap the Kinect RGB camera.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class KinectRGBCamera implements RGBCamera {

    /**
     * Open a connection to the Kinect's RGB camera.
     *
     * @throws RGBCameraConnectionIssue if a communications error occurs
     */
    public void open() throws RGBCameraConnectionIssue {
        /**
         * TODO write
         */
    }

    /**
     * Check if the camera has an open connection to the Kinect
     *
     * @return boolean representation of the connection state
     */
    public boolean isOpen () {
        /**
         * TODO write
         */
        return false;
    }

    public void close() {
        /**
         * TODO write
         */
    }
}
