package com.github.thinhda.web;

import com.github.thinhda.verticles.ServerVerticle;
import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class FailureHandler implements Handler<RoutingContext> {

    private final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);

    @Override
    public void handle(final RoutingContext routingContext) {
        LOGGER.error("Router error", routingContext.failure());
        routingContext.response()
                .setStatusCode(routingContext.statusCode())
                .end();
    }
}