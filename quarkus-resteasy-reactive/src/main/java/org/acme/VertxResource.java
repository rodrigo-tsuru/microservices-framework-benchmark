package org.acme;

import io.vertx.mutiny.core.Vertx;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/vertx")                        
public class VertxResource {

    private final Vertx vertx;

    @Inject                             
    public VertxResource(Vertx vertx) { 
        this.vertx = vertx;             
    }

    // @see https://quarkus.io/blog/resteasy-reactive-smart-dispatch/
    @GET
    @Path("/hello")
    public Uni<String> hello() {     
        return Uni.createFrom().item("Hello World!");
    }
}
