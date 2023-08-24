package com.google.common.collect;

import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
class ObjectCountLinkedHashMap<K> extends ObjectCountHashMap<K> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    transient long[] links;

    static <K> ObjectCountLinkedHashMap<K> create() {
        return new ObjectCountLinkedHashMap<>();
    }

    static <K> ObjectCountLinkedHashMap<K> createWithExpectedSize(int r1) {
        return new ObjectCountLinkedHashMap<>(r1);
    }

    ObjectCountLinkedHashMap() {
        this(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap(int r2) {
        this(r2, 1.0f);
    }

    ObjectCountLinkedHashMap(int r1, float f) {
        super(r1, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap(ObjectCountHashMap<K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int firstIndex = objectCountHashMap.firstIndex();
        while (firstIndex != -1) {
            put(objectCountHashMap.getKey(firstIndex), objectCountHashMap.getValue(firstIndex));
            firstIndex = objectCountHashMap.nextIndex(firstIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void init(int r3, float f) {
        super.init(r3, f);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[r3];
        this.links = jArr;
        Arrays.fill(jArr, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int firstIndex() {
        int r0 = this.firstEntry;
        if (r0 == -2) {
            return -1;
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int nextIndex(int r2) {
        int successor = getSuccessor(r2);
        if (successor == -2) {
            return -1;
        }
        return successor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public int nextIndexAfterRemove(int r2, int r3) {
        return r2 == size() ? r3 : r2;
    }

    private int getPredecessor(int r4) {
        return (int) (this.links[r4] >>> 32);
    }

    private int getSuccessor(int r4) {
        return (int) this.links[r4];
    }

    private void setSuccessor(int r8, int r9) {
        long[] jArr = this.links;
        jArr[r8] = (jArr[r8] & (-4294967296L)) | (r9 & BodyPartID.bodyIdMax);
    }

    private void setPredecessor(int r6, int r7) {
        long[] jArr = this.links;
        jArr[r6] = (jArr[r6] & BodyPartID.bodyIdMax) | (r7 << 32);
    }

    private void setSucceeds(int r2, int r3) {
        if (r2 == -2) {
            this.firstEntry = r3;
        } else {
            setSuccessor(r2, r3);
        }
        if (r3 == -2) {
            this.lastEntry = r2;
        } else {
            setPredecessor(r3, r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void insertEntry(int r1, @ParametricNullness K k, int r3, int r4) {
        super.insertEntry(r1, k, r3, r4);
        setSucceeds(this.lastEntry, r1);
        setSucceeds(r1, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void moveLastEntry(int r4) {
        int size = size() - 1;
        setSucceeds(getPredecessor(r4), getSuccessor(r4));
        if (r4 < size) {
            setSucceeds(getPredecessor(size), r4);
            setSucceeds(r4, getSuccessor(size));
        }
        super.moveLastEntry(r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void resizeEntries(int r5) {
        super.resizeEntries(r5);
        long[] jArr = this.links;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, r5);
        this.links = copyOf;
        Arrays.fill(copyOf, length, r5, -1L);
    }

    @Override // com.google.common.collect.ObjectCountHashMap
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }
}
