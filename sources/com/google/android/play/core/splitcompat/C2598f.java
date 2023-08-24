package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipFile;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitcompat.f */
/* loaded from: classes3.dex */
public final class C2598f implements InterfaceC2600h {

    /* renamed from: a */
    final /* synthetic */ Set f911a;

    /* renamed from: b */
    final /* synthetic */ C2609q f912b;

    /* renamed from: c */
    final /* synthetic */ C2603k f913c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2598f(C2603k c2603k, Set set, C2609q c2609q) {
        this.f913c = c2603k;
        this.f911a = set;
        this.f912b = c2609q;
    }

    @Override // com.google.android.play.core.splitcompat.InterfaceC2600h
    /* renamed from: a */
    public final void mo585a(ZipFile zipFile, Set<C2602j> set) throws IOException {
        this.f911a.addAll(C2603k.m581a(this.f913c, set, this.f912b, zipFile));
    }
}
