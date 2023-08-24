package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpo extends zzfpz {
    private final String zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfpo(String str, String str2, zzfpn zzfpnVar) {
        this.zza = str;
        this.zzb = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfpz) {
            zzfpz zzfpzVar = (zzfpz) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzfpzVar.zzb()) : zzfpzVar.zzb() == null) {
                String str2 = this.zzb;
                if (str2 != null ? str2.equals(zzfpzVar.zza()) : zzfpzVar.zza() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return "OverlayDisplayDismissRequest{sessionToken=" + str + ", appId=" + str2 + "}";
    }

    @Override // com.google.android.gms.internal.ads.zzfpz
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzfpz
    public final String zzb() {
        return this.zza;
    }

    public final int hashCode() {
        String str = this.zza;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.zzb;
        return hashCode ^ (str2 != null ? str2.hashCode() : 0);
    }
}
