package com.acme.apidomains;

import io.grpc.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.io.IOException;

@SpringBootTest()
@DirtiesContext()
public abstract class AbstractIntegrationContainerBaseTest {

    protected static final DockerComposeContainer<?> env;
    protected static Server server;
    protected static ManagedChannel channel;

    static {
        env =
                new DockerComposeContainer<>(
                                new File("src/test/resources/docker-compose-pg-test.yaml"))
                        .withExposedService(ServiceNames.API_DOMAINS_POSTGRESQL, 5432);
        env.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.flyway.url",
                () ->
                        String.format(
                                "jdbc:postgresql://%s:%d/api_domains",
                                env.getServiceHost(ServiceNames.API_DOMAINS_POSTGRESQL, 5432),
                                5432));
        registry.add(
                "spring.r2dbc.url",
                () ->
                        String.format(
                                "r2dbc:postgresql://%s:%d/api_domains",
                                env.getServiceHost(ServiceNames.API_DOMAINS_POSTGRESQL, 5432),
                                5432));
    }

    protected void startGrpcServer(BindableService service) throws IOException {
        server = ServerBuilder.forPort(9999).addService(service).build().start();
        channel =
                ManagedChannelBuilder.forAddress("localhost", server.getPort())
                        .usePlaintext()
                        .build();
    }

    protected void shutdownGrpcServer() throws InterruptedException {
        server.shutdown();
        server.awaitTermination();
        channel.shutdown();
        server = null;
        channel = null;
    }
}
