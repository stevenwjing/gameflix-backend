package com.example.gameflix.service;

import com.example.gameflix.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Test
    void getAllGames_ShouldReturnList() {
        List<Game> games = gameService.getAllGames();
        assertFalse(games.isEmpty());
    }

    @Test
    void getGameById_ShouldReturnCorrectGame() {
        Game game = gameService.getGameById(2L);
        assertEquals("Systems Integration Quest", game.getTitle());
        assertEquals("Simulation", game.getGenre());
    }

    @Test
    void saveGame_ThenDeleteGame_ShouldPersistAndRemove() {
        Game game = new Game();
        game.setGenre("Puzzle");
        game.setPlatform("Switch");
        game.setTitle("Test Save And Delete Game");
        game.setPrice("$4.99");

        gameService.saveGame(game);
        long newId = game.getId();
        assertTrue(newId > 0);

        Game fetched = gameService.getGameById(newId);
        assertEquals("Test Save And Delete Game", fetched.getTitle());

        gameService.deleteGameById(newId);
        assertThrows(RuntimeException.class, () -> gameService.getGameById(newId));
    }
}
