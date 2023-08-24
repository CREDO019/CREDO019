package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.work.WorkRequest;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbsu implements zzbsm, zzbsk {
    private final zzcmn zza;

    public zzbsu(Context context, zzcgt zzcgtVar, zzapb zzapbVar, com.google.android.gms.ads.internal.zza zzaVar) throws zzcmy {
        com.google.android.gms.ads.internal.zzt.zzA();
        zzcmn zza = zzcmz.zza(context, zzcoc.zza(), "", false, false, null, null, zzcgtVar, null, null, null, zzbel.zza(), null, null);
        this.zza = zza;
        ((View) zza).setWillNotDraw(true);
    }

    private static final void zzs(Runnable runnable) {
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        if (zzcgg.zzt()) {
            runnable.run();
        } else {
            com.google.android.gms.ads.internal.util.zzs.zza.post(runnable);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final void zza(final String str) {
        zzs(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbsp
            @Override // java.lang.Runnable
            public final void run() {
                zzbsu.this.zzm(str);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final /* synthetic */ void zzb(String str, String str2) {
        zzbsj.zzc(this, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final void zzc() {
        this.zza.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final /* synthetic */ void zzd(String str, Map map) {
        zzbsj.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzbsi
    public final /* synthetic */ void zze(String str, JSONObject jSONObject) {
        zzbsj.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final void zzf(final String str) {
        zzs(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbsq
            @Override // java.lang.Runnable
            public final void run() {
                zzbsu.this.zzn(str);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final void zzg(final String str) {
        zzs(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbss
            @Override // java.lang.Runnable
            public final void run() {
                zzbsu.this.zzo(str);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final void zzh(String str) {
        final String format = String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", str);
        zzs(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbsr
            @Override // java.lang.Runnable
            public final void run() {
                zzbsu.this.zzp(format);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final boolean zzi() {
        return this.zza.zzaB();
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final zzbtt zzj() {
        return new zzbtt(this);
    }

    @Override // com.google.android.gms.internal.ads.zzbsm
    public final void zzk(final zzbta zzbtaVar) {
        this.zza.zzP().zzF(new zzcnz(null) { // from class: com.google.android.gms.internal.ads.zzbsn
            @Override // com.google.android.gms.internal.ads.zzcnz
            public final void zza() {
                zzbta zzbtaVar2 = zzbta.this;
                final zzbtr zzbtrVar = zzbtaVar2.zza;
                final zzbtq zzbtqVar = zzbtaVar2.zzb;
                final zzbsm zzbsmVar = zzbtaVar2.zzc;
                com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbsz
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbtr.this.zzi(zzbtqVar, zzbsmVar);
                    }
                }, WorkRequest.MIN_BACKOFF_MILLIS);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbsv
    public final /* synthetic */ void zzl(String str, JSONObject jSONObject) {
        zzbsj.zzd(this, str, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(String str) {
        this.zza.zza(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzn(String str) {
        this.zza.loadData(str, "text/html", "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(String str) {
        this.zza.loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(String str) {
        this.zza.loadData(str, "text/html", "UTF-8");
    }

    @Override // com.google.android.gms.internal.ads.zzbts
    public final void zzq(String str, zzbpq zzbpqVar) {
        this.zza.zzaf(str, new zzbst(this, zzbpqVar));
    }

    @Override // com.google.android.gms.internal.ads.zzbts
    public final void zzr(String str, final zzbpq zzbpqVar) {
        this.zza.zzax(str, new Predicate() { // from class: com.google.android.gms.internal.ads.zzbso
            @Override // com.google.android.gms.common.util.Predicate
            public final boolean apply(Object obj) {
                zzbpq zzbpqVar2;
                zzbpq zzbpqVar3 = zzbpq.this;
                zzbpq zzbpqVar4 = (zzbpq) obj;
                if (zzbpqVar4 instanceof zzbst) {
                    zzbpqVar2 = ((zzbst) zzbpqVar4).zzb;
                    return zzbpqVar2.equals(zzbpqVar3);
                }
                return false;
            }
        });
    }
}
