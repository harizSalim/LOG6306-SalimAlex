package com.auth0.lock.validation;

/**
 * Created by sebastiencaron on 2015-10-30.
 */
public class LowPasswordValidator extends BaseFragmentValidator {

    public LowPasswordValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
    }

    @Override
    protected boolean doValidate(String value) {
        return value.length() > 0;
    }
}
