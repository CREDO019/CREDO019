package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zznv extends Exception {
    public final int zza;
    public final boolean zzb;
    public final zzaf zzc;

    public zznv(int r3, zzaf zzafVar, boolean z) {
        super("AudioTrack write failed: " + r3);
        this.zzb = z;
        this.zza = r3;
        this.zzc = zzafVar;
    }
}
