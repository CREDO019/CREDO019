package com.google.android.gms.internal.ads;

import android.util.Log;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzasm {
    public final zzaxy zza;
    public final Object zzb;
    public final int zzc;
    public final zzayk[] zzd;
    public final boolean[] zze;
    public final long zzf;
    public int zzg;
    public long zzh;
    public boolean zzi;
    public boolean zzj;
    public boolean zzk;
    public zzasm zzl;
    public zzaze zzm;
    private final zzasx[] zzn;
    private final zzasy[] zzo;
    private final zzazd zzp;
    private final zzaya zzq;
    private zzaze zzr;
    private final zzcjt zzs;

    public final long zza(long j, boolean z) {
        return zzb(j, false, new boolean[2]);
    }

    public final long zzb(long j, boolean z, boolean[] zArr) {
        zzazb zzazbVar = this.zzm.zzb;
        int r3 = 0;
        while (true) {
            boolean z2 = true;
            if (r3 >= 2) {
                break;
            }
            boolean[] zArr2 = this.zze;
            if (z || !this.zzm.zza(this.zzr, r3)) {
                z2 = false;
            }
            zArr2[r3] = z2;
            r3++;
        }
        long zzB = this.zza.zzB(zzazbVar.zzb(), this.zze, this.zzd, zArr, j);
        this.zzr = this.zzm;
        this.zzk = false;
        int r32 = 0;
        while (true) {
            zzayk[] zzaykVarArr = this.zzd;
            if (r32 >= 2) {
                this.zzs.zzd(this.zzn, this.zzm.zza, zzazbVar);
                return zzB;
            }
            if (zzaykVarArr[r32] != null) {
                zzazy.zze(zzazbVar.zza(r32) != null);
                this.zzk = true;
            } else {
                zzazy.zze(zzazbVar.zza(r32) == null);
            }
            r32++;
        }
    }

    public final void zzc() {
        try {
            this.zzq.zzc(this.zza);
        } catch (RuntimeException e) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", e);
        }
    }

    public final boolean zzd() {
        return this.zzj && (!this.zzk || this.zza.zzg() == Long.MIN_VALUE);
    }

    public final boolean zze() throws zzase {
        zzaze zzc = this.zzp.zzc(this.zzo, this.zza.zzn());
        zzaze zzazeVar = this.zzr;
        if (zzazeVar != null) {
            for (int r3 = 0; r3 < 2; r3++) {
                if (zzc.zza(zzazeVar, r3)) {
                }
            }
            return false;
        }
        this.zzm = zzc;
        return true;
    }

    public zzasm(zzasx[] zzasxVarArr, zzasy[] zzasyVarArr, long j, zzazd zzazdVar, zzcjt zzcjtVar, zzaya zzayaVar, Object obj, int r9, int r10, boolean z, long j2, byte[] bArr) {
        this.zzn = zzasxVarArr;
        this.zzo = zzasyVarArr;
        this.zzf = j;
        this.zzp = zzazdVar;
        this.zzs = zzcjtVar;
        this.zzq = zzayaVar;
        Objects.requireNonNull(obj);
        this.zzb = obj;
        this.zzc = r9;
        this.zzg = r10;
        this.zzi = z;
        this.zzh = j2;
        this.zzd = new zzayk[2];
        this.zze = new boolean[2];
        this.zza = zzayaVar.zze(r10, zzcjtVar.zzl());
    }
}
