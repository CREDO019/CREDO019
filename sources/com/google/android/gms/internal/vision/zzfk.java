package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzfk extends zzfm {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzfh zzsa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfk(zzfh zzfhVar) {
        this.zzsa = zzfhVar;
        this.limit = zzfhVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.vision.zzfq
    public final byte nextByte() {
        int r0 = this.position;
        if (r0 >= this.limit) {
            throw new NoSuchElementException();
        }
        this.position = r0 + 1;
        return this.zzsa.zzao(r0);
    }
}
