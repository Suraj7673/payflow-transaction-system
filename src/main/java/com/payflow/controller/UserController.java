package com.payflow.controller;

import com.payflow.dto.AddBalanceRequest;
import com.payflow.dto.CreateUserRequest;
import com.payflow.dto.TransferRequest;
import com.payflow.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/balance/{userId}")
    public String getBalance(@PathVariable int userId) {
        return userService.getBalance(userId).toString();
    }
    @PostMapping
    public String createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/add-balance")
    public String addBalance(@RequestBody AddBalanceRequest request) {
        return userService.addBalance(request);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}