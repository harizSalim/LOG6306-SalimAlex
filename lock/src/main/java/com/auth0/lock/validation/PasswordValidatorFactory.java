package com.auth0.lock.validation;

import com.auth0.lock.R;

/**
 * Created by sebastiencaron on 2015-11-06.
 */
public final class PasswordValidatorFactory {

    public static BaseFragmentValidator CreateInstance(String policy, int fieldResource)
    {
        BaseFragmentValidator valueToReturn;

        switch (policy)
        {
            case "low":
                valueToReturn = new LowPasswordValidator(fieldResource, R.string.com_auth0_invalid_credentials_title, R.string.com_auth0_invalid_low_password_message);
                break;

            case "fair":
                valueToReturn = new FairPasswordValidator(fieldResource, R.string.com_auth0_invalid_credentials_title, R.string.com_auth0_invalid_fair_password_message);
                break;

            case "good":
                valueToReturn = new GoodPasswordValidator(fieldResource, R.string.com_auth0_invalid_credentials_title, R.string.com_auth0_invalid_good_password_message);
                break;

            case "excellent":
                valueToReturn = new ExcellentPasswordValidator(fieldResource, R.string.com_auth0_invalid_credentials_title, R.string.com_auth0_invalid_excellent_password_message);
                break;

            default:
                valueToReturn = new PasswordValidator(fieldResource, R.string.com_auth0_invalid_credentials_title, R.string.com_auth0_invalid_password_message);
                break;
        }

        return valueToReturn;
    }
}
