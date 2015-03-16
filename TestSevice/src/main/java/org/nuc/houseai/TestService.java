package org.nuc.houseai;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class TestService extends DistryService {
    private static final String SERVICE_NAME = "testService";
    private static final Logger LOGGER = Logger.getLogger(TestService.class);

    public TestService(ServiceConfiguration configuration) throws Exception {
        super(SERVICE_NAME, configuration);
        LOGGER.info("Starting service");
        while (true) {
            LOGGER.info("Sending message to listeners");
            sendMessage(Topics.MEDIA, "melodie.mp3");
            Thread.sleep(10000);
        }
    }

    public static void main(String[] ars) {
        try {
            new TestService(DistryUtils.createServiceConfiguration()).start();

        } catch (Exception e) {
            LOGGER.error("Failed to start service", e);
            e.printStackTrace();
        }
    }
}
