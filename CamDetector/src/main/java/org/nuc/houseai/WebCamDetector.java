package org.nuc.houseai;

import org.apache.log4j.Logger;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.distry.service.messaging.ActiveMQAdapter;

public class WebCamDetector {

    private static final Logger LOGGER = Logger.getLogger(WebCamDetector.class);

    public static void main(String[] args) {
        try {
            final CamDetector camDetector = new CamDetector(new ServiceConfiguration(new ActiveMQAdapter("failover://(tcp://192.168.0.101:61616)"), true, 10, "Admin.Heartbeat", true, "Admin.Cmd",
                    "Admin.Publish"));
            new Thread() {
                @Override
                public void run() {
                    try {
                        camDetector.start();
                    } catch (Exception e) {
                        LOGGER.error("Failed to start CamDetector", e);
                    }
                }
            }.start();
            Thread.sleep(10000);
            camDetector.stop();
        } catch (Exception e1) {
            LOGGER.fatal("Failed to start CamDetector. Will exit program", e1);
        }

    }
}
