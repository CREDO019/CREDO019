package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int r1, int r2, float f, float f2, int r5) {
        this.inputSampleRateHz = r1;
        this.channelCount = r2;
        this.speed = f;
        this.pitch = f2;
        this.rate = r1 / r5;
        this.minPeriod = r1 / 400;
        int r12 = r1 / 65;
        this.maxPeriod = r12;
        int r13 = r12 * 2;
        this.maxRequiredFrameCount = r13;
        this.downSampleBuffer = new short[r13];
        this.inputBuffer = new short[r13 * r2];
        this.outputBuffer = new short[r13 * r2];
        this.pitchBuffer = new short[r13 * r2];
    }

    public int getPendingInputBytes() {
        return this.inputFrameCount * this.channelCount * 2;
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int r1 = this.channelCount;
        int r0 = remaining / r1;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, r0);
        this.inputBuffer = ensureSpaceForAdditionalFrames;
        shortBuffer.get(ensureSpaceForAdditionalFrames, this.inputFrameCount * this.channelCount, ((r1 * r0) * 2) / 2);
        this.inputFrameCount += r0;
        processStreamInput();
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.channelCount, this.outputFrameCount);
        shortBuffer.put(this.outputBuffer, 0, this.channelCount * min);
        int r5 = this.outputFrameCount - min;
        this.outputFrameCount = r5;
        short[] sArr = this.outputBuffer;
        int r2 = this.channelCount;
        System.arraycopy(sArr, min * r2, sArr, 0, r5 * r2);
    }

    public void queueEndOfStream() {
        int r4;
        int r0 = this.inputFrameCount;
        float f = this.speed;
        float f2 = this.pitch;
        int r2 = this.outputFrameCount + ((int) ((((r0 / (f / f2)) + this.pitchFrameCount) / (this.rate * f2)) + 0.5f));
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, r0, (this.maxRequiredFrameCount * 2) + r0);
        int r3 = 0;
        while (true) {
            r4 = this.maxRequiredFrameCount;
            int r6 = this.channelCount;
            if (r3 >= r4 * 2 * r6) {
                break;
            }
            this.inputBuffer[(r6 * r0) + r3] = 0;
            r3++;
        }
        this.inputFrameCount += r4 * 2;
        processStreamInput();
        if (this.outputFrameCount > r2) {
            this.outputFrameCount = r2;
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
    }

    public int getOutputSize() {
        return this.outputFrameCount * this.channelCount * 2;
    }

    private short[] ensureSpaceForAdditionalFrames(short[] sArr, int r4, int r5) {
        int length = sArr.length;
        int r1 = this.channelCount;
        int r0 = length / r1;
        return r4 + r5 <= r0 ? sArr : Arrays.copyOf(sArr, (((r0 * 3) / 2) + r5) * r1);
    }

    private void removeProcessedInputFrames(int r5) {
        int r0 = this.inputFrameCount - r5;
        short[] sArr = this.inputBuffer;
        int r2 = this.channelCount;
        System.arraycopy(sArr, r5 * r2, sArr, 0, r2 * r0);
        this.inputFrameCount = r0;
    }

    private void copyToOutput(short[] sArr, int r5, int r6) {
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r6);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int r1 = this.channelCount;
        System.arraycopy(sArr, r5 * r1, ensureSpaceForAdditionalFrames, this.outputFrameCount * r1, r1 * r6);
        this.outputFrameCount += r6;
    }

    private int copyInputToOutput(int r3) {
        int min = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, r3, min);
        this.remainingInputToCopyFrameCount -= min;
        return min;
    }

    private void downSampleInput(short[] sArr, int r8, int r9) {
        int r0 = this.maxRequiredFrameCount / r9;
        int r1 = this.channelCount;
        int r92 = r9 * r1;
        int r82 = r8 * r1;
        for (int r2 = 0; r2 < r0; r2++) {
            int r4 = 0;
            for (int r3 = 0; r3 < r92; r3++) {
                r4 += sArr[(r2 * r92) + r82 + r3];
            }
            this.downSampleBuffer[r2] = (short) (r4 / r92);
        }
    }

    private int findPitchPeriodInRange(short[] sArr, int r11, int r12, int r13) {
        int r112 = r11 * this.channelCount;
        int r0 = 1;
        int r2 = 255;
        int r3 = 0;
        int r4 = 0;
        while (r12 <= r13) {
            int r6 = 0;
            for (int r5 = 0; r5 < r12; r5++) {
                r6 += Math.abs(sArr[r112 + r5] - sArr[(r112 + r12) + r5]);
            }
            if (r6 * r3 < r0 * r12) {
                r3 = r12;
                r0 = r6;
            }
            if (r6 * r2 > r4 * r12) {
                r2 = r12;
                r4 = r6;
            }
            r12++;
        }
        this.minDiff = r0 / r3;
        this.maxDiff = r4 / r2;
        return r3;
    }

    private boolean previousPeriodBetter(int r3, int r4) {
        return r3 != 0 && this.prevPeriod != 0 && r4 <= r3 * 3 && r3 * 2 > this.prevMinDiff * 3;
    }

    private int findPitchPeriod(short[] sArr, int r8) {
        int r7;
        int r0 = this.inputSampleRateHz;
        int r02 = r0 > AMDF_FREQUENCY ? r0 / AMDF_FREQUENCY : 1;
        if (this.channelCount == 1 && r02 == 1) {
            r7 = findPitchPeriodInRange(sArr, r8, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, r8, r02);
            int findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / r02, this.maxPeriod / r02);
            if (r02 != 1) {
                int r1 = findPitchPeriodInRange * r02;
                int r03 = r02 * 4;
                int r3 = r1 - r03;
                int r12 = r1 + r03;
                int r04 = this.minPeriod;
                if (r3 < r04) {
                    r3 = r04;
                }
                int r05 = this.maxPeriod;
                if (r12 > r05) {
                    r12 = r05;
                }
                if (this.channelCount == 1) {
                    r7 = findPitchPeriodInRange(sArr, r8, r3, r12);
                } else {
                    downSampleInput(sArr, r8, 1);
                    r7 = findPitchPeriodInRange(this.downSampleBuffer, 0, r3, r12);
                }
            } else {
                r7 = findPitchPeriodInRange;
            }
        }
        int r82 = previousPeriodBetter(this.minDiff, this.maxDiff) ? this.prevPeriod : r7;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = r7;
        return r82;
    }

    private void moveNewSamplesToPitchBuffer(int r7) {
        int r0 = this.outputFrameCount - r7;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, r0);
        this.pitchBuffer = ensureSpaceForAdditionalFrames;
        short[] sArr = this.outputBuffer;
        int r3 = this.channelCount;
        System.arraycopy(sArr, r7 * r3, ensureSpaceForAdditionalFrames, this.pitchFrameCount * r3, r3 * r0);
        this.outputFrameCount = r7;
        this.pitchFrameCount += r0;
    }

    private void removePitchFrames(int r6) {
        if (r6 == 0) {
            return;
        }
        short[] sArr = this.pitchBuffer;
        int r1 = this.channelCount;
        System.arraycopy(sArr, r6 * r1, sArr, 0, (this.pitchFrameCount - r6) * r1);
        this.pitchFrameCount -= r6;
    }

    private short interpolate(short[] sArr, int r4, int r5, int r6) {
        short s = sArr[r4];
        short s2 = sArr[r4 + this.channelCount];
        int r42 = this.newRatePosition * r5;
        int r52 = this.oldRatePosition;
        int r1 = r52 * r6;
        int r53 = (r52 + 1) * r6;
        int r43 = r53 - r42;
        int r54 = r53 - r1;
        return (short) (((s * r43) + ((r54 - r43) * s2)) / r54);
    }

    private void adjustRate(float f, int r10) {
        int r2;
        int r5;
        if (this.outputFrameCount == r10) {
            return;
        }
        int r0 = this.inputSampleRateHz;
        int r9 = (int) (r0 / f);
        while (true) {
            if (r9 <= 16384 && r0 <= 16384) {
                break;
            }
            r9 /= 2;
            r0 /= 2;
        }
        moveNewSamplesToPitchBuffer(r10);
        int r1 = 0;
        while (true) {
            int r22 = this.pitchFrameCount;
            if (r1 < r22 - 1) {
                while (true) {
                    r2 = this.oldRatePosition;
                    int r3 = (r2 + 1) * r9;
                    r5 = this.newRatePosition;
                    if (r3 <= r5 * r0) {
                        break;
                    }
                    this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                    int r23 = 0;
                    while (true) {
                        int r32 = this.channelCount;
                        if (r23 < r32) {
                            this.outputBuffer[(this.outputFrameCount * r32) + r23] = interpolate(this.pitchBuffer, (r32 * r1) + r23, r0, r9);
                            r23++;
                        }
                    }
                    this.newRatePosition++;
                    this.outputFrameCount++;
                }
                int r24 = r2 + 1;
                this.oldRatePosition = r24;
                if (r24 == r0) {
                    this.oldRatePosition = 0;
                    Assertions.checkState(r5 == r9);
                    this.newRatePosition = 0;
                }
                r1++;
            } else {
                removePitchFrames(r22 - 1);
                return;
            }
        }
    }

    private int skipPitchPeriod(short[] sArr, int r10, float f, int r12) {
        int r11;
        if (f >= 2.0f) {
            r11 = (int) (r12 / (f - 1.0f));
        } else {
            this.remainingInputToCopyFrameCount = (int) ((r12 * (2.0f - f)) / (f - 1.0f));
            r11 = r12;
        }
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r11);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        overlapAdd(r11, this.channelCount, ensureSpaceForAdditionalFrames, this.outputFrameCount, sArr, r10, sArr, r10 + r12);
        this.outputFrameCount += r11;
        return r11;
    }

    private int insertPitchPeriod(short[] sArr, int r11, float f, int r13) {
        int r12;
        if (f < 0.5f) {
            r12 = (int) ((r13 * f) / (1.0f - f));
        } else {
            this.remainingInputToCopyFrameCount = (int) ((r13 * ((2.0f * f) - 1.0f)) / (1.0f - f));
            r12 = r13;
        }
        int r8 = r13 + r12;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, r8);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int r1 = this.channelCount;
        System.arraycopy(sArr, r11 * r1, ensureSpaceForAdditionalFrames, this.outputFrameCount * r1, r1 * r13);
        overlapAdd(r12, this.channelCount, this.outputBuffer, this.outputFrameCount + r13, sArr, r11 + r13, sArr, r11);
        this.outputFrameCount += r8;
        return r12;
    }

    private void changeSpeed(float f) {
        int insertPitchPeriod;
        int r0 = this.inputFrameCount;
        if (r0 < this.maxRequiredFrameCount) {
            return;
        }
        int r1 = 0;
        do {
            if (this.remainingInputToCopyFrameCount > 0) {
                insertPitchPeriod = copyInputToOutput(r1);
            } else {
                int findPitchPeriod = findPitchPeriod(this.inputBuffer, r1);
                if (f > 1.0d) {
                    insertPitchPeriod = findPitchPeriod + skipPitchPeriod(this.inputBuffer, r1, f, findPitchPeriod);
                } else {
                    insertPitchPeriod = insertPitchPeriod(this.inputBuffer, r1, f, findPitchPeriod);
                }
            }
            r1 += insertPitchPeriod;
        } while (this.maxRequiredFrameCount + r1 <= r0);
        removeProcessedInputFrames(r1);
    }

    private void processStreamInput() {
        int r0 = this.outputFrameCount;
        float f = this.speed;
        float f2 = this.pitch;
        float f3 = f / f2;
        float f4 = this.rate * f2;
        double d = f3;
        if (d > 1.00001d || d < 0.99999d) {
            changeSpeed(f3);
        } else {
            copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f4 != 1.0f) {
            adjustRate(f4, r0);
        }
    }

    private static void overlapAdd(int r8, int r9, short[] sArr, int r11, short[] sArr2, int r13, short[] sArr3, int r15) {
        for (int r1 = 0; r1 < r9; r1++) {
            int r2 = (r11 * r9) + r1;
            int r3 = (r15 * r9) + r1;
            int r4 = (r13 * r9) + r1;
            for (int r5 = 0; r5 < r8; r5++) {
                sArr[r2] = (short) (((sArr2[r4] * (r8 - r5)) + (sArr3[r3] * r5)) / r8);
                r2 += r9;
                r4 += r9;
                r3 += r9;
            }
        }
    }
}
