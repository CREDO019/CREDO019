package com.google.android.play.core.splitinstall.testing;

import android.content.Intent;
import com.google.android.play.core.splitinstall.InterfaceC2639d;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.testing.i */
/* loaded from: classes3.dex */
public final class C2665i implements InterfaceC2639d {

    /* renamed from: a */
    final /* synthetic */ List f1066a;

    /* renamed from: b */
    final /* synthetic */ List f1067b;

    /* renamed from: c */
    final /* synthetic */ long f1068c;

    /* renamed from: d */
    final /* synthetic */ boolean f1069d;

    /* renamed from: e */
    final /* synthetic */ List f1070e;

    /* renamed from: f */
    final /* synthetic */ FakeSplitInstallManager f1071f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2665i(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, long j, boolean z, List list3) {
        this.f1071f = fakeSplitInstallManager;
        this.f1066a = list;
        this.f1067b = list2;
        this.f1068c = j;
        this.f1069d = z;
        this.f1070e = list3;
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: a */
    public final void mo483a() {
        Set set;
        Set set2;
        set = this.f1071f.f1036l;
        set.addAll(this.f1066a);
        set2 = this.f1071f.f1037m;
        set2.addAll(this.f1067b);
        this.f1071f.m502a(5, 0, Long.valueOf(this.f1068c), Long.valueOf(this.f1068c), null, null, null);
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: a */
    public final void mo482a(int r2) {
        this.f1071f.m503a(r2);
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2639d
    /* renamed from: b */
    public final void mo481b() {
        if (this.f1069d) {
            return;
        }
        this.f1071f.m489a((List<Intent>) this.f1070e, (List<String>) this.f1066a, (List<String>) this.f1067b, this.f1068c, true);
    }
}
