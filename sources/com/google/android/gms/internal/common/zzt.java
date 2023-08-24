package com.google.android.gms.internal.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
final class zzt extends zzw {
    final /* synthetic */ zzu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzt(zzu zzuVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.zza = zzuVar;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int zzc(int r1) {
        return r1 + 1;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int zzd(int r5) {
        zzo zzoVar = this.zza.zza;
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzs.zzb(r5, length, "index");
        while (r5 < length) {
            if (zzoVar.zza(charSequence.charAt(r5))) {
                return r5;
            }
            r5++;
        }
        return -1;
    }
}
