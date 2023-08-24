package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfsn extends zzfsq {
    final /* synthetic */ zzfso zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfsn(zzfso zzfsoVar, zzfss zzfssVar, CharSequence charSequence) {
        super(zzfssVar, charSequence);
        this.zza = zzfsoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfsq
    public final int zzc(int r1) {
        return r1;
    }

    @Override // com.google.android.gms.internal.ads.zzfsq
    public final int zzd(int r2) {
        int r22 = r2 + 4000;
        if (r22 < this.zzb.length()) {
            return r22;
        }
        return -1;
    }
}
