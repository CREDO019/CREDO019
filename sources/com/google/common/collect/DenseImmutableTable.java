package com.google.common.collect;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Immutable(containerOf = {"R", "C", ExifInterface.GPS_MEASUREMENT_INTERRUPTED})
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final int[] cellColumnIndices;
    private final int[] cellRowIndices;
    private final int[] columnCounts;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final int[] rowCounts;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    private final V[][] values;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        this.values = (V[][]) ((Object[][]) Array.newInstance(Object.class, immutableSet.size(), immutableSet2.size()));
        ImmutableMap<R, Integer> indexMap = Maps.indexMap(immutableSet);
        this.rowKeyToIndex = indexMap;
        ImmutableMap<C, Integer> indexMap2 = Maps.indexMap(immutableSet2);
        this.columnKeyToIndex = indexMap2;
        this.rowCounts = new int[indexMap.size()];
        this.columnCounts = new int[indexMap2.size()];
        int[] r11 = new int[immutableList.size()];
        int[] r12 = new int[immutableList.size()];
        for (int r1 = 0; r1 < immutableList.size(); r1++) {
            Table.Cell<R, C, V> cell = immutableList.get(r1);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            Integer num = this.rowKeyToIndex.get(rowKey);
            Objects.requireNonNull(num);
            int intValue = num.intValue();
            Integer num2 = this.columnKeyToIndex.get(columnKey);
            Objects.requireNonNull(num2);
            int intValue2 = num2.intValue();
            checkNoDuplicate(rowKey, columnKey, this.values[intValue][intValue2], cell.getValue());
            this.values[intValue][intValue2] = cell.getValue();
            int[] r0 = this.rowCounts;
            r0[intValue] = r0[intValue] + 1;
            int[] r02 = this.columnCounts;
            r02[intValue2] = r02[intValue2] + 1;
            r11[r1] = intValue;
            r12[r1] = intValue2;
        }
        this.cellRowIndices = r11;
        this.cellColumnIndices = r12;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        private final int size;

        @CheckForNull
        abstract V getValue(int r1);

        abstract ImmutableMap<K, Integer> keyToIndex();

        ImmutableArrayMap(int r1) {
            this.size = r1;
        }

        private boolean isFull() {
            return this.size == keyToIndex().size();
        }

        K getKey(int r2) {
            return keyToIndex().keySet().asList().get(r2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : super.createKeySet();
        }

        @Override // java.util.Map
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        @CheckForNull
        public V get(@CheckForNull Object obj) {
            Integer num = keyToIndex().get(obj);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.DenseImmutableTable.ImmutableArrayMap.1
                private int index = -1;
                private final int maxIndex;

                {
                    this.maxIndex = ImmutableArrayMap.this.keyToIndex().size();
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                @CheckForNull
                public Map.Entry<K, V> computeNext() {
                    int r0 = this.index;
                    while (true) {
                        this.index = r0 + 1;
                        int r02 = this.index;
                        if (r02 < this.maxIndex) {
                            Object value = ImmutableArrayMap.this.getValue(r02);
                            if (value == null) {
                                r0 = this.index;
                            } else {
                                return Maps.immutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                            }
                        } else {
                            return endOfData();
                        }
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class Row extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        Row(int r2) {
            super(DenseImmutableTable.this.rowCounts[r2]);
            this.rowIndex = r2;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        @CheckForNull
        V getValue(int r3) {
            return (V) DenseImmutableTable.this.values[this.rowIndex][r3];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class Column extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return true;
        }

        Column(int r2) {
            super(DenseImmutableTable.this.columnCounts[r2]);
            this.columnIndex = r2;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        @CheckForNull
        V getValue(int r2) {
            return (V) DenseImmutableTable.this.values[r2][this.columnIndex];
        }
    }

    /* loaded from: classes3.dex */
    private final class RowMap extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<C, V> getValue(int r3) {
            return new Row(r3);
        }
    }

    /* loaded from: classes3.dex */
    private final class ColumnMap extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return false;
        }

        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.DenseImmutableTable.ImmutableArrayMap
        public ImmutableMap<R, V> getValue(int r3) {
            return new Column(r3);
        }
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf((Map) this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.Table
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf((Map) this.rowMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return this.values[num.intValue()][num2.intValue()];
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override // com.google.common.collect.RegularImmutableTable
    Table.Cell<R, C, V> getCell(int r5) {
        int r0 = this.cellRowIndices[r5];
        int r52 = this.cellColumnIndices[r5];
        R r = rowKeySet().asList().get(r0);
        C c = columnKeySet().asList().get(r52);
        V v = this.values[r0][r52];
        Objects.requireNonNull(v);
        return cellOf(r, c, v);
    }

    @Override // com.google.common.collect.RegularImmutableTable
    V getValue(int r3) {
        V v = this.values[this.cellRowIndices[r3]][this.cellColumnIndices[r3]];
        Objects.requireNonNull(v);
        return v;
    }

    @Override // com.google.common.collect.ImmutableTable
    ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
    }
}
