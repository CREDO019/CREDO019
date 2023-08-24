package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdik {
    private final Set zza;
    private final Set zzb;
    private final Set zzc;
    private final Set zzd;
    private final Set zze;
    private final Set zzf;
    private final Set zzg;
    private final Set zzh;
    private final Set zzi;
    private final Set zzj;
    private final Set zzk;
    private final Set zzl;
    private final Set zzm;
    private final Set zzn;
    private final zzfae zzo;
    private zzdda zzp;
    private zzejx zzq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdik(zzdii zzdiiVar, zzdij zzdijVar) {
        this.zza = zzdii.zzp(zzdiiVar);
        this.zzb = zzdii.zzC(zzdiiVar);
        this.zzd = zzdii.zzs(zzdiiVar);
        this.zze = zzdii.zzx(zzdiiVar);
        this.zzc = zzdii.zzt(zzdiiVar);
        this.zzf = zzdii.zzu(zzdiiVar);
        this.zzg = zzdii.zzv(zzdiiVar);
        this.zzh = zzdii.zzq(zzdiiVar);
        this.zzi = zzdii.zzr(zzdiiVar);
        this.zzj = zzdii.zzw(zzdiiVar);
        this.zzk = zzdii.zzB(zzdiiVar);
        this.zzl = zzdii.zzz(zzdiiVar);
        this.zzo = zzdii.zzo(zzdiiVar);
        this.zzm = zzdii.zzy(zzdiiVar);
        this.zzn = zzdii.zzA(zzdiiVar);
    }

    public final zzdda zza(Set set) {
        if (this.zzp == null) {
            this.zzp = new zzdda(set);
        }
        return this.zzp;
    }

    public final zzejx zzb(Clock clock, zzejy zzejyVar, zzegp zzegpVar, zzfju zzfjuVar) {
        if (this.zzq == null) {
            this.zzq = new zzejx(clock, zzejyVar, zzegpVar, zzfjuVar);
        }
        return this.zzq;
    }

    public final zzfae zzc() {
        return this.zzo;
    }

    public final Set zzd() {
        return this.zzm;
    }

    public final Set zze() {
        return this.zza;
    }

    public final Set zzf() {
        return this.zzh;
    }

    public final Set zzg() {
        return this.zzi;
    }

    public final Set zzh() {
        return this.zzd;
    }

    public final Set zzi() {
        return this.zzc;
    }

    public final Set zzj() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzk() {
        return this.zzg;
    }

    public final Set zzl() {
        return this.zzj;
    }

    public final Set zzm() {
        return this.zze;
    }

    public final Set zzn() {
        return this.zzl;
    }

    public final Set zzo() {
        return this.zzn;
    }

    public final Set zzp() {
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzq() {
        return this.zzb;
    }
}
