package com.acme.apidomains.process_design.handlers;

import com.acme.apidomains.interceptors.LogGrpcInterceptor;
import com.acme.apidomains.process_design.mappers.SkillMapper;
import com.acme.apidomains.process_design.services.SkillService;
import com.acme.domains.process_design.ReactorQueryServiceGrpc;
import com.acme.domains.process_design.transport.ListSkills;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;


@GRpcService(interceptors = {LogGrpcInterceptor.class})
@Slf4j
@RequiredArgsConstructor
public class ProcessDesignGrpcService extends ReactorQueryServiceGrpc.QueryServiceImplBase {

    private final SkillService skillService;

    @Override
    public Mono<ListSkills.ListSkillsResponse> listSkills(Mono<ListSkills.ListSkillsRequest> request) {
        return request.flatMap(req -> skillService.findAllSkills().map(SkillMapper::toGrpc).collectList().map(SkillMapper::toGrpcResponse)
                .doOnSuccess(response -> log.info("Result: {}", response)));
    }
}
