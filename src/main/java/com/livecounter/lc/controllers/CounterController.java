package com.livecounter.lc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.livecounter.lc.service.CounterService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CounterController {
    final private CounterService counterService;

    @GetMapping("/counter")
    public SseEmitter getCounter() {
        return counterService.subscribe();
    }
}
