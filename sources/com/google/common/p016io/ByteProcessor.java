package com.google.common.p016io;

import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;

@ElementTypesAreNonnullByDefault
@DoNotMock("Implement it normally")
/* renamed from: com.google.common.io.ByteProcessor */
/* loaded from: classes3.dex */
public interface ByteProcessor<T> {
    @ParametricNullness
    T getResult();

    boolean processBytes(byte[] bArr, int r2, int r3) throws IOException;
}
