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
