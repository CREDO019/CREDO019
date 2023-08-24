package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcnj extends com.google.android.gms.ads.internal.client.zzdj {
    private final zzciw zza;
    private final boolean zzc;
    private final boolean zzd;
    private int zze;
    private com.google.android.gms.ads.internal.client.zzdn zzf;
    private boolean zzg;
    private float zzi;
    private float zzj;
    private float zzk;
    private boolean zzl;
    private boolean zzm;
    private zzbni zzn;
    private final Object zzb = new Object();
    private boolean zzh = true;

    public zzcnj(zzciw zzciwVar, float f, boolean z, boolean z2) {
        this.zza = zzciwVar;
        this.zzi = f;
        this.zzc = z;
        this.zzd = z2;
    }

    private final void zzw(final int r9, final int r10, final boolean z, final boolean z2) {
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcni
            @Override // java.lang.Runnable
            public final void run() {
                zzcnj.this.zzd(r9, r10, z, z2);
            }
        });
    }

    private final void zzx(String str, Map map) {
        final HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcnh
            @Override // java.lang.Runnable
            public final void run() {
                zzcnj.this.zzr(hashMap);
            }
        });
    }

    public final void zzc(float f, float f2, int r6, boolean z, float f3) {
        boolean z2;
        boolean z3;
        int r5;
        synchronized (this.zzb) {
            z2 = true;
            if (f2 == this.zzi && f3 == this.zzk) {
                z2 = false;
            }
            this.zzi = f2;
            this.zzj = f;
            z3 = this.zzh;
            this.zzh = z;
            r5 = this.zze;
            this.zze = r6;
            float f4 = this.zzk;
            this.zzk = f3;
            if (Math.abs(f3 - f4) > 1.0E-4f) {
                this.zza.zzH().invalidate();
            }
        }
        if (z2) {
            try {
                zzbni zzbniVar = this.zzn;
                if (zzbniVar != null) {
                    zzbniVar.zze();
                }
            } catch (RemoteException e) {
                zzcgn.zzl("#007 Could not call remote method.", e);
            }
        }
        zzw(r5, r6, z3, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(int r9, int r10, boolean z, boolean z2) {
        int r4;
        boolean z3;
        boolean z4;
        com.google.android.gms.ads.internal.client.zzdn zzdnVar;
        com.google.android.gms.ads.internal.client.zzdn zzdnVar2;
        com.google.android.gms.ads.internal.client.zzdn zzdnVar3;
        synchronized (this.zzb) {
            boolean z5 = this.zzg;
            boolean z6 = false;
            if (z5 || r10 != 1) {
                r4 = r10;
                z3 = false;
            } else {
                r4 = 1;
                z3 = true;
            }
            if (r9 == r10 || r4 != 1) {
                z4 = false;
            } else {
                r4 = 1;
                z4 = true;
            }
            boolean z7 = r9 != r10 && r4 == 2;
            boolean z8 = r9 != r10 && r4 == 3;
            this.zzg = (z5 || z3) ? true : true;
            if (z3) {
                try {
                    com.google.android.gms.ads.internal.client.zzdn zzdnVar4 = this.zzf;
                    if (zzdnVar4 != null) {
                        zzdnVar4.zzi();
                    }
                } catch (RemoteException e) {
                    zzcgn.zzl("#007 Could not call remote method.", e);
                }
            }
            if (z4 && (zzdnVar3 = this.zzf) != null) {
                zzdnVar3.zzh();
            }
            if (z7 && (zzdnVar2 = this.zzf) != null) {
                zzdnVar2.zzg();
            }
            if (z8) {
                com.google.android.gms.ads.internal.client.zzdn zzdnVar5 = this.zzf;
                if (zzdnVar5 != null) {
                    zzdnVar5.zze();
                }
                this.zza.zzy();
            }
            if (z != z2 && (zzdnVar = this.zzf) != null) {
                zzdnVar.zzf(z2);
            }
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final float zze() {
        float f;
        synchronized (this.zzb) {
            f = this.zzk;
        }
        return f;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final float zzf() {
        float f;
        synchronized (this.zzb) {
            f = this.zzj;
        }
        return f;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final float zzg() {
        float f;
        synchronized (this.zzb) {
            f = this.zzi;
        }
        return f;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final int zzh() {
        int r1;
        synchronized (this.zzb) {
            r1 = this.zze;
        }
        return r1;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final com.google.android.gms.ads.internal.client.zzdn zzi() throws RemoteException {
        com.google.android.gms.ads.internal.client.zzdn zzdnVar;
        synchronized (this.zzb) {
            zzdnVar = this.zzf;
        }
        return zzdnVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final void zzj(boolean z) {
        zzx(true != z ? "unmute" : "mute", null);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final void zzk() {
        zzx("pause", null);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final void zzl() {
        zzx("play", null);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final void zzm(com.google.android.gms.ads.internal.client.zzdn zzdnVar) {
        synchronized (this.zzb) {
            this.zzf = zzdnVar;
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final void zzn() {
        zzx("stop", null);
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final boolean zzo() {
        boolean z;
        boolean zzp = zzp();
        synchronized (this.zzb) {
            z = false;
            if (!zzp) {
                try {
                    if (this.zzm && this.zzd) {
                        z = true;
                    }
                } finally {
                }
            }
        }
        return z;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final boolean zzp() {
        boolean z;
        synchronized (this.zzb) {
            z = false;
            if (this.zzc && this.zzl) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.google.android.gms.ads.internal.client.zzdk
    public final boolean zzq() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zzh;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzr(Map map) {
        this.zza.zzd("pubVideoCmd", map);
    }

    public final void zzs(com.google.android.gms.ads.internal.client.zzff zzffVar) {
        boolean z = zzffVar.zza;
        boolean z2 = zzffVar.zzb;
        boolean z3 = zzffVar.zzc;
        synchronized (this.zzb) {
            this.zzl = z2;
            this.zzm = z3;
        }
        zzx("initialState", CollectionUtils.mapOf("muteStart", true != z ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "customControlsRequested", true != z2 ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "clickToExpandRequested", true != z3 ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE));
    }

    public final void zzt(float f) {
        synchronized (this.zzb) {
            this.zzj = f;
        }
    }

    public final void zzu() {
        boolean z;
        int r2;
        synchronized (this.zzb) {
            z = this.zzh;
            r2 = this.zze;
            this.zze = 3;
        }
        zzw(r2, 3, z, z);
    }

    public final void zzv(zzbni zzbniVar) {
        synchronized (this.zzb) {
            this.zzn = zzbniVar;
        }
    }
}
