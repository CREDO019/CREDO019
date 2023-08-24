package org.bouncycastle.asn1.est;

/* renamed from: org.bouncycastle.asn1.est.Utils */
/* loaded from: classes5.dex */
class C5127Utils {
    C5127Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AttrOrOID[] clone(AttrOrOID[] attrOrOIDArr) {
        AttrOrOID[] attrOrOIDArr2 = new AttrOrOID[attrOrOIDArr.length];
        System.arraycopy(attrOrOIDArr, 0, attrOrOIDArr2, 0, attrOrOIDArr.length);
        return attrOrOIDArr2;
    }
}
