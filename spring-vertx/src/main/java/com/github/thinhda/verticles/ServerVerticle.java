package com.github.thinhda.verticles;

import com.github.thinhda.service.PingService;
import com.github.thinhda.web.DefaultRouting;
import com.github.thinhda.web.FailureHandler;
import com.github.thinhda.web.RedisRouting;
import io.vertx.core.*;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by thinhda. Date: 8/4/20
 */
@Component
public class ServerVerticle extends AbstractVerticle {

    private final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);

    private final JsonObject configuration;

    public ServerVerticle(final JsonObject configuration) {
        this.configuration = configuration;
    }

    @Override
    public void start() {
        final Router router = Router.router(vertx);
        final HttpServer httpServer = vertx.createHttpServer();
        final FailureHandler failureHandler = new FailureHandler();

        router.get("/").handler(DefaultRouting.welcome());
        router.get("/redisStatic").handler(RedisRouting.connectRedis(vertx, getConnectionString())).failureHandler(failureHandler);
        router.post("/redisParameter/:key/:value").handler(ctx -> {

            String keyRedis = ctx.pathParam("key");
            String valueRedis = ctx.pathParam("value");

            RedisRouting.connectRedis(vertx, keyRedis, valueRedis, getConnectionString());

        }).failureHandler(failureHandler);

        httpServer.requestHandler(router)
                .listen(8080, listenHandler -> {
                    if (listenHandler.failed()) {
                        LOGGER.error("HTTP Server error", listenHandler.cause());
                        return;
                    }
                    LOGGER.info("HTTP Server started on port " + 8080);
                });

    }

    private String getConnectionString() {
        return configuration.getString("redis.connectionString");
    }
}
