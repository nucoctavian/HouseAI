package org.nuc.houseai;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.jms.JMSException;

import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.Topics;

import au.edu.jcu.v4l4j.CaptureCallback;
import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.V4L4JConstants;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.VideoFrame;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;

public class CamDetector extends DistryService implements CaptureCallback {

    private static int width, height, std, channel;
    private static String device;

    private VideoDevice videoDevice;
    private FrameGrabber frameGrabber;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final String SERVICE_NAME = "camDetection";

    public CamDetector(ServiceConfiguration configuration) throws Exception {
        super(SERVICE_NAME, configuration);

        device = (System.getProperty("test.device") != null) ? System.getProperty("test.device") : "/dev/video0";
        width = (System.getProperty("test.width") != null) ? Integer.parseInt(System.getProperty("test.width")) : 640;
        height = (System.getProperty("test.height") != null) ? Integer.parseInt(System.getProperty("test.height")) : 480;
        std = (System.getProperty("test.standard") != null) ? Integer.parseInt(System.getProperty("test.standard")) : V4L4JConstants.STANDARD_WEBCAM;
        channel = (System.getProperty("test.channel") != null) ? Integer.parseInt(System.getProperty("test.channel")) : 0;

        try {
            videoDevice = new VideoDevice(device);
            frameGrabber = videoDevice.getJPEGFrameGrabber(width, height, channel, std, 80);
            frameGrabber.setCaptureCallback(this);
            width = frameGrabber.getWidth();
            height = frameGrabber.getHeight();
            System.out.println("Starting capture at " + width + "x" + height);

        } catch (V4L4JException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        try {
            super.start();
            frameGrabber.startCapture();
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        countDownLatch.countDown();
    }

    public void exceptionReceived(V4L4JException arg0) {
        arg0.printStackTrace();
    }

    public void nextFrame(VideoFrame frame) {
        try {
            long now = System.currentTimeMillis();
            File outputfile = new File(String.format("image-%d.jpg", now));
            ImageIO.write(frame.getBufferedImage(), "jpg", outputfile);
            this.sendMessage(Topics.CAM_DETECTOR, "Captura primita");
            frame.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
