package com.github.thinhda.web;


import com.github.thinhda.config.ServiceConfig;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public final class RedisRouting {

    public static Handler<RoutingContext> connectRedis(Vertx vertx, String key, String value, String connectionString) {

        if (StringUtils.isEmpty(connectionString)) {
            connectionString = RedisOptions.DEFAULT_ENDPOINT;
        }

        // Create the redis client
        Redis client = Redis.createClient(vertx, connectionString);
        RedisAPI redis = RedisAPI.api(client);

        return routingContext -> {


            client.connect()
                    .compose(conn -> redis.set(Arrays.asList(key, value))
                            .compose(v -> {
                                System.out.println("key stored");

                                return redis.get(key);
                            })).onComplete(ar -> {
                        if (ar.succeeded()) {
                            routingContext
                                    .response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(200)
                                    .end("Key " + key + " value: " + ar.result());
                        } else {
                            routingContext
                                    .response()
                                    .putHeader("content-type", "application/json")
                                    .setStatusCode(500)
                                    .end("Connection or Operation Failed " + ar.cause());
                        }
                    });


        };
    }

    public static Handler<RoutingContext> connectRedis(Vertx vertx, String connectionString) {
        return connectRedis(vertx, "keyDefault", "valueDefault", connectionString);
    }
}

