package com.itsmidris.edulead_crm.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}
