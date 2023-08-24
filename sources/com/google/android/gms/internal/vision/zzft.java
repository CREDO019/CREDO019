package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzft {
    int zzsf;
    int zzsg;
    private int zzsh;
    zzfy zzsi;
    private boolean zzsj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzft zza(byte[] bArr, int r7, int r8, boolean z) {
        zzfv zzfvVar = new zzfv(bArr, 0, r8, false);
        try {
            zzfvVar.zzas(r8);
            return zzfvVar;
        } catch (zzhc e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzau(int r1) {
        return (-(r1 & 1)) ^ (r1 >>> 1);
    }

    public static long zzr(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract void zzaq(int r1) throws zzhc;

    public abstract boolean zzar(int r1) throws IOException;

    public abstract int zzas(int r1) throws zzhc;

    public abstract void zzat(int r1);

    public abstract boolean zzdt() throws IOException;

    public abstract long zzdw() throws IOException;

    public abstract long zzdx() throws IOException;

    public abstract int zzdy() throws IOException;

    public abstract long zzdz() throws IOException;

    public abstract int zzea() throws IOException;

    public abstract boolean zzeb() throws IOException;

    public abstract String zzec() throws IOException;

    public abstract zzfh zzed() throws IOException;

    public abstract int zzee() throws IOException;

    public abstract int zzef() throws IOException;

    public abstract int zzeg() throws IOException;

    public abstract long zzeh() throws IOException;

    public abstract int zzei() throws IOException;

    public abstract long zzej() throws IOException;

    public abstract int zzex() throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long zzey() throws IOException;

    public abstract int zzez();

    private zzft() {
        this.zzsg = 100;
        this.zzsh = Integer.MAX_VALUE;
        this.zzsj = false;
    }
}
