package com.spring.holaeat.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    //MEMBER_NOT_FOUND(404, "Member Not Found");
    NO_SUCH_ALGORITHM(400, "No Such Algorithm"), MEMBER_EXISTS(403,"Member Exists" ),UNABLE_TO_SEND_EMAIL(401,"Unable To Send Email");


    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
