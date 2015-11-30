package com.auth0.twitter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.auth0.core.Application;
import com.auth0.core.Strategies;
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

    private String apiKey = "vntUu4tx2RYYvtsiUJ5JqJGmc";
    private String apiSecret = "Ks4UkOv2oB03gterbPgguFQWROOeJYHOMwdGWTfOLr3JXmDZXB";

    private TwitterSession twitterSession;
    private IdentityProviderCallback callback;

    public TwitterIdentityProvider(Context context)
    {
        this.context = context;
    }
    @Override
    public void setCallback(IdentityProviderCallback callback) {
        this.callback = callback;

    }

    @Override
    public void start(Activity activity, String serviceName) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(this.apiKey, this.apiSecret);
        TwitterCore twitterCore =  new TwitterCore(authConfig);
        Fabric fabric = Fabric.with(this.context, twitterCore);

        twitterCore.logIn(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                twitterSession = result.data;
                callback.onSuccess(Strategies.Twitter.getName(), result.data.getAuthToken().token);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                int a = 5;
                Dialog dialog = new Dialog(context);
                dialog.setTitle("SSSS");
                callback.onFailure(dialog);
            }
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean authorize(Activity activity, int requestCode, int resultCode, Intent data) {
        return IdentityProvider.WEBVIEW_AUTH_REQUEST_CODE == requestCode;
    }

    @Override
    public void clearSession() {
        int f=5;
    }

    @Override
    public void start(Activity activity, IdentityProviderRequest request, Application application) {
        start(activity, Strategies.Twitter.getName());
    }
}
