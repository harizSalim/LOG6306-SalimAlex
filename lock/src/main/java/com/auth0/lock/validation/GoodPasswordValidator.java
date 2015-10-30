package com.auth0.lock.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sebastiencaron on 2015-10-30.
 */
public class GoodPasswordValidator extends BaseFragmentValidator {

    public GoodPasswordValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
    }

    @Override
    protected boolean doValidate(String value) {
        boolean passwordLength = value.length() >= 8;

        // The regex was based on the following site:
        // http://stackoverflow.com/questions/12586340/regex-to-find-special-characters-in-java
        Pattern regex = Pattern.compile("[a-z]");
        boolean lowerCase = regex.matcher(value).find();

        regex = Pattern.compile("[A-Z]");
        boolean upperCase = regex.matcher(value).find();

        regex = Pattern.compile("[0-9]");
        boolean number = regex.matcher(value).find();

        regex = Pattern.compile("[$&+,:;=?@#|]");
        boolean specialCharacter = regex.matcher(value).find();

        // Check if 3 out of 4 are met
        int criteriaCount = 0;
        criteriaCount += lowerCase ? 1:0;
        criteriaCount += upperCase ? 1:0;
        criteriaCount += number ? 1:0;
        criteriaCount += specialCharacter ? 1:0;

        return passwordLength && criteriaCount >= 3;
    }
}
