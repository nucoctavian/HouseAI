package org.nuc.houseai;

import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class TestService extends DistryService {
    private static final String SERVICE_NAME = "testService";

    public TestService(ServiceConfiguration configuration) throws Exception {
        super(SERVICE_NAME, configuration);
        while (true) {
            sendMessage(Topics.MEDIA, "melodie.mp3");
            Thread.sleep(10000);
        }
    }

    public static void main(String[] ars) throws Exception {
        new TestService(DistryUtils.createServiceConfiguration()).start();
    }
}
