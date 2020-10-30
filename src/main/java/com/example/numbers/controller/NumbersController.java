package com.example.numbers.controller;

import com.example.numbers.constants.NumberApplicationConstants;
import com.example.numbers.dto.ResponseDto;
import com.example.numbers.service.NumbersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(NumberApplicationConstants.API
        + NumberApplicationConstants.VERSION_ONE
        + NumberApplicationConstants.NUMBER)
public class NumbersController {

    @Autowired
    private NumbersService numbersService;


    @GetMapping(NumberApplicationConstants.MEAN_AND_MEDIAN)
    public ResponseEntity<ResponseDto> getMeanAndMedian() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(NumberApplicationConstants.SUCCESS);
        responseDto.setStatus(HttpStatus.OK.toString());
        responseDto.setData(numbersService.getMeanAndMedian());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addListOfNumbers(
            @RequestParam("list") List<Integer> list) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(NumberApplicationConstants.SUCCESS);
        responseDto.setStatus(HttpStatus.OK.toString());
        responseDto.setData(numbersService.addNumber(list));
        return ResponseEntity.ok(responseDto);
    }
}
