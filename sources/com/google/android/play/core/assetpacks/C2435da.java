package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.C2554cl;
import com.google.android.play.core.internal.InterfaceC2556cn;
import java.io.File;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.play.core.assetpacks.da */
/* loaded from: classes3.dex */
public final class C2435da implements InterfaceC2556cn<C2433cz> {

    /* renamed from: a */
    private final InterfaceC2556cn<String> f662a;

    /* renamed from: b */
    private final InterfaceC2556cn<C2376aw> f663b;

    /* renamed from: c */
    private final InterfaceC2556cn<C2406bz> f664c;

    /* renamed from: d */
    private final InterfaceC2556cn<Context> f665d;

    /* renamed from: e */
    private final InterfaceC2556cn<C2446dl> f666e;

    /* renamed from: f */
    private final InterfaceC2556cn<Executor> f667f;

    public C2435da(InterfaceC2556cn<String> interfaceC2556cn, InterfaceC2556cn<C2376aw> interfaceC2556cn2, InterfaceC2556cn<C2406bz> interfaceC2556cn3, InterfaceC2556cn<Context> interfaceC2556cn4, InterfaceC2556cn<C2446dl> interfaceC2556cn5, InterfaceC2556cn<Executor> interfaceC2556cn6) {
        this.f662a = interfaceC2556cn;
        this.f663b = interfaceC2556cn2;
        this.f664c = interfaceC2556cn3;
        this.f665d = interfaceC2556cn4;
        this.f666e = interfaceC2556cn5;
        this.f667f = interfaceC2556cn6;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2556cn
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C2433cz mo473a() {
        String mo473a = this.f662a.mo473a();
        C2376aw mo473a2 = this.f663b.mo473a();
        C2406bz mo473a3 = this.f664c.mo473a();
        Context mo473a4 = ((C2474s) this.f665d).mo473a();
        C2446dl mo473a5 = this.f666e.mo473a();
        return new C2433cz(mo473a != null ? new File(mo473a4.getExternalFilesDir(null), mo473a) : mo473a4.getExternalFilesDir(null), mo473a2, mo473a3, mo473a4, mo473a5, C2554cl.m710b(this.f667f));
    }
}
