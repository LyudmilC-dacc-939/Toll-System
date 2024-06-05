package com.example.spring_into.service.impl;

import com.example.spring_into.converter.TollConverter;
import com.example.spring_into.dto.TollRequest;
import com.example.spring_into.model.TollPass;
import com.example.spring_into.repository.TollRepository;
import com.example.spring_into.service.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TollServiceImpl implements TollService {

    private final TollRepository tollRepository;
    private final TollConverter tollConverter;

    @Autowired
    public TollServiceImpl(TollRepository tollRepository, TollConverter tollConverter) {
        this.tollRepository = tollRepository;
        this.tollConverter = tollConverter;
    }


    @Override
    public TollPass addToll(TollRequest tollRequest) throws Exception {
        TollPass tollPass = tollConverter.toTollPass(tollRequest);
        tollRepository.save(tollPass);
        return null;
    }

    @Override
    public boolean checkValidity(String regNumber, String country) {
        Optional<TollPass> tollPass = tollRepository.findByRegNumberAndCountry(regNumber,country);
        //sendFine()
        if(!tollPass.isEmpty()){
            return !tollPass.get().getExpDate().isBefore(Instant.now());
        } else{
            return false;
        }

    }
}
