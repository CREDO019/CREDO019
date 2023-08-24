package com.facebook.react.modules.appregistry;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes.dex */
public interface AppRegistry extends JavaScriptModule {
    void runApplication(String str, WritableMap writableMap);

    void startHeadlessTask(int r1, String str, WritableMap writableMap);

    void unmountApplicationComponentAtRootTag(int r1);
}
