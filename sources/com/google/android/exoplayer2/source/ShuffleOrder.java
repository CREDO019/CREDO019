package com.google.android.exoplayer2.source;

import java.util.Arrays;
import java.util.Random;

/* loaded from: classes2.dex */
public interface ShuffleOrder {
    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int r1, int r2);

    ShuffleOrder cloneAndRemove(int r1, int r2);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int r1);

    int getPreviousIndex(int r1);

    /* loaded from: classes2.dex */
    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int r2) {
            this(r2, new Random());
        }

        public DefaultShuffleOrder(int r2, long j) {
            this(r2, new Random(j));
        }

        public DefaultShuffleOrder(int[] r2, long j) {
            this(Arrays.copyOf(r2, r2.length), new Random(j));
        }

        private DefaultShuffleOrder(int r1, Random random) {
            this(createShuffledList(r1, random), random);
        }

        private DefaultShuffleOrder(int[] r3, Random random) {
            this.shuffled = r3;
            this.random = random;
            this.indexInShuffled = new int[r3.length];
            for (int r4 = 0; r4 < r3.length; r4++) {
                this.indexInShuffled[r3[r4]] = r4;
            }
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLength() {
            return this.shuffled.length;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getNextIndex(int r3) {
            int r32 = this.indexInShuffled[r3] + 1;
            int[] r0 = this.shuffled;
            if (r32 < r0.length) {
                return r0[r32];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getPreviousIndex(int r2) {
            int r22 = this.indexInShuffled[r2] - 1;
            if (r22 >= 0) {
                return this.shuffled[r22];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLastIndex() {
            int[] r0 = this.shuffled;
            if (r0.length > 0) {
                return r0[r0.length - 1];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getFirstIndex() {
            int[] r0 = this.shuffled;
            if (r0.length > 0) {
                return r0[0];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int r9, int r10) {
            int[] r0 = new int[r10];
            int[] r1 = new int[r10];
            int r2 = 0;
            int r3 = 0;
            while (r3 < r10) {
                r0[r3] = this.random.nextInt(this.shuffled.length + 1);
                int r5 = r3 + 1;
                int nextInt = this.random.nextInt(r5);
                r1[r3] = r1[nextInt];
                r1[nextInt] = r3 + r9;
                r3 = r5;
            }
            Arrays.sort(r0);
            int[] r32 = new int[this.shuffled.length + r10];
            int r4 = 0;
            int r52 = 0;
            while (true) {
                int[] r6 = this.shuffled;
                if (r2 < r6.length + r10) {
                    if (r4 < r10 && r52 == r0[r4]) {
                        r32[r2] = r1[r4];
                        r4++;
                    } else {
                        int r7 = r52 + 1;
                        r32[r2] = r6[r52];
                        if (r32[r2] >= r9) {
                            r32[r2] = r32[r2] + r10;
                        }
                        r52 = r7;
                    }
                    r2++;
                } else {
                    return new DefaultShuffleOrder(r32, new Random(this.random.nextLong()));
                }
            }
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int r8, int r9) {
            int r0 = r9 - r8;
            int[] r1 = new int[this.shuffled.length - r0];
            int r2 = 0;
            int r3 = 0;
            while (true) {
                int[] r4 = this.shuffled;
                if (r2 < r4.length) {
                    if (r4[r2] < r8 || r4[r2] >= r9) {
                        r1[r2 - r3] = r4[r2] >= r8 ? r4[r2] - r0 : r4[r2];
                    } else {
                        r3++;
                    }
                    r2++;
                } else {
                    return new DefaultShuffleOrder(r1, new Random(this.random.nextLong()));
                }
            }
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        private static int[] createShuffledList(int r5, Random random) {
            int[] r0 = new int[r5];
            int r1 = 0;
            while (r1 < r5) {
                int r2 = r1 + 1;
                int nextInt = random.nextInt(r2);
                r0[r1] = r0[nextInt];
                r0[nextInt] = r1;
                r1 = r2;
            }
            return r0;
        }
    }

    /* loaded from: classes2.dex */
    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getPreviousIndex(int r2) {
            int r22 = r2 - 1;
            if (r22 >= 0) {
                return r22;
            }
            return -1;
        }

        public UnshuffledShuffleOrder(int r1) {
            this.length = r1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLength() {
            return this.length;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getNextIndex(int r2) {
            int r22 = r2 + 1;
            if (r22 < this.length) {
                return r22;
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLastIndex() {
            int r0 = this.length;
            if (r0 > 0) {
                return r0 - 1;
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getFirstIndex() {
            return this.length > 0 ? 0 : -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int r2, int r3) {
            return new UnshuffledShuffleOrder(this.length + r3);
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int r3, int r4) {
            return new UnshuffledShuffleOrder((this.length - r4) + r3);
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }
    }
}
