package org.bouncycastle.pqc.crypto.sphincs;

/* loaded from: classes3.dex */
class Tree {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class leafaddr {
        int level;
        long subleaf;
        long subtree;

        public leafaddr() {
        }

        public leafaddr(leafaddr leafaddrVar) {
            this.level = leafaddrVar.level;
            this.subtree = leafaddrVar.subtree;
            this.subleaf = leafaddrVar.subleaf;
        }
    }

    Tree() {
    }

    static void gen_leaf_wots(HashFunctions hashFunctions, byte[] bArr, int r15, byte[] bArr2, int r17, byte[] bArr3, leafaddr leafaddrVar) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[2144];
        Wots wots = new Wots();
        Seed.get_seed(hashFunctions, bArr4, 0, bArr3, leafaddrVar);
        wots.wots_pkgen(hashFunctions, bArr5, 0, bArr4, 0, bArr2, r17);
        l_tree(hashFunctions, bArr, r15, bArr5, 0, bArr2, r17);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l_tree(HashFunctions hashFunctions, byte[] bArr, int r16, byte[] bArr2, int r18, byte[] bArr3, int r20) {
        int r0;
        int r10 = 67;
        for (int r11 = 0; r11 < 7; r11++) {
            int r13 = 0;
            while (true) {
                r0 = r10 >>> 1;
                if (r13 >= r0) {
                    break;
                }
                hashFunctions.hash_2n_n_mask(bArr2, r18 + (r13 * 32), bArr2, r18 + (r13 * 2 * 32), bArr3, r20 + (r11 * 2 * 32));
                r13++;
            }
            if ((r10 & 1) != 0) {
                System.arraycopy(bArr2, r18 + ((r10 - 1) * 32), bArr2, (r0 * 32) + r18, 32);
                r0++;
            }
            r10 = r0;
        }
        System.arraycopy(bArr2, r18, bArr, r16, 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void treehash(HashFunctions hashFunctions, byte[] bArr, int r19, int r20, byte[] bArr2, leafaddr leafaddrVar, byte[] bArr3, int r24) {
        leafaddr leafaddrVar2 = new leafaddr(leafaddrVar);
        int r0 = r20 + 1;
        byte[] bArr4 = new byte[r0 * 32];
        int[] r14 = new int[r0];
        int r13 = 1;
        int r12 = (int) (leafaddrVar2.subleaf + (1 << r20));
        int r8 = 0;
        while (leafaddrVar2.subleaf < r12) {
            gen_leaf_wots(hashFunctions, bArr4, r8 * 32, bArr3, r24, bArr2, leafaddrVar2);
            r14[r8] = 0;
            int r02 = r8 + r13;
            while (r02 > r13) {
                int r1 = r02 - 1;
                int r3 = r02 - 2;
                if (r14[r1] == r14[r3]) {
                    int r2 = r3 * 32;
                    int[] r6 = r14;
                    hashFunctions.hash_2n_n_mask(bArr4, r2, bArr4, r2, bArr3, r24 + ((r14[r1] + 7) * 2 * 32));
                    r6[r3] = r6[r3] + 1;
                    r02--;
                    r12 = r12;
                    r14 = r6;
                    r13 = 1;
                }
            }
            leafaddrVar2.subleaf++;
            r8 = r02;
            r12 = r12;
            r14 = r14;
            r13 = 1;
        }
        for (int r03 = 0; r03 < 32; r03++) {
            bArr[r19 + r03] = bArr4[r03];
        }
    }
}
