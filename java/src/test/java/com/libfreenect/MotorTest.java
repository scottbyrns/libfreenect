package com.libfreenect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the implementation of Motor
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public class MotorTest {

    Motor motor;

    @Before
    public void setup () {
        try {
            motor = new KinectMotor();
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }
    }

    @Test
    public void testGetMotorPosition () {
        try {
            assertEquals(0.0, motor.getPostion(), 0.001);
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }
    }

    @Test
    public void testSetMotorPositionMin () {
        try {
            motor.setPosition(0.0);
        }
        catch (MotorPositionOutOfBounds e) {
            fail("The motor attempted to move out of bounds");
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }
    }

    @Test
    public void testSetMotorPosition () {
        try {
            motor.setPosition(0.5);
        }
        catch (MotorPositionOutOfBounds e) {
            fail("The motor attempted to move out of bounds");
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }

        try {
            assertEquals(0.5, motor.getPostion(), 0.001);
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }
    }

    @Test
    public void testSetMotorPositoinDoesntGoOutOfBunds () {
        boolean outOfBounds = false;
        try {
            motor.setPosition(1.1);
            motor.setPosition(-0.1);
        }
        catch (MotorPositionOutOfBounds e) {
            outOfBounds = true;
        }
        catch (MotorConnectionIssue e) {
            fail("Failed to connect to the Kinect camera motor");
        }

        assertTrue(outOfBounds);
    }

    @After
    public void teardown () {
        motor = null;
    }

}
