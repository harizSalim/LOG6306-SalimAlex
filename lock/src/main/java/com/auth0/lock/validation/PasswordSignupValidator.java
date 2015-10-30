package com.auth0.lock.validation;

public class PasswordSignupValidator extends BaseFragmentValidator {

	public PasswordSignupValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
    }
	
	@Override
    protected boolean doValidate(String value) {
        return value.length() > 0;
    }
}
