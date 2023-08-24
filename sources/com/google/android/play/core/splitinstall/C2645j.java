package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import java.io.File;

/* renamed from: com.google.android.play.core.splitinstall.j */
/* loaded from: classes3.dex */
public final class C2645j implements InterfaceC2556cn<C2644i> {

    /* renamed from: a */
    private final InterfaceC2556cn<C2670w> f1001a;

    /* renamed from: b */
    private final InterfaceC2556cn<FakeSplitInstallManager> f1002b;

    /* renamed from: c */
    private final InterfaceC2556cn<File> f1003c;

    public C2645j(InterfaceC2556cn<C2670w> interfaceC2556cn, InterfaceC2556cn<FakeSplitInstallManager> interfaceC2556cn2, InterfaceC2556cn<File> interfaceC2556cn3) {
        this.f1001a = interfaceC2556cn;
        this.f1002b = interfaceC2556cn2;
        this.f1003c = interfaceC2556cn3;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2644i mo473a() {
        return new C2644i(C2554cl.m710b(this.f1001a), C2554cl.m710b(this.f1002b), C2554cl.m710b(this.f1003c));
    }
}
