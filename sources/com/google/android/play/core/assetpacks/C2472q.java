package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.internal.C2532bq;
import com.google.android.play.core.internal.InterfaceC2556cn;

/* renamed from: com.google.android.play.core.assetpacks.q */
/* loaded from: classes3.dex */
public final class C2472q implements InterfaceC2556cn<AssetPackManager> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2464j> f777a;

    /* renamed from: b */
    private final InterfaceC2556cn<Context> f778b;

    public C2472q(InterfaceC2556cn<C2464j> interfaceC2556cn, InterfaceC2556cn<Context> interfaceC2556cn2) {
        this.f777a = interfaceC2556cn;
        this.f778b = interfaceC2556cn2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ AssetPackManager mo473a() {
        C2464j mo473a = this.f777a.mo473a();
        Context mo473a2 = ((C2474s) this.f778b).mo473a();
        C2464j c2464j = mo473a;
        C2532bq.m750a(mo473a2.getPackageManager(), new ComponentName(mo473a2.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"), 4);
        PlayCoreDialogWrapperActivity.m824a(mo473a2);
        C2532bq.m736b(c2464j);
        return c2464j;
    }
}
