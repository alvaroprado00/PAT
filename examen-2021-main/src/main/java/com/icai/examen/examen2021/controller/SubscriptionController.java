package com.icai.examen.examen2021.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SubscriptionController {

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @PostMapping("/api/subscribe")
    public ResponseEntity<SubscribeResponse> subscribe(
            @Valid @RequestBody SubscribeRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        //Codigo de negocio omitido

        return ResponseEntity.ok().body(new SubscribeResponse("Ok"));
    }

}