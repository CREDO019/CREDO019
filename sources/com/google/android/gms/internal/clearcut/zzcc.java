package com.google.android.gms.internal.clearcut;

/* loaded from: classes2.dex */
final /* synthetic */ class zzcc {
    static final /* synthetic */ int[] zzje;
    static final /* synthetic */ int[] zzjf;

    static {
        int[] r0 = new int[zzcq.values().length];
        zzjf = r0;
        try {
            r0[zzcq.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zzjf[zzcq.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zzjf[zzcq.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] r3 = new int[zzcd.values().length];
        zzje = r3;
        try {
            r3[zzcd.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zzje[zzcd.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zzje[zzcd.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
