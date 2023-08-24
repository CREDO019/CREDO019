package org.bouncycastle.pqc.crypto.mceliece;

/* loaded from: classes3.dex */
public class McElieceCCA2Parameters extends McElieceParameters {
    private final String digest;

    public McElieceCCA2Parameters() {
        this(11, 50, "SHA-256");
    }

    public McElieceCCA2Parameters(int r2) {
        this(r2, "SHA-256");
    }

    public McElieceCCA2Parameters(int r2, int r3) {
        this(r2, r3, "SHA-256");
    }

    public McElieceCCA2Parameters(int r2, int r3, int r4) {
        this(r2, r3, r4, "SHA-256");
    }

    public McElieceCCA2Parameters(int r1, int r2, int r3, String str) {
        super(r1, r2, r3);
        this.digest = str;
    }

    public McElieceCCA2Parameters(int r1, int r2, String str) {
        super(r1, r2);
        this.digest = str;
    }

    public McElieceCCA2Parameters(int r1, String str) {
        super(r1);
        this.digest = str;
    }

    public McElieceCCA2Parameters(String str) {
        this(11, 50, str);
    }

    public String getDigest() {
        return this.digest;
    }
}
