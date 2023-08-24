package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ByteQuadsCanonicalizer {
    private static final int DEFAULT_T_SIZE = 64;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    private static final int MAX_T_SIZE = 65536;
    private static final int MIN_HASH_SIZE = 16;
    private static final int MULT = 33;
    private static final int MULT2 = 65599;
    private static final int MULT3 = 31;
    private int _count;
    private final boolean _failOnDoS;
    private int[] _hashArea;
    private boolean _hashShared;
    private int _hashSize;
    private boolean _intern;
    private int _longNameOffset;
    private String[] _names;
    private transient boolean _needRehash;
    private final ByteQuadsCanonicalizer _parent;
    private int _secondaryStart;
    private final int _seed;
    private int _spilloverEnd;
    private final AtomicReference<TableInfo> _tableInfo;
    private int _tertiaryShift;
    private int _tertiaryStart;

    static int _calcTertiaryShift(int r1) {
        int r12 = r1 >> 2;
        if (r12 < 64) {
            return 4;
        }
        if (r12 <= 256) {
            return 5;
        }
        return r12 <= 1024 ? 6 : 7;
    }

    private ByteQuadsCanonicalizer(int r2, boolean z, int r4, boolean z2) {
        this._parent = null;
        this._seed = r4;
        this._intern = z;
        this._failOnDoS = z2;
        int r3 = 16;
        if (r2 < 16) {
            r2 = 16;
        } else if (((r2 - 1) & r2) != 0) {
            while (r3 < r2) {
                r3 += r3;
            }
            r2 = r3;
        }
        this._tableInfo = new AtomicReference<>(TableInfo.createInitial(r2));
    }

    private ByteQuadsCanonicalizer(ByteQuadsCanonicalizer byteQuadsCanonicalizer, boolean z, int r3, boolean z2, TableInfo tableInfo) {
        this._parent = byteQuadsCanonicalizer;
        this._seed = r3;
        this._intern = z;
        this._failOnDoS = z2;
        this._tableInfo = null;
        this._count = tableInfo.count;
        int r1 = tableInfo.size;
        this._hashSize = r1;
        int r12 = r1 << 2;
        this._secondaryStart = r12;
        this._tertiaryStart = r12 + (r12 >> 1);
        this._tertiaryShift = tableInfo.tertiaryShift;
        this._hashArea = tableInfo.mainHash;
        this._names = tableInfo.names;
        this._spilloverEnd = tableInfo.spilloverEnd;
        this._longNameOffset = tableInfo.longNameOffset;
        this._needRehash = false;
        this._hashShared = true;
    }

    public static ByteQuadsCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static ByteQuadsCanonicalizer createRoot(int r3) {
        return new ByteQuadsCanonicalizer(64, true, r3, true);
    }

    public ByteQuadsCanonicalizer makeChild(int r8) {
        return new ByteQuadsCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(r8), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(r8), this._tableInfo.get());
    }

    public void release() {
        if (this._parent == null || !maybeDirty()) {
            return;
        }
        this._parent.mergeChild(new TableInfo(this));
        this._hashShared = true;
    }

    private void mergeChild(TableInfo tableInfo) {
        int r0 = tableInfo.count;
        TableInfo tableInfo2 = this._tableInfo.get();
        if (r0 == tableInfo2.count) {
            return;
        }
        if (r0 > 6000) {
            tableInfo = TableInfo.createInitial(64);
        }
        this._tableInfo.compareAndSet(tableInfo2, tableInfo);
    }

    public int size() {
        AtomicReference<TableInfo> atomicReference = this._tableInfo;
        if (atomicReference != null) {
            return atomicReference.get().count;
        }
        return this._count;
    }

    public int bucketCount() {
        return this._hashSize;
    }

    public boolean maybeDirty() {
        return !this._hashShared;
    }

    public int hashSeed() {
        return this._seed;
    }

    public int primaryCount() {
        int r0 = this._secondaryStart;
        int r1 = 0;
        for (int r2 = 3; r2 < r0; r2 += 4) {
            if (this._hashArea[r2] != 0) {
                r1++;
            }
        }
        return r1;
    }

    public int secondaryCount() {
        int r1 = this._tertiaryStart;
        int r2 = 0;
        for (int r0 = this._secondaryStart + 3; r0 < r1; r0 += 4) {
            if (this._hashArea[r0] != 0) {
                r2++;
            }
        }
        return r2;
    }

    public int tertiaryCount() {
        int r0 = this._tertiaryStart + 3;
        int r1 = this._hashSize + r0;
        int r2 = 0;
        while (r0 < r1) {
            if (this._hashArea[r0] != 0) {
                r2++;
            }
            r0 += 4;
        }
        return r2;
    }

    public int spilloverCount() {
        return (this._spilloverEnd - _spilloverStart()) >> 2;
    }

    public int totalCount() {
        int r0 = this._hashSize << 3;
        int r2 = 0;
        for (int r1 = 3; r1 < r0; r1 += 4) {
            if (this._hashArea[r1] != 0) {
                r2++;
            }
        }
        return r2;
    }

    public String toString() {
        int primaryCount = primaryCount();
        int secondaryCount = secondaryCount();
        int tertiaryCount = tertiaryCount();
        int spilloverCount = spilloverCount();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", getClass().getName(), Integer.valueOf(this._count), Integer.valueOf(this._hashSize), Integer.valueOf(primaryCount), Integer.valueOf(secondaryCount), Integer.valueOf(tertiaryCount), Integer.valueOf(spilloverCount), Integer.valueOf(primaryCount + secondaryCount + tertiaryCount + spilloverCount), Integer.valueOf(totalCount()));
    }

    public String findName(int r7) {
        int _calcOffset = _calcOffset(calcHash(r7));
        int[] r1 = this._hashArea;
        int r2 = r1[_calcOffset + 3];
        if (r2 == 1) {
            if (r1[_calcOffset] == r7) {
                return this._names[_calcOffset >> 2];
            }
        } else if (r2 == 0) {
            return null;
        }
        int r22 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int r5 = r1[r22 + 3];
        if (r5 == 1) {
            if (r1[r22] == r7) {
                return this._names[r22 >> 2];
            }
        } else if (r5 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, r7);
    }

    public String findName(int r7, int r8) {
        int _calcOffset = _calcOffset(calcHash(r7, r8));
        int[] r1 = this._hashArea;
        int r2 = r1[_calcOffset + 3];
        if (r2 == 2) {
            if (r7 == r1[_calcOffset] && r8 == r1[_calcOffset + 1]) {
                return this._names[_calcOffset >> 2];
            }
        } else if (r2 == 0) {
            return null;
        }
        int r22 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int r5 = r1[r22 + 3];
        if (r5 == 2) {
            if (r7 == r1[r22] && r8 == r1[r22 + 1]) {
                return this._names[r22 >> 2];
            }
        } else if (r5 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, r7, r8);
    }

    public String findName(int r7, int r8, int r9) {
        int _calcOffset = _calcOffset(calcHash(r7, r8, r9));
        int[] r1 = this._hashArea;
        int r2 = r1[_calcOffset + 3];
        if (r2 == 3) {
            if (r7 == r1[_calcOffset] && r1[_calcOffset + 1] == r8 && r1[_calcOffset + 2] == r9) {
                return this._names[_calcOffset >> 2];
            }
        } else if (r2 == 0) {
            return null;
        }
        int r22 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int r5 = r1[r22 + 3];
        if (r5 == 3) {
            if (r7 == r1[r22] && r1[r22 + 1] == r8 && r1[r22 + 2] == r9) {
                return this._names[r22 >> 2];
            }
        } else if (r5 == 0) {
            return null;
        }
        return _findSecondary(_calcOffset, r7, r8, r9);
    }

    public String findName(int[] r8, int r9) {
        if (r9 < 4) {
            if (r9 == 3) {
                return findName(r8[0], r8[1], r8[2]);
            }
            if (r9 == 2) {
                return findName(r8[0], r8[1]);
            }
            return findName(r8[0]);
        }
        int calcHash = calcHash(r8, r9);
        int _calcOffset = _calcOffset(calcHash);
        int[] r3 = this._hashArea;
        int r4 = r3[_calcOffset + 3];
        if (calcHash == r3[_calcOffset] && r4 == r9 && _verifyLongName(r8, r9, r3[_calcOffset + 1])) {
            return this._names[_calcOffset >> 2];
        }
        if (r4 == 0) {
            return null;
        }
        int r42 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        int r5 = r3[r42 + 3];
        if (calcHash == r3[r42] && r5 == r9 && _verifyLongName(r8, r9, r3[r42 + 1])) {
            return this._names[r42 >> 2];
        }
        return _findSecondary(_calcOffset, calcHash, r8, r9);
    }

    private final int _calcOffset(int r2) {
        return (r2 & (this._hashSize - 1)) << 2;
    }

    private String _findSecondary(int r7, int r8) {
        int r0 = this._tertiaryStart;
        int r1 = this._tertiaryShift;
        int r02 = r0 + ((r7 >> (r1 + 2)) << r1);
        int[] r72 = this._hashArea;
        int r12 = (1 << r1) + r02;
        while (r02 < r12) {
            int r4 = r72[r02 + 3];
            if (r8 == r72[r02] && 1 == r4) {
                return this._names[r02 >> 2];
            }
            if (r4 == 0) {
                return null;
            }
            r02 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (r8 == r72[_spilloverStart] && 1 == r72[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int r7, int r8, int r9) {
        int r0 = this._tertiaryStart;
        int r1 = this._tertiaryShift;
        int r02 = r0 + ((r7 >> (r1 + 2)) << r1);
        int[] r72 = this._hashArea;
        int r12 = (1 << r1) + r02;
        while (r02 < r12) {
            int r4 = r72[r02 + 3];
            if (r8 == r72[r02] && r9 == r72[r02 + 1] && 2 == r4) {
                return this._names[r02 >> 2];
            }
            if (r4 == 0) {
                return null;
            }
            r02 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (r8 == r72[_spilloverStart] && r9 == r72[_spilloverStart + 1] && 2 == r72[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int r7, int r8, int r9, int r10) {
        int r0 = this._tertiaryStart;
        int r1 = this._tertiaryShift;
        int r02 = r0 + ((r7 >> (r1 + 2)) << r1);
        int[] r72 = this._hashArea;
        int r12 = (1 << r1) + r02;
        while (r02 < r12) {
            int r4 = r72[r02 + 3];
            if (r8 == r72[r02] && r9 == r72[r02 + 1] && r10 == r72[r02 + 2] && 3 == r4) {
                return this._names[r02 >> 2];
            }
            if (r4 == 0) {
                return null;
            }
            r02 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (r8 == r72[_spilloverStart] && r9 == r72[_spilloverStart + 1] && r10 == r72[_spilloverStart + 2] && 3 == r72[_spilloverStart + 3]) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int r6, int r7, int[] r8, int r9) {
        int r0 = this._tertiaryStart;
        int r1 = this._tertiaryShift;
        int r02 = r0 + ((r6 >> (r1 + 2)) << r1);
        int[] r62 = this._hashArea;
        int r12 = (1 << r1) + r02;
        while (r02 < r12) {
            int r3 = r62[r02 + 3];
            if (r7 == r62[r02] && r9 == r3 && _verifyLongName(r8, r9, r62[r02 + 1])) {
                return this._names[r02 >> 2];
            }
            if (r3 == 0) {
                return null;
            }
            r02 += 4;
        }
        for (int _spilloverStart = _spilloverStart(); _spilloverStart < this._spilloverEnd; _spilloverStart += 4) {
            if (r7 == r62[_spilloverStart] && r9 == r62[_spilloverStart + 3] && _verifyLongName(r8, r9, r62[_spilloverStart + 1])) {
                return this._names[_spilloverStart >> 2];
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean _verifyLongName(int[] r6, int r7, int r8) {
        /*
            r5 = this;
            int[] r0 = r5._hashArea
            r1 = 0
            r2 = 1
            switch(r7) {
                case 4: goto L42;
                case 5: goto L34;
                case 6: goto L26;
                case 7: goto L18;
                case 8: goto Lc;
                default: goto L7;
            }
        L7:
            boolean r6 = r5._verifyLongName2(r6, r7, r8)
            return r6
        Lc:
            r7 = r6[r1]
            int r3 = r8 + 1
            r8 = r0[r8]
            if (r7 == r8) goto L15
            return r1
        L15:
            r8 = r3
            r7 = 1
            goto L19
        L18:
            r7 = 0
        L19:
            int r3 = r7 + 1
            r7 = r6[r7]
            int r4 = r8 + 1
            r8 = r0[r8]
            if (r7 == r8) goto L24
            return r1
        L24:
            r8 = r4
            goto L27
        L26:
            r3 = 0
        L27:
            int r7 = r3 + 1
            r3 = r6[r3]
            int r4 = r8 + 1
            r8 = r0[r8]
            if (r3 == r8) goto L32
            return r1
        L32:
            r8 = r4
            goto L35
        L34:
            r7 = 0
        L35:
            int r3 = r7 + 1
            r7 = r6[r7]
            int r4 = r8 + 1
            r8 = r0[r8]
            if (r7 == r8) goto L40
            return r1
        L40:
            r8 = r4
            goto L43
        L42:
            r3 = 0
        L43:
            int r7 = r3 + 1
            r3 = r6[r3]
            int r4 = r8 + 1
            r8 = r0[r8]
            if (r3 == r8) goto L4e
            return r1
        L4e:
            int r8 = r7 + 1
            r7 = r6[r7]
            int r3 = r4 + 1
            r4 = r0[r4]
            if (r7 == r4) goto L59
            return r1
        L59:
            int r7 = r8 + 1
            r8 = r6[r8]
            int r4 = r3 + 1
            r3 = r0[r3]
            if (r8 == r3) goto L64
            return r1
        L64:
            r6 = r6[r7]
            r7 = r0[r4]
            if (r6 == r7) goto L6b
            return r1
        L6b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer._verifyLongName(int[], int, int):boolean");
    }

    private boolean _verifyLongName2(int[] r6, int r7, int r8) {
        int r1 = 0;
        while (true) {
            int r2 = r1 + 1;
            int r4 = r8 + 1;
            if (r6[r1] != this._hashArea[r8]) {
                return false;
            }
            if (r2 >= r7) {
                return true;
            }
            r1 = r2;
            r8 = r4;
        }
    }

    public String addName(String str, int r5) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(calcHash(r5));
        int[] r1 = this._hashArea;
        r1[_findOffsetForAdd] = r5;
        r1[_findOffsetForAdd + 3] = 1;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        _verifyNeedForRehash();
        return str;
    }

    public String addName(String str, int r4, int r5) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(r5 == 0 ? calcHash(r4) : calcHash(r4, r5));
        int[] r1 = this._hashArea;
        r1[_findOffsetForAdd] = r4;
        r1[_findOffsetForAdd + 1] = r5;
        r1[_findOffsetForAdd + 3] = 2;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        _verifyNeedForRehash();
        return str;
    }

    public String addName(String str, int r4, int r5, int r6) {
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int _findOffsetForAdd = _findOffsetForAdd(calcHash(r4, r5, r6));
        int[] r1 = this._hashArea;
        r1[_findOffsetForAdd] = r4;
        r1[_findOffsetForAdd + 1] = r5;
        r1[_findOffsetForAdd + 2] = r6;
        r1[_findOffsetForAdd + 3] = 3;
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        _verifyNeedForRehash();
        return str;
    }

    public String addName(String str, int[] r8, int r9) {
        int _findOffsetForAdd;
        _verifySharing();
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        if (r9 == 1) {
            _findOffsetForAdd = _findOffsetForAdd(calcHash(r8[0]));
            int[] r92 = this._hashArea;
            r92[_findOffsetForAdd] = r8[0];
            r92[_findOffsetForAdd + 3] = 1;
        } else if (r9 == 2) {
            _findOffsetForAdd = _findOffsetForAdd(calcHash(r8[0], r8[1]));
            int[] r93 = this._hashArea;
            r93[_findOffsetForAdd] = r8[0];
            r93[_findOffsetForAdd + 1] = r8[1];
            r93[_findOffsetForAdd + 3] = 2;
        } else if (r9 == 3) {
            int _findOffsetForAdd2 = _findOffsetForAdd(calcHash(r8[0], r8[1], r8[2]));
            int[] r4 = this._hashArea;
            r4[_findOffsetForAdd2] = r8[0];
            r4[_findOffsetForAdd2 + 1] = r8[1];
            r4[_findOffsetForAdd2 + 2] = r8[2];
            r4[_findOffsetForAdd2 + 3] = 3;
            _findOffsetForAdd = _findOffsetForAdd2;
        } else {
            int calcHash = calcHash(r8, r9);
            _findOffsetForAdd = _findOffsetForAdd(calcHash);
            this._hashArea[_findOffsetForAdd] = calcHash;
            int _appendLongName = _appendLongName(r8, r9);
            int[] r0 = this._hashArea;
            r0[_findOffsetForAdd + 1] = _appendLongName;
            r0[_findOffsetForAdd + 3] = r9;
        }
        this._names[_findOffsetForAdd >> 2] = str;
        this._count++;
        _verifyNeedForRehash();
        return str;
    }

    private void _verifyNeedForRehash() {
        if (this._count > (this._hashSize >> 1)) {
            int _spilloverStart = (this._spilloverEnd - _spilloverStart()) >> 2;
            int r1 = this._count;
            if (_spilloverStart > ((r1 + 1) >> 7) || r1 > this._hashSize * 0.8d) {
                this._needRehash = true;
            }
        }
    }

    private void _verifySharing() {
        if (this._hashShared) {
            int[] r0 = this._hashArea;
            this._hashArea = Arrays.copyOf(r0, r0.length);
            String[] strArr = this._names;
            this._names = (String[]) Arrays.copyOf(strArr, strArr.length);
            this._hashShared = false;
            _verifyNeedForRehash();
        }
        if (this._needRehash) {
            rehash();
        }
    }

    private int _findOffsetForAdd(int r5) {
        int _calcOffset = _calcOffset(r5);
        int[] r0 = this._hashArea;
        if (r0[_calcOffset + 3] == 0) {
            return _calcOffset;
        }
        int r1 = this._secondaryStart + ((_calcOffset >> 3) << 2);
        if (r0[r1 + 3] == 0) {
            return r1;
        }
        int r12 = this._tertiaryStart;
        int r2 = this._tertiaryShift;
        int r13 = r12 + ((_calcOffset >> (r2 + 2)) << r2);
        int r22 = (1 << r2) + r13;
        while (r13 < r22) {
            if (r0[r13 + 3] == 0) {
                return r13;
            }
            r13 += 4;
        }
        int r02 = this._spilloverEnd;
        int r14 = r02 + 4;
        this._spilloverEnd = r14;
        if (r14 >= (this._hashSize << 3)) {
            if (this._failOnDoS) {
                _reportTooManyCollisions();
            }
            this._needRehash = true;
        }
        return r02;
    }

    private int _appendLongName(int[] r5, int r6) {
        int r0 = this._longNameOffset;
        int r1 = r0 + r6;
        int[] r2 = this._hashArea;
        if (r1 > r2.length) {
            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + Math.max(r1 - r2.length, Math.min(4096, this._hashSize)));
        }
        System.arraycopy(r5, 0, this._hashArea, r0, r6);
        this._longNameOffset += r6;
        return r0;
    }

    public int calcHash(int r2) {
        int r22 = r2 ^ this._seed;
        int r23 = r22 + (r22 >>> 16);
        int r24 = r23 ^ (r23 << 3);
        return r24 + (r24 >>> 12);
    }

    public int calcHash(int r2, int r3) {
        int r22 = r2 + (r2 >>> 15);
        int r23 = ((r22 ^ (r22 >>> 9)) + (r3 * 33)) ^ this._seed;
        int r24 = r23 + (r23 >>> 16);
        int r25 = r24 ^ (r24 >>> 4);
        return r25 + (r25 << 3);
    }

    public int calcHash(int r2, int r3, int r4) {
        int r22 = r2 ^ this._seed;
        int r23 = (((r22 + (r22 >>> 9)) * 31) + r3) * 33;
        int r24 = (r23 + (r23 >>> 15)) ^ r4;
        int r25 = r24 + (r24 >>> 4);
        int r26 = r25 + (r25 >>> 15);
        return r26 ^ (r26 << 9);
    }

    public int calcHash(int[] r5, int r6) {
        if (r6 < 4) {
            throw new IllegalArgumentException();
        }
        int r0 = r5[0] ^ this._seed;
        int r02 = r0 + (r0 >>> 9) + r5[1];
        int r03 = ((r02 + (r02 >>> 15)) * 33) ^ r5[2];
        int r04 = r03 + (r03 >>> 4);
        for (int r1 = 3; r1 < r6; r1++) {
            int r2 = r5[r1];
            r04 += r2 ^ (r2 >> 21);
        }
        int r05 = r04 * MULT2;
        int r06 = r05 + (r05 >>> 19);
        return (r06 << 5) ^ r06;
    }

    private void rehash() {
        this._needRehash = false;
        this._hashShared = false;
        int[] r1 = this._hashArea;
        String[] strArr = this._names;
        int r3 = this._hashSize;
        int r4 = this._count;
        int r5 = r3 + r3;
        int r6 = this._spilloverEnd;
        if (r5 > 65536) {
            nukeSymbols(true);
            return;
        }
        this._hashArea = new int[r1.length + (r3 << 3)];
        this._hashSize = r5;
        int r32 = r5 << 2;
        this._secondaryStart = r32;
        this._tertiaryStart = r32 + (r32 >> 1);
        this._tertiaryShift = _calcTertiaryShift(r5);
        this._names = new String[strArr.length << 1];
        nukeSymbols(false);
        int[] r33 = new int[16];
        int r8 = 0;
        for (int r52 = 0; r52 < r6; r52 += 4) {
            int r10 = r1[r52 + 3];
            if (r10 != 0) {
                r8++;
                String str = strArr[r52 >> 2];
                if (r10 == 1) {
                    r33[0] = r1[r52];
                    addName(str, r33, 1);
                } else if (r10 == 2) {
                    r33[0] = r1[r52];
                    r33[1] = r1[r52 + 1];
                    addName(str, r33, 2);
                } else if (r10 == 3) {
                    r33[0] = r1[r52];
                    r33[1] = r1[r52 + 1];
                    r33[2] = r1[r52 + 2];
                    addName(str, r33, 3);
                } else {
                    if (r10 > r33.length) {
                        r33 = new int[r10];
                    }
                    System.arraycopy(r1, r1[r52 + 1], r33, 0, r10);
                    addName(str, r33, r10);
                }
            }
        }
        if (r8 == r4) {
            return;
        }
        throw new IllegalStateException("Failed rehash(): old count=" + r4 + ", copyCount=" + r8);
    }

    private void nukeSymbols(boolean z) {
        this._count = 0;
        this._spilloverEnd = _spilloverStart();
        this._longNameOffset = this._hashSize << 3;
        if (z) {
            Arrays.fill(this._hashArea, 0);
            Arrays.fill(this._names, (Object) null);
        }
    }

    private final int _spilloverStart() {
        int r0 = this._hashSize;
        return (r0 << 3) - r0;
    }

    protected void _reportTooManyCollisions() {
        if (this._hashSize <= 1024) {
            return;
        }
        throw new IllegalStateException("Spill-over slots in symbol table with " + this._count + " entries, hash area of " + this._hashSize + " slots is now full (all " + (this._hashSize >> 3) + " slots -- suspect a DoS attack based on hash collisions. You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TableInfo {
        public final int count;
        public final int longNameOffset;
        public final int[] mainHash;
        public final String[] names;
        public final int size;
        public final int spilloverEnd;
        public final int tertiaryShift;

        public TableInfo(int r1, int r2, int r3, int[] r4, String[] strArr, int r6, int r7) {
            this.size = r1;
            this.count = r2;
            this.tertiaryShift = r3;
            this.mainHash = r4;
            this.names = strArr;
            this.spilloverEnd = r6;
            this.longNameOffset = r7;
        }

        public TableInfo(ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
            this.size = byteQuadsCanonicalizer._hashSize;
            this.count = byteQuadsCanonicalizer._count;
            this.tertiaryShift = byteQuadsCanonicalizer._tertiaryShift;
            this.mainHash = byteQuadsCanonicalizer._hashArea;
            this.names = byteQuadsCanonicalizer._names;
            this.spilloverEnd = byteQuadsCanonicalizer._spilloverEnd;
            this.longNameOffset = byteQuadsCanonicalizer._longNameOffset;
        }

        public static TableInfo createInitial(int r9) {
            int r7 = r9 << 3;
            return new TableInfo(r9, 0, ByteQuadsCanonicalizer._calcTertiaryShift(r9), new int[r7], new String[r9 << 1], r7 - r9, r7);
        }
    }
}
