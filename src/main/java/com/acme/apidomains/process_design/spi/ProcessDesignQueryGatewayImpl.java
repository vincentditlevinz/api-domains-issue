package com.acme.apidomains.process_design.spi;

import static com.acme.domains.process_design.QueryServiceProto.ListSkillsRequest;
import static com.acme.domains.process_design.QueryServiceProto.ListSkillsResponse;

import com.acme.apidomains.process_design.ProcessDesignQueryHandler;
import com.acme.apidomains.process_design.services.SkillService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessDesignQueryGatewayImpl implements ProcessDesignQueryGateway {

    private final ProcessDesignQueryHandler processDesignQueryHandler;

    @Override
    public Mono<ListSkillsResponse> listSkills(Mono<ListSkillsRequest> request) {
        SkillService.GatewayContext.set(true);
        return processDesignQueryHandler.listSkills(request);
    }
}
