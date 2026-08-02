package com.example.gameflix.service;

import com.example.gameflix.model.Game;
import com.example.gameflix.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GameServiceImplTest {
    @Autowired
    private GameRepository repository;

    @Test
    void getAllGames() {
        List<Game> items = repository.findAll();
        assertEquals(3, items.size());   // 3 games in your DB
    }

    @Test
    public void testFindOne() {
        Game game = repository.findById(2L).get();   // your first game is id 2
        assertEquals("Systems Integration Quest", game.getTitle());
    }
}
