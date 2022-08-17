package org.acme;

import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/camel")                        
public class VertxResource {


    @Inject
    EventBus bus;

    private final Vertx vertx;

    @Inject                             
    public VertxResource(Vertx vertx) { 
        this.vertx = vertx;             
    }

    // @see https://quarkus.io/blog/resteasy-reactive-smart-dispatch/
    // @see https://quarkus.io/guides/reactive-event-bus
    @GET
    @Path("/hello")
    public Uni<String> hello() {     
        return bus.<String>request("camel.route1", "teste")
                .onItem().transform(Message::body);   
    }
}
