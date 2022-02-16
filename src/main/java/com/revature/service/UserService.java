package com.revature.service;


import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repo.UserDAO;
import com.revature.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {this.userDAO = userDAO;}

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUser(int id){
        Optional<User> user = userDAO.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return new User();
    }

    public int saveUser(User user){
        try {
            //before creating a new user, we have to first encrypt their password
            user.setPassword(Encryptor.encodePassword(user.getPassword()));
            userDAO.save(user);
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public boolean deleteUser(int id){
        try {
            User user = getUser(id);
            userDAO.delete(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUserByUsername(UserDTO userDTO) {
        String username = userDTO.getUsername();
        Optional<User> user = userDAO.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        return new User(); //TODO: Should this return null instead of a user that has null values?
    }
}
