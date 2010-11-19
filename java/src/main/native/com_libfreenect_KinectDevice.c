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
#include "com_libfreenect_KinectDevice.h"

freenect_context *f_ctx;
freenect_device *f_dev;

int die = 0;
int rgbCameraState = 0;
int depthCameraState = 1;
int accelerometerState = 0;

JNIEnv * env;
jobject obj;
jclass kinectDevice;
jmethodID getNextCommand, getLEDStatus, getMotorPosition;

uint16_t t_gamma[2048];
uint8_t gl_depth_back[640*480*4];

void depth_cb(freenect_device *dev, freenect_depth *depth, uint32_t timestamp)
{
    if (depthCameraState == 0) {
        return;
    }
    
	int i;

	for (i=0; i<FREENECT_FRAME_PIX; i++) {
		int pval = t_gamma[depth[i]];
		int lb = pval & 0xff;
		switch (pval>>8) {
			case 0:
				gl_depth_back[3*i+0] = 255;
				gl_depth_back[3*i+1] = 255-lb;
				gl_depth_back[3*i+2] = 255-lb;
				break;
			case 1:
				gl_depth_back[3*i+0] = 255;
				gl_depth_back[3*i+1] = lb;
				gl_depth_back[3*i+2] = 0;
				break;
			case 2:
				gl_depth_back[3*i+0] = 255-lb;
				gl_depth_back[3*i+1] = 255;
				gl_depth_back[3*i+2] = 0;
				break;
			case 3:
				gl_depth_back[3*i+0] = 0;
				gl_depth_back[3*i+1] = 255;
				gl_depth_back[3*i+2] = lb;
				break;
			case 4:
				gl_depth_back[3*i+0] = 0;
				gl_depth_back[3*i+1] = 255-lb;
				gl_depth_back[3*i+2] = 255;
				break;
			case 5:
				gl_depth_back[3*i+0] = 0;
				gl_depth_back[3*i+1] = 0;
				gl_depth_back[3*i+2] = 255-lb;
				break;
			default:
				gl_depth_back[3*i+0] = 0;
				gl_depth_back[3*i+1] = 0;
				gl_depth_back[3*i+2] = 0;
				break;
		}
	}


    jmethodID mid = (*env)->GetMethodID(env, kinectDevice, "setDepthImageData", "([I)V");

    jintArray arr = (*env)->NewIntArray(env, FREENECT_RGB_SIZE);
    jint *returnBody = malloc(FREENECT_RGB_SIZE * sizeof(jint));

    if (gl_depth_back == NULL) {
        printf("WTF");
    }
    else {

        int i = 0;
        for (i = 0; i < FREENECT_RGB_SIZE; i += 1) {
            returnBody[i] = gl_depth_back[i];
        }

        (*env)->SetIntArrayRegion(env, arr, 0, FREENECT_RGB_SIZE, returnBody);
        (*env)->CallVoidMethod(env, obj, mid, arr);
        (*env)->DeleteLocalRef(env, arr);
    }

    free(returnBody);
}

void rgb_cb(freenect_device *dev, freenect_pixel *rgb, uint32_t timestamp)
{
    if (rgbCameraState == 0) {
        return;
    }

    jmethodID mid = (*env)->GetMethodID(env, kinectDevice, "setImageData", "([I)V");

    jintArray arr = (*env)->NewIntArray(env, FREENECT_RGB_SIZE);
    jint *returnBody = malloc(FREENECT_RGB_SIZE * sizeof(jint));

    if (rgb == NULL) {
        printf("WTF");
    }
    else {

        int i = 0;
        for (i = 0; i < FREENECT_RGB_SIZE; i += 1) {
            returnBody[i] = rgb[i];
        }

        (*env)->SetIntArrayRegion(env, arr, 0, FREENECT_RGB_SIZE, returnBody);
        (*env)->CallVoidMethod(env, obj, mid, arr);
        (*env)->DeleteLocalRef(env, arr);
    }

    free(returnBody);
}

void setLedStatus (int ledStatus) {
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
}

int commandMonitor () {

    jint command = (*env)->CallIntMethod(env, obj, getNextCommand);

//    printf("%d\n", command);

//    INITIALIZE_KINECT(0),
//    DISSCONNECT_KINECT(1),
//
//    TURN_RGB_CAMERA_ON(2),
//    TURN_RGB_CAMERA_OFF(3),
//
//    TURN_DEPTH_CAMERA_ON(4),
//    TURN_DEPTH_CAMERA_OFF(5),
//
//    UPDATE_MOTOR_POSITION(6),
//    UPDATE_LED_STATE(7),
//
//    TURN_ACCELEROMETER_ON(8),
//    TURN_ACCELEROMETER_OFF(9);

    if (command == 1) {
        die = 1;
    }
    else if (command == 2) {
        rgbCameraState = 1;
    }
    else if (command == 3) {
        rgbCameraState = 0;
    }
    else if (command == 4) {
        depthCameraState = 1;
    }
    else if (command == 5) {
        depthCameraState = 0;
    }
    else if (command == 6) {
        freenect_set_tilt(f_dev, (*env)->CallIntMethod(env, obj, getMotorPosition));
    }
    else if (command == 7) {
        setLedStatus((*env)->CallIntMethod(env, obj, getLEDStatus));
    }
    else if (command == 8) {
        accelerometerState = 1;
    }
    else if (command == 9) {
        accelerometerState = 0;
    }


    return 1;

}



void initJNIApi () {
    kinectDevice = (*env)->GetObjectClass(env, obj);
    getNextCommand = (*env)->GetMethodID(env, kinectDevice, "getNextCommand", "()I");
    getLEDStatus = (*env)->GetMethodID(env, kinectDevice, "getLedStatus", "()I");
    getMotorPosition = (*env)->GetMethodID(env, kinectDevice, "getMotorPosition", "()I"); 
}



JNIEXPORT void JNICALL Java_com_libfreenect_KinectDevice_open (JNIEnv * e, jobject o) {

	int i;
	for (i=0; i<2048; i++) {
		float v = i/2048.0;
		v = powf(v, 3)* 6;
		t_gamma[i] = v*6*256;
	}

    env = e;
    obj = o;

    initJNIApi();



	if (freenect_init(&f_ctx, NULL) < 0) {
		printf("freenect_init() failed\n");
	}

	if (freenect_open_device(f_ctx, &f_dev, 0) < 0) {
		printf("Could not open device\n");
	}

    printf("Set RGB Callback");
    freenect_set_rgb_callback(f_dev, rgb_cb);

	freenect_set_depth_callback(f_dev, depth_cb);

    printf("Starting RGB");
    freenect_start_rgb(f_dev);
    freenect_start_depth(f_dev);

    while(die == 0 && freenect_process_events(f_ctx) >= 0) {
        commandMonitor();
    }

    freenect_close_device(f_dev);

}

