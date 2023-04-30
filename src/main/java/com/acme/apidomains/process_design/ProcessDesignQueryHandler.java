package com.acme.apidomains.process_design;

import static com.acme.domains.process_design.QueryServiceProto.ListSkillsRequest;
import static com.acme.domains.process_design.QueryServiceProto.ListSkillsResponse;

import com.acme.apidomains.framework.LogGrpcInterceptor;
import com.acme.apidomains.process_design.mappers.SkillMapper;
import com.acme.apidomains.process_design.services.SkillService;
import com.acme.domains.process_design.ReactorQueryServiceGrpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.lognet.springboot.grpc.GRpcService;

import reactor.core.publisher.Mono;

@GRpcService(interceptors = LogGrpcInterceptor.class)
@Slf4j
@RequiredArgsConstructor
public class ProcessDesignQueryHandler extends ReactorQueryServiceGrpc.QueryServiceImplBase {

    private final SkillService skillService;

    @Override
    public Mono<ListSkillsResponse> listSkills(Mono<ListSkillsRequest> request) {
        return request.flatMap(
                req ->
                        skillService
                                .findAllSkills()
                                .map(SkillMapper::toGrpc)
                                .collectList()
                                .map(SkillMapper::toGrpcResponse)
                                .doOnSuccess(response -> log.info("Result: {}", response)));
    }
}
