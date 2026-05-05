package com.payflow.controller;

import com.payflow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final UserService userService;

    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/balance")
    public Map<String, Object> getBalance(Principal principal) {
        return Map.of(
                "balance",
                userService.getMyBalance(principal.getName())
        );
    }
}