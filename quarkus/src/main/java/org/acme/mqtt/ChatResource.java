package org.acme.mqtt;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple resource retrieving the "in-memory" "chat-stream" and sending the
 * items to a server sent event.
 */
@Path("/chat")
@Tag(name = "Server Sent Events")
public class ChatResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChatResource.class.getName());

    @Inject
    @Channel("chat-stream")
    Publisher<String> chat;

    public ChatResource() {
        LOG.info("constructor");
    }

    @GET
    @Path("/chat-stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> chat() {
        return chat;
    }
}
