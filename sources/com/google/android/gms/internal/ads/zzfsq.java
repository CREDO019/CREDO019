package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzfsq extends zzfrl {
    final CharSequence zzb;
    final zzfrr zzc;
    int zzd = 0;
    int zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzfsq(zzfss zzfssVar, CharSequence charSequence) {
        zzfrr zzfrrVar;
        zzfrrVar = zzfssVar.zza;
        this.zzc = zzfrrVar;
        this.zze = Integer.MAX_VALUE;
        this.zzb = charSequence;
    }

    @Override // com.google.android.gms.internal.ads.zzfrl
    @CheckForNull
    protected final /* bridge */ /* synthetic */ Object zza() {
        int zzc;
        int r0 = this.zzd;
        while (true) {
            int r1 = this.zzd;
            if (r1 == -1) {
                zzb();
                return null;
            }
            int zzd = zzd(r1);
            if (zzd == -1) {
                zzd = this.zzb.length();
                this.zzd = -1;
                zzc = -1;
            } else {
                zzc = zzc(zzd);
                this.zzd = zzc;
            }
            if (zzc == r0) {
                int r3 = zzc + 1;
                this.zzd = r3;
                if (r3 > this.zzb.length()) {
                    this.zzd = -1;
                }
            } else {
                if (r0 < zzd) {
                    this.zzb.charAt(r0);
                }
                if (r0 < zzd) {
                    this.zzb.charAt(zzd - 1);
                }
                int r32 = this.zze;
                if (r32 == 1) {
                    zzd = this.zzb.length();
                    this.zzd = -1;
                    if (zzd > r0) {
                        this.zzb.charAt(zzd - 1);
                    }
                } else {
                    this.zze = r32 - 1;
                }
                return this.zzb.subSequence(r0, zzd).toString();
            }
        }
    }

    abstract int zzc(int r1);

    abstract int zzd(int r1);
}
