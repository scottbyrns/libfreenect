package com.libfreenect;

import com.wapmx.nativeutils.jniloader.NativeLoader;

/**
 * Created by scott Date: Nov 15, 2010 Time: 10:41:39 PM
 */
public class KinectDevice implements Kinect {

    static {
        try {
            NativeLoader.loadLibrary("com_libfreenect_KinectDriver");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public native void connect ();

    public Motor getMotor() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMotor(Motor motor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Led getLed() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLed(Led led) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
