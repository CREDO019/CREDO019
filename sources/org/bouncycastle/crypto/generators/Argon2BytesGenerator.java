package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Argon2BytesGenerator {
    private static final int ARGON2_ADDRESSES_IN_BLOCK = 128;
    private static final int ARGON2_BLOCK_SIZE = 1024;
    private static final int ARGON2_PREHASH_DIGEST_LENGTH = 64;
    private static final int ARGON2_PREHASH_SEED_LENGTH = 72;
    private static final int ARGON2_QWORDS_IN_BLOCK = 128;
    private static final int ARGON2_SYNC_POINTS = 4;
    private static final long M32L = 4294967295L;
    private static final int MAX_PARALLELISM = 16777216;
    private static final int MIN_ITERATIONS = 1;
    private static final int MIN_OUTLEN = 4;
    private static final int MIN_PARALLELISM = 1;
    private static final byte[] ZERO_BYTES = new byte[4];
    private int laneLength;
    private Block[] memory;
    private Argon2Parameters parameters;
    private int segmentLength;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Block {
        private static final int SIZE = 128;

        /* renamed from: v */
        private final long[] f1985v;

        private Block() {
            this.f1985v = new long[128];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void copyBlock(Block block) {
            System.arraycopy(block.f1985v, 0, this.f1985v, 0, 128);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void xor(Block block, Block block2) {
            long[] jArr = this.f1985v;
            long[] jArr2 = block.f1985v;
            long[] jArr3 = block2.f1985v;
            for (int r1 = 0; r1 < 128; r1++) {
                jArr[r1] = jArr2[r1] ^ jArr3[r1];
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void xorWith(Block block) {
            long[] jArr = this.f1985v;
            long[] jArr2 = block.f1985v;
            for (int r1 = 0; r1 < 128; r1++) {
                jArr[r1] = jArr[r1] ^ jArr2[r1];
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void xorWith(Block block, Block block2) {
            long[] jArr = this.f1985v;
            long[] jArr2 = block.f1985v;
            long[] jArr3 = block2.f1985v;
            for (int r1 = 0; r1 < 128; r1++) {
                jArr[r1] = jArr[r1] ^ (jArr2[r1] ^ jArr3[r1]);
            }
        }

        public Block clear() {
            Arrays.fill(this.f1985v, 0L);
            return this;
        }

        void fromBytes(byte[] bArr) {
            if (bArr.length < 1024) {
                throw new IllegalArgumentException("input shorter than blocksize");
            }
            Pack.littleEndianToLong(bArr, 0, this.f1985v);
        }

        void toBytes(byte[] bArr) {
            if (bArr.length < 1024) {
                throw new IllegalArgumentException("output shorter than blocksize");
            }
            Pack.longToLittleEndian(this.f1985v, bArr, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class FillBlock {

        /* renamed from: R */
        Block f1986R;

        /* renamed from: Z */
        Block f1987Z;
        Block addressBlock;
        Block inputBlock;

        private FillBlock() {
            this.f1986R = new Block();
            this.f1987Z = new Block();
            this.addressBlock = new Block();
            this.inputBlock = new Block();
        }

        private void applyBlake() {
            for (int r2 = 0; r2 < 8; r2++) {
                int r3 = r2 * 16;
                Argon2BytesGenerator.roundFunction(this.f1987Z, r3, r3 + 1, r3 + 2, r3 + 3, r3 + 4, r3 + 5, r3 + 6, r3 + 7, r3 + 8, r3 + 9, r3 + 10, r3 + 11, r3 + 12, r3 + 13, r3 + 14, r3 + 15);
            }
            for (int r1 = 0; r1 < 8; r1++) {
                int r22 = r1 * 2;
                Argon2BytesGenerator.roundFunction(this.f1987Z, r22, r22 + 1, r22 + 16, r22 + 17, r22 + 32, r22 + 33, r22 + 48, r22 + 49, r22 + 64, r22 + 65, r22 + 80, r22 + 81, r22 + 96, r22 + 97, r22 + 112, r22 + 113);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillBlock(Block block, Block block2) {
            this.f1987Z.copyBlock(block);
            applyBlake();
            block2.xor(block, this.f1987Z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillBlock(Block block, Block block2, Block block3) {
            this.f1986R.xor(block, block2);
            this.f1987Z.copyBlock(this.f1986R);
            applyBlake();
            block3.xor(this.f1986R, this.f1987Z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fillBlockWithXor(Block block, Block block2, Block block3) {
            this.f1986R.xor(block, block2);
            this.f1987Z.copyBlock(this.f1986R);
            applyBlake();
            block3.xorWith(this.f1986R, this.f1987Z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Position {
        int lane;
        int pass;
        int slice;

        Position() {
        }
    }

    /* renamed from: F */
    private static void m32F(long[] jArr, int r2, int r3, int r4, int r5) {
        quarterRound(jArr, r2, r3, r5, 32);
        quarterRound(jArr, r4, r5, r3, 24);
        quarterRound(jArr, r2, r3, r5, 16);
        quarterRound(jArr, r4, r5, r3, 63);
    }

    private static void addByteString(byte[] bArr, Digest digest, byte[] bArr2) {
        if (bArr2 == null) {
            digest.update(ZERO_BYTES, 0, 4);
            return;
        }
        Pack.intToLittleEndian(bArr2.length, bArr, 0);
        digest.update(bArr, 0, 4);
        digest.update(bArr2, 0, bArr2.length);
    }

    private void digest(byte[] bArr, byte[] bArr2, int r8, int r9) {
        Block block = this.memory[this.laneLength - 1];
        for (int r1 = 1; r1 < this.parameters.getLanes(); r1++) {
            int r3 = this.laneLength;
            block.xorWith(this.memory[(r1 * r3) + (r3 - 1)]);
        }
        block.toBytes(bArr);
        hash(bArr, bArr2, r8, r9);
    }

    private void doInit(Argon2Parameters argon2Parameters) {
        int memory = argon2Parameters.getMemory();
        if (memory < argon2Parameters.getLanes() * 8) {
            memory = argon2Parameters.getLanes() * 8;
        }
        int lanes = memory / (argon2Parameters.getLanes() * 4);
        this.segmentLength = lanes;
        this.laneLength = lanes * 4;
        initMemory(lanes * argon2Parameters.getLanes() * 4);
    }

    private void fillFirstBlocks(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[72];
        System.arraycopy(bArr2, 0, bArr3, 0, 64);
        bArr3[64] = 1;
        for (int r2 = 0; r2 < this.parameters.getLanes(); r2++) {
            Pack.intToLittleEndian(r2, bArr2, 68);
            Pack.intToLittleEndian(r2, bArr3, 68);
            hash(bArr2, bArr, 0, 1024);
            this.memory[(this.laneLength * r2) + 0].fromBytes(bArr);
            hash(bArr3, bArr, 0, 1024);
            this.memory[(this.laneLength * r2) + 1].fromBytes(bArr);
        }
    }

    private void fillMemoryBlocks() {
        FillBlock fillBlock = new FillBlock();
        Position position = new Position();
        for (int r3 = 0; r3 < this.parameters.getIterations(); r3++) {
            position.pass = r3;
            for (int r4 = 0; r4 < 4; r4++) {
                position.slice = r4;
                for (int r5 = 0; r5 < this.parameters.getLanes(); r5++) {
                    position.lane = r5;
                    fillSegment(fillBlock, position);
                }
            }
        }
    }

    private void fillSegment(FillBlock fillBlock, Position position) {
        Block block;
        Block block2;
        boolean isDataIndependentAddressing = isDataIndependentAddressing(position);
        int startingIndex = getStartingIndex(position);
        int r1 = (position.lane * this.laneLength) + (position.slice * this.segmentLength) + startingIndex;
        int prevOffset = getPrevOffset(r1);
        if (isDataIndependentAddressing) {
            Block clear = fillBlock.addressBlock.clear();
            Block clear2 = fillBlock.inputBlock.clear();
            initAddressBlocks(fillBlock, position, clear2, clear);
            block = clear;
            block2 = clear2;
        } else {
            block = null;
            block2 = null;
        }
        boolean isWithXor = isWithXor(position);
        int r14 = startingIndex;
        int r15 = r1;
        int r16 = prevOffset;
        while (r14 < this.segmentLength) {
            long pseudoRandom = getPseudoRandom(fillBlock, r14, block, block2, r16, isDataIndependentAddressing);
            int refLane = getRefLane(position, pseudoRandom);
            int refColumn = getRefColumn(position, r14, pseudoRandom, refLane == position.lane);
            Block[] blockArr = this.memory;
            Block block3 = blockArr[r16];
            Block block4 = blockArr[(this.laneLength * refLane) + refColumn];
            Block block5 = blockArr[r15];
            if (isWithXor) {
                fillBlock.fillBlockWithXor(block3, block4, block5);
            } else {
                fillBlock.fillBlock(block3, block4, block5);
            }
            r14++;
            r16 = r15;
            r15++;
        }
    }

    private int getPrevOffset(int r3) {
        int r0 = this.laneLength;
        return r3 % r0 == 0 ? (r3 + r0) - 1 : r3 - 1;
    }

    private long getPseudoRandom(FillBlock fillBlock, int r2, Block block, Block block2, int r5, boolean z) {
        if (z) {
            int r22 = r2 % 128;
            if (r22 == 0) {
                nextAddresses(fillBlock, block2, block);
            }
            return block.f1985v[r22];
        }
        return this.memory[r5].f1985v[0];
    }

    private int getRefColumn(Position position, int r7, long j, boolean z) {
        int r72;
        int r6;
        int r0 = position.pass;
        int r2 = 0;
        int r62 = position.slice;
        if (r0 != 0) {
            int r02 = this.segmentLength;
            int r3 = this.laneLength;
            int r63 = ((r62 + 1) * r02) % r3;
            int r32 = r3 - r02;
            if (z) {
                r72 = (r32 + r7) - 1;
            } else {
                r72 = r32 + (r7 != 0 ? 0 : -1);
            }
            r2 = r63;
            r6 = r72;
        } else if (z) {
            r6 = ((r62 * this.segmentLength) + r7) - 1;
        } else {
            r6 = (r62 * this.segmentLength) + (r7 != 0 ? 0 : -1);
        }
        long j2 = j & 4294967295L;
        return ((int) (r2 + ((r6 - 1) - ((r6 * ((j2 * j2) >>> 32)) >>> 32)))) % this.laneLength;
    }

    private int getRefLane(Position position, long j) {
        int lanes = (int) ((j >>> 32) % this.parameters.getLanes());
        return (position.pass == 0 && position.slice == 0) ? position.lane : lanes;
    }

    private static int getStartingIndex(Position position) {
        return (position.pass == 0 && position.slice == 0) ? 2 : 0;
    }

    private void hash(byte[] bArr, byte[] bArr2, int r9, int r10) {
        byte[] bArr3 = new byte[4];
        Pack.intToLittleEndian(r10, bArr3, 0);
        if (r10 <= 64) {
            Blake2bDigest blake2bDigest = new Blake2bDigest(r10 * 8);
            blake2bDigest.update(bArr3, 0, 4);
            blake2bDigest.update(bArr, 0, bArr.length);
            blake2bDigest.doFinal(bArr2, r9);
            return;
        }
        Blake2bDigest blake2bDigest2 = new Blake2bDigest(512);
        byte[] bArr4 = new byte[64];
        blake2bDigest2.update(bArr3, 0, 4);
        blake2bDigest2.update(bArr, 0, bArr.length);
        blake2bDigest2.doFinal(bArr4, 0);
        System.arraycopy(bArr4, 0, bArr2, r9, 32);
        int r92 = r9 + 32;
        int r1 = 2;
        int r0 = ((r10 + 31) / 32) - 2;
        while (r1 <= r0) {
            blake2bDigest2.update(bArr4, 0, 64);
            blake2bDigest2.doFinal(bArr4, 0);
            System.arraycopy(bArr4, 0, bArr2, r92, 32);
            r1++;
            r92 += 32;
        }
        Blake2bDigest blake2bDigest3 = new Blake2bDigest((r10 - (r0 * 32)) * 8);
        blake2bDigest3.update(bArr4, 0, 64);
        blake2bDigest3.doFinal(bArr2, r92);
    }

    private void initAddressBlocks(FillBlock fillBlock, Position position, Block block, Block block2) {
        block.f1985v[0] = intToLong(position.pass);
        block.f1985v[1] = intToLong(position.lane);
        block.f1985v[2] = intToLong(position.slice);
        block.f1985v[3] = intToLong(this.memory.length);
        block.f1985v[4] = intToLong(this.parameters.getIterations());
        block.f1985v[5] = intToLong(this.parameters.getType());
        if (position.pass == 0 && position.slice == 0) {
            nextAddresses(fillBlock, block, block2);
        }
    }

    private void initMemory(int r4) {
        this.memory = new Block[r4];
        int r42 = 0;
        while (true) {
            Block[] blockArr = this.memory;
            if (r42 >= blockArr.length) {
                return;
            }
            blockArr[r42] = new Block();
            r42++;
        }
    }

    private void initialize(byte[] bArr, byte[] bArr2, int r7) {
        Blake2bDigest blake2bDigest = new Blake2bDigest(512);
        Pack.intToLittleEndian(new int[]{this.parameters.getLanes(), r7, this.parameters.getMemory(), this.parameters.getIterations(), this.parameters.getVersion(), this.parameters.getType()}, bArr, 0);
        blake2bDigest.update(bArr, 0, 24);
        addByteString(bArr, blake2bDigest, bArr2);
        addByteString(bArr, blake2bDigest, this.parameters.getSalt());
        addByteString(bArr, blake2bDigest, this.parameters.getSecret());
        addByteString(bArr, blake2bDigest, this.parameters.getAdditional());
        byte[] bArr3 = new byte[72];
        blake2bDigest.doFinal(bArr3, 0);
        fillFirstBlocks(bArr, bArr3);
    }

    private long intToLong(int r5) {
        return r5 & 4294967295L;
    }

    private boolean isDataIndependentAddressing(Position position) {
        if (this.parameters.getType() != 1) {
            return this.parameters.getType() == 2 && position.pass == 0 && position.slice < 2;
        }
        return true;
    }

    private boolean isWithXor(Position position) {
        return (position.pass == 0 || this.parameters.getVersion() == 16) ? false : true;
    }

    private void nextAddresses(FillBlock fillBlock, Block block, Block block2) {
        long[] jArr = block.f1985v;
        jArr[6] = jArr[6] + 1;
        fillBlock.fillBlock(block, block2);
        fillBlock.fillBlock(block2, block2);
    }

    private static void quarterRound(long[] jArr, int r13, int r14, int r15, int r16) {
        long j = jArr[r13];
        long j2 = jArr[r14];
        long j3 = j + j2 + ((j & 4294967295L) * 2 * (4294967295L & j2));
        long rotateRight = Longs.rotateRight(jArr[r15] ^ j3, r16);
        jArr[r13] = j3;
        jArr[r15] = rotateRight;
    }

    private void reset() {
        if (this.memory == null) {
            return;
        }
        int r0 = 0;
        while (true) {
            Block[] blockArr = this.memory;
            if (r0 >= blockArr.length) {
                return;
            }
            Block block = blockArr[r0];
            if (block != null) {
                block.clear();
            }
            r0++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void roundFunction(Block block, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32) {
        long[] jArr = block.f1985v;
        m32F(jArr, r17, r21, r25, r29);
        m32F(jArr, r18, r22, r26, r30);
        m32F(jArr, r19, r23, r27, r31);
        m32F(jArr, r20, r24, r28, r32);
        m32F(jArr, r17, r22, r27, r32);
        m32F(jArr, r18, r23, r28, r29);
        m32F(jArr, r19, r24, r25, r30);
        m32F(jArr, r20, r21, r26, r31);
    }

    public int generateBytes(byte[] bArr, byte[] bArr2) {
        return generateBytes(bArr, bArr2, 0, bArr2.length);
    }

    public int generateBytes(byte[] bArr, byte[] bArr2, int r4, int r5) {
        if (r5 >= 4) {
            byte[] bArr3 = new byte[1024];
            initialize(bArr3, bArr, r5);
            fillMemoryBlocks();
            digest(bArr3, bArr2, r4, r5);
            reset();
            return r5;
        }
        throw new IllegalStateException("output length less than 4");
    }

    public int generateBytes(char[] cArr, byte[] bArr) {
        return generateBytes(this.parameters.getCharToByteConverter().convert(cArr), bArr);
    }

    public int generateBytes(char[] cArr, byte[] bArr, int r4, int r5) {
        return generateBytes(this.parameters.getCharToByteConverter().convert(cArr), bArr, r4, r5);
    }

    public void init(Argon2Parameters argon2Parameters) {
        this.parameters = argon2Parameters;
        if (argon2Parameters.getLanes() < 1) {
            throw new IllegalStateException("lanes must be greater than 1");
        }
        if (argon2Parameters.getLanes() > 16777216) {
            throw new IllegalStateException("lanes must be less than 16777216");
        }
        if (argon2Parameters.getMemory() >= argon2Parameters.getLanes() * 2) {
            if (argon2Parameters.getIterations() < 1) {
                throw new IllegalStateException("iterations is less than: 1");
            }
            doInit(argon2Parameters);
            return;
        }
        throw new IllegalStateException("memory is less than: " + (argon2Parameters.getLanes() * 2) + " expected " + (argon2Parameters.getLanes() * 2));
    }
}
