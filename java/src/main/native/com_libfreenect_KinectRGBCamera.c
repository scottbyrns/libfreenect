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
#include "com_libfreenect_KinectRGBCamera.h"

//void rgb_cb(freenect_device *dev, freenect_pixel *rgb, uint32_t timestamp)
//{
//	pthread_mutex_lock(&gl_backbuf_mutex);
//	got_frames++;
//	memcpy(gl_rgb_back, rgb, FREENECT_RGB_SIZE);
//	pthread_cond_signal(&gl_frame_cond);
//	pthread_mutex_unlock(&gl_backbuf_mutex);
//}

static JavaVM *global_jvm = NULL;
//uint8_t gl_rgb_back[640*480*4];
JNIEnv * env;
jobject obj;

void rgb_cb(freenect_device *dev, freenect_pixel *rgb, uint32_t timestamp)
{
    jclass KinectCamera = (*env)->GetObjectClass(env, obj);
    jmethodID mid = (*env)->GetMethodID(env, KinectCamera, "setImageData", "([I)V");

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

JNIEXPORT void JNICALL Java_com_libfreenect_KinectRGBCamera_captureRawImage (JNIEnv * e, jobject o) {

    env = e;
    obj = o;
    
    freenect_context *f_ctx;
	freenect_device *f_dev;

	printf("Kinect camera test\n");

	if (freenect_init(&f_ctx, NULL) < 0) {
		printf("freenect_init() failed\n");
	}

	if (freenect_open_device(f_ctx, &f_dev, 0) < 0) {
		printf("Could not open device\n");
	}

    printf("Set RGB Callback");
    freenect_set_rgb_callback(f_dev, rgb_cb);

    printf("Starting RGB");
	freenect_start_rgb(f_dev);
	
	while(freenect_process_events(f_ctx) >= 0 );

	freenect_close_device(f_dev);

}
