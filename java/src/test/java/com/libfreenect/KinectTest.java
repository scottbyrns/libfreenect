package com.libfreenect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by scott Date: Nov 15, 2010 Time: 11:50:52 PM
 */
public class KinectTest {

    private Kinect kinect;

    @Before
    public void setup () {
        kinect = new KinectDevice();
    }

    @Test
    public void testConnect () {
        kinect.connect();
    }

    @After
    public void teardown () {
        kinect = null;
    }

}
