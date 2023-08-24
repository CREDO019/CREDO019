package com.google.android.gms.internal.ads;

import android.os.IBinder;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpr extends zzfqk {
    private final IBinder zza;
    private final String zzb;
    private final int zzc;
    private final float zzd;
    private final int zze;
    private final String zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfpr(IBinder iBinder, boolean z, String str, int r4, float f, int r6, String str2, int r8, String str3, zzfpq zzfpqVar) {
        this.zza = iBinder;
        this.zzb = str;
        this.zzc = r4;
        this.zzd = f;
        this.zze = r8;
        this.zzf = str3;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfqk) {
            zzfqk zzfqkVar = (zzfqk) obj;
            if (this.zza.equals(zzfqkVar.zze())) {
                zzfqkVar.zzi();
                String str2 = this.zzb;
                if (str2 != null ? str2.equals(zzfqkVar.zzg()) : zzfqkVar.zzg() == null) {
                    if (this.zzc == zzfqkVar.zzc() && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzfqkVar.zza())) {
                        zzfqkVar.zzb();
                        zzfqkVar.zzh();
                        if (this.zze == zzfqkVar.zzd() && ((str = this.zzf) != null ? str.equals(zzfqkVar.zzf()) : zzfqkVar.zzf() == null)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ 1237) * 1000003;
        String str = this.zzb;
        int hashCode2 = (((((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.zzc) * 1000003) ^ Float.floatToIntBits(this.zzd)) * 583896283) ^ this.zze) * 1000003;
        String str2 = this.zzf;
        return hashCode2 ^ (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        int r2 = this.zzc;
        float f = this.zzd;
        int r4 = this.zze;
        String str2 = this.zzf;
        return "OverlayDisplayShowRequest{windowToken=" + obj + ", stableSessionToken=false, appId=" + str + ", layoutGravity=" + r2 + ", layoutVerticalMargin=" + f + ", displayMode=0, sessionToken=null, windowWidthPx=" + r4 + ", adFieldEnifd=" + str2 + "}";
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final float zza() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final int zzb() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final int zzd() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final IBinder zze() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final String zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final String zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final String zzh() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzfqk
    public final boolean zzi() {
        return false;
    }
}
