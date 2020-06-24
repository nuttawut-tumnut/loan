package com.digitalacademy.loan.service;

import com.digitalacademy.loan.constants.LineError;
import com.digitalacademy.loan.exception.LineException;
import com.digitalacademy.loan.model.LineModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LineService {
    private static final Logger log = LogManager.getLogger(LineService.class.getName());
    public LineModel getLineById(Long id) throws Exception {
        log.info("Get Line By Id: {}",id);
        LineModel lineModel = new LineModel();
        if(id.equals(1L)) {
            lineModel.setId(1L);
            lineModel.setStatus("Success");
            lineModel.setText("Hello");
        }else if(id.equals(2L)) {
            log.info("id: {}",id);
            throw new LineException(LineError.GET_LINE_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }else {
            log.error("id: {}",id);
            throw new Exception("Test Throw new Exception with LINE");
        }
        return lineModel;
    }

}
