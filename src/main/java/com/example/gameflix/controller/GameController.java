package com.example.gameflix.controller;

import com.example.gameflix.model.Game;
import com.example.gameflix.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewGameForm")
    public String showNewGameForm(Model model) {
        // create model attribute to bind form data
        Game game = new Game();
        model.addAttribute("game", game);
        return "new_game";
    }

    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute("game") Game game) {
        // save game to database
        gameService.saveGame(game);
        return "redirect:/";
    }

    @GetMapping("/showGameFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get game from the service
        Game game = gameService.getGameById(id);
        // set game as a model attribute to pre-populate the form
        model.addAttribute("game", game);
        return "update_game";
    }

    @GetMapping("/deleteGame/{id}")
    public String deleteGame(@PathVariable(value = "id") long id) {
        // call delete game method
        this.gameService.deleteGameById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Game> page = gameService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Game> listGames = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listGames", listGames);
        return "index";
    }
}
