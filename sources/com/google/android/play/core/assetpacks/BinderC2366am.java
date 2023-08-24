package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.C2682i;
import java.util.List;

/* renamed from: com.google.android.play.core.assetpacks.am */
/* loaded from: classes3.dex */
final class BinderC2366am extends BinderC2364ak<List<String>> {

    /* renamed from: c */
    final /* synthetic */ C2371ar f421c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinderC2366am(C2371ar c2371ar, C2682i<List<String>> c2682i) {
        super(c2371ar, c2682i);
        this.f421c = c2371ar;
    }

    @Override // com.google.android.play.core.assetpacks.BinderC2364ak, com.google.android.play.core.internal.InterfaceC2574u
    /* renamed from: a */
    public final void mo657a(List<Bundle> list) {
        super.mo657a(list);
        this.f419a.m454b((C2682i<T>) C2371ar.m1028a(this.f421c, list));
    }
}
