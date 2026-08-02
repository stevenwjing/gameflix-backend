package com.example.gameflix.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import com.example.gameflix.model.Game;
import com.example.gameflix.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() { return gameRepository.findAll(); }

    @Override
    public void saveGame(Game game) { this.gameRepository.save(game); }

    @Override
    public Game getGameById(long id) {
        Optional<Game> optional = gameRepository.findById(id);
        Game game = null;
        if (optional.isPresent()) {
            game = optional.get();
        } else {
            throw new RuntimeException(" Game not found for id :: " + id);
        }
        return game;
    }

    @Override
    public void deleteGameById(long id) { this.gameRepository.deleteById(id); }

    @Override
    public Page<Game> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.gameRepository.findAll(pageable);
    }
}
