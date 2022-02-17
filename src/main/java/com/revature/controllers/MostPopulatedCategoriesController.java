package com.revature.controllers;

import com.revature.models.MostPopulatedCategories;
import com.revature.models.User;
import com.revature.service.MostPopulatedCategoriesService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/mpcategories")
public class MostPopulatedCategoriesController {

    private MostPopulatedCategoriesService mostPopulatedCategoriesService;

    @Autowired
    public MostPopulatedCategoriesController(MostPopulatedCategoriesService mostPopulatedCategoriesService) {
        this.mostPopulatedCategoriesService = mostPopulatedCategoriesService;
    }

    @GetMapping
    public ResponseEntity<List<MostPopulatedCategories>> getAllCategories(){
        return ResponseEntity.status(200).body(mostPopulatedCategoriesService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostPopulatedCategories> getCategoryById(@PathVariable("id") int id){
        MostPopulatedCategories mpc = mostPopulatedCategoriesService.getCategory(id);
        if(mpc!=null){
            return ResponseEntity.status(200).body(mpc);
        }
        return ResponseEntity.status(204).build();
    }

    @PostMapping
    public ResponseEntity<Integer> addUser(@RequestBody ArrayList<MostPopulatedCategories> mpcs){
        //Attempt to create a new user. A returned value of 0 means it worked,
        //otherwise an appropriate error code is returned
        System.out.println(mpcs.get(0));
        Integer errorCode = mostPopulatedCategoriesService.saveCategories(mpcs);

        if (errorCode == 0) return ResponseEntity.status(201).body(errorCode);

        return ResponseEntity.status(400).body(errorCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id){
        Boolean worked = mostPopulatedCategoriesService.deleteCategory(id);
        if(worked){
            return ResponseEntity.status(200).body(worked);
        }
        return ResponseEntity.status(400).body(worked);
    }
}