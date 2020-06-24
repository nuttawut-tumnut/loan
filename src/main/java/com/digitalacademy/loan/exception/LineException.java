package com.digitalacademy.loan.exception;

import com.digitalacademy.loan.constants.LineError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LineException extends Exception {
    private LineError lineError;
    private HttpStatus httpStatus = HttpStatus.OK;

    public LineException(LineError lineError,HttpStatus httpStatus) {
        this.lineError = lineError;
        this.httpStatus = httpStatus;
    }
}
