package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public interface zaq {
    ExecutorService zaa(ThreadFactory threadFactory, int r2);

    ExecutorService zab(int r1, int r2);

    ExecutorService zac(int r1, ThreadFactory threadFactory, int r3);
}
