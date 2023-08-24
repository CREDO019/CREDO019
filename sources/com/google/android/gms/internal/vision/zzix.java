package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzix extends zzjd {
    private final /* synthetic */ zziw zzaab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzix(zziw zziwVar) {
        super(zziwVar, null);
        this.zzaab = zziwVar;
    }

    @Override // com.google.android.gms.internal.vision.zzjd, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zziy(this.zzaab, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzix(zziw zziwVar, zziv zzivVar) {
        this(zziwVar);
    }
}
