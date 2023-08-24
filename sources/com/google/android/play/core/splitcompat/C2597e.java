package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.e */
/* loaded from: classes3.dex */
public final class C2597e implements InterfaceC2600h {

    /* renamed from: a */
    final /* synthetic */ C2609q f907a;

    /* renamed from: b */
    final /* synthetic */ Set f908b;

    /* renamed from: c */
    final /* synthetic */ AtomicBoolean f909c;

    /* renamed from: d */
    final /* synthetic */ C2603k f910d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2597e(C2603k c2603k, C2609q c2609q, Set set, AtomicBoolean atomicBoolean) {
        this.f910d = c2603k;
        this.f907a = c2609q;
        this.f908b = set;
        this.f909c = atomicBoolean;
    }

    @Override // com.google.android.play.core.splitcompat.InterfaceC2600h
    /* renamed from: a */
    public final void mo585a(ZipFile zipFile, Set<C2602j> set) throws IOException {
        this.f910d.m578a(this.f907a, set, new C2596d(this));
    }
}
