package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zztd implements zzwp, zzrv {
    final /* synthetic */ zzti zza;
    private final Uri zzc;
    private final zzfw zzd;
    private final zzsz zze;
    private final zzzi zzf;
    private final zzdg zzg;
    private volatile boolean zzi;
    private long zzk;
    private zzaam zzn;
    private boolean zzo;
    private final zzaaf zzh = new zzaaf();
    private boolean zzj = true;
    private long zzm = -1;
    private final long zzb = zzrx.zza();
    private zzfa zzl = zzj(0);

    public zztd(zzti zztiVar, Uri uri, zzev zzevVar, zzsz zzszVar, zzzi zzziVar, zzdg zzdgVar) {
        this.zza = zztiVar;
        this.zzc = uri;
        this.zzd = new zzfw(zzevVar);
        this.zze = zzszVar;
        this.zzf = zzziVar;
        this.zzg = zzdgVar;
    }

    public static /* bridge */ /* synthetic */ long zzb(zztd zztdVar) {
        return zztdVar.zzm;
    }

    public static /* bridge */ /* synthetic */ long zzc(zztd zztdVar) {
        return zztdVar.zzb;
    }

    public static /* bridge */ /* synthetic */ long zzd(zztd zztdVar) {
        return zztdVar.zzk;
    }

    public static /* bridge */ /* synthetic */ zzfa zze(zztd zztdVar) {
        return zztdVar.zzl;
    }

    public static /* bridge */ /* synthetic */ zzfw zzf(zztd zztdVar) {
        return zztdVar.zzd;
    }

    public static /* bridge */ /* synthetic */ void zzg(zztd zztdVar, long j, long j2) {
        zztdVar.zzh.zza = j;
        zztdVar.zzk = j2;
        zztdVar.zzj = true;
        zztdVar.zzo = false;
    }

    private final zzfa zzj(long j) {
        Map map;
        zzey zzeyVar = new zzey();
        zzeyVar.zzd(this.zzc);
        zzeyVar.zzc(j);
        zzeyVar.zza(6);
        map = zzti.zzb;
        zzeyVar.zzb(map);
        return zzeyVar.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzwp
    public final void zzh() {
        this.zzi = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:145:0x0083 A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0099 A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00af A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x00c5 A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x00e1 A[Catch: all -> 0x01e3, TRY_LEAVE, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0116 A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0129 A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x016c A[Catch: all -> 0x01e3, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0175 A[Catch: all -> 0x01e3, TRY_LEAVE, TryCatch #3 {all -> 0x01e3, blocks: (B:126:0x000c, B:128:0x0022, B:129:0x0025, B:132:0x003a, B:133:0x0040, B:143:0x0078, B:145:0x0083, B:147:0x008f, B:149:0x0099, B:151:0x00a5, B:153:0x00af, B:155:0x00bb, B:157:0x00c5, B:159:0x00d7, B:161:0x00e1, B:162:0x00e7, B:172:0x0116, B:173:0x011c, B:175:0x0129, B:177:0x0131, B:179:0x014d, B:181:0x016c, B:182:0x0171, B:184:0x0175, B:165:0x00ef, B:169:0x0106, B:137:0x004a, B:141:0x0064), top: B:223:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0182 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0200 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x01c3 A[EDGE_INSN: B:233:0x01c3->B:198:0x01c3 ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzwp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztd.zzi():void");
    }

    @Override // com.google.android.gms.internal.ads.zzrv
    public final void zza(zzed zzedVar) {
        long zzO;
        long max;
        if (this.zzo) {
            zzO = this.zza.zzO();
            max = Math.max(zzO, this.zzk);
        } else {
            max = this.zzk;
        }
        int zza = zzedVar.zza();
        zzaam zzaamVar = this.zzn;
        Objects.requireNonNull(zzaamVar);
        zzaak.zzb(zzaamVar, zzedVar, zza);
        zzaamVar.zzs(max, 1, zza, 0, null);
        this.zzo = true;
    }
}
