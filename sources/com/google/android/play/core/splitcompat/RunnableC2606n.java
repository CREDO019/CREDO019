package com.google.android.play.core.splitcompat;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.n */
/* loaded from: classes3.dex */
public final class RunnableC2606n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SplitCompat f924a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2606n(SplitCompat splitCompat) {
        this.f924a = splitCompat;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2595c c2595c;
        try {
            c2595c = this.f924a.f899b;
            c2595c.m608a();
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to cleanup splitcompat storage", e);
        }
    }
}
