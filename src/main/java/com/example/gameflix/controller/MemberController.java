package com.example.gameflix.controller;

import com.example.gameflix.model.Game;
import com.example.gameflix.model.Member;
import com.example.gameflix.repository.MemberRepository;
import com.example.gameflix.repository.GameRepository;
import com.example.gameflix.service.MemberService;
import com.example.gameflix.service.GameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MemberService memberService;
    @Autowired
    private GameService gameService;

    @GetMapping("/memberList")
    public String viewMemberPage(Model model) {
        model.addAttribute("listMembers", memberService.getAllMembers());
        return "member_list";
    }

    @GetMapping("/showNewMemberForm")
    public String showNewMemberForm(Model model) {
        // create model attribute to bind form data
        Member member = new Member();
        model.addAttribute("member", member);
        return "new_member";
    }

    @PostMapping("/saveMember")
    public String saveMember(@ModelAttribute("member") Member member) {
        // save member to database
        memberService.saveMember(member);
        return "redirect:/memberList";
    }

    @GetMapping("/showMemberFormForUpdate/{id}")
    public String showMemberFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get member from the service
        Member member = memberService.getMemberById(id);
        List<Game> allGames = gameService.getAllGames();
        model.addAttribute("allGames", allGames);
        // set member as a model attribute to pre-populate the form
        model.addAttribute("member", member);

        return "update_member";
    }

    @GetMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable(value = "id") long id) {
        // call delete member method
        this.memberService.deleteMemberById(id);
        return "redirect:/memberList";
    }
}
