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

import java.awt.image.BufferedImage;

/**
 * Abstraction of the common camera functionality for the Kinect cameras.
 *
 * @author Scott Byrns
 * @version 0.1a
 */
public abstract class Camera {

    /**
     * Width of the image
     */
    private int width = 640;
    /**
     * Height of the image
     */
    private int height = 480;

    protected int[] imageData;

    private KinectDevice hostDevice;

    public Camera (KinectDevice hostDevice) {
        imageData = new int[getWidth() * getHeight() * 3];
        setHostDevice(hostDevice);
    }

    /**
     * Set the host Kinect device for this function
     *
     * @param hostDevice host device
     */
    protected void setHostDevice(KinectDevice hostDevice) {
        this.hostDevice = hostDevice;
    }

    protected KinectDevice getHostDevice () {
        return this.hostDevice;
    }

    /**
     * Capture a single still image from the kinects RGB camera.
     *
     * @return still image from rgb camera
     */
    public BufferedImage captureStillImage () {
        return captureImage();
    }

    public BufferedImage captureImage () {

        int[] imageRGB = new int[getWidth()*getHeight()];
        int pos = 0;
        for (int i = 0; i < getImageData().length; i += 3) {
            imageRGB[pos++] = (256*256*getImageData()[i]+256*getImageData()[i+1]+getImageData()[i+2]);

        }

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        image.setRGB(0, 0, getWidth(), getHeight(), imageRGB, 0, 640);
        return image;
    }

    /**
     * Get the width of the image
     *
     * @return width
     */
    protected int getWidth () {
        return width;
    }

    /**
     * Get the height of the image
     *
     * @return height
     */
    protected int getHeight () {
        return height;
    }

    public void setImageData(int[] imageData) {
        this.imageData = imageData;
    }

    public int[] getImageData() {
        return this.imageData;
    }

    /**
     * Open a connection to the Kinect's camera.
     */
    public abstract void open();
    /**
     * Close the connection to the Kinect's camera.
     */
    public abstract void close();
}
