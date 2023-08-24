package com.google.android.gms.internal.common;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
public final class zzaf extends zzag {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzag zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(zzag zzagVar, int r2, int r3) {
        this.zzc = zzagVar;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzs.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.common.zzag, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.common.zzag
    public final zzag zzh(int r3, int r4) {
        zzs.zzc(r3, r4, this.zzb);
        zzag zzagVar = this.zzc;
        int r1 = this.zza;
        return zzagVar.subList(r3 + r1, r4 + r1);
    }
}
