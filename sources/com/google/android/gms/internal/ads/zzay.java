package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzay {
    public final Uri zza;
    public final List zze;
    public final zzfuv zzg;
    @Deprecated
    public final List zzh;
    public final Object zzi;
    public final String zzb = null;
    public final zzas zzc = null;
    public final zzai zzd = null;
    public final String zzf = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzay(Uri uri, String str, zzas zzasVar, zzai zzaiVar, List list, String str2, zzfuv zzfuvVar, Object obj, zzax zzaxVar) {
        this.zza = uri;
        this.zze = list;
        this.zzg = zzfuvVar;
        zzfus zzi = zzfuv.zzi();
        if (zzfuvVar.size() > 0) {
            zzbe zzbeVar = (zzbe) zzfuvVar.get(0);
            throw null;
        }
        this.zzh = zzi.zzg();
        this.zzi = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzay) {
            zzay zzayVar = (zzay) obj;
            if (this.zza.equals(zzayVar.zza)) {
                String str = zzayVar.zzb;
                if (zzel.zzT(null, null)) {
                    zzas zzasVar = zzayVar.zzc;
                    if (zzel.zzT(null, null)) {
                        zzai zzaiVar = zzayVar.zzd;
                        if (zzel.zzT(null, null) && this.zze.equals(zzayVar.zze)) {
                            String str2 = zzayVar.zzf;
                            if (zzel.zzT(null, null) && this.zzg.equals(zzayVar.zzg)) {
                                Object obj2 = zzayVar.zzi;
                                if (zzel.zzT(null, null)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() * 923521) + this.zze.hashCode()) * 961) + this.zzg.hashCode()) * 31;
    }
}
