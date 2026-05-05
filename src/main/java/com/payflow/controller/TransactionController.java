package com.payflow.controller;

import com.payflow.model.Transaction;
import com.payflow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final UserService userService;

    public TransactionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Transaction> getTransactions(Principal principal) {
        return userService.getMyTransactions(principal.getName());
    }
}