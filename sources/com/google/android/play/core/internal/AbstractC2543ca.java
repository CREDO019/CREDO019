package com.google.android.play.core.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.play.core.internal.ca */
/* loaded from: classes3.dex */
public abstract class AbstractC2543ca implements Closeable {
    /* renamed from: a */
    public abstract long mo719a();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract InputStream mo717a(long j, long j2) throws IOException;

    /* renamed from: b */
    public synchronized InputStream m720b() throws IOException {
        return mo717a(0L, mo719a());
    }
}
