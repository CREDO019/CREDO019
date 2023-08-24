package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzffk implements zzffi {
    private final String zza;

    public zzffk(String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzffi
    public final boolean equals(Object obj) {
        if (obj instanceof zzffk) {
            return this.zza.equals(((zzffk) obj).zza);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzffi
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza;
    }
}
