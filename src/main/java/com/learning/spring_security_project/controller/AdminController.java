package com.learning.spring_security_project.controller;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v1/admin")
@EnableMethodSecurity(jsr250Enabled = true)
public class AdminController {

    @GetMapping("/vip")
    @RolesAllowed({ "ADMIN" })
    public String zoneVip() {
        return "Zone Vip";
    }

    @GetMapping("/normal")
    @RolesAllowed({ "ADMIN", "USER" })
    public String zoneNormal() {
        return "Zone normal";
    }

    @GetMapping("/infor")
    public Authentication getInforUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
