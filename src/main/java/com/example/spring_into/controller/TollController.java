package com.example.spring_into.controller;

import com.example.spring_into.dto.TollRequest;
import com.example.spring_into.model.TollPass;
import com.example.spring_into.service.OwnerService;
import com.example.spring_into.service.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/toll")
public class TollController {

    @Autowired
    TollService tollService;
    @Autowired
    OwnerService ownerService;

    @PostMapping
    public ResponseEntity<String> addToll(@RequestBody TollRequest tollRequest) throws Exception {
        tollService.addToll(tollRequest);
        return null;
    }

    @GetMapping
    public Boolean checkValidity(@RequestParam(name = "regNumber") String regNumber,
                                 @RequestParam(name = "country") String country) {
        return tollService.checkValidity(regNumber, country);
    }

    @GetMapping(path = {"owner/{ownerId}"})
    public Set<TollPass> getTollForUser(@PathVariable("ownerId") Long ownerId) {
        return ownerService.getTollsForOwner(ownerId);
    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteToll(@PathVariable("id") Long id) {
        tollService.deleteToll(id);
    }


}
