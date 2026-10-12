package com.learning.spring_security_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@Controller
public class MyController {

    @GetMapping("info") // user, admin, mod
    public ResponseEntity<Map<String, Object>> infoUser(Authentication authentication) {
        String username = authentication.getName();
        String principal = authentication.getPrincipal().toString();
        // roles
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("username", username, "principal", principal, "roles", roles));
    }

    @GetMapping("admin") // has -> amdin
    public String infoAdmin() {
        return "login";
    }

    @GetMapping("mod") // has -> amdin
    public String infoMod() {
        return "mod";
    }

//    @GetMapping("login")
//    public String publicMethod() {
//        return "login";
//    }

//    @GetMapping("public")
//    public String publicMethod() {
//        return "public";
//    }
//
//    @GetMapping("private")
//    public String privateMethod() {
//        return "private";
//    }
}
