package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzexy implements zzeoe {
    protected final zzcok zza;
    private final Context zzb;
    private final Executor zzc;
    private final zzeyo zzd;
    private final zzfah zze;
    private final zzcgt zzf;
    private final ViewGroup zzg;
    private final zzfje zzh;
    private final zzfdl zzi;
    @Nullable
    private zzfyx zzj;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzexy(Context context, Executor executor, zzcok zzcokVar, zzfah zzfahVar, zzeyo zzeyoVar, zzfdl zzfdlVar, zzcgt zzcgtVar) {
        this.zzb = context;
        this.zzc = executor;
        this.zza = zzcokVar;
        this.zze = zzfahVar;
        this.zzd = zzeyoVar;
        this.zzi = zzfdlVar;
        this.zzf = zzcgtVar;
        this.zzg = new FrameLayout(context);
        this.zzh = zzcokVar.zzy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized zzdcg zzm(zzfaf zzfafVar) {
        zzexx zzexxVar = (zzexx) zzfafVar;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgU)).booleanValue()) {
            zzcwx zzcwxVar = new zzcwx(this.zzg);
            zzdci zzdciVar = new zzdci();
            zzdciVar.zzc(this.zzb);
            zzdciVar.zzf(zzexxVar.zza);
            zzdck zzg = zzdciVar.zzg();
            zzdii zzdiiVar = new zzdii();
            zzdiiVar.zzc(this.zzd, this.zzc);
            zzdiiVar.zzl(this.zzd, this.zzc);
            return zzc(zzcwxVar, zzg, zzdiiVar.zzn());
        }
        zzeyo zzi = zzeyo.zzi(this.zzd);
        zzdii zzdiiVar2 = new zzdii();
        zzdiiVar2.zzb(zzi, this.zzc);
        zzdiiVar2.zzg(zzi, this.zzc);
        zzdiiVar2.zzh(zzi, this.zzc);
        zzdiiVar2.zzi(zzi, this.zzc);
        zzdiiVar2.zzc(zzi, this.zzc);
        zzdiiVar2.zzl(zzi, this.zzc);
        zzdiiVar2.zzm(zzi);
        zzcwx zzcwxVar2 = new zzcwx(this.zzg);
        zzdci zzdciVar2 = new zzdci();
        zzdciVar2.zzc(this.zzb);
        zzdciVar2.zzf(zzexxVar.zza);
        return zzc(zzcwxVar2, zzdciVar2.zzg(), zzdiiVar2.zzn());
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zza() {
        zzfyx zzfyxVar = this.zzj;
        return (zzfyxVar == null || zzfyxVar.isDone()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0045 A[Catch: all -> 0x00f4, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:9:0x0026, B:14:0x0045, B:17:0x0056, B:21:0x005c, B:23:0x006c, B:25:0x0074, B:27:0x0089, B:29:0x00a2, B:31:0x00a6, B:32:0x00af, B:12:0x003e), top: B:38:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0056 A[Catch: all -> 0x00f4, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:9:0x0026, B:14:0x0045, B:17:0x0056, B:21:0x005c, B:23:0x006c, B:25:0x0074, B:27:0x0089, B:29:0x00a2, B:31:0x00a6, B:32:0x00af, B:12:0x003e), top: B:38:0x0001 }] */
    @Override // com.google.android.gms.internal.ads.zzeoe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean zzb(com.google.android.gms.ads.internal.client.zzl r8, java.lang.String r9, com.google.android.gms.internal.ads.zzeoc r10, com.google.android.gms.internal.ads.zzeod r11) throws android.os.RemoteException {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzexy.zzb(com.google.android.gms.ads.internal.client.zzl, java.lang.String, com.google.android.gms.internal.ads.zzeoc, com.google.android.gms.internal.ads.zzeod):boolean");
    }

    protected abstract zzdcg zzc(zzcwx zzcwxVar, zzdck zzdckVar, zzdik zzdikVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzk() {
        this.zzd.zza(zzfem.zzd(6, null, null));
    }

    public final void zzl(com.google.android.gms.ads.internal.client.zzw zzwVar) {
        this.zzi.zzt(zzwVar);
    }
}
