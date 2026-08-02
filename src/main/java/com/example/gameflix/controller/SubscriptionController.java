package com.example.gameflix.controller;

import com.example.gameflix.model.Game;
import com.example.gameflix.model.Member;
import com.example.gameflix.model.User;
import com.example.gameflix.service.GameService;
import com.example.gameflix.service.MemberService;
import com.example.gameflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/myGames")
public class SubscriptionController {

    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private MemberService memberService;

    @GetMapping
    public String viewMyGames(Model model, Authentication authentication) {
        Member member = currentMember(authentication);
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("member", member);
        model.addAttribute("allGames", allGames);

        Set<Long> subscribedIds = member == null
                ? Set.of()
                : member.getGames().stream().map(Game::getId).collect(Collectors.toSet());
        model.addAttribute("subscribedIds", subscribedIds);
        return "my_games";
    }

    @PostMapping("/subscribe/{gameId}")
    public String subscribe(@PathVariable long gameId, Authentication authentication) {
        Member member = currentMember(authentication);
        if (member != null) {
            Game game = gameService.getGameById(gameId);
            member.getGames().add(game);
            memberService.saveMember(member);
        }
        return "redirect:/myGames";
    }

    @PostMapping("/unsubscribe/{gameId}")
    public String unsubscribe(@PathVariable long gameId, Authentication authentication) {
        Member member = currentMember(authentication);
        if (member != null) {
            member.getGames().removeIf(g -> g.getId() == gameId);
            memberService.saveMember(member);
        }
        return "redirect:/myGames";
    }

    private Member currentMember(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        return user != null ? user.getMember() : null;
    }
}
