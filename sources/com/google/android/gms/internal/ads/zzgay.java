package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgay extends zzgab {
    private final String zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgay(String str, int r2, zzgax zzgaxVar) {
        this.zza = str;
        this.zzb = r2;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zza;
        int r2 = this.zzb - 2;
        objArr[1] = r2 != 1 ? r2 != 2 ? r2 != 3 ? r2 != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }
}
