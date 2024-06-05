package com.example.spring_into.controller;

import com.example.spring_into.dto.TollRequest;
import com.example.spring_into.service.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/toll")
public class TollController {

    @Autowired
    TollService tollService;

    @PostMapping
    public ResponseEntity<String> addToll(@RequestBody TollRequest tollRequest) throws Exception {
        tollService.addToll(tollRequest);
        return null;
    }

    @GetMapping
    public Boolean checkValidity(@RequestParam(name = "regNumber")String regNumber,
                                 @RequestParam(name = "country")String country){
       return tollService.checkValidity(regNumber, country);
    }
}
