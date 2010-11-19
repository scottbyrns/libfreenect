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

import com.libfreenect.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Forgive this code it is a sad excuse for software. I will write something much
 * more consise and clean in the near future. For now I want to get the rework of
 * the libfreenect code into git.
 *
 * Created: Nov 17, 2010
 *
 * @author scott
 */
public class Kinect extends JFrame implements ActionListener {

    Button up, down, cameraOn;
    KinectDevice device;

    public static void main (String[] args) {
        new Kinect();
    }

    Kinect () {

        cameraOn = new Button("Turn RGB Camera On");
        cameraOn.setBounds(640,60, 100, 30);
        cameraOn.addActionListener(this);

        up = new Button ("TILT UP");
        up.setBounds(640,0,100,30);
        down = new Button ("TILT DOWN");
        down.setBounds(640,30,100,30);
        up.addActionListener(this);
        down.addActionListener(this);

        device = KinectDevice.createInstance();
        device.getLed().setStatus(LEDStatus.RED);

        try {
            device.getMotor().setPosition(0.0);
        }
        catch (MotorPositionOutOfBounds e) {

        }


        Camera cam = device.getRGBCamera();
        Camera depthCamera = device.getDepthCamera();

        cam.open();
        depthCamera.open();

        CameraPanel depthPanel = new DepthPanel();
        CameraPanel panel = new RGBPanel ();

        panel.add(up);
        panel.add(down);

        panel.setLocation(0, 0);
        depthPanel.setLocation(640, 0);

        add(panel);

		setSize(1280, 480);
		setVisible(true);


        setTitle("OpenKinect Demo");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        while (true) {
            try {
	    		panel.setLeftImage(cam.captureImage());
                panel.setRightImage(depthCamera.captureImage());
                panel.repaint();
                

    			Thread.currentThread().sleep(1000/30);
            } catch (InterruptedException e) {
            }
        }
	}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == up) {
            try {
                device.getMotor().setPosition(0.0);
            }
            catch (MotorPositionOutOfBounds eb) {

            }
        }
        else if (e.getSource() == down) {
            try {
                device.getMotor().setPosition(1.0);
            }
            catch (MotorPositionOutOfBounds eb) {

            }
        }
        else if (e.getSource() == cameraOn) {
            device.issueCommand(KinectCommands.TURN_RGB_CAMERA_ON);
        }
    }
    
}
