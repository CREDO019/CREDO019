package org.bouncycastle.crypto.prng;

/* loaded from: classes5.dex */
public class ThreadedSeedGenerator {

    /* loaded from: classes5.dex */
    private class SeedGenerator implements Runnable {
        private volatile int counter;
        private volatile boolean stop;

        private SeedGenerator() {
            this.counter = 0;
            this.stop = false;
        }

        public byte[] generateSeed(int r7, boolean z) {
            Thread thread = new Thread(this);
            byte[] bArr = new byte[r7];
            this.counter = 0;
            this.stop = false;
            thread.start();
            if (!z) {
                r7 *= 8;
            }
            int r0 = 0;
            for (int r2 = 0; r2 < r7; r2++) {
                while (this.counter == r0) {
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException unused) {
                    }
                }
                r0 = this.counter;
                if (z) {
                    bArr[r2] = (byte) (r0 & 255);
                } else {
                    int r4 = r2 / 8;
                    bArr[r4] = (byte) ((bArr[r4] << 1) | (r0 & 1));
                }
            }
            this.stop = true;
            return bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.stop) {
                this.counter++;
            }
        }
    }

    public byte[] generateSeed(int r3, boolean z) {
        return new SeedGenerator().generateSeed(r3, z);
    }
}
