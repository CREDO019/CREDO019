package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfit implements zzfir {
    private final Context zza;
    private final int zzl;
    private long zzb = 0;
    private long zzc = -1;
    private boolean zzd = false;
    private int zzm = 2;
    private int zzn = 2;
    private int zze = 0;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private boolean zzj = false;
    private boolean zzk = false;

    public zzfit(Context context, int r4) {
        this.zza = context;
        this.zzl = r4;
    }

    public final synchronized zzfit zzA(int r1) {
        this.zzm = r1;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzo(zzeVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzb(zzfdd zzfddVar) {
        zzp(zzfddVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzc(String str) {
        zzq(str);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzd(String str) {
        zzr(str);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zze(boolean z) {
        zzs(z);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzf() {
        zzt();
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzg() {
        zzu();
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final synchronized boolean zzh() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final boolean zzi() {
        return !TextUtils.isEmpty(this.zzh);
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final synchronized zzfiv zzj() {
        if (this.zzj) {
            return null;
        }
        this.zzj = true;
        if (!this.zzk) {
            zzt();
        }
        if (this.zzc < 0) {
            zzu();
        }
        return new zzfiv(this, null);
    }

    @Override // com.google.android.gms.internal.ads.zzfir
    public final /* bridge */ /* synthetic */ zzfir zzk(int r1) {
        zzA(r1);
        return this;
    }

    public final synchronized zzfit zzo(com.google.android.gms.ads.internal.client.zze zzeVar) {
        IBinder iBinder = zzeVar.zze;
        if (iBinder == null) {
            return this;
        }
        zzdcr zzdcrVar = (zzdcr) iBinder;
        String zzj = zzdcrVar.zzj();
        if (!TextUtils.isEmpty(zzj)) {
            this.zzf = zzj;
        }
        String zzh = zzdcrVar.zzh();
        if (!TextUtils.isEmpty(zzh)) {
            this.zzg = zzh;
        }
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x002b, code lost:
        r2.zzg = r0.zzac;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized com.google.android.gms.internal.ads.zzfit zzp(com.google.android.gms.internal.ads.zzfdd r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzfcv r0 = r3.zzb     // Catch: java.lang.Throwable -> L31
            java.lang.String r0 = r0.zzb     // Catch: java.lang.Throwable -> L31
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L31
            if (r0 != 0) goto L11
            com.google.android.gms.internal.ads.zzfcv r0 = r3.zzb     // Catch: java.lang.Throwable -> L31
            java.lang.String r0 = r0.zzb     // Catch: java.lang.Throwable -> L31
            r2.zzf = r0     // Catch: java.lang.Throwable -> L31
        L11:
            java.util.List r3 = r3.zza     // Catch: java.lang.Throwable -> L31
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L31
        L17:
            boolean r0 = r3.hasNext()     // Catch: java.lang.Throwable -> L31
            if (r0 == 0) goto L2f
            java.lang.Object r0 = r3.next()     // Catch: java.lang.Throwable -> L31
            com.google.android.gms.internal.ads.zzfcs r0 = (com.google.android.gms.internal.ads.zzfcs) r0     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = r0.zzac     // Catch: java.lang.Throwable -> L31
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L31
            if (r1 != 0) goto L17
            java.lang.String r3 = r0.zzac     // Catch: java.lang.Throwable -> L31
            r2.zzg = r3     // Catch: java.lang.Throwable -> L31
        L2f:
            monitor-exit(r2)
            return r2
        L31:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfit.zzp(com.google.android.gms.internal.ads.zzfdd):com.google.android.gms.internal.ads.zzfit");
    }

    public final synchronized zzfit zzq(String str) {
        this.zzh = str;
        return this;
    }

    public final synchronized zzfit zzr(String str) {
        this.zzi = str;
        return this;
    }

    public final synchronized zzfit zzs(boolean z) {
        this.zzd = z;
        return this;
    }

    public final synchronized zzfit zzt() {
        Configuration configuration;
        this.zze = com.google.android.gms.ads.internal.zzt.zzr().zzl(this.zza);
        Resources resources = this.zza.getResources();
        int r1 = 2;
        if (resources != null && (configuration = resources.getConfiguration()) != null) {
            r1 = configuration.orientation == 2 ? 4 : 3;
        }
        this.zzn = r1;
        this.zzb = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        this.zzk = true;
        return this;
    }

    public final synchronized zzfit zzu() {
        this.zzc = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        return this;
    }
}
