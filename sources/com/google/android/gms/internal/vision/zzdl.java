package com.google.android.gms.internal.vision;

import java.util.Map;
import kotlin.UShort;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzdl<K, V> extends zzdg<K, V> {
    private static final zzdg<Object, Object> zzlz = new zzdl(null, new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzlx;
    private final transient Object zzma;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
        r0[r6] = (byte) r2;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009d, code lost:
        r0[r6] = (short) r2;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d2, code lost:
        r0[r7] = r2;
        r3 = r3 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [int[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <K, V> com.google.android.gms.internal.vision.zzdl<K, V> zza(int r10, java.lang.Object[] r11) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzdl.zza(int, java.lang.Object[]):com.google.android.gms.internal.vision.zzdl");
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int r6) {
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        String valueOf3 = String.valueOf(objArr[r6]);
        String valueOf4 = String.valueOf(objArr[r6 ^ 1]);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        sb.append(" and ");
        sb.append(valueOf3);
        sb.append("=");
        sb.append(valueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    private zzdl(Object obj, Object[] objArr, int r3) {
        this.zzma = obj;
        this.zzlx = objArr;
        this.size = r3;
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdg, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzma;
        Object[] objArr = this.zzlx;
        int r2 = this.size;
        if (obj == null) {
            return null;
        }
        if (r2 == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        } else if (obj2 == null) {
            return null;
        } else {
            if (obj2 instanceof byte[]) {
                byte[] bArr = (byte[]) obj2;
                int length = bArr.length - 1;
                int zzr = zzdd.zzr(obj.hashCode());
                while (true) {
                    int r0 = zzr & length;
                    int r6 = bArr[r0] & 255;
                    if (r6 == 255) {
                        return null;
                    }
                    if (objArr[r6].equals(obj)) {
                        return (V) objArr[r6 ^ 1];
                    }
                    zzr = r0 + 1;
                }
            } else if (obj2 instanceof short[]) {
                short[] sArr = (short[]) obj2;
                int length2 = sArr.length - 1;
                int zzr2 = zzdd.zzr(obj.hashCode());
                while (true) {
                    int r02 = zzr2 & length2;
                    int r62 = sArr[r02] & UShort.MAX_VALUE;
                    if (r62 == 65535) {
                        return null;
                    }
                    if (objArr[r62].equals(obj)) {
                        return (V) objArr[r62 ^ 1];
                    }
                    zzr2 = r02 + 1;
                }
            } else {
                int[] r03 = (int[]) obj2;
                int length3 = r03.length - 1;
                int zzr3 = zzdd.zzr(obj.hashCode());
                while (true) {
                    int r5 = zzr3 & length3;
                    int r63 = r03[r5];
                    if (r63 == -1) {
                        return null;
                    }
                    if (objArr[r63].equals(obj)) {
                        return (V) objArr[r63 ^ 1];
                    }
                    zzr3 = r5 + 1;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.vision.zzdg
    final zzdj<Map.Entry<K, V>> zzce() {
        return new zzdk(this, this.zzlx, 0, this.size);
    }

    @Override // com.google.android.gms.internal.vision.zzdg
    final zzdj<K> zzcf() {
        return new zzdm(this, new zzdp(this.zzlx, 0, this.size));
    }

    @Override // com.google.android.gms.internal.vision.zzdg
    final zzdc<V> zzcg() {
        return new zzdp(this.zzlx, 1, this.size);
    }
}
