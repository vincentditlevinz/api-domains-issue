package com.acme.apidomains.process_runtime;

import com.acme.apidomains.process_design.spi.ListSkillCalled;
import com.acme.apidomains.process_design.spi.ProcessDesignQueryGateway;
import com.acme.domains.process_design.QueryServiceProto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
class ProcessRuntimeQueryHandler {
    private final ProcessDesignQueryGateway processDesignQueryGateway;

    @EventListener
    public void on(ListSkillCalled event) {
        log.info("Received ListSkillCalled event: {}", event);
        processDesignQueryGateway
                .listSkills(Mono.just(QueryServiceProto.ListSkillsRequest.newBuilder().build()))
                .subscribe(
                        skills -> log.info("Skills read from process_design gateway:\n{}", skills));
    }
}
