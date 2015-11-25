package com.auth0.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.auth0.core.Application;
import com.auth0.identity.IdentityProvider;
import com.auth0.identity.IdentityProviderCallback;
import com.auth0.identity.IdentityProviderRequest;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

/**
 * Created by sebastiencaron on 2015-11-15.
 */
public class TwitterIdentityProvider implements IdentityProvider {

    private Context context;

    private String apiKey;
    private String apiSecret;

    private TwitterSession twitterSession;

    public TwitterIdentityProvider(Context context)
    {
        this.context = context;
//        this.apiKey = applicationKey;
//        this.apiSecret = applicationSecret;

    }
    @Override
    public void setCallback(IdentityProviderCallback callback) {
        new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                twitterSession = result.data;
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        };
    }

    @Override
    public void start(Activity activity, String serviceName) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean authorize(Activity activity, int requestCode, int resultCode, Intent data) {
        return false;
    }

    @Override
    public void clearSession() {

    }

    @Override
    public void start(Activity activity, IdentityProviderRequest request, Application application) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(this.apiKey, this.apiSecret);
        Fabric.with(activity.getApplication(), new TwitterCore(authConfig));
    }
}
