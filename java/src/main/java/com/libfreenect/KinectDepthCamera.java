package com.libfreenect;

/**
 * Implementation of the DepthCamera class to wrap the Kinect depth camera.
 *
 * @author Scott Byrns
 * @version 0.1a
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
