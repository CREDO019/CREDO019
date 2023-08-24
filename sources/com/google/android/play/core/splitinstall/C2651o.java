package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.play.core.splitinstall.o */
/* loaded from: classes3.dex */
public final class C2651o {

    /* renamed from: a */
    private static final AtomicReference<InterfaceC2650n> f1009a = new AtomicReference<>(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static InterfaceC2650n m518a() {
        return f1009a.get();
    }

    /* renamed from: a */
    public static void m517a(InterfaceC2650n interfaceC2650n) {
        f1009a.compareAndSet(null, interfaceC2650n);
    }
}
