package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvi implements zzbbm, zzddt, com.google.android.gms.ads.internal.overlay.zzo, zzdds {
    private final zzcvd zza;
    private final zzcve zzb;
    private final zzbur zzd;
    private final Executor zze;
    private final Clock zzf;
    private final Set zzc = new HashSet();
    private final AtomicBoolean zzg = new AtomicBoolean(false);
    private final zzcvh zzh = new zzcvh();
    private boolean zzi = false;
    private WeakReference zzj = new WeakReference(this);

    public zzcvi(zzbuo zzbuoVar, zzcve zzcveVar, Executor executor, zzcvd zzcvdVar, Clock clock) {
        this.zza = zzcvdVar;
        this.zzd = zzbuoVar.zza("google.afma.activeView.handleUpdate", zzbuc.zza, zzbuc.zza);
        this.zzb = zzcveVar;
        this.zze = executor;
        this.zzf = clock;
    }

    private final void zzk() {
        for (zzcmn zzcmnVar : this.zzc) {
            this.zza.zzf(zzcmnVar);
        }
        this.zza.zze();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzb() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzbK() {
        this.zzh.zzb = false;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final synchronized void zzbq(Context context) {
        this.zzh.zze = "u";
        zzg();
        zzk();
        this.zzi = true;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final synchronized void zzbr() {
        this.zzh.zzb = true;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final synchronized void zzbs(Context context) {
        this.zzh.zzb = true;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final synchronized void zzbt(Context context) {
        this.zzh.zzb = false;
        zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final synchronized void zzc(zzbbl zzbblVar) {
        zzcvh zzcvhVar = this.zzh;
        zzcvhVar.zza = zzbblVar.zzj;
        zzcvhVar.zzf = zzbblVar;
        zzg();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzf(int r1) {
    }

    public final synchronized void zzg() {
        if (this.zzj.get() == null) {
            zzj();
        } else if (!this.zzi && this.zzg.get()) {
            try {
                this.zzh.zzd = this.zzf.elapsedRealtime();
                final JSONObject zzb = this.zzb.zzb(this.zzh);
                for (final zzcmn zzcmnVar : this.zzc) {
                    this.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcvg
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzcmn.this.zzl("AFMA_updateActiveView", zzb);
                        }
                    });
                }
                zzchd.zzb(this.zzd.zzb(zzb), "ActiveViewListener.callActiveViewJs");
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.zze.zzb("Failed to call ActiveViewJS", e);
            }
        }
    }

    public final synchronized void zzh(zzcmn zzcmnVar) {
        this.zzc.add(zzcmnVar);
        this.zza.zzd(zzcmnVar);
    }

    public final void zzi(Object obj) {
        this.zzj = new WeakReference(obj);
    }

    public final synchronized void zzj() {
        zzk();
        this.zzi = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final synchronized void zzl() {
        if (this.zzg.compareAndSet(false, true)) {
            this.zza.zzc(this);
            zzg();
        }
    }
}
