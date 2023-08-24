package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcx implements Comparator {
    public zzbcx(zzbcy zzbcyVar) {
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        zzbcm zzbcmVar = (zzbcm) obj;
        zzbcm zzbcmVar2 = (zzbcm) obj2;
        if (zzbcmVar.zzd() >= zzbcmVar2.zzd()) {
            if (zzbcmVar.zzd() > zzbcmVar2.zzd()) {
                return 1;
            }
            if (zzbcmVar.zzb() >= zzbcmVar2.zzb()) {
                if (zzbcmVar.zzb() > zzbcmVar2.zzb()) {
                    return 1;
                }
                float zza = (zzbcmVar.zza() - zzbcmVar.zzd()) * (zzbcmVar.zzc() - zzbcmVar.zzb());
                float zza2 = (zzbcmVar2.zza() - zzbcmVar2.zzd()) * (zzbcmVar2.zzc() - zzbcmVar2.zzb());
                if (zza <= zza2) {
                    return zza < zza2 ? 1 : 0;
                }
            }
        }
        return -1;
    }
}
