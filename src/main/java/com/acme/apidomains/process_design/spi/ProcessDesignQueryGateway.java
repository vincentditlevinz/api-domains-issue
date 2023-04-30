package com.acme.apidomains.process_design.spi;

import static com.acme.domains.process_design.QueryServiceProto.ListSkillsRequest;
import static com.acme.domains.process_design.QueryServiceProto.ListSkillsResponse;

import reactor.core.publisher.Mono;

public interface ProcessDesignQueryGateway {
    Mono<ListSkillsResponse> listSkills(Mono<ListSkillsRequest> request);
}
