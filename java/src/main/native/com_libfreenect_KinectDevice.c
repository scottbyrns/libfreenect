#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>

#include <libusb.h>
#include "libfreenect.h"
#include "com_libfreenect_KinectDevice.h"


JNIEXPORT void JNICALL Java_com_libfreenect_KinectDevice_connect (JNIEnv * jnienv, jobject jobject) {
    freenect_context *f_ctx;
	freenect_device *f_dev;

	printf("Kinect camera test\n");

	if (freenect_init(&f_ctx, NULL) < 0) {
		printf("freenect_init() failed\n");
	}

	if (freenect_open_device(f_ctx, &f_dev, 0) < 0) {
		printf("Could not open device\n");
	}

    freenect_close_device(f_dev);

}

