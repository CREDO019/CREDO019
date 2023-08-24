package org.bouncycastle.asn1;

/* loaded from: classes5.dex */
final class ASN1Tag {
    private final int tagClass;
    private final int tagNumber;

    private ASN1Tag(int r1, int r2) {
        this.tagClass = r1;
        this.tagNumber = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Tag create(int r1, int r2) {
        return new ASN1Tag(r1, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTagClass() {
        return this.tagClass;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTagNumber() {
        return this.tagNumber;
    }
}
