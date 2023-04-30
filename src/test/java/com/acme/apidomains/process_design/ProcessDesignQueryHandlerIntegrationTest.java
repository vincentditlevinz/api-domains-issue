package com.acme.apidomains.process_design;

import static com.acme.domains.process_design.QueryServiceProto.ListSkillsResponse.Skill.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.acme.apidomains.AbstractIntegrationContainerBaseTest;
import com.acme.domains.process_design.QueryServiceProto;
import com.acme.domains.process_design.ReactorQueryServiceGrpc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class ProcessDesignQueryHandlerIntegrationTest extends AbstractIntegrationContainerBaseTest {
    @Autowired private ProcessDesignQueryHandler processDesignQueryHandler;

    @BeforeEach
    public void setup() throws Exception {
        startGrpcServer(processDesignQueryHandler);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        shutdownGrpcServer();
    }

    @Test
    public void testListSkills() {
        var stub = ReactorQueryServiceGrpc.newReactorStub(channel);

        var request = Mono.just(QueryServiceProto.ListSkillsRequest.newBuilder().build());
        var response = request.transform(stub::listSkills);

        var skill1 =
                newBuilder()
                        .setId(2)
                        .setName("api-rest")
                        .setDescription(
                                "A skill to handle rest api call service tasks defined in business"
                                        + " processes")
                        .build();
        var skill2 =
                newBuilder()
                        .setId(1)
                        .setName("email")
                        .setDescription(
                                "A skill to handle email service tasks defined in business"
                                        + " processes")
                        .build();

        response.as(StepVerifier::create)
                .assertNext(
                        listSkillsResponse ->
                                assertIterableEquals(
                                        List.of(skill1, skill2),
                                        listSkillsResponse.getSkillsList()))
                .verifyComplete();
    }
}
