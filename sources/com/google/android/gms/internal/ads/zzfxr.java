package com.google.android.gms.internal.ads;

import androidx.concurrent.futures.C0255xc40028dd;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfxr extends zzfxq {
    final AtomicReferenceFieldUpdater zza;
    final AtomicIntegerFieldUpdater zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxr(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
        super(null);
        this.zza = atomicReferenceFieldUpdater;
        this.zzb = atomicIntegerFieldUpdater;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfxq
    public final int zza(zzfxt zzfxtVar) {
        return this.zzb.decrementAndGet(zzfxtVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfxq
    public final void zzb(zzfxt zzfxtVar, @CheckForNull Set set, Set set2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = this.zza;
        while (!C0255xc40028dd.m1422m(atomicReferenceFieldUpdater, zzfxtVar, null, set2) && atomicReferenceFieldUpdater.get(zzfxtVar) == null) {
        }
    }
}
