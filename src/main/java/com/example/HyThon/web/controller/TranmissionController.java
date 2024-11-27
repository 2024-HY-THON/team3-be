package com.example.HyThon.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transmission")
public class TranmissionController {

    @GetMapping()
    public String temp() {
        return "ok";
    }
}
