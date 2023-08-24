package com.google.common.hash;

import java.nio.Buffer;

@ElementTypesAreNonnullByDefault
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void position(Buffer buffer, int r1) {
        buffer.position(r1);
    }

    private Java8Compatibility() {
    }
}
