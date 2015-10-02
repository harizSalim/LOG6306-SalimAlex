/*
 * EmailValidator.java
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

import java.util.regex.Pattern;

public class EmailValidator extends BaseFragmentValidator {

    private final Pattern pattern;

    private static final String EMAIL_PATTERN = "^[A-Z0-9a-z\\._%+-]+@([A-Za-z0-9-]+[\\.])+[A-Za-z]{2,}$";

    public EmailValidator(int fieldResource, int errorTitleResource, int errorMessageResource) {
        super(fieldResource, errorTitleResource, errorMessageResource);
        this.pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    protected boolean doValidate(String value) {
        String email = value.trim();
        return email.length() > 0 && pattern.matcher(email).matches();
    }

}
