com_libfreenect_KinectDriver.o :
	gcc -I $${JAVA_HOME}/include -dynamiclib -o libcom_libfreenect_KinectDriver.dylib com_libfreenect_KinectDevice.c libfreenect.a /usr/local/lib/libusb-1.0.dylib
	gcc -I $${JAVA_HOME}/include -dynamiclib -o libcom_libfreenect_KinectMotor.dylib com_libfreenect_KinectMotor.c libfreenect.a /usr/local/lib/libusb-1.0.dylib
	gcc -I $${JAVA_HOME}/include -dynamiclib -o libcom_libfreenect_KinectLed.dylib com_libfreenect_KinectLed.c libfreenect.a /usr/local/lib/libusb-1.0.dylib
	gcc -I $${JAVA_HOME}/include -dynamiclib -o libcom_libfreenect_KinectRGBCamera.dylib com_libfreenect_KinectRGBCamera.c libfreenect.a /usr/local/lib/libusb-1.0.dylib