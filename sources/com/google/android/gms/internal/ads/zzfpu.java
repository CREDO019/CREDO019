package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpu extends zzfqm {
    private final int zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfpu(int r1, String str, zzfpt zzfptVar) {
        this.zza = r1;
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfqm) {
            zzfqm zzfqmVar = (zzfqm) obj;
            if (this.zza == zzfqmVar.zza() && ((str = this.zzb) != null ? str.equals(zzfqmVar.zzb()) : zzfqmVar.zzb() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = (this.zza ^ 1000003) * 1000003;
        String str = this.zzb;
        return r0 ^ (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        int r0 = this.zza;
        String str = this.zzb;
        return "OverlayDisplayState{statusCode=" + r0 + ", sessionToken=" + str + "}";
    }

    @Override // com.google.android.gms.internal.ads.zzfqm
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfqm
    public final String zzb() {
        return this.zzb;
    }
}
