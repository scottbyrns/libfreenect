package com.libfreenect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the DepthCamera implementation for the Kinect
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class DepthCameraTest {

    private DepthCamera depthCamera;

    @Before
    public void setup () {
        depthCamera = new KinectDepthCamera();
    }

    @Test
    public void testOpen () {
        /**
         * TODO write this test when the class is better defined.
         */
    }

    @Test
    public void testIsOpen () {
        /**
         * TODO write this test when the class is better defined.
         */
    }

    @Test
    public void testClose () {
        /**
         * TODO write this test when the class is better defined.
         */
    }

    @After
    public void teardown () {
        depthCamera = null;
    }

}
