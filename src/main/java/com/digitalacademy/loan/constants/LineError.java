package com.digitalacademy.loan.constants;

import lombok.Getter;

@Getter
public enum LineError {
    GET_LINE_EXCEPTION("LINE4001","Can't get line text"),
    GET_LINE_NOT_FOUND("LINE4002","Line text not found");

    private String code;
    private String message;
    LineError(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
