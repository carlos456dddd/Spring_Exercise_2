package com.posexample.springexample.controller;

import com.posexample.springexample.dto.userRequest;
import com.posexample.springexample.dto.userResponse;
import com.posexample.springexample.service.userService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    public final userService service;

    public UserController(userService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<userResponse> getAllUser() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public userResponse getByIdUser(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public userResponse postUser(@RequestBody userRequest dates) {
        return service.createUser(dates);
    }

}
