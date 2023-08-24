package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public abstract class zzckz implements Releasable {
    protected final Context zza;
    protected final String zzb;
    protected final WeakReference zzc;

    public zzckz(zzciw zzciwVar) {
        Context context = zzciwVar.getContext();
        this.zza = context;
        this.zzb = com.google.android.gms.ads.internal.zzt.zzq().zzc(context, zzciwVar.zzp().zza);
        this.zzc = new WeakReference(zzciwVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zza(zzckz zzckzVar, String str, Map map) {
        zzciw zzciwVar = (zzciw) zzckzVar.zzc.get();
        if (zzciwVar != null) {
            zzciwVar.zzd("onPrecacheEvent", map);
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
    }

    public abstract void zzb();

    public final void zzc(String str, String str2, String str3, String str4) {
        zzcgg.zza.post(new zzcky(this, str, str2, str3, str4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzd(String str, String str2, int r5) {
        zzcgg.zza.post(new zzckw(this, str, str2, r5));
    }

    public final void zze(String str, String str2, long j) {
        zzcgg.zza.post(new zzckx(this, str, str2, j));
    }

    public final void zzf(String str, String str2, int r18, int r19, long j, long j2, boolean z, int r25, int r26) {
        zzcgg.zza.post(new zzckv(this, str, str2, r18, r19, j, j2, z, r25, r26));
    }

    public final void zzg(String str, String str2, long j, long j2, boolean z, long j3, long j4, long j5, int r34, int r35) {
        zzcgg.zza.post(new zzcku(this, str, str2, j, j2, j3, j4, j5, z, r34, r35));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzh(int r1) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzn(int r1) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzo(int r1) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzp(int r1) {
    }

    public abstract boolean zzq(String str);

    public boolean zzr(String str, String[] strArr) {
        return zzq(str);
    }

    public boolean zzs(String str, String[] strArr, zzckr zzckrVar) {
        return zzq(str);
    }
}
