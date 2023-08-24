package com.google.android.play.core.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.play.core.internal.ce */
/* loaded from: classes3.dex */
final class C2547ce {

    /* renamed from: a */
    private final ConcurrentHashMap<C2546cd, List<Throwable>> f850a = new ConcurrentHashMap<>(16, 0.75f, 10);

    /* renamed from: b */
    private final ReferenceQueue<Throwable> f851b = new ReferenceQueue<>();

    /* renamed from: a */
    public final List<Throwable> m716a(Throwable th) {
        while (true) {
            Reference<? extends Throwable> poll = this.f851b.poll();
            if (poll == null) {
                break;
            }
            this.f850a.remove(poll);
        }
        List<Throwable> list = this.f850a.get(new C2546cd(th, null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.f850a.putIfAbsent(new C2546cd(th, this.f851b), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
