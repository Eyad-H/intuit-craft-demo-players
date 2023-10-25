package com.playerApplication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggerController {
    Logger logger = LoggerFactory.getLogger(LoggerController.class);

    public void info(String info) {
        logger.info(info);
    }
}
