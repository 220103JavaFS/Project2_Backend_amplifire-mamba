package com.revature.controllers;

import com.revature.models.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUser(id);
        if(user!=null){
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(204).build();
    }

    @PostMapping
    public ResponseEntity<Integer> addUser(@RequestBody User user){
        //Attempt to create a new user. A returned value of 0 means it worked,
        //otherwise an appropriate error code is returned
        Integer errorCode = userService.saveUser(user);
        if (errorCode == 0) return ResponseEntity.status(201).body(errorCode);

        return ResponseEntity.status(400).body(errorCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
