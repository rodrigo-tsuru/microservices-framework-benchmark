package com.github.thinhda.web;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;

public final class DefaultRouting {

    @Autowired
    private static final String TEXT = "Welcome to Vert.x HTTP Server";

    private DefaultRouting() {}

    public static Handler<RoutingContext> welcome() {
        return routingContext -> routingContext
                .response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end( TEXT);
    }
}