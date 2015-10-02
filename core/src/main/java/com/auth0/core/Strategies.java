package com.auth0.core;

import static com.auth0.core.Strategies.Type.*;

/**
 * An enum with all strategies available in Auth0
 */
public enum Strategies {
    Auth0("auth0", DATABASE),

    Email("email", PASSWORDLESS),
    SMS("sms", PASSWORDLESS),

    Amazon("amazon", SOCIAL),
    AOL("aol", SOCIAL),
    Baidu("baidu", SOCIAL),
    Box("box", SOCIAL),
    Dwolla("dwolla", SOCIAL),
    EBay("ebay", SOCIAL),
    Evernote("evernote", SOCIAL),
    EvernoteSandbox("evernote-sandbox", SOCIAL),
    Facebook("facebook", SOCIAL),
    Fitbit("fitbit", SOCIAL),
    Github("github", SOCIAL),
    GooglePlus("google-oauth2", SOCIAL),
    Instagram("instagram", SOCIAL),
    Linkedin("linkedin", SOCIAL),
    Miicard("miicard", SOCIAL),
    Paypal("paypal", SOCIAL),
    PlanningCenter("planningcenter", SOCIAL),
    RenRen("renren", SOCIAL),
    Salesforce("salesforce", SOCIAL),
    SalesforceSandbox("salesforce-sandbox", SOCIAL),
    Shopify("shopify", SOCIAL),
    Soundcloud("soundcloud", SOCIAL),
    TheCity("thecity", SOCIAL),
    TheCitySandbox("thecity-sandbox", SOCIAL),
    ThirtySevenSignals("thirtysevensignals", SOCIAL),
    Twitter("twitter", SOCIAL),
    VK("vkontakte", SOCIAL),
    Weibo("weibo", SOCIAL),
    WindowsLive("windowslive", SOCIAL),
    Wordpress("wordpress", SOCIAL),
    Yahoo("yahoo", SOCIAL),
    Yammer("yammer", SOCIAL),
    Yandex("yandex", SOCIAL),

    ActiveDirectory("ad", ENTERPRISE),
    ADFS("adfs", ENTERPRISE),
    Auth0LDAP("auth0-adldap", ENTERPRISE),
    Custom("custom", ENTERPRISE),
    GoogleApps("google-apps", ENTERPRISE),
    GoogleOpenId("google-openid", ENTERPRISE),
    IP("ip", ENTERPRISE),
    MSCRM("mscrm", ENTERPRISE),
    Office365("office365", ENTERPRISE),
    PingFederate("pingfederate", ENTERPRISE),
    SAMLP("samlp", ENTERPRISE),
    Sharepoint("sharepoint", ENTERPRISE),
    Waad("waad", ENTERPRISE);


    private String name;
    private Type type;

    Strategies(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public static Strategies fromName(String name) {
        Strategies strategy = null;
        for (Strategies str: values()) {
            if (str.getName().equals(name)) {
                strategy = str;
                break;
            }
        }
        return strategy;
    }

    public enum Type {
        DATABASE,
        SOCIAL,
        ENTERPRISE,
        PASSWORDLESS;
    }
}
