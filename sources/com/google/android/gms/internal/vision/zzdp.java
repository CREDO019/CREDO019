package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdp extends zzdf<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzlx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdp(Object[] objArr, int r2, int r3) {
        this.zzlx = objArr;
        this.offset = r2;
        this.size = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzct.zzc(r3, this.size);
        return this.zzlx[(r3 * 2) + this.offset];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }
}
