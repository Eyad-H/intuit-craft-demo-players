package com.playerApplication.controller;

import com.playerApplication.model.Player;
import com.playerApplication.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")

public class PlayerController {

    @Autowired
    public LoggerController logger;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<Map<String, Player>> getAllPlayers(){
        logger.info(("getAllPlayers API started"));
        return  new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping(value ={"/player/", "/player/{id}"})
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") String playerId ){
        logger.info("getPlayerById API started with player id: "+playerId);
        return new ResponseEntity<>(playerService.getPlayerById(playerId), HttpStatus.OK);
    }

}
