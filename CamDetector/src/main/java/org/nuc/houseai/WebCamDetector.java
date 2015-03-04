package org.nuc.houseai;

import au.edu.jcu.v4l4j.exceptions.V4L4JException;

public class WebCamDetector {

    public static void main(String[] args) throws V4L4JException, InterruptedException {
        final CamDetector camDetector = new CamDetector();
        new Thread(){
            @Override 
            public void run(){
                try {
                    camDetector.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Thread.sleep(10000);
        camDetector.stop();
    }
}
