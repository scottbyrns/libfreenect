package com.example.kinect;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created: Nov 17, 2010
 *
 * @author scott
 */
public class RGBPanel extends Panel {

    private BufferedImage image;

    public RGBPanel () {
        
    }

    public void paint (Graphics graphics) {
        graphics.drawImage(getImage(), 0, 0, null);
    }

    public BufferedImage getImage () {
        return image;
    }

    public void setImage (BufferedImage image) {
        this.image = image;
    }

}
