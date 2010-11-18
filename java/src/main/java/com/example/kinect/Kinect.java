package com.example.kinect;

import com.libfreenect.KinectDevice;
import com.libfreenect.KinectLed;
import com.libfreenect.KinectMotor;
import com.libfreenect.KinectRGBCamera;


import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created: Nov 17, 2010
 *
 * @author scott
 */
public class Kinect extends JFrame {

    public static void main (String[] args) {
        new Kinect();
    }

    Kinect () {
        KinectRGBCamera cam = new KinectRGBCamera();
        RGBPanel panel = new RGBPanel ();

        Thread thread1 = new Thread(cam, "thread1");
        thread1.start();
        

        getContentPane().add(panel);
		this.setSize(1280, 480);
		setVisible(true);
        while (true) {
            try {
	    		panel.setImage(cam.captureImage());
                panel.repaint();
    			Thread.currentThread().sleep(1000/30);
            } catch (InterruptedException e) {
            }
        }
	}

}
