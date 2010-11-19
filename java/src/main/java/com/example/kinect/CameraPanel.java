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


package com.example.kinect;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created: Nov 18, 2010
 *
 * @author scott
 */
public class CameraPanel extends Panel {

    private BufferedImage image, leftImage, rightImage;
    private CannyEdgeDetection detector;
    private BufferedImage resizedImage;
    public boolean edges = false;
    public int offsetLeft = 0;

    public CameraPanel () {
        detector = new CannyEdgeDetection();

        //adjust its parameters as desired
        detector.setLowThreshold(0.2f);
        detector.setHighThreshold(1.0f);
    }

    public void paint (Graphics graphics) {

        graphics.drawImage(leftImage, 0, 0, null);
        graphics.drawImage(rightImage, 640, 0, null);
        if (edges) {
            graphics.drawImage(getImage(), offsetLeft, 0, null);
        }
    }

    public BufferedImage getImage () {


        //apply it to an image

        resizedImage = new BufferedImage(640 / 3, 480 / 3, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();



        g.drawImage(rightImage, 0, 0, 640 / 3, 480 / 3, null);
        g.dispose();

        detector.setSourceImage(resizedImage);
        detector.process();

        BufferedImage edges = detector.getEdgesImage();
        resizedImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
        g = resizedImage.createGraphics();
        g.drawImage(edges, 0, 0, 640, 480, null);
        g.dispose();


        return resizedImage;
    }

    public void setImage (BufferedImage image) {
        this.image = image;
    }

    public void setLeftImage (BufferedImage image) {
        leftImage = image;
    }

    public void setRightImage (BufferedImage image) {
        rightImage = image;
    }
}
