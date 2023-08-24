package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class Token {
    static final Token EMPTY = new SimpleToken(null, 0, 0);
    private final Token previous;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void appendTo(BitArray bitArray, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token(Token token) {
        this.previous = token;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Token getPrevious() {
        return this.previous;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Token add(int r2, int r3) {
        return new SimpleToken(this, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Token addBinaryShift(int r2, int r3) {
        return new BinaryShiftToken(this, r2, r3);
    }
}
