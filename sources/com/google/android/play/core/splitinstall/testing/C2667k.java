package com.google.android.play.core.splitinstall.testing;

import android.content.Context;
import com.google.android.play.core.internal.InterfaceC2556cn;
import com.google.android.play.core.splitinstall.C2652p;
import com.google.android.play.core.splitinstall.C2673z;
import java.io.File;

/* renamed from: com.google.android.play.core.splitinstall.testing.k */
/* loaded from: classes3.dex */
public final class C2667k implements InterfaceC2556cn<FakeSplitInstallManager> {

    /* renamed from: a */
    private final InterfaceC2556cn<Context> f1072a;

    /* renamed from: b */
    private final InterfaceC2556cn<File> f1073b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2652p> f1074c;

    public C2667k(InterfaceC2556cn<Context> interfaceC2556cn, InterfaceC2556cn<File> interfaceC2556cn2, InterfaceC2556cn<C2652p> interfaceC2556cn3) {
        this.f1072a = interfaceC2556cn;
        this.f1073b = interfaceC2556cn2;
        this.f1074c = interfaceC2556cn3;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ FakeSplitInstallManager mo473a() {
        return new FakeSplitInstallManager(((C2673z) this.f1072a).mo473a(), this.f1073b.mo473a(), this.f1074c.mo473a());
    }
}
