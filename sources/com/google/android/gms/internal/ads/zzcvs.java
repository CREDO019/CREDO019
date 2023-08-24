package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvs implements zzbbm {
    private zzcmn zza;
    private final Executor zzb;
    private final zzcve zzc;
    private final Clock zzd;
    private boolean zze = false;
    private boolean zzf = false;
    private final zzcvh zzg = new zzcvh();

    public zzcvs(Executor executor, zzcve zzcveVar, Clock clock) {
        this.zzb = executor;
        this.zzc = zzcveVar;
        this.zzd = clock;
    }

    private final void zzg() {
        try {
            final JSONObject zzb = this.zzc.zzb(this.zzg);
            if (this.zza != null) {
                this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcvr
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcvs.this.zzd(zzb);
                    }
                });
            }
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzb("Failed to call video active view js", e);
        }
    }

    public final void zza() {
        this.zze = false;
    }

    public final void zzb() {
        this.zze = true;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        zzcvh zzcvhVar = this.zzg;
        zzcvhVar.zza = this.zzf ? false : zzbblVar.zzj;
        zzcvhVar.zzd = this.zzd.elapsedRealtime();
        this.zzg.zzf = zzbblVar;
        if (this.zze) {
            zzg();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(JSONObject jSONObject) {
        this.zza.zzl("AFMA_updateActiveView", jSONObject);
    }

    public final void zze(boolean z) {
        this.zzf = z;
    }

    public final void zzf(zzcmn zzcmnVar) {
        this.zza = zzcmnVar;
    }
}
