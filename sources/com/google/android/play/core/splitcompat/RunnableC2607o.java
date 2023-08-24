package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.o */
/* loaded from: classes3.dex */
public final class RunnableC2607o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Set f925a;

    /* renamed from: b */
    final /* synthetic */ SplitCompat f926b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2607o(SplitCompat splitCompat, Set set) {
        this.f926b = splitCompat;
        this.f925a = set;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2595c c2595c;
        try {
            for (String str : this.f925a) {
                c2595c = this.f926b.f899b;
                c2595c.m591f(str);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to remove from splitcompat storage split that is already installed", e);
        }
    }
}
