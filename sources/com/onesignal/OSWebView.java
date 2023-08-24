package com.onesignal;

import android.content.Context;
import android.webkit.WebView;

/* loaded from: classes3.dex */
public class OSWebView extends WebView {
    @Override // android.webkit.WebView, android.view.View
    public void computeScroll() {
    }

    @Override // android.view.View
    public boolean overScrollBy(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, boolean z) {
        return false;
    }

    @Override // android.view.View
    public void scrollTo(int r1, int r2) {
    }

    public OSWebView(Context context) {
        super(context);
    }
}
