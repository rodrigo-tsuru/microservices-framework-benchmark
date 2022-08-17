package org.acme.verticle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.DeploymentOptions;
import io.vertx.mutiny.core.Vertx;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MyApp {

    private static final int INSTANCES = Runtime.getRuntime().availableProcessors() * 2;
    private static final Logger LOGGER = Logger.getLogger("MyApp");

    void init(@Observes StartupEvent ev, Vertx vertx, Instance<MyVerticle> verticles) {
        LOGGER.info(String.format("Starting %s verticles!", INSTANCES));
        DeploymentOptions options = new DeploymentOptions().setInstances(INSTANCES);

        vertx.deployVerticle(verticles::get, options).await().indefinitely();
    }
}
