package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
final class zzfx implements Cloneable {
    private Object value;
    private zzfv<?, ?> zzrp;
    private List<Object> zzrq = new ArrayList();

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzen()];
        zza(zzfs.zzg(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzeq */
    public final zzfx clone() {
        Object clone;
        zzfx zzfxVar = new zzfx();
        try {
            zzfxVar.zzrp = this.zzrp;
            List<Object> list = this.zzrq;
            if (list == null) {
                zzfxVar.zzrq = null;
            } else {
                zzfxVar.zzrq.addAll(list);
            }
            Object obj = this.value;
            if (obj != null) {
                if (obj instanceof zzfz) {
                    clone = (zzfz) ((zzfz) obj).clone();
                } else if (obj instanceof byte[]) {
                    clone = ((byte[]) obj).clone();
                } else {
                    int r3 = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length];
                        zzfxVar.value = bArr2;
                        while (r3 < bArr.length) {
                            bArr2[r3] = (byte[]) bArr[r3].clone();
                            r3++;
                        }
                    } else if (obj instanceof boolean[]) {
                        clone = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        clone = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        clone = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        clone = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        clone = ((double[]) obj).clone();
                    } else if (obj instanceof zzfz[]) {
                        zzfz[] zzfzVarArr = (zzfz[]) obj;
                        zzfz[] zzfzVarArr2 = new zzfz[zzfzVarArr.length];
                        zzfxVar.value = zzfzVarArr2;
                        while (r3 < zzfzVarArr.length) {
                            zzfzVarArr2[r3] = (zzfz) zzfzVarArr[r3].clone();
                            r3++;
                        }
                    }
                }
                zzfxVar.value = clone;
            }
            return zzfxVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<Object> list;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfx) {
            zzfx zzfxVar = (zzfx) obj;
            if (this.value == null || zzfxVar.value == null) {
                List<Object> list2 = this.zzrq;
                if (list2 == null || (list = zzfxVar.zzrq) == null) {
                    try {
                        return Arrays.equals(toByteArray(), zzfxVar.toByteArray());
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                }
                return list2.equals(list);
            }
            zzfv<?, ?> zzfvVar = this.zzrp;
            if (zzfvVar != zzfxVar.zzrp) {
                return false;
            }
            if (zzfvVar.zzrk.isArray()) {
                Object obj2 = this.value;
                return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzfxVar.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzfxVar.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzfxVar.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzfxVar.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzfxVar.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzfxVar.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzfxVar.value);
            }
            return this.value.equals(zzfxVar.value);
        }
        return false;
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzfs zzfsVar) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.zzrq.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzen() {
        if (this.value == null) {
            Iterator<Object> it = this.zzrq.iterator();
            if (it.hasNext()) {
                it.next();
                throw new NoSuchMethodError();
            }
            return 0;
        }
        throw new NoSuchMethodError();
    }
}
