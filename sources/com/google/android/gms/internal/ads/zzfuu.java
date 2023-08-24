package com.google.android.gms.internal.ads;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfuu extends zzfuv {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzfuv zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuu(zzfuv zzfuvVar, int r2, int r3) {
        this.zzc = zzfuvVar;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzfsf.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzfuv, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzfuv
    public final zzfuv zzh(int r3, int r4) {
        zzfsf.zzg(r3, r4, this.zzb);
        zzfuv zzfuvVar = this.zzc;
        int r1 = this.zza;
        return zzfuvVar.subList(r3 + r1, r4 + r1);
    }
}
