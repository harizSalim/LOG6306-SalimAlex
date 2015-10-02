/*
 * AuthenticatedAPIClient.java
 *
 * Copyright (c) 2015 Auth0 (http://auth0.com)
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

package com.auth0.api;

import android.util.Log;

import com.auth0.api.callback.BaseCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

/**
 * API client for Auth0 API v2.
 * @see <a href="https://auth0.com/docs/apiv2">API v2 docs</a>
 */
public class AuthenticatedAPIClient extends BaseAPIClient {

    private static final String TAG = AuthenticatedAPIClient.class.getName();

    /**
     * Creates a new API client instance providing  API and Configuration Urls different than the default. (Useful for on premise deploys).
     * @param clientID Your application clientID.
     * @param baseURL Auth0's  API endpoint
     * @param configurationURL Auth0's enpoint where App info can be retrieved.
     * @param tenantName Name of the tenant. Can be null
     */
    public AuthenticatedAPIClient(String clientID, String baseURL, String configurationURL, String tenantName) {
        super(clientID, baseURL, configurationURL, tenantName);
    }

    /**
     * Creates a new API client instance providing API and Configuration Urls different than the default. (Useful for on premise deploys).
     * @param clientID Your application clientID.
     * @param baseURL Auth0's API endpoint
     * @param configurationURL Auth0's endpoint where App info can be retrieved.
     */
    public AuthenticatedAPIClient(String clientID, String baseURL, String configurationURL) {
        super(clientID, baseURL, configurationURL);
    }

    /**
     * Creates a new API client using clientID and tenant name.
     * @param clientID Your application clientID.
     * @param tenantName Name of the tenant.
     */
    public AuthenticatedAPIClient(String clientID, String tenantName) {
        super(clientID, tenantName);
    }

    /**
     * Adds the header 'Authorization' with a given JWT token.
     * @param jwt a valid JWT token
     */
    public void setJWT(String jwt) {
        this.client.addHeader("Authorization", "Bearer " + jwt);
    }

    /**
     * Creates a user and request a SMS code using a configured SMS connection
     * @param phoneNumber phone number that will receive the SMS code.
     * @param callback callback when the SMS is sent or with the failure reason.
     * @deprecated Use passwordless endpoints from {@link APIClient}
     */
    public void requestSmsCode(String phoneNumber, final BaseCallback<Void> callback) {
        String requestCodeUrl = getBaseURL() + "/api/v2/users";
        Map<String, Object> params = ParameterBuilder.newBuilder()
                .clearAll()
                .setConnection("sms")
                .set("email_verified", false)
                .set("phone_number", phoneNumber)
                .asDictionary();

        Log.v(AuthenticatedAPIClient.class.getName(), "Requesting SMS code for phone " + phoneNumber);
        HttpEntity entity = this.entityBuilder.newEntityFrom(params);
        this.client.post(null, requestCodeUrl, entity, APPLICATION_JSON, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                callback.onSuccess(null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "Failed to request SMS code", error);
                Map errorResponse = null;
                if (statusCode == 400 || statusCode == 401) {
                    try {
                        errorResponse = new ObjectMapper().readValue(responseBody, Map.class);
                        Log.e(APIClient.class.getName(), "Login error " + errorResponse);
                    } catch (IOException e) {
                        Log.w(APIClient.class.getName(), "Failed to parse json error response", error);
                    }
                }
                callback.onFailure(new APIClientException("Failed to request SMS code", error, statusCode, errorResponse));
            }
        });
    }
}
