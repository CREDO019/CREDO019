package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgqh implements zzgpu {
    private final zzgpx zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgqh(zzgpx zzgpxVar, String str, Object[] objArr) {
        this.zza = zzgpxVar;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        int r4 = charAt & 8191;
        int r0 = 13;
        int r1 = 1;
        while (true) {
            int r2 = r1 + 1;
            char charAt2 = str.charAt(r1);
            if (charAt2 < 55296) {
                this.zzd = r4 | (charAt2 << r0);
                return;
            }
            r4 |= (charAt2 & 8191) << r0;
            r0 += 13;
            r1 = r2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgpu
    public final zzgpx zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzgpu
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }

    @Override // com.google.android.gms.internal.ads.zzgpu
    public final int zzc() {
        return (this.zzd & 1) == 1 ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc;
    }
}
