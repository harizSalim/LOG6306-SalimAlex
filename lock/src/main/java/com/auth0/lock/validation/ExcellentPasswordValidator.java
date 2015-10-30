package com.auth0.lock.validation;

import java.util.regex.Pattern;

/**
 * Created by sebastiencaron on 2015-10-30.
 */
public class ExcellentPasswordValidator extends BaseFragmentValidator{

    public ExcellentPasswordValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
    }

    @Override
    protected boolean doValidate(String value) {
        boolean passwordLength = value.length() >= 10;

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

        boolean foundDuplicate = false;

        String[] characters = value.split("(?!^)");

        for (String substring:characters){
            if (this.CharacterDuplicateInString(substring, value))
            {
                foundDuplicate = true;
                break;
            }
        }

        return passwordLength &&  !foundDuplicate && criteriaCount >= 3;
    }

    // Based on the website:
    // http://www.java2s.com/Code/Java/Data-Type/Countthenumberofinstancesofsubstringwithinastring.htm
    private boolean CharacterDuplicateInString(String duplicate, String stringToAnalyze)
    {
        int count = 0;
        int index = 0;

        while ((index = stringToAnalyze.indexOf(duplicate, index)) != -1)
        {
            index++;
            count++;
        }

        return count > 1;
    }
}
