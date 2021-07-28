package com.github.thinhda;

import com.github.thinhda.verticles.ServerVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/** Created by thinhda. Date: 8/3/20 */
@SpringBootApplication(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class
    })
@Configuration
@ComponentScan(basePackages = {"com.github.thinhda"})
public class VertxSpringApplication {

  private static final int INSTANCES = Runtime.getRuntime().availableProcessors() * 2;

  //@Autowired private ServerVerticle serverVerticle;

  public static void main(String[] args) {
    SpringApplication.run(VertxSpringApplication.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
    DeploymentOptions options = new DeploymentOptions().setInstances(INSTANCES);
    Vertx.vertx(new VertxOptions().setPreferNativeTransport(true))
    .deployVerticle(ServerVerticle.class.getName(),options);
  }
}
