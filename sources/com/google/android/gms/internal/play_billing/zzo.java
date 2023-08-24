package com.google.android.gms.internal.play_billing;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes3.dex */
public final class zzo extends zzp {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzp zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzp zzpVar, int r2, int r3) {
        this.zzc = zzpVar;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzj.zza(r3, this.zzb, "index");
        return this.zzc.get(r3 + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzp, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int r1, int r2) {
        return subList(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzm
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzm
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzm
    public final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.play_billing.zzp
    public final zzp zzf(int r3, int r4) {
        zzj.zzc(r3, r4, this.zzb);
        zzp zzpVar = this.zzc;
        int r1 = this.zza;
        return zzpVar.subList(r3 + r1, r4 + r1);
    }
}
