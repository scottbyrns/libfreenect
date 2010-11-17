package com.libfreenect;

/**
 * Kinect's RGB camera
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public interface RGBCamera {

    /**
     * Open a connection to the Kinect's RGB camera.
     *
     * @throws RGBCameraConnectionIssue if a communications error occurs
     */
    public void open () throws RGBCameraConnectionIssue;

    /**
     * Check if the camera has an open connection to the Kinect
     *
     * @return boolean representation of the connection state
     */
    public boolean isOpen ();

    /**
     * Close the connection to the Kinect's RGB camera.
     */
    public void close ();

}
