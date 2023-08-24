package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfrr implements zzfsg {
    public static zzfrr zzc(char c) {
        return new zzfro(c);
    }

    @Override // com.google.android.gms.internal.ads.zzfsg
    @Deprecated
    public final /* synthetic */ boolean zza(Object obj) {
        return zzb(((Character) obj).charValue());
    }

    public abstract boolean zzb(char c);
}
