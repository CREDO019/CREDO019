package com.google.common.base;

import java.nio.Buffer;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class Java8Compatibility {
    static void clear(Buffer buffer) {
        buffer.clear();
    }

    static void flip(Buffer buffer) {
        buffer.flip();
    }

    static void limit(Buffer buffer, int r1) {
        buffer.limit(r1);
    }

    static void position(Buffer buffer, int r1) {
        buffer.position(r1);
    }

    private Java8Compatibility() {
    }
}
