package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfdn {
    public final com.google.android.gms.ads.internal.client.zzff zza;
    public final zzbrx zzb;
    public final zzeno zzc;
    public final com.google.android.gms.ads.internal.client.zzl zzd;
    public final com.google.android.gms.ads.internal.client.zzq zze;
    public final String zzf;
    public final ArrayList zzg;
    public final ArrayList zzh;
    public final zzblo zzi;
    public final com.google.android.gms.ads.internal.client.zzw zzj;
    public final int zzk;
    public final AdManagerAdViewOptions zzl;
    public final PublisherAdViewOptions zzm;
    public final com.google.android.gms.ads.internal.client.zzbz zzn;
    public final zzfda zzo;
    public final boolean zzp;
    public final boolean zzq;
    public final com.google.android.gms.ads.internal.client.zzcd zzr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfdn(zzfdl zzfdlVar, zzfdm zzfdmVar) {
        com.google.android.gms.ads.internal.client.zzff zzffVar;
        zzblo zzbloVar;
        this.zze = zzfdl.zzf(zzfdlVar);
        this.zzf = zzfdl.zzH(zzfdlVar);
        this.zzr = zzfdl.zzP(zzfdlVar);
        int r3 = zzfdl.zzd(zzfdlVar).zza;
        long j = zzfdl.zzd(zzfdlVar).zzb;
        Bundle bundle = zzfdl.zzd(zzfdlVar).zzc;
        int r7 = zzfdl.zzd(zzfdlVar).zzd;
        List list = zzfdl.zzd(zzfdlVar).zze;
        boolean z = zzfdl.zzd(zzfdlVar).zzf;
        int r10 = zzfdl.zzd(zzfdlVar).zzg;
        boolean z2 = true;
        if (!zzfdl.zzd(zzfdlVar).zzh && !zzfdl.zzN(zzfdlVar)) {
            z2 = false;
        }
        this.zzd = new com.google.android.gms.ads.internal.client.zzl(r3, j, bundle, r7, list, z, r10, z2, zzfdl.zzd(zzfdlVar).zzi, zzfdl.zzd(zzfdlVar).zzj, zzfdl.zzd(zzfdlVar).zzk, zzfdl.zzd(zzfdlVar).zzl, zzfdl.zzd(zzfdlVar).zzm, zzfdl.zzd(zzfdlVar).zzn, zzfdl.zzd(zzfdlVar).zzo, zzfdl.zzd(zzfdlVar).zzp, zzfdl.zzd(zzfdlVar).zzq, zzfdl.zzd(zzfdlVar).zzr, zzfdl.zzd(zzfdlVar).zzs, zzfdl.zzd(zzfdlVar).zzt, zzfdl.zzd(zzfdlVar).zzu, zzfdl.zzd(zzfdlVar).zzv, com.google.android.gms.ads.internal.util.zzs.zza(zzfdl.zzd(zzfdlVar).zzw), zzfdl.zzd(zzfdlVar).zzx);
        if (zzfdl.zzj(zzfdlVar) != null) {
            zzffVar = zzfdl.zzj(zzfdlVar);
        } else {
            zzffVar = zzfdl.zzk(zzfdlVar) != null ? zzfdl.zzk(zzfdlVar).zzf : null;
        }
        this.zza = zzffVar;
        this.zzg = zzfdl.zzJ(zzfdlVar);
        this.zzh = zzfdl.zzK(zzfdlVar);
        if (zzfdl.zzJ(zzfdlVar) == null) {
            zzbloVar = null;
        } else {
            zzbloVar = zzfdl.zzk(zzfdlVar) == null ? new zzblo(new NativeAdOptions.Builder().build()) : zzfdl.zzk(zzfdlVar);
        }
        this.zzi = zzbloVar;
        this.zzj = zzfdl.zzh(zzfdlVar);
        this.zzk = zzfdl.zza(zzfdlVar);
        this.zzl = zzfdl.zzb(zzfdlVar);
        this.zzm = zzfdl.zzc(zzfdlVar);
        this.zzn = zzfdl.zzi(zzfdlVar);
        this.zzb = zzfdl.zzl(zzfdlVar);
        this.zzo = new zzfda(zzfdl.zzn(zzfdlVar), null);
        this.zzp = zzfdl.zzL(zzfdlVar);
        this.zzc = zzfdl.zzm(zzfdlVar);
        this.zzq = zzfdl.zzM(zzfdlVar);
    }

    public final zzbnr zza() {
        PublisherAdViewOptions publisherAdViewOptions = this.zzm;
        if (publisherAdViewOptions == null && this.zzl == null) {
            return null;
        }
        return publisherAdViewOptions != null ? publisherAdViewOptions.zzb() : this.zzl.zza();
    }
}
