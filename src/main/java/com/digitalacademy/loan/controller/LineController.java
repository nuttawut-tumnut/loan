package com.digitalacademy.loan.controller;

import com.digitalacademy.loan.constants.LineError;
import com.digitalacademy.loan.constants.Response;
import com.digitalacademy.loan.exception.LineException;
import com.digitalacademy.loan.model.LineModel;
import com.digitalacademy.loan.model.ResponseModel;
import com.digitalacademy.loan.model.StatusModel;
import com.digitalacademy.loan.service.LineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="LINE")
public class LineController {
    private static Logger log = LogManager.getLogger(LineController.class.getName());

    private LineService lineService;

    @Autowired
    public LineController(LineService lineService) { this.lineService = lineService; }

    @GetMapping("/text/{id}")
    public HttpEntity<?> getLineTextById(@PathVariable Long id) throws Exception {
        try{
            log.info("Get line text by id: {}",id);
            LineModel lineModel = lineService.getLineById(id);
            log.info("Response id: {}, status is {}",lineModel.getId(),lineModel.getStatus());
            StatusModel statusModel = new StatusModel(Response.SUCCESS_CODE.getContent(), Response.SUCCESS.getContent());
            return ResponseEntity.ok(new ResponseModel(statusModel,lineModel));
        }catch (LineException e) {
            log.error("Line Exception by id: {}",id);
            LineError lineError = e.getLineError();
            return ResponseEntity.ok(
                    new ResponseModel(
                            new StatusModel(
                                    lineError.getCode(),
                                    lineError.getMessage()
                            )
                    )
            );

        }catch (Exception e) {
            log.error("Exception by id: {}",id);
            LineError lineError = LineError.GET_LINE_NOT_FOUND;
            return new ResponseModel(
                    new StatusModel(
                            lineError.getCode(),
                            lineError.getMessage()
                    )
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
