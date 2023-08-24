package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcu {
    final long zza;
    final String zzb;
    final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbcu(long j, String str, int r4) {
        this.zza = j;
        this.zzb = str;
        this.zzc = r4;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzbcu)) {
            zzbcu zzbcuVar = (zzbcu) obj;
            if (zzbcuVar.zza == this.zza && zzbcuVar.zzc == this.zzc) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.zza;
    }
}
