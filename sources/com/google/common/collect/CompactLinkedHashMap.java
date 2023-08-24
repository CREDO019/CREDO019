package com.google.common.collect;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    private final boolean accessOrder;
    private transient int firstEntry;
    private transient int lastEntry;
    @CheckForNull
    transient long[] links;

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int r1) {
        return new CompactLinkedHashMap<>(r1);
    }

    CompactLinkedHashMap() {
        this(3);
    }

    CompactLinkedHashMap(int r2) {
        this(r2, false);
    }

    CompactLinkedHashMap(int r1, boolean z) {
        super(r1);
        this.accessOrder = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void init(int r1) {
        super.init(r1);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public int allocArrays() {
        int allocArrays = super.allocArrays();
        this.links = new long[allocArrays];
        return allocArrays;
    }

    @Override // com.google.common.collect.CompactHashMap
    Map<K, V> createHashFloodingResistantDelegate(int r4) {
        return new LinkedHashMap(r4, 1.0f, this.accessOrder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public Map<K, V> convertToHashFloodingResistantImplementation() {
        Map<K, V> convertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.links = null;
        return convertToHashFloodingResistantImplementation;
    }

    private int getPredecessor(int r3) {
        return ((int) (link(r3) >>> 32)) - 1;
    }

    @Override // com.google.common.collect.CompactHashMap
    int getSuccessor(int r3) {
        return ((int) link(r3)) - 1;
    }

    private void setSuccessor(int r7, int r8) {
        setLink(r7, (link(r7) & (-4294967296L)) | ((r8 + 1) & BodyPartID.bodyIdMax));
    }

    private void setPredecessor(int r5, int r6) {
        setLink(r5, (link(r5) & BodyPartID.bodyIdMax) | ((r6 + 1) << 32));
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
    @Override // com.google.common.collect.CompactHashMap
    public void insertEntry(int r1, @ParametricNullness K k, @ParametricNullness V v, int r4, int r5) {
        super.insertEntry(r1, k, v, r4, r5);
        setSucceeds(this.lastEntry, r1);
        setSucceeds(r1, -2);
    }

    @Override // com.google.common.collect.CompactHashMap
    void accessEntry(int r3) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(r3), getSuccessor(r3));
            setSucceeds(this.lastEntry, r3);
            setSucceeds(r3, -2);
            incrementModCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void moveLastEntry(int r3, int r4) {
        int size = size() - 1;
        super.moveLastEntry(r3, r4);
        setSucceeds(getPredecessor(r3), getSuccessor(r3));
        if (r3 < size) {
            setSucceeds(getPredecessor(size), r3);
            setSucceeds(r3, getSuccessor(size));
        }
        setLink(size, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void resizeEntries(int r2) {
        super.resizeEntries(r2);
        this.links = Arrays.copyOf(requireLinks(), r2);
    }

    @Override // com.google.common.collect.CompactHashMap
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashMap
    int adjustAfterRemove(int r2, int r3) {
        return r2 >= size() ? r3 : r2;
    }

    @Override // com.google.common.collect.CompactHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = this.links;
        if (jArr != null) {
            Arrays.fill(jArr, 0, size(), 0L);
        }
        super.clear();
    }

    private long[] requireLinks() {
        long[] jArr = this.links;
        Objects.requireNonNull(jArr);
        return jArr;
    }

    private long link(int r4) {
        return requireLinks()[r4];
    }

    private void setLink(int r2, long j) {
        requireLinks()[r2] = j;
    }
}
