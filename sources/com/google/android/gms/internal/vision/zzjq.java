package com.google.android.gms.internal.vision;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzjq implements Iterator<String> {
    private final /* synthetic */ zzjo zzaan;
    private Iterator<String> zzabj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjq(zzjo zzjoVar) {
        zzhj zzhjVar;
        this.zzaan = zzjoVar;
        zzhjVar = zzjoVar.zzaao;
        this.zzabj = zzhjVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzabj.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zzabj.next();
    }
}
