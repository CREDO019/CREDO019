package com.google.android.play.core.appupdate;

import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.appupdate.h */
/* loaded from: classes3.dex */
public final class C2335h implements InterfaceC2556cn<AppUpdateManager> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2332e> f306a;

    public C2335h(InterfaceC2556cn<C2332e> interfaceC2556cn) {
        this.f306a = interfaceC2556cn;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ AppUpdateManager mo473a() {
        C2332e mo473a = this.f306a.mo473a();
        C2532bq.m736b(mo473a);
        return mo473a;
    }
}
