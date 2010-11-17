package com.libfreenect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the RGBCamera implementation for the Kinect
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class RGBCameraTest{

    private RGBCamera rgbCamera;

    @Before
    public void setup () {
        rgbCamera = new KinectRGBCamera();
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
        rgbCamera = null;
    }

}
