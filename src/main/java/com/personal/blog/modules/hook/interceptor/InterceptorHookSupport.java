package com.personal.blog.modules.hook.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;

/**
 * 拦截类钩子支持类
 * @author weizp
 */
public abstract class InterceptorHookSupport implements InterceptorHook {
    @Autowired
    protected InterceptorHookManager interceptorHookManager;

    @PreDestroy
    public void destroy() {
        interceptorHookManager.removeInterceptorHook(this);
    }
}
