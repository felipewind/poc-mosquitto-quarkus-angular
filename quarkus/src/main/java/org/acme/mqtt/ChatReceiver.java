package org.acme.mqtt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ChatReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(ChatReceiver.class.getName());

    @Inject
    ChatSocket chatSocket;

    @Incoming("chat-stream")
    public void onTopic(String message) {
        LOG.info("Receiving chat: " + message);
    }

}
