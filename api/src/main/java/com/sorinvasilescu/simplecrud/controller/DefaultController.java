package com.sorinvasilescu.simplecrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {

    @GetMapping
    public ResponseEntity<?> defaultAction() {
        return ResponseEntity.ok("Ok");
    }
}
