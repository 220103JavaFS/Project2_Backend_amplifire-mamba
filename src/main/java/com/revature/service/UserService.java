package com.revature.service;


import com.revature.models.User;
import com.revature.repo.UserDAO;
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
}
