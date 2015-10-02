/*
 * RepeatPasswordValidator.java
 *
 * Copyright (c) 2014 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auth0.lock.validation;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.auth0.lock.event.AuthenticationError;
import com.auth0.lock.widget.CredentialField;

public class RepeatPasswordValidator implements Validator {

    private final int fieldResource;
    private final int passwordFieldResource;
    private final int errorTitleResource;
    private final int errorMessageResource;

    public RepeatPasswordValidator(int fieldResource, int passwordFieldResource, int errorTitleResource, int errorMessageResource) {
        this.fieldResource = fieldResource;
        this.passwordFieldResource = passwordFieldResource;
        this.errorTitleResource = errorTitleResource;
        this.errorMessageResource = errorMessageResource;
    }

    @Override
    public AuthenticationError validateFrom(Fragment fragment) {
        CredentialField field = (CredentialField) fragment.getView().findViewById(fieldResource);
        EditText passwordField = (EditText) fragment.getView().findViewById(passwordFieldResource);
        String repeat = field.getText().toString();
        String password = passwordField.getText().toString();
        boolean invalid = repeat.length() == 0 || !repeat.equals(password);
        field.markAsInvalid(invalid);
        return invalid ? new AuthenticationError(errorTitleResource, errorMessageResource) : null;
    }

}
