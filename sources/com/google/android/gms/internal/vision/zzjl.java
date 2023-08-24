package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzjl extends zzjj<zzjm, zzjm> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final boolean zza(zzis zzisVar) {
        return false;
    }

    private static void zza(Object obj, zzjm zzjmVar) {
        ((zzgs) obj).zzwd = zzjmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final void zzg(Object obj) {
        ((zzgs) obj).zzwd.zzdp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ int zzr(zzjm zzjmVar) {
        return zzjmVar.zzgf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ int zzx(zzjm zzjmVar) {
        return zzjmVar.zzii();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ zzjm zzh(zzjm zzjmVar, zzjm zzjmVar2) {
        zzjm zzjmVar3 = zzjmVar;
        zzjm zzjmVar4 = zzjmVar2;
        return zzjmVar4.equals(zzjm.zzig()) ? zzjmVar3 : zzjm.zza(zzjmVar3, zzjmVar4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zzc(zzjm zzjmVar, zzkg zzkgVar) throws IOException {
        zzjmVar.zza(zzkgVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zza(zzjm zzjmVar, zzkg zzkgVar) throws IOException {
        zzjmVar.zzb(zzkgVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zzg(Object obj, zzjm zzjmVar) {
        zza(obj, zzjmVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ zzjm zzw(Object obj) {
        zzjm zzjmVar = ((zzgs) obj).zzwd;
        if (zzjmVar == zzjm.zzig()) {
            zzjm zzih = zzjm.zzih();
            zza(obj, zzih);
            return zzih;
        }
        return zzjmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ zzjm zzv(Object obj) {
        return ((zzgs) obj).zzwd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zzf(Object obj, zzjm zzjmVar) {
        zza(obj, zzjmVar);
    }

    @Override // com.google.android.gms.internal.vision.zzjj
    final /* synthetic */ zzjm zzn(zzjm zzjmVar) {
        zzjm zzjmVar2 = zzjmVar;
        zzjmVar2.zzdp();
        return zzjmVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ zzjm zzif() {
        return zzjm.zzih();
    }

    @Override // com.google.android.gms.internal.vision.zzjj
    final /* synthetic */ void zza(zzjm zzjmVar, int r2, zzjm zzjmVar2) {
        zzjmVar.zzb((r2 << 3) | 3, zzjmVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zza(zzjm zzjmVar, int r2, zzfh zzfhVar) {
        zzjmVar.zzb((r2 << 3) | 2, zzfhVar);
    }

    @Override // com.google.android.gms.internal.vision.zzjj
    final /* synthetic */ void zzb(zzjm zzjmVar, int r2, long j) {
        zzjmVar.zzb((r2 << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.vision.zzjj
    final /* synthetic */ void zzc(zzjm zzjmVar, int r2, int r3) {
        zzjmVar.zzb((r2 << 3) | 5, Integer.valueOf(r3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzjj
    public final /* synthetic */ void zza(zzjm zzjmVar, int r2, long j) {
        zzjmVar.zzb(r2 << 3, Long.valueOf(j));
    }
}
