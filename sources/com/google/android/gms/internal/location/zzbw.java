package com.google.android.gms.internal.location;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
public final class zzbw extends zzbx {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbx zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbw(zzbx zzbxVar, int r2, int r3) {
        this.zzc = zzbxVar;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzbr.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.location.zzbx, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.location.zzbx
    public final zzbx zzh(int r3, int r4) {
        zzbr.zzc(r3, r4, this.zzb);
        zzbx zzbxVar = this.zzc;
        int r1 = this.zza;
        return zzbxVar.subList(r3 + r1, r4 + r1);
    }
}
