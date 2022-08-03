package org.acme.http;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.DeploymentOptions;
import io.vertx.mutiny.core.Vertx;

@ApplicationScoped
public class MyApp {

    void init(@Observes StartupEvent ev, Vertx vertx, Instance<MyVerticle> verticles) {
        vertx.deployVerticle(verticles::get, new DeploymentOptions().setInstances(32)).await().indefinitely();
    }
}
