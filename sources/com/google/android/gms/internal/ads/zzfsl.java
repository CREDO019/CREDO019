package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfsl extends zzfsq {
    final /* synthetic */ zzfsm zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfsl(zzfsm zzfsmVar, zzfss zzfssVar, CharSequence charSequence) {
        super(zzfssVar, charSequence);
        this.zza = zzfsmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfsq
    final int zzc(int r1) {
        return r1 + 1;
    }

    @Override // com.google.android.gms.internal.ads.zzfsq
    final int zzd(int r5) {
        zzfrr zzfrrVar = this.zza.zza;
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzfsf.zzb(r5, length, "index");
        while (r5 < length) {
            if (zzfrrVar.zzb(charSequence.charAt(r5))) {
                return r5;
            }
            r5++;
        }
        return -1;
    }
}
