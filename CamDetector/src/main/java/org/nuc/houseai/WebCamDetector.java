package org.nuc.houseai;

import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.distry.service.messaging.ActiveMQAdapter;

public class WebCamDetector {

    public static void main(String[] args) throws Exception {
        final CamDetector camDetector = new CamDetector(new ServiceConfiguration(new ActiveMQAdapter("failover://(tcp://192.168.0.101:61616)"), true, 10, "Admin.Heartbeat", true, "Admin.Cmd", "Admin.Publish"));
        new Thread() {
            @Override
            public void run() {
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
