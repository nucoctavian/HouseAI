package org.nuc.houseai.dummy;

import java.io.Serializable;

import org.nuc.distry.service.DistryListener;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.distry.service.messaging.ActiveMQAdapter;

public class DummyListener extends DistryService {
    private static final String SERVICE_NAME = "dummy";
    public DummyListener(String serviceName, ServiceConfiguration configuration) throws Exception {
        super(serviceName, configuration);
    }

    public static void main(String[] args) throws Exception {
        DummyListener dummyListener = new DummyListener(SERVICE_NAME, new ServiceConfiguration(new ActiveMQAdapter("failover://(tcp://192.168.0.101:61616)"), true, 10, "Admin.Heartbeat", true, "Admin.Cmd", "Admin.Publish"));
        dummyListener.start();
        
        dummyListener.addMessageListener("HouseAI.CamDetector", new DistryListener() {
            public void onMessage(Serializable receivedMessage) {
                System.out.println("Received message " + receivedMessage);
            }
        });
    }

}
