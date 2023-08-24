package com.google.android.play.core.splitcompat;

import com.google.android.play.core.splitinstall.InterfaceC2650n;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.l */
/* loaded from: classes3.dex */
public final class C2604l implements InterfaceC2650n {

    /* renamed from: a */
    final /* synthetic */ SplitCompat f922a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2604l(SplitCompat splitCompat) {
        this.f922a = splitCompat;
    }

    @Override // com.google.android.play.core.splitinstall.InterfaceC2650n
    /* renamed from: a */
    public final Set<String> mo519a() {
        Set<String> m611c;
        m611c = this.f922a.m611c();
        return m611c;
    }
}
