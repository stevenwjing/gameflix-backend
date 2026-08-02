package com.example.gameflix.controller;

import com.example.gameflix.model.Member;
import com.example.gameflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registration", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registration") RegistrationForm form, Model model) {
        Member member = new Member();
        member.setMemberName(form.getMemberName());
        member.setMemberEmail(form.getMemberEmail());

        boolean created = userService.registerMember(form.getUsername(), form.getPassword(), member);
        if (!created) {
            model.addAttribute("error", "That username is already taken.");
            return "register";
        }
        return "redirect:/login?registered";
    }
}
