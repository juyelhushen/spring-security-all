package com.jskool.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("couarses")
    public String getCoarses() {
        return "couarses";
    }

    @GetMapping("security/ht")
    public String getCoarseSecurity() {
        return "security";
    }
}
