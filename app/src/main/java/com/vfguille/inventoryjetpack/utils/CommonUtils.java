package com.vfguille.inventoryjetpack.utils;

import java.util.regex.Pattern;

public class CommonUtils {
    private final static int MAX_LENGTH_PASSWORD = 12;
    private final static int MIN_LENGTH_PASSWORD = 8;
    private final static String REGEXP_PASSWORD = "^(?=.*\\\\d)(?=.*[A-Z])(?=.*[a-z]).{8,12}$";
    private final static String REGEXP_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Za-z]{2,6}$";

    public static boolean regexpPasswordsValidation(String pass){
        //(?=.*\d)      #   must contains one digit from 0-9
        //(?=.*[a-z])   #   must contains one lowercase characters
        //(?=.*[A-Z])   #   must contains one uppercase characters
        //{8,12}        #   length at least 8 characters and maximum of 12
        Pattern pattern = Pattern.compile(REGEXP_PASSWORD);
        return pattern.matcher(pass).matches();
    }

    public static boolean checkPasswordLength(String pass){
        return (pass.length() >= MIN_LENGTH_PASSWORD && pass.length() <= MAX_LENGTH_PASSWORD);
    }

    public static boolean regexpEmailValidation(String email){
        return email.matches(REGEXP_EMAIL);
    }
}
