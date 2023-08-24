package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CharsToNameCanonicalizer {
    private static final int DEFAULT_T_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_LENGTH = 100;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    private static final int MAX_T_SIZE = 65536;
    private Bucket[] _buckets;
    private boolean _canonicalize;
    private final int _flags;
    private boolean _hashShared;
    private int _indexMask;
    private int _longestCollisionList;
    private BitSet _overflows;
    private final CharsToNameCanonicalizer _parent;
    private final int _seed;
    private int _size;
    private int _sizeThreshold;
    private String[] _symbols;
    private final AtomicReference<TableInfo> _tableInfo;

    private static int _thresholdSize(int r1) {
        return r1 - (r1 >> 2);
    }

    private CharsToNameCanonicalizer(int r2) {
        this._parent = null;
        this._seed = r2;
        this._canonicalize = true;
        this._flags = -1;
        this._hashShared = false;
        this._longestCollisionList = 0;
        this._tableInfo = new AtomicReference<>(TableInfo.createInitial(64));
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, int r2, int r3, TableInfo tableInfo) {
        this._parent = charsToNameCanonicalizer;
        this._seed = r3;
        this._tableInfo = null;
        this._flags = r2;
        this._canonicalize = JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(r2);
        this._symbols = tableInfo.symbols;
        this._buckets = tableInfo.buckets;
        this._size = tableInfo.size;
        this._longestCollisionList = tableInfo.longestCollisionList;
        int length = this._symbols.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._hashShared = true;
    }

    public static CharsToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static CharsToNameCanonicalizer createRoot(int r1) {
        return new CharsToNameCanonicalizer(r1);
    }

    public CharsToNameCanonicalizer makeChild(int r4) {
        return new CharsToNameCanonicalizer(this, r4, this._seed, this._tableInfo.get());
    }

    public void release() {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        if (maybeDirty() && (charsToNameCanonicalizer = this._parent) != null && this._canonicalize) {
            charsToNameCanonicalizer.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }

    private void mergeChild(TableInfo tableInfo) {
        int r0 = tableInfo.size;
        TableInfo tableInfo2 = this._tableInfo.get();
        if (r0 == tableInfo2.size) {
            return;
        }
        if (r0 > MAX_ENTRIES_FOR_REUSE) {
            tableInfo = TableInfo.createInitial(64);
        }
        this._tableInfo.compareAndSet(tableInfo2, tableInfo);
    }

    public int size() {
        AtomicReference<TableInfo> atomicReference = this._tableInfo;
        if (atomicReference != null) {
            return atomicReference.get().size;
        }
        return this._size;
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public boolean maybeDirty() {
        return !this._hashShared;
    }

    public int hashSeed() {
        return this._seed;
    }

    public int collisionCount() {
        Bucket[] bucketArr;
        int r3 = 0;
        for (Bucket bucket : this._buckets) {
            if (bucket != null) {
                r3 += bucket.length;
            }
        }
        return r3;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public String findSymbol(char[] cArr, int r8, int r9, int r10) {
        if (r9 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(cArr, r8, r9);
        }
        int _hashToIndex = _hashToIndex(r10);
        String str = this._symbols[_hashToIndex];
        if (str != null) {
            if (str.length() == r9) {
                int r1 = 0;
                while (str.charAt(r1) == cArr[r8 + r1]) {
                    r1++;
                    if (r1 == r9) {
                        return str;
                    }
                }
            }
            Bucket bucket = this._buckets[_hashToIndex >> 1];
            if (bucket != null) {
                String has = bucket.has(cArr, r8, r9);
                if (has != null) {
                    return has;
                }
                String _findSymbol2 = _findSymbol2(cArr, r8, r9, bucket.next);
                if (_findSymbol2 != null) {
                    return _findSymbol2;
                }
            }
        }
        return _addSymbol(cArr, r8, r9, r10, _hashToIndex);
    }

    private String _findSymbol2(char[] cArr, int r3, int r4, Bucket bucket) {
        while (bucket != null) {
            String has = bucket.has(cArr, r3, r4);
            if (has != null) {
                return has;
            }
            bucket = bucket.next;
        }
        return null;
    }

    private String _addSymbol(char[] cArr, int r3, int r4, int r5, int r6) {
        if (this._hashShared) {
            copyArrays();
            this._hashShared = false;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            r6 = _hashToIndex(calcHash(cArr, r3, r4));
        }
        String str = new String(cArr, r3, r4);
        if (JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
            str = InternCache.instance.intern(str);
        }
        this._size++;
        String[] strArr = this._symbols;
        if (strArr[r6] == null) {
            strArr[r6] = str;
        } else {
            int r2 = r6 >> 1;
            Bucket bucket = new Bucket(str, this._buckets[r2]);
            int r42 = bucket.length;
            if (r42 > 100) {
                _handleSpillOverflow(r2, bucket);
            } else {
                this._buckets[r2] = bucket;
                this._longestCollisionList = Math.max(r42, this._longestCollisionList);
            }
        }
        return str;
    }

    private void _handleSpillOverflow(int r4, Bucket bucket) {
        BitSet bitSet = this._overflows;
        if (bitSet == null) {
            BitSet bitSet2 = new BitSet();
            this._overflows = bitSet2;
            bitSet2.set(r4);
        } else if (bitSet.get(r4)) {
            if (JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
                reportTooManyCollisions(100);
            }
            this._canonicalize = false;
        } else {
            this._overflows.set(r4);
        }
        this._symbols[r4 + r4] = bucket.symbol;
        this._buckets[r4] = null;
        this._size -= bucket.length;
        this._longestCollisionList = -1;
    }

    public int _hashToIndex(int r2) {
        int r22 = r2 + (r2 >>> 15);
        int r23 = r22 ^ (r22 << 7);
        return (r23 + (r23 >>> 3)) & this._indexMask;
    }

    public int calcHash(char[] cArr, int r4, int r5) {
        int r0 = this._seed;
        int r52 = r5 + r4;
        while (r4 < r52) {
            r0 = (r0 * 33) + cArr[r4];
            r4++;
        }
        if (r0 == 0) {
            return 1;
        }
        return r0;
    }

    public int calcHash(String str) {
        int length = str.length();
        int r1 = this._seed;
        for (int r2 = 0; r2 < length; r2++) {
            r1 = (r1 * 33) + str.charAt(r2);
        }
        if (r1 == 0) {
            return 1;
        }
        return r1;
    }

    private void copyArrays() {
        String[] strArr = this._symbols;
        this._symbols = (String[]) Arrays.copyOf(strArr, strArr.length);
        Bucket[] bucketArr = this._buckets;
        this._buckets = (Bucket[]) Arrays.copyOf(bucketArr, bucketArr.length);
    }

    private void rehash() {
        String[] strArr = this._symbols;
        int length = strArr.length;
        int r2 = length + length;
        if (r2 > 65536) {
            this._size = 0;
            this._canonicalize = false;
            this._symbols = new String[64];
            this._buckets = new Bucket[32];
            this._indexMask = 63;
            this._hashShared = false;
            return;
        }
        Bucket[] bucketArr = this._buckets;
        this._symbols = new String[r2];
        this._buckets = new Bucket[r2 >> 1];
        this._indexMask = r2 - 1;
        this._sizeThreshold = _thresholdSize(r2);
        int r5 = 0;
        int r6 = 0;
        for (String str : strArr) {
            if (str != null) {
                r5++;
                int _hashToIndex = _hashToIndex(calcHash(str));
                String[] strArr2 = this._symbols;
                if (strArr2[_hashToIndex] == null) {
                    strArr2[_hashToIndex] = str;
                } else {
                    int r8 = _hashToIndex >> 1;
                    Bucket bucket = new Bucket(str, this._buckets[r8]);
                    this._buckets[r8] = bucket;
                    r6 = Math.max(r6, bucket.length);
                }
            }
        }
        int r1 = length >> 1;
        for (int r22 = 0; r22 < r1; r22++) {
            for (Bucket bucket2 = bucketArr[r22]; bucket2 != null; bucket2 = bucket2.next) {
                r5++;
                String str2 = bucket2.symbol;
                int _hashToIndex2 = _hashToIndex(calcHash(str2));
                String[] strArr3 = this._symbols;
                if (strArr3[_hashToIndex2] == null) {
                    strArr3[_hashToIndex2] = str2;
                } else {
                    int r9 = _hashToIndex2 >> 1;
                    Bucket bucket3 = new Bucket(str2, this._buckets[r9]);
                    this._buckets[r9] = bucket3;
                    r6 = Math.max(r6, bucket3.length);
                }
            }
        }
        this._longestCollisionList = r6;
        this._overflows = null;
        if (r5 != this._size) {
            throw new IllegalStateException(String.format("Internal error on SymbolTable.rehash(): had %d entries; now have %d", Integer.valueOf(this._size), Integer.valueOf(r5)));
        }
    }

    protected void reportTooManyCollisions(int r4) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + r4 + " -- suspect a DoS attack based on hash collisions");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Bucket {
        public final int length;
        public final Bucket next;
        public final String symbol;

        public Bucket(String str, Bucket bucket) {
            this.symbol = str;
            this.next = bucket;
            this.length = bucket != null ? 1 + bucket.length : 1;
        }

        public String has(char[] cArr, int r6, int r7) {
            if (this.symbol.length() != r7) {
                return null;
            }
            int r0 = 0;
            while (this.symbol.charAt(r0) == cArr[r6 + r0]) {
                r0++;
                if (r0 >= r7) {
                    return this.symbol;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class TableInfo {
        final Bucket[] buckets;
        final int longestCollisionList;
        final int size;
        final String[] symbols;

        public TableInfo(int r1, int r2, String[] strArr, Bucket[] bucketArr) {
            this.size = r1;
            this.longestCollisionList = r2;
            this.symbols = strArr;
            this.buckets = bucketArr;
        }

        public TableInfo(CharsToNameCanonicalizer charsToNameCanonicalizer) {
            this.size = charsToNameCanonicalizer._size;
            this.longestCollisionList = charsToNameCanonicalizer._longestCollisionList;
            this.symbols = charsToNameCanonicalizer._symbols;
            this.buckets = charsToNameCanonicalizer._buckets;
        }

        public static TableInfo createInitial(int r3) {
            return new TableInfo(0, 0, new String[r3], new Bucket[r3 >> 1]);
        }
    }
}
