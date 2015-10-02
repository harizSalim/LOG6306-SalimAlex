/*
 * LockTest.java
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

package com.auth0.lock;

import com.auth0.api.APIClient;
import com.auth0.core.Auth0;
import com.auth0.core.Strategies;
import com.auth0.identity.IdentityProvider;
import com.auth0.identity.WebIdentityProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18, manifest = Config.NONE)
public class LockTest {

    private static final String NAME = Strategies.Facebook.getName();
    @Mock
    private IdentityProvider provider;
    @Mock
    private APIClient client;
    @Mock
    private Auth0 auth0;

    private Lock lock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(auth0.getClientId()).thenReturn("CLIENT_ID");
        when(auth0.getAuthorizeUrl()).thenReturn("https://samples.auth0.com/authorize");
        when(auth0.newAPIClient()).thenReturn(client);
        lock = new Lock(auth0);
    }

    @Test
    public void shouldRegisterProvider() throws Exception {
        lock.setProvider(NAME, provider);
        assertThat(lock.providerForName(NAME), equalTo(provider));
    }

    @Test
    public void shouldResetAllProviders() throws Exception {
        lock.setProvider(NAME, provider);
        lock.resetAllProviders();
        verify(provider).stop();
    }

    @Test
    public void shouldReturnDefaultWithUnknownProvider() throws Exception {
        assertThat(lock.providerForName("UNKOWN"), instanceOf(WebIdentityProvider.class));
    }
}