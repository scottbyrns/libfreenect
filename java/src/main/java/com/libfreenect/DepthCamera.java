package com.libfreenect;

/**
 * Kinect's Depth camera
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public interface DepthCamera {

    /**
     * Open a connection to the Kinect's depth camera.
     *
     * @throws DepthCameraConnectionIssue if a communications error occurs
     */
    public void open () throws DepthCameraConnectionIssue;

    /**
     * Check if the camera has an open connection to the Kinect
     *
     * @return boolean representation of the connection state
     */
    public boolean isOpen ();

    /**
     * Close the connection to the Kinect's depth camera.
     */
    public void close ();
    
}
