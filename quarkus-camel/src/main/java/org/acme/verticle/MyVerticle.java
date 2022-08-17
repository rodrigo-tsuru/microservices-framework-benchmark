package org.acme.verticle;

import javax.enterprise.context.Dependent;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;

@Dependent
public class MyVerticle extends AbstractVerticle {

    @Override
    public Uni<Void> asyncStart() {
        return vertx.eventBus().consumer("address").handler(m -> m.reply("Hello from " + this)).completionHandler();
    }
}
