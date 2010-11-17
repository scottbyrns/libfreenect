#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>

#include <libusb.h>
#include "libfreenect.h"
#include "com_libfreenect_KinectLed.h"


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

    printf("%d", ledStatus);

    if (freenect_set_led(f_dev, led) < 0) {
        printf("Failed to set the devices LED status.\n");
    }

	freenect_close_device(f_dev);

}