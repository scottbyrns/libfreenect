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
 * Implementation of the DepthCamera class to wrap the Kinect depth camera.
 *
 * @author Scott Byrns
 * @version 0.1a
 * TODO actually write this
 */
public class KinectDepthCamera implements DepthCamera {

    /**
     * Open a connection to the Kinect's depth camera.
     *
     * @throws DepthCameraConnectionIssue if a communications error occurs
     */
    public void open() throws DepthCameraConnectionIssue {
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

    /**
     * Close the connection to the Kinect's depth camera.
     */
    public void close() {
        /**
         * TODO write
         */
    }
}
