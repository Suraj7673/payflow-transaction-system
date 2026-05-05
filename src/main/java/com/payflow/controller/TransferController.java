package com.payflow.controller;

import com.payflow.dto.TransferRequest;
import com.payflow.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final UserService userService;

    public TransferController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String transfer(@RequestBody TransferRequest request, Principal principal) {
        return userService.transfer(request, principal.getName());
    }
}