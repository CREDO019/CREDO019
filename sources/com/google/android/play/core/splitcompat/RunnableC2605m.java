package com.google.android.play.core.splitcompat;

import android.content.Context;
import com.google.android.play.core.splitinstall.C2656t;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.m */
/* loaded from: classes3.dex */
public final class RunnableC2605m implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f923a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2605m(Context context) {
        this.f923a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2656t.m508a(this.f923a).m639a(true);
    }
}
