package com.personal.blog.base.oauth.utils;

import com.personal.blog.base.oauth.APIConfig;


public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
