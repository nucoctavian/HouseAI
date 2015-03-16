package org.nuc.houseai.media;

import java.io.Serializable;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.nuc.distry.service.DistryListener;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class MediaService extends DistryService {

    private static final String MEDIA = "Media";
    private static final Logger LOGGER = Logger.getLogger(MediaService.class);

    public MediaService(ServiceConfiguration configuration) throws Exception {
        super(MEDIA, configuration);
        addMessageListener(Topics.MEDIA, new DistryListener() {

            public void onMessage(Serializable message) {
                LOGGER.info("received " + message);
                play(message.toString());
            }
        });
    }

    public static void main(String[] args) {
        try {
            new MediaService(DistryUtils.createServiceConfiguration()).start();
        } catch (JMSException e) {
            LOGGER.fatal("Failed to connect to broker", e);
        } catch (Exception e) {
            LOGGER.fatal("Failed to start MediaService", e);
            e.printStackTrace();
        }
    }

    public void play(String melody) {
        new SoundJLayer(melody).play();
    }

}
