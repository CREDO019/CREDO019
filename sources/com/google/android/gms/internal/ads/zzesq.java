package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesq implements zzeun {
    private final zzfyy zza;
    private final zzfdn zzb;
    private final PackageInfo zzc;
    private final com.google.android.gms.ads.internal.util.zzg zzd;

    public zzesq(zzfyy zzfyyVar, zzfdn zzfdnVar, PackageInfo packageInfo, com.google.android.gms.ads.internal.util.zzg zzgVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfdnVar;
        this.zzc = packageInfo;
        this.zzd = zzgVar;
    }

    public static /* synthetic */ zzesr zzc(final zzesq zzesqVar) {
        final ArrayList arrayList = zzesqVar.zzb.zzg;
        return arrayList == null ? new zzesr() { // from class: com.google.android.gms.internal.ads.zzesm
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                Bundle bundle = (Bundle) obj;
            }
        } : arrayList.isEmpty() ? new zzesr() { // from class: com.google.android.gms.internal.ads.zzesn
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                ((Bundle) obj).putInt("native_version", 0);
            }
        } : new zzesr() { // from class: com.google.android.gms.internal.ads.zzeso
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                zzesq.this.zzd(arrayList, (Bundle) obj);
            }
        };
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 26;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzesp
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzesq.zzc(zzesq.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e2, code lost:
        if (r9 == 3) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ void zzd(java.util.ArrayList r9, android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzesq.zzd(java.util.ArrayList, android.os.Bundle):void");
    }
}
