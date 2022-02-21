package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.service.UserService;
import com.revature.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;

import static com.revature.utils.Encryptor.encodePassword;

@RestController
@RequestMapping("/login")
@CrossOrigin()
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {this.userService = userService;}

    @PostMapping
    public ResponseEntity<User> getUserByUsername(@RequestBody UserDTO userDTO, HttpServletRequest request){

        User user = userService.getUserByUsername(userDTO);
        String givenPass = userDTO.getPassword();
        if(user!=null){
            if (Encryptor.encodePassword(givenPass).equals(user.getPassword())) {
                request.getSession().setAttribute("user", user.getUserId());
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping
    public void logout(@RequestBody User user, HttpServletRequest request) {
        //before logging the user out update any potential stat changes of there's in the database. also, check
        //to make sure that the user hasn't changed their password, if so then make sure to encrypt it first
        System.out.println("User logging out:" + user);
        User nonUpdatedUser = userService.getUser(user.getUserId()); //need to call db to check current password

        if(!nonUpdatedUser.getPassword().equals(user.getPassword())){
            user.setPassword(Encryptor.encodePassword(user.getPassword())); //passwords don't match, encrypt before saving
        }
        userService.saveUser(user);
        System.out.println("called from the logout backend function. user in db is now: " + user);
        HttpSession session = request.getSession();
        session.invalidate();
    }
}