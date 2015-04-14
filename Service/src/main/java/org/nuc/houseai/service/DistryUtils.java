package org.nuc.houseai.service;
import org.nuc.distry.service.ServiceConfiguration;
import org.nuc.distry.service.messaging.ActiveMQAdapter;

public class DistryUtils {
    public static ServiceConfiguration createServiceConfiguration() throws Exception {
        return new ServiceConfiguration(new ActiveMQAdapter("failover://(tcp://127.0.0.1:61616)"), true, 10, "Admin.Heartbeat", true, "Admin.Cmd", "Admin.Publish");
    }
}
