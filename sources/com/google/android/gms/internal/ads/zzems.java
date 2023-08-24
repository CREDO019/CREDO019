package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.formats.AdManagerAdViewOptions;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzems extends com.google.android.gms.ads.internal.client.zzbn {
    final zzfdl zza;
    final zzdox zzb;
    private final Context zzc;
    private final zzcok zzd;
    private com.google.android.gms.ads.internal.client.zzbf zze;

    public zzems(zzcok zzcokVar, Context context, String str) {
        zzfdl zzfdlVar = new zzfdl();
        this.zza = zzfdlVar;
        this.zzb = new zzdox();
        this.zzd = zzcokVar;
        zzfdlVar.zzs(str);
        this.zzc = context;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final com.google.android.gms.ads.internal.client.zzbl zze() {
        zzdoz zzg = this.zzb.zzg();
        this.zza.zzB(zzg.zzi());
        this.zza.zzC(zzg.zzh());
        zzfdl zzfdlVar = this.zza;
        if (zzfdlVar.zzg() == null) {
            zzfdlVar.zzr(com.google.android.gms.ads.internal.client.zzq.zzc());
        }
        return new zzemt(this.zzc, this.zzd, this.zza, zzg, this.zze);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzf(zzbmy zzbmyVar) {
        this.zzb.zza(zzbmyVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzg(zzbnb zzbnbVar) {
        this.zzb.zzb(zzbnbVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzh(String str, zzbnh zzbnhVar, zzbne zzbneVar) {
        this.zzb.zzc(str, zzbnhVar, zzbneVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzi(zzbsg zzbsgVar) {
        this.zzb.zzd(zzbsgVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzj(zzbnl zzbnlVar, com.google.android.gms.ads.internal.client.zzq zzqVar) {
        this.zzb.zze(zzbnlVar);
        this.zza.zzr(zzqVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzk(zzbno zzbnoVar) {
        this.zzb.zzf(zzbnoVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzl(com.google.android.gms.ads.internal.client.zzbf zzbfVar) {
        this.zze = zzbfVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzm(AdManagerAdViewOptions adManagerAdViewOptions) {
        this.zza.zzq(adManagerAdViewOptions);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzn(zzbrx zzbrxVar) {
        this.zza.zzv(zzbrxVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzo(zzblo zzbloVar) {
        this.zza.zzA(zzbloVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzp(PublisherAdViewOptions publisherAdViewOptions) {
        this.zza.zzD(publisherAdViewOptions);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbo
    public final void zzq(com.google.android.gms.ads.internal.client.zzcd zzcdVar) {
        this.zza.zzQ(zzcdVar);
    }
}
