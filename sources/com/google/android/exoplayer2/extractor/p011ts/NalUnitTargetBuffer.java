package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

/* renamed from: com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer */
/* loaded from: classes2.dex */
final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int r2, int r3) {
        this.targetType = r2;
        byte[] bArr = new byte[r3 + 3];
        this.nalData = bArr;
        bArr[2] = 1;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void startNalUnit(int r4) {
        Assertions.checkState(!this.isFilling);
        boolean z = r4 == this.targetType;
        this.isFilling = z;
        if (z) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }

    public void appendToNalUnit(byte[] bArr, int r6, int r7) {
        if (this.isFilling) {
            int r72 = r7 - r6;
            byte[] bArr2 = this.nalData;
            int length = bArr2.length;
            int r2 = this.nalLength;
            if (length < r2 + r72) {
                this.nalData = Arrays.copyOf(bArr2, (r2 + r72) * 2);
            }
            System.arraycopy(bArr, r6, this.nalData, this.nalLength, r72);
            this.nalLength += r72;
        }
    }

    public boolean endNalUnit(int r3) {
        if (this.isFilling) {
            this.nalLength -= r3;
            this.isFilling = false;
            this.isCompleted = true;
            return true;
        }
        return false;
    }
}
