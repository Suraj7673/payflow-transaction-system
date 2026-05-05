package com.payflow.controller;

import com.payflow.dto.AddBalanceRequest;
import com.payflow.dto.CreateUserRequest;
import com.payflow.dto.TransferRequest;
import com.payflow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Create user + wallet
    @PostMapping("/create")
    public String createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    // ✅ Add balance
    @PostMapping("/add-balance")
    public String addBalance(@RequestBody AddBalanceRequest request) {
        return userService.addBalance(request);
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request, Principal principal) {
        return userService.transfer(request, principal.getName());
    }


}