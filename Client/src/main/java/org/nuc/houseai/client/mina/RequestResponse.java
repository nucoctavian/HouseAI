package org.nuc.houseai.client.mina;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.nuc.houseai.core.Request;
import org.nuc.houseai.core.Response;
import org.nuc.houseai.utils.Container;

public class RequestResponse<T extends Request> {

    private final MinaClient client;

    public RequestResponse(MinaClient client) {
        this.client = client;

    }

    public Response<T> getResponse(T request) {
        final Container<Response<T>> container = new Container<Response<T>>();
        final CountDownLatch latch = new CountDownLatch(1);
        final IoHandlerAdapter responseHandler = new IoHandlerAdapter() {
            @SuppressWarnings("unchecked")
            public void messageReceived(IoSession session, Object object) throws Exception {
                if (object instanceof Request) {
                    container.setValue((Response<T>) object);
                }
            }
        };
        client.getHandleAggregator().addHandler(responseHandler);

        client.sendMessage(request);

        try {
            latch.await(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {

        }

        client.getHandleAggregator().removeHandler(responseHandler);
        return container.getValue();
    }
}
