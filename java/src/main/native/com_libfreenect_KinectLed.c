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

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>

#include <libusb.h>
#include "libfreenect.h"
#include "com_libfreenect_KinectLed.h"

/**
 * Commit the provided LED status to the device.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
JNIEXPORT void JNICALL Java_com_libfreenect_KinectLed_commitLEDToDevice (JNIEnv * a, jobject o, jint ledStatus) {
    freenect_context *f_ctx;
	freenect_device *f_dev;

	if (freenect_init(&f_ctx, NULL) < 0) {
		printf("freenect_init() failed\n");
	}

	if (freenect_open_device(f_ctx, &f_dev, 0) < 0) {
		printf("Could not open device\n");
	}


    freenect_led led;

    /**
     * TODO Clean up this hack.
     */

    if (ledStatus == 0) {
	    led = FREENECT_LED_OFF;
    }
    else if (ledStatus == 1) {
        led = FREENECT_LED_GREEN;
    }
    else if (ledStatus == 2) {
    	led = FREENECT_LED_RED;
    }
    else if (ledStatus == 3) {
    	led = FREENECT_LED_ORANGE;
    }
    else if (ledStatus == 4) {
    	led = FREENECT_LED_BLINKING_ORANGE;
    }
    else if (ledStatus == 5) {
    	led = FREENECT_LED_BLINKING_GREEN;
    }
    else if (ledStatus == 6) {
    	led = FREENECT_LED_ALTERNATE_RED_ORANGE;
    }
    else {
    	led = FREENECT_LED_ALTERNATE_RED_GREEN;
    }

    printf("%d\n", ledStatus);

    if (freenect_set_led(f_dev, led) < 0) {
        printf("Failed to set the devices LED status.\n");
    }

	freenect_close_device(f_dev);

}