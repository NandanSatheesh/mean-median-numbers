package com.example.numbers.service;

import com.example.numbers.dto.response.MeanAndMedianResponseDto;

import java.util.List;

public interface NumbersService {

    String addNumber(List<Integer> list);

    MeanAndMedianResponseDto getMeanAndMedian();
}
