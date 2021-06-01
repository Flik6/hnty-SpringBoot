package com.coco360.service;


import com.coco360.util.LittleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class RemoteComputerServices {

    @Autowired
    LittleUtils littleUtils;
    public String captureScreen(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        return littleUtils.ImageToBase64(image);

    }

}
