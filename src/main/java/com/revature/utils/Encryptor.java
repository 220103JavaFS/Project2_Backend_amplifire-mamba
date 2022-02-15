package com.revature.utils;

import java.util.Base64;

public class Encryptor {
    public static String encodePassword(String password){
        byte[] bytes = password.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
