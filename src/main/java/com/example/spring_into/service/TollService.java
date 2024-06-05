package com.example.spring_into.service;

import com.example.spring_into.dto.TollRequest;
import com.example.spring_into.model.TollPass;

public interface TollService {
    TollPass addToll(TollRequest tollPass) throws Exception;

    boolean checkValidity(String regNumber, String country);
}
