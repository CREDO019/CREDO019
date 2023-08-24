package org.bouncycastle.util.p041io.pem;

import java.io.IOException;

/* renamed from: org.bouncycastle.util.io.pem.PemObjectParser */
/* loaded from: classes4.dex */
public interface PemObjectParser {
    Object parseObject(PemObject pemObject) throws IOException;
}
