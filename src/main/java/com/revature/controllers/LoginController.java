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
@CrossOrigin("http://localhost:4200")
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

    @RequestMapping
    public void logout(HttpServletRequest request) {
       HttpSession session = request.getSession();
       session.invalidate();
    }
}
