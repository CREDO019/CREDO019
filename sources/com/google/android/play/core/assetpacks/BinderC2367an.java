package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.google.android.play.core.assetpacks.an */
/* loaded from: classes3.dex */
final class BinderC2367an extends BinderC2364ak<Void> {

    /* renamed from: c */
    final /* synthetic */ C2371ar f422c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinderC2367an(C2371ar c2371ar, C2682i<Void> c2682i) {
        super(c2371ar, c2682i);
        this.f422c = c2371ar;
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public final void mo658a(Bundle bundle, Bundle bundle2) {
        AtomicBoolean atomicBoolean;
        C2494af c2494af;
        super.mo658a(bundle, bundle2);
        atomicBoolean = this.f422c.f437g;
        if (!atomicBoolean.compareAndSet(true, false)) {
            c2494af = C2371ar.f431a;
            c2494af.m804d("Expected keepingAlive to be true, but was false.", new Object[0]);
        }
        if (bundle.getBoolean("keep_alive")) {
            this.f422c.mo834a();
        }
    }
}
