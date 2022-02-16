package com.revature.controllers;

import com.revature.models.MostPopulatedCategories;
import com.revature.models.Statistic;
import com.revature.models.User;
import com.revature.service.MostPopulatedCategoriesService;
import com.revature.service.UserService;
import com.revature.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private MostPopulatedCategoriesService mpcService;

    @Autowired
    public UserController(UserService userService, MostPopulatedCategoriesService mpcService) {
        this.userService = userService;
        this.mpcService = mpcService;
    }

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
        user.setPassword(Encryptor.encodePassword(user.getPassword())); //first encrypt the password
        Integer errorCode = userService.saveUser(user);
        if (errorCode == 0) {
            //this is going to seem a little funky here but is the best I can think of for now. When a user is created
            //they should have a blank array of stats associated with them. Each stat, in turn, is linked to the user.
            //When a new user is passed to this function, it will have a user id of 0, the actual user id isn't generated
            //until after they're created in the database. If we try to create the stats before putting the user in the
            //database, all of the stats will belong to user with id 0 (which won't exist). To get around this we need to
            //create the user in the database first, then retrieve them from the database and add stats after their unique id
            //number has been generated.
            User newUser = userService.findUserByUsername(user.getUsername()); //get the newly created user from the DB
            newUser.setPassword(user.getPassword()); //need to set password to unencrypted version so it isn't encrypted twice
            List<MostPopulatedCategories> currentCategories = mpcService.getAllCategories(); //get all categories currently saved in the DB
            List<Statistic> blankUserStats = new ArrayList<>(); //create a blank statistic array
            for (MostPopulatedCategories cat : currentCategories) {
                Statistic newStat = new Statistic();
                newStat.setStatOwner(user); //must link the stat to the user
                newStat.setCategoryName(cat.getTitle()); //must give stat a category title
                blankUserStats.add(newStat);
            }
            newUser.setUserStats(blankUserStats);

            //now we need to update the user in the database (feels redundant to me but for now it works)
            userService.saveUser(newUser);

            return ResponseEntity.status(201).body(errorCode);
        }

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
