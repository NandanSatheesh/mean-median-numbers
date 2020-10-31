package com.example.numbers.service.impl;

import com.example.numbers.dto.response.MeanAndMedianResponseDto;
import com.example.numbers.exception.NoElementInListException;
import com.example.numbers.service.NumbersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class NumberServiceImpl implements NumbersService {

    private final List<Integer> numberList =
            Collections.synchronizedList(new LinkedList<>());

    private final AtomicInteger sum = new AtomicInteger(0);

    @Override
    public String addNumber(List<Integer> list) {
        log.info("adding items to the list: {}", list);
        for (Integer item : list) {
            sum.getAndAdd(item);
            addToList(item);
            log.info("final list after addition of the new element: {}",
                    numberList);
        }
        return "Item Added to List";
    }

    @Override
    public MeanAndMedianResponseDto getMeanAndMedian() {
        log.info("finding mean and median for the list: {}", numberList);
        int size = numberList.size();
        double mean = 0.0f;
        double median = 0.0f;
        if (size == 0) {
            log.error("the list is empty: {}", numberList);
            throw new NoElementInListException("The list is empty");
        }
        mean = getMeanFromList();
        median = getMedianFromList();
        log.info("computed mean: {}, median: {}", mean, median);
        return MeanAndMedianResponseDto.builder()
                .mean(mean)
                .median(median)
                .build();
    }

    private double getMeanFromList() {
        return 1.0f * sum.get() / numberList.size();
    }

    private double getMedianFromList() {
        if (numberList.size() % 2 == 0) {
            return (double) (numberList.get((numberList.size() - 1) / 2)
                    + numberList.get(numberList.size() / 2)) / 2.0f;
        } else {
            return numberList.get(numberList.size() / 2);
        }
    }

    private void addToList(int num) {
        int index = Collections.binarySearch(numberList, num);
        if (index < 0) {
            index = -(index + 1);
        } else {
            index++;
        }
        log.info("adding item: {} to the position: {} in the list", num, index);
        if (numberList.size() == index) {
            numberList.add(num);
        } else {
            numberList.add(index, num);
        }
    }
}
