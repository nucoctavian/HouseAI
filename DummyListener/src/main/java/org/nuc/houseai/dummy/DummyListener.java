package org.nuc.houseai.dummy;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.nuc.distry.service.DistryListener;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class DummyListener extends DistryService {
    private static final String SERVICE_NAME = "dummy";
    private static final Logger LOGGER = Logger.getLogger(DummyListener.class);

    public DummyListener(String serviceName, ServiceConfiguration configuration) throws Exception {
        super(serviceName, configuration);
    }

    public static void main(String[] args) {
        DummyListener dummyListener;
        try {
            dummyListener = new DummyListener(SERVICE_NAME, DistryUtils.createServiceConfiguration());
            dummyListener.start();

            dummyListener.addMessageListener(Topics.CAM_DETECTOR, new DistryListener() {
                public void onMessage(Serializable receivedMessage) {
                    LOGGER.info("Received message " + receivedMessage);
                }
            });
        } catch (Exception e) {
            LOGGER.fatal("Failed to start DummyListener", e);
            e.printStackTrace();
        }
    }

}
