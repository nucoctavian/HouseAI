package org.nuc.houseai.media;

import java.io.Serializable;

import org.nuc.distry.service.DistryListener;
import org.nuc.distry.service.DistryService;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.houseai.service.DistryUtils;
import org.nuc.houseai.service.Topics;

public class MediaService extends DistryService {

    private static final String MEDIA = "Media";

    public MediaService( ServiceConfiguration configuration) throws Exception {
        super(MEDIA, configuration);
        addMessageListener(Topics.MEDIA, new DistryListener() {
            
            public void onMessage(Serializable message) {
                System.out.println("received "+ message);
                play(message.toString());
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new MediaService(DistryUtils.createServiceConfiguration()).start();
    }
    public void play(String melody){
        new SoundJLayer(melody).play();
    }

}
