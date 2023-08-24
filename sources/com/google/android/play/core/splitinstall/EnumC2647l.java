package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* renamed from: com.google.android.play.core.splitinstall.l */
/* loaded from: classes3.dex */
public final class EnumC2647l implements InterfaceC2640e {

    /* renamed from: a */
    public static final EnumC2647l f1005a = new EnumC2647l();

    /* renamed from: b */
    private static final AtomicReference<InterfaceC2641f> f1006b = new AtomicReference<>(null);

    private EnumC2647l() {
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2640e
    /* renamed from: a */
    public final InterfaceC2641f mo524a() {
        return f1006b.get();
    }

    /* renamed from: a */
    public final void m523a(InterfaceC2641f interfaceC2641f) {
        f1006b.set(interfaceC2641f);
    }
}
