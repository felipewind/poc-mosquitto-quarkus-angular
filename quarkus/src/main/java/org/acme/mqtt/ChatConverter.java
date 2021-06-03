package org.acme.mqtt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.annotations.Broadcast;

/**
 * A bean consuming data from the "chat" MQTT topic and applying some
 * conversion. The result is pushed to the "chat-stream" stream which is an
 * in-memory stream.
 */
@ApplicationScoped
public class ChatConverter {

    private static final Logger LOG = LoggerFactory.getLogger(ChatConverter.class.getName());

    @Inject
    ChatSocket chatSocket;

    @Incoming("topic-receive-chat")
    @Outgoing("chat-stream")
    @Broadcast
    public String chat(byte[] chatRaw) {
        String message = new String(chatRaw);
        LOG.info("Receiving chat: " + message);
        chatSocket.broadcastToWebSocket(message);
        return message;
    }

}
