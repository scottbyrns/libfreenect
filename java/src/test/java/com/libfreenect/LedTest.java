package com.libfreenect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the implementation of Led
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class LedTest {

    private KinectLed kinectLed;

    @Before
    public void setup () {
        kinectLed = new KinectLed();
    }

    @Test
    public void testGetStatus () {
        try {
            assertEquals(LEDStatus.GREEN, kinectLed.getStatus());
        }
        catch (LedConnectionIssue e) {
            fail("Error attempting to communicate with the Kinect's led.");
        }
    }

    @Test
    public void testSetStatus () {
        kinectLed.setStatus(LEDStatus.BLINKING_YELLOW);
        try {
            assertEquals(LEDStatus.BLINKING_YELLOW, kinectLed.getStatus());
        }
        catch (LedConnectionIssue e) {
            fail("Error attempting to communicate with the Kinect's led.");
        }
    }

    @After
    public void teardown () {
        kinectLed = null;
    }

}
