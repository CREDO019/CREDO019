package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

/* loaded from: classes.dex */
class JSCJavaScriptExecutor extends JavaScriptExecutor {
    private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "JSCJavaScriptExecutor";
    }

    static {
        ReactBridge.staticInit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSCJavaScriptExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }
}
