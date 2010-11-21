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

import com.wapmx.nativeutils.jniloader.NativeLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Kinect interface to wrap the
 * functions of the Kinect in a simple portable object.
 *
 * @author Scott Byrns
 * @version 0.2a
 * TODO document
 */
public class KinectDevice implements Kinect {

    public static KinectDevice kinectDevice = null;

    private List<KinectCommands> commandQueue;

    private Thread communicationsThread;

    private Motor motor;
    private Led led;
    private Accelerometer accelerometer;
    private Camera rgbCamera;
    private KinectDepthCamera depthCamera;

    static {
        try {
            NativeLoader.loadLibrary("com_libfreenect_KinectDriver");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected KinectDevice () {
        commandQueue = new ArrayList<KinectCommands>();

        setLed(new KinectLed(this));
        setMotor(new KinectMotor(this));
        setRGBCamera(new KinectRGBCamera(this));
        setDepthCamera(new KinectDepthCamera(this));

        communicationsThread = new Thread(this, "Communications Thread");

        communicationsThread.start();
    }

    /**
     * Create a new instance of the KinectDevice or return the current instance.
     *
     * @return instance of the KinectDevice
     */
    public static KinectDevice createInstance() {
        if (null == kinectDevice) {
            kinectDevice = new KinectDevice();
        }
        return kinectDevice;
    }

    /**
     * Start the camera running.
     */
    @Override
    public void run () {
        issueCommand(KinectCommands.INITIALIZE_KINECT);
        open();
    }

    /**
     * Open a connection to the Kinect device.
     */
    private native void open();

    /**
     * Close the connection to the Kinect Device
     */
    public void close() {
        issueCommand(KinectCommands.DISSCONNECT_KINECT);
    }

    /**
     * Get the Kinect's RGB Camera
     *
     * @return RGB Camera
     */
    public Camera getRGBCamera () {
       return rgbCamera;
    }

    /**
     * Set the Kinect's RGB Camera
     *
     * @param rgbCamera new RGB camera to assign to the Kinect
     */
    private void setRGBCamera(Camera rgbCamera) {
        this.rgbCamera = rgbCamera;
    }

    /**
     * Get the Kinect's Depth Camera
     *
     * @return KinectDepthCamera Camera
     */
    public KinectDepthCamera getDepthCamera () {
        return depthCamera;
    }

    /**
     * Set the Kinect's Depth Camera
     *
     * @param depthCamera new depth camera to assign to the Kinect
     */
    private void setDepthCamera(KinectDepthCamera depthCamera) {
        this.depthCamera = depthCamera;
    }

    /**
     * Get the Kinect's motor.
     *
     * @return motor
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Set the Kinect's motor.
     *
     * @param motor new motor to assign to the Kinect
     */
    private void setMotor(Motor motor) {
        this.motor = motor;
    }

    /**
     * Get the Kinect's LED
     *
     * @return led
     */
    public Led getLed() {
        return led;
    }

    /**
     * Set the Kinect's LED
     *
     * @param led new led to assign to the Kinect
     */
    private void setLed(Led led) {
        this.led = led;
    }

    /**
     * Get the Kinect's Accelerometer
     *
     * @return accelerometer
     */
    public Accelerometer getAccelerometer() {
        return accelerometer;
    }

    /**
     * Set the Kinect's Accelerometer
     *
     * @param accelerometer new accelerometer to assign to the Kinect
     */
    private void setAccelerometer(Accelerometer accelerometer) {
        this.accelerometer = accelerometer;
    }

    /**
     * Issue a command to the Kinect JNI driver.
     * Any commands issued will be placed in a queue and executed in the order they were received.
     *
     * @param command command to be issued to the Kinect
     */
    public void issueCommand (KinectCommands command) {
        commandQueue.add(command);
    }

    public int getNextCommand () {
        int command = commandQueue.get(0).command();
        commandQueue.remove(0);
        return command; 
    }

    private int getLedStatus () {
        return getLed().getStatus().status();
    }

    private int getMotorPosition () {
        return (int)(getMotor().getPostion() * 100);
    }

    private void setImageData(int[] imageData) {
        getRGBCamera().setImageData(imageData);
    }

    private void setDepthImageData(int[] imageData) {
        getDepthCamera().setImageData(imageData);
    }
}
