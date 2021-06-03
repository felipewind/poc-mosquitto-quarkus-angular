package org.acme.mqtt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/chat-emmiter")
@Tag(name = "Send message to Mosquitto Topic")
@ApplicationScoped
public class ChatEmmiter {

    private static final Logger LOG = LoggerFactory.getLogger(ChatEmmiter.class.getName());

    @Inject
    @Channel("topic-send-chat")
    Emitter<String> emitter;

    @GET
    @Operation(summary = "Send message to Mosquitto Topic")
    @Produces(MediaType.TEXT_PLAIN)
    public String sessionGet(@QueryParam("message") String message) {

        LOG.info("Sending the message=[" + message + "]");

        sendMessage(message);

        return "Message sent successfully";
    }

    public void sendMessage(String message) {
        emitter.send(message);
    }

}
