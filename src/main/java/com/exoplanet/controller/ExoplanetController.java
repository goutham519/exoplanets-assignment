package com.exoplanet.controller;

import com.exoplanet.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/exoplanets")
public class ExoplanetController {
    
    @Autowired
    ExoplanetService exoplanetService;

    @GetMapping("/requested-details")
    public ResponseEntity<Map> getExoplanetsWithReqDetails() {
        Map planetList = exoplanetService.getExoplanetDetails();
        return ResponseEntity.status(HttpStatus.OK).body(planetList);
    }

}
