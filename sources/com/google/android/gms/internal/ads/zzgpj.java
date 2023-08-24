package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgpj extends zzgpl {
    private zzgpj() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgpj(zzgpi zzgpiVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final List zza(Object obj, long j) {
        zzgow zzgowVar = (zzgow) zzgrr.zzh(obj, j);
        if (zzgowVar.zzc()) {
            return zzgowVar;
        }
        int size = zzgowVar.size();
        zzgow zzd = zzgowVar.zzd(size == 0 ? 10 : size + size);
        zzgrr.zzv(obj, j, zzd);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final void zzb(Object obj, long j) {
        ((zzgow) zzgrr.zzh(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final void zzc(Object obj, Object obj2, long j) {
        zzgow zzgowVar = (zzgow) zzgrr.zzh(obj, j);
        zzgow zzgowVar2 = (zzgow) zzgrr.zzh(obj2, j);
        int size = zzgowVar.size();
        int size2 = zzgowVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzgowVar.zzc()) {
                zzgowVar = zzgowVar.zzd(size2 + size);
            }
            zzgowVar.addAll(zzgowVar2);
        }
        if (size > 0) {
            zzgowVar2 = zzgowVar;
        }
        zzgrr.zzv(obj, j, zzgowVar2);
    }
}
