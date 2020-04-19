package com.ngatdo.rest.controller;

import com.ngatdo.rest.model.User;

import com.ngatdo.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public User[] getUser() {
        return  userService.getAllUsers();
    }

    @GetMapping(value = "/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        return userService.getUsers(name);
    }

    @PostMapping(value = "/add")
    public User addUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @PutMapping(value = "/edit")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete/{name}")

    public User deleteUser(@PathVariable("name") String name) {
         return userService.deleteUser(name);
    }
}
