package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdsf {
    private final Executor zza;
    private final zzcvs zzb;
    private final zzdkg zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdsf(Executor executor, zzcvs zzcvsVar, zzdkg zzdkgVar) {
        this.zza = executor;
        this.zzc = zzdkgVar;
        this.zzb = zzcvsVar;
    }

    public final void zza(final zzcmn zzcmnVar) {
        if (zzcmnVar == null) {
            return;
        }
        this.zzc.zza(zzcmnVar.zzH());
        this.zzc.zzj(new zzbbm() { // from class: com.google.android.gms.internal.ads.zzdsb
            @Override // com.google.android.gms.internal.ads.zzbbm
            public final void zzc(zzbbl zzbblVar) {
                zzcmn.this.zzP().zzo(zzbblVar.zzd.left, zzbblVar.zzd.top, false);
            }
        }, this.zza);
        this.zzc.zzj(new zzbbm() { // from class: com.google.android.gms.internal.ads.zzdsc
            @Override // com.google.android.gms.internal.ads.zzbbm
            public final void zzc(zzbbl zzbblVar) {
                zzcmn zzcmnVar2 = zzcmn.this;
                HashMap hashMap = new HashMap();
                hashMap.put("isVisible", true != zzbblVar.zzj ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                zzcmnVar2.zzd("onAdVisibilityChanged", hashMap);
            }
        }, this.zza);
        this.zzc.zzj(this.zzb, this.zza);
        this.zzb.zzf(zzcmnVar);
        zzcmnVar.zzaf("/trackActiveViewUnit", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdsd
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdsf.this.zzb((zzcmn) obj, map);
            }
        });
        zzcmnVar.zzaf("/untrackActiveViewUnit", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdse
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzdsf.this.zzc((zzcmn) obj, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzcmn zzcmnVar, Map map) {
        this.zzb.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzcmn zzcmnVar, Map map) {
        this.zzb.zza();
    }
}
