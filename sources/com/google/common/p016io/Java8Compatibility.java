package com.google.common.p016io;

import java.nio.Buffer;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.Java8Compatibility */
/* loaded from: classes3.dex */
final class Java8Compatibility {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear(Buffer buffer) {
        buffer.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void flip(Buffer buffer) {
        buffer.flip();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void limit(Buffer buffer, int r1) {
        buffer.limit(r1);
    }

    static void mark(Buffer buffer) {
        buffer.mark();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void position(Buffer buffer, int r1) {
        buffer.position(r1);
    }

    static void reset(Buffer buffer) {
        buffer.reset();
    }

    private Java8Compatibility() {
    }
}
