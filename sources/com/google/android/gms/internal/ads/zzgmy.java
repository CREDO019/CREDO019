package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmy extends zzgnb {
    private final int zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgmy(byte[] bArr, int r3, int r4) {
        super(bArr);
        zzq(r3, r3 + r4, bArr.length);
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.android.gms.internal.ads.zzgnb, com.google.android.gms.internal.ads.zzgnf
    public final byte zza(int r3) {
        zzB(r3, this.zzd);
        return this.zza[this.zzc + r3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnb, com.google.android.gms.internal.ads.zzgnf
    public final byte zzb(int r3) {
        return this.zza[this.zzc + r3];
    }

    @Override // com.google.android.gms.internal.ads.zzgnb
    protected final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgnb, com.google.android.gms.internal.ads.zzgnf
    public final int zzd() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnb, com.google.android.gms.internal.ads.zzgnf
    public final void zze(byte[] bArr, int r4, int r5, int r6) {
        System.arraycopy(this.zza, this.zzc + r4, bArr, r5, r6);
    }
}
