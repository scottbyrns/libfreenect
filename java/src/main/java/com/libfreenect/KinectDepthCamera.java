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

/**
 * Implementation of the DepthCamera class to wrap the Kinect depth camera.
 *
 * @author Scott Byrns
 * @version 0.2a
 */
public class KinectDepthCamera extends Camera {

    public KinectDepthCamera(KinectDevice device) {
        super(device);
    }

    /**
     * Open a connection to the Kinect's depth camera.
     */
    public void open() {
        getHostDevice().issueCommand(KinectCommands.TURN_DEPTH_CAMERA_ON);
    }

    /**
     * Close the connection to the Kinect's depth camera.
     */
    public void close() {
        getHostDevice().issueCommand(KinectCommands.TURN_DEPTH_CAMERA_OFF);
    }

    /**
     * Get the coordinates of the points recorded with the depth camera.
     *
     * @return points recorded with the depth camera
     */
    public int[][] getPoints() {
        int[] data = getImageData();
        int[][] points = new int[data.length/3][3];

        float z, r, g, b, x, y;
        int lb = 0;
        int pval = 0;
        if (data.length > 0) {

            for (int i = 0; i < data.length; i += 3) {
                r = data[i];
                g = data[i + 1];
                b = data[i + 2];

                y = i / 1920;
                x = (i - (y * 1920)) / 3;

                if (r == 0 && g == 0 && b > 0) {
                    pval = 5 << 8;
                    lb = 255 - (int) b;
                }
                if (r == 0 && g > 0 && g < 255 && b == 255) {
                    pval = 4 << 8;
                    lb = 255 - (int) g;
                }
                if (b == 0 && g == 255 && r > 0) {
                    pval = 2 << 8;
                    lb = 255 - (int) r;
                }
                if (r == 0 && g == 255) {
                    pval = 3 << 8;
                    lb = (int) b;
                }
                if (r == 255 && b == 0) {
                    pval = 1 << 8;
                    lb = (int) g;
                }
                if (r == 255 && b == g) {
                    pval = 0 << 8;
                    lb = 255 - (int) b;
                }
                if (r == 0 && g == 0 && b == 0) {
                    pval = -1;
                }

                z = pval + lb;
                z *= 0.8;


                points[i / 3][0] = (int) x;
                points[i / 3][1] = (int) y;
                points[i / 3][2] = (int) z;
            }


        }
        return points;
    }
}

