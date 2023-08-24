package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.io.File;

/* renamed from: com.google.android.play.core.splitinstall.aa */
/* loaded from: classes3.dex */
public final class C2611aa implements InterfaceC2556cn<File> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f945a;

    public C2611aa(InterfaceC2556cn<Context> interfaceC2556cn) {
        this.f945a = interfaceC2556cn;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ File mo473a() {
        return C2672y.m475a(((C2673z) this.f945a).mo473a());
    }
}
