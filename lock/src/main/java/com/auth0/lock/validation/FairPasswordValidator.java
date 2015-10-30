package com.auth0.lock.validation;

import java.util.regex.Pattern;

/**
 * Created by sebastiencaron on 2015-10-30.
 */
public class FairPasswordValidator extends BaseFragmentValidator {

    public FairPasswordValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
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

        return passwordLength && lowerCase && upperCase && number;
    }
}
