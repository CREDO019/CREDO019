package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.C1856C;
import java.io.File;

/* loaded from: classes2.dex */
public class CacheSpan implements Comparable<CacheSpan> {
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastTouchTimestamp;
    public final long length;
    public final long position;

    public CacheSpan(String str, long j, long j2) {
        this(str, j, j2, C1856C.TIME_UNSET, null);
    }

    public CacheSpan(String str, long j, long j2, long j3, File file) {
        this.key = str;
        this.position = j;
        this.length = j2;
        this.isCached = file != null;
        this.file = file;
        this.lastTouchTimestamp = j3;
    }

    public boolean isOpenEnded() {
        return this.length == -1;
    }

    public boolean isHoleSpan() {
        return !this.isCached;
    }

    @Override // java.lang.Comparable
    public int compareTo(CacheSpan cacheSpan) {
        if (!this.key.equals(cacheSpan.key)) {
            return this.key.compareTo(cacheSpan.key);
        }
        int r5 = ((this.position - cacheSpan.position) > 0L ? 1 : ((this.position - cacheSpan.position) == 0L ? 0 : -1));
        if (r5 == 0) {
            return 0;
        }
        return r5 < 0 ? -1 : 1;
    }

    public String toString() {
        return "[" + this.position + ", " + this.length + "]";
    }
}
