package com.zm.utils.security;

public class BCryptUtils {

    public static String encrypt(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
}
