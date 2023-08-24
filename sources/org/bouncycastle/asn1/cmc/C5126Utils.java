package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.Extension;

/* renamed from: org.bouncycastle.asn1.cmc.Utils */
/* loaded from: classes5.dex */
class C5126Utils {
    C5126Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BodyPartID[] clone(BodyPartID[] bodyPartIDArr) {
        BodyPartID[] bodyPartIDArr2 = new BodyPartID[bodyPartIDArr.length];
        System.arraycopy(bodyPartIDArr, 0, bodyPartIDArr2, 0, bodyPartIDArr.length);
        return bodyPartIDArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Extension[] clone(Extension[] extensionArr) {
        Extension[] extensionArr2 = new Extension[extensionArr.length];
        System.arraycopy(extensionArr, 0, extensionArr2, 0, extensionArr.length);
        return extensionArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BodyPartID[] toBodyPartIDArray(ASN1Sequence aSN1Sequence) {
        BodyPartID[] bodyPartIDArr = new BodyPartID[aSN1Sequence.size()];
        for (int r1 = 0; r1 != aSN1Sequence.size(); r1++) {
            bodyPartIDArr[r1] = BodyPartID.getInstance(aSN1Sequence.getObjectAt(r1));
        }
        return bodyPartIDArr;
    }
}
