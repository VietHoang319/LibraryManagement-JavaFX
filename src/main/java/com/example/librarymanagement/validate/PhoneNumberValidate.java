package com.example.librarymanagement.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidate {
    private static final String PHONENUMBER_REGEX = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
    private static Pattern pattern;

    public PhoneNumberValidate() {
        pattern = Pattern.compile(PHONENUMBER_REGEX);
    }

    public boolean validate(String regex) {
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
