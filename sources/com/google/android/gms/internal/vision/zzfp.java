package com.google.android.gms.internal.vision;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzfp {
    private final byte[] buffer;
    private final zzga zzsd;

    private zzfp(int r1) {
        byte[] bArr = new byte[r1];
        this.buffer = bArr;
        this.zzsd = zzga.zze(bArr);
    }

    public final zzfh zzev() {
        this.zzsd.zzfh();
        return new zzfr(this.buffer);
    }

    public final zzga zzew() {
        return this.zzsd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfp(int r1, zzfk zzfkVar) {
        this(r1);
    }
}
