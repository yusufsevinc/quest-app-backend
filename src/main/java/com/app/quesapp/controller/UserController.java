package com.app.quesapp.controller;

import com.app.quesapp.model.User;
import com.app.quesapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveUser(newUser);
    }


    @GetMapping("/{userId}")
    public User getByUser(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateByUser(@PathVariable Long userId ,@RequestBody User newUser){
      return userService.updateUser(userId,newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
