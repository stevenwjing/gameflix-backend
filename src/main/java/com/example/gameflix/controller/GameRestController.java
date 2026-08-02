package com.example.gameflix.controller;

import com.example.gameflix.model.Game;
import com.example.gameflix.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GameRestController {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/game/", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            return new ResponseEntity<List<Game>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
}
