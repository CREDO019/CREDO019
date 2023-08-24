package com.google.android.gms.internal.ads;

import java.io.Closeable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfoq implements Closeable {
    public static zzfpc zza() {
        return new zzfpc();
    }

    public static zzfpc zzb(final int r2, zzfpb zzfpbVar) {
        return new zzfpc(new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfoo
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                Integer valueOf;
                valueOf = Integer.valueOf(r2);
                return valueOf;
            }
        }, new zzfsv() { // from class: com.google.android.gms.internal.ads.zzfop
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return zzfoq.zze();
            }
        }, zzfpbVar);
    }

    public static zzfpc zzc(zzfsv<Integer> zzfsvVar, zzfsv<Integer> zzfsvVar2, zzfpb zzfpbVar) {
        return new zzfpc(zzfsvVar, zzfsvVar2, zzfpbVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer zze() {
        return -1;
    }
}
