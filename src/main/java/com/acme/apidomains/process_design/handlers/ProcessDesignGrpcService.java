package com.acme.apidomains.process_design.handlers;

import com.acme.apidomains.interceptors.LogGrpcInterceptor;
import com.acme.apidomains.process_design.mappers.SkillMapper;
import com.acme.apidomains.process_design.services.SkillService;
import com.acme.domains.process_design.QueryServiceGrpc;
import com.acme.domains.process_design.transport.ListSkills;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;


@GRpcService(interceptors = {LogGrpcInterceptor.class})
@Slf4j
@RequiredArgsConstructor
public class ProcessDesignGrpcService extends QueryServiceGrpc.QueryServiceImplBase {

    private final SkillService skillService;

    public void listSkills(ListSkills.ListSkillsRequest request, StreamObserver<ListSkills.ListSkillsResponse> responseObserver) {
         skillService.findAllSkills().map(SkillMapper::toGrpc).collectList().map(SkillMapper::toGrpcResponse)
                 // These log shows the next step
                 .log()
                 // GRpcExceptionHandlerInterceptor do not intercept exceptions correctly, I have to do this to obtain the correct behavior which is not handy.
                 //.onErrorMap(ex -> Status.INTERNAL.withDescription(ex.getLocalizedMessage()).withCause(ex).asRuntimeException())
                 .subscribe(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }
}
