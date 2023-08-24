package com.amplitude.api;

/* loaded from: classes.dex */
public interface Middleware {
    void run(MiddlewarePayload middlewarePayload, MiddlewareNext middlewareNext);
}
