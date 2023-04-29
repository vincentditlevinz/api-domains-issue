package com.acme.apidomains.interceptors;

import io.grpc.Status;

import lombok.extern.slf4j.Slf4j;

import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.lognet.springboot.grpc.recovery.GRpcServiceAdvice;
import org.springframework.dao.DataAccessException;

@GRpcServiceAdvice
@Slf4j
public class GrpcExceptionAdvice {

    @GRpcExceptionHandler
    public Status handle(Throwable ex, GRpcExceptionScope scope) {
        var status = Status.INTERNAL.withDescription(ex.getLocalizedMessage()).withCause(ex);
        log.error("(GrpcExceptionAdvice) Throwable: ", ex);
        return status;
    }

    @GRpcExceptionHandler
    public Status handle(DataAccessException ex, GRpcExceptionScope scope) {
        var status =
                Status.INVALID_ARGUMENT.withDescription(ex.getLocalizedMessage()).withCause(ex);
        log.error("(GrpcExceptionAdvice) DataAccessException: ", ex);
        return status;
    }

    @GRpcExceptionHandler
    public Status handle(IllegalArgumentException ex, GRpcExceptionScope scope) {
        var status =
                Status.INVALID_ARGUMENT.withDescription(ex.getLocalizedMessage()).withCause(ex);
        log.error("(GrpcExceptionAdvice) IllegalArgumentException: ", ex);
        return status;
    }
}
