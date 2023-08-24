package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzvn extends zzvk {
    private final boolean zze;
    private final zzvc zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final boolean zzm;
    private final int zzn;
    private final boolean zzo;
    private final boolean zzp;
    private final int zzq;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0031, code lost:
        if (r2 <= 2.14748365E9f) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0048, code lost:
        if (r1 >= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0050, code lost:
        if (r1 >= 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x005d, code lost:
        if (r1 >= 0.0f) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0065, code lost:
        if (r7 >= 0) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x00af A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzvn(int r5, com.google.android.gms.internal.ads.zzcp r6, int r7, com.google.android.gms.internal.ads.zzvc r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvn.<init>(int, com.google.android.gms.internal.ads.zzcp, int, com.google.android.gms.internal.ads.zzvc, int, int, boolean):void");
    }

    public static /* synthetic */ int zza(zzvn zzvnVar, zzvn zzvnVar2) {
        zzfwd zzfwdVar;
        zzfwd zza;
        zzfwd zzfwdVar2;
        if (!zzvnVar.zze || !zzvnVar.zzh) {
            zzfwdVar = zzvo.zzc;
            zza = zzfwdVar.zza();
        } else {
            zza = zzvo.zzc;
        }
        zzfuk zzj = zzfuk.zzj();
        Integer valueOf = Integer.valueOf(zzvnVar.zzi);
        Integer valueOf2 = Integer.valueOf(zzvnVar2.zzi);
        boolean z = zzvnVar.zzf.zzz;
        zzfwdVar2 = zzvo.zzd;
        return zzj.zzc(valueOf, valueOf2, zzfwdVar2).zzc(Integer.valueOf(zzvnVar.zzj), Integer.valueOf(zzvnVar2.zzj), zza).zzc(Integer.valueOf(zzvnVar.zzi), Integer.valueOf(zzvnVar2.zzi), zza).zza();
    }

    public static /* synthetic */ int zzd(zzvn zzvnVar, zzvn zzvnVar2) {
        zzfuk zzb = zzfuk.zzj().zzd(zzvnVar.zzh, zzvnVar2.zzh).zzb(zzvnVar.zzl, zzvnVar2.zzl);
        boolean z = zzvnVar.zzm;
        boolean z2 = zzvnVar2.zzm;
        zzfuk zzc = zzb.zzd(true, true).zzd(zzvnVar.zze, zzvnVar2.zze).zzd(zzvnVar.zzg, zzvnVar2.zzg).zzc(Integer.valueOf(zzvnVar.zzk), Integer.valueOf(zzvnVar2.zzk), zzfwd.zzc().zza());
        boolean z3 = zzvnVar.zzo;
        zzfuk zzd = zzc.zzd(z3, zzvnVar2.zzo);
        boolean z4 = zzvnVar.zzp;
        zzfuk zzd2 = zzd.zzd(z4, zzvnVar2.zzp);
        if (z3 && z4) {
            zzd2 = zzd2.zzb(zzvnVar.zzq, zzvnVar2.zzq);
        }
        return zzd2.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final int zzb() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final /* bridge */ /* synthetic */ boolean zzc(zzvk zzvkVar) {
        zzvn zzvnVar = (zzvn) zzvkVar;
        if (zzel.zzT(this.zzd.zzm, zzvnVar.zzd.zzm)) {
            boolean z = this.zzf.zzJ;
            return this.zzo == zzvnVar.zzo && this.zzp == zzvnVar.zzp;
        }
        return false;
    }
}
