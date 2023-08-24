package org.bouncycastle.pqc.crypto.lms;

/* loaded from: classes3.dex */
public interface LMSContextBasedVerifier {
    LMSContext generateLMSContext(byte[] bArr);

    boolean verify(LMSContext lMSContext);
}
