package org.nuc.houseai.dummy;

import java.io.Serializable;

import org.nuc.distry.service.DistryListener;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class DummyListener extends DistryService {
    private static final String SERVICE_NAME = "dummy";
    public DummyListener(String serviceName, ServiceConfiguration configuration) throws Exception {
        super(serviceName, configuration);
    }

    public static void main(String[] args) throws Exception {
        DummyListener dummyListener = new DummyListener(SERVICE_NAME, DistryUtils.createServiceConfiguration());
        dummyListener.start();
        
        dummyListener.addMessageListener(Topics.CAM_DETECTOR, new DistryListener() {
            public void onMessage(Serializable receivedMessage) {
                System.out.println("Received message " + receivedMessage);
            }
        });
    }

}
