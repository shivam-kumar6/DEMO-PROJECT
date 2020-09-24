package com.project.in.teams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnprocessableEntity extends RuntimeException {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public UnprocessableEntity(String msg) {
        super(msg);
    }
}
