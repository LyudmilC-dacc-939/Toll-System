package com.example.spring_into.controller;

import com.example.spring_into.dto.OwnerRequest;
import com.example.spring_into.dto.OwnerResponse;
import com.example.spring_into.model.Owner;
import com.example.spring_into.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/owner")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @PostMapping(path = "/{ownerId}")
    Owner updateOwner(@RequestBody OwnerRequest request,
                      @PathVariable("ownerId") Long ownerId) {
        return ownerService.updateOwner(request, ownerId);
    }

    @DeleteMapping(path = {"/{ownerId}"})
    void deleteOwner(@PathVariable("ownerId") Long ownerId) {
        ownerService.deleteOwnerById(ownerId);
    }

    @PostMapping
    ResponseEntity<OwnerResponse> addOwner(@RequestBody OwnerRequest request) {
        ownerService.addOwner(request);

        return new ResponseEntity<>(ownerService.addOwner(request),
                HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnerResponse> findById(@PathVariable("id") Long ownerId) {
        return new ResponseEntity<>(ownerService.findOwnerById(ownerId), HttpStatus.FOUND);
    }
}
