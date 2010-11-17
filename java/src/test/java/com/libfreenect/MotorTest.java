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
            motor.setPosition(0.5);
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
