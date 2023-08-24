package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.C2682i;
import java.util.List;

/* renamed from: com.google.android.play.core.assetpacks.aq */
/* loaded from: classes3.dex */
final class BinderC2370aq extends BinderC2364ak<AssetPackStates> {

    /* renamed from: c */
    private final List<String> f429c;

    /* renamed from: d */
    private final C2406bz f430d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2370aq(C2371ar c2371ar, C2682i<AssetPackStates> c2682i, C2406bz c2406bz, List<String> list) {
        super(c2371ar, c2682i);
        this.f430d = c2406bz;
        this.f429c = list;
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public final void mo660a(int r3, Bundle bundle) {
        super.mo660a(r3, bundle);
        this.f419a.m454b((C2682i<T>) AssetPackStates.m1033a(bundle, this.f430d, this.f429c));
    }
}
