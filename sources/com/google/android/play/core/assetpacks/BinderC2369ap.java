package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.ap */
/* loaded from: classes3.dex */
final class BinderC2369ap extends BinderC2364ak<AssetPackStates> {

    /* renamed from: c */
    private final C2406bz f427c;

    /* renamed from: d */
    private final InterfaceC2379az f428d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2369ap(C2371ar c2371ar, C2682i<AssetPackStates> c2682i, C2406bz c2406bz, InterfaceC2379az interfaceC2379az) {
        super(c2371ar, c2682i);
        this.f427c = c2406bz;
        this.f428d = interfaceC2379az;
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: c */
    public final void mo651c(Bundle bundle, Bundle bundle2) {
        super.mo651c(bundle, bundle2);
        this.f419a.m454b((C2682i<T>) AssetPackStates.m1034a(bundle, this.f427c, this.f428d));
    }
}
