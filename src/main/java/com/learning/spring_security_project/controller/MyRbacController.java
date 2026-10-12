package com.learning.spring_security_project.controller;


import com.learning.spring_security_project.entity.ShopeeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyRbacController {

    @GetMapping("/user/info")
    public ResponseEntity<ShopeeUser> index(Authentication authentication) {
        System.out.println(authentication);
        ShopeeUser principal = (ShopeeUser)authentication.getPrincipal();
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/admin/manager/user")
    public ResponseEntity<String> managerUser() {
        return ResponseEntity.ok("Admin Page: Manager Users....");
    }

    @GetMapping("/admin/manager/shop")
    public ResponseEntity<String> managerShop() {
        return ResponseEntity.ok("Admin Page: Manager Shop....");
    }

    //
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/manager/statistics")
    public ResponseEntity<String> statistic() {
        return ResponseEntity.ok("Statics Page: Statics View....");
    }

    // delete User: Role is ADMIN and Name is "admin"
    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admim'")
    @GetMapping("/admin/manager/user/delete")
    public ResponseEntity<String> deleteUser() {
        return ResponseEntity.ok("Delete User Page: Delete is OK....");
    }

    //
    @PreAuthorize("hasAuthority('seller:product:manage')")
    @GetMapping("/shop/manager/product")
    public ResponseEntity<String> managerProduct() {
        return ResponseEntity.ok("Shop Page: Manager product....");
    }

    @PreAuthorize("hasAuthority('seller:order:view')")
    @GetMapping("/shop/manager/order")
    public ResponseEntity<String> managerOrder() {
        return ResponseEntity.ok("Shop Page: Manager order....");
    }
}
