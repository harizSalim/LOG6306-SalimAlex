package com.auth0.lock.validation;

public class PasswordSignupValidator extends BaseFragmentValidator {

	public PasswordValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
    }
	
	@Override
    protected boolean doValidate(String value) {
        return value.length() > 0;
    }
}
