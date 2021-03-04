package com.widgets_are_us.customers_service.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RestController
public class HelloWorldController {

    public static final String HELLO_WORLD = "Hello world!";

    @ResponseBody
    @GetMapping("/hello-world")
    public ResponseEntity<String> helloWorld() {
        log.info("Hello world endpoint hit. Checking log level.");

        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warn message");
        log.error("This is an error message");

        return new ResponseEntity<>(HELLO_WORLD, HttpStatus.OK);
    }
}
