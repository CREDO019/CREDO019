package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzvp {
    public final zzcp zza;
    public final int[] zzb;

    public zzvp(zzcp zzcpVar, int[] r4, int r5) {
        if (r4.length == 0) {
            zzdu.zza("ETSDefinition", "Empty tracks are not allowed", new IllegalArgumentException());
        }
        this.zza = zzcpVar;
        this.zzb = r4;
    }
}
