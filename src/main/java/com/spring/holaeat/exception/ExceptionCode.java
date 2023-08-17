package com.spring.holaeat.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    //MEMBER_NOT_FOUND(404, "Member Not Found");
    NO_SUCH_ALGORITHM(400, "인증 번호 생성을 위한 알고리즘을 찾을 수 없습니다."),
    MEMBER_EXISTS(404,"이미 존재하는 회원입니다." ),
    UNABLE_TO_SEND_EMAIL(404,"이메일을 전송할 수 없습니다."),
    AUTH_CODE_IS_NOT_SAME(404, "인증 번호가 일치하지 않습니다.");


    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
