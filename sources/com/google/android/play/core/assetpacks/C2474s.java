package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.s */
/* loaded from: classes3.dex */
public final class C2474s implements InterfaceC2556cn<Context> {

    /* renamed from: a */
    private final C2469n f782a;

    public C2474s(C2469n c2469n) {
        this.f782a = c2469n;
    }

    /* renamed from: a */
    public static Context m838a(C2469n c2469n) {
        Context m842a = c2469n.m842a();
        C2532bq.m736b(m842a);
        return m842a;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: b */
    public final Context mo473a() {
        return m838a(this.f782a);
    }
}
