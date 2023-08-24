package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa;
    private ArrayList zab;

    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zaa = false;
    }

    private final void zab() {
        synchronized (this) {
            if (!this.zaa) {
                int count = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                ArrayList arrayList = new ArrayList();
                this.zab = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int r4 = 1; r4 < count; r4++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(r4);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, r4, windowIndex);
                        if (string2 == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + primaryDataMarkerColumn + ", at row: " + r4 + ", for window: " + windowIndex);
                        }
                        if (!string2.equals(string)) {
                            this.zab.add(Integer.valueOf(r4));
                            string = string2;
                        }
                    }
                }
                this.zaa = true;
            }
        }
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final T get(int r7) {
        int intValue;
        int intValue2;
        zab();
        int zaa = zaa(r7);
        int r1 = 0;
        if (r7 >= 0 && r7 != this.zab.size()) {
            if (r7 == this.zab.size() - 1) {
                intValue = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                intValue2 = ((Integer) this.zab.get(r7)).intValue();
            } else {
                intValue = ((Integer) this.zab.get(r7 + 1)).intValue();
                intValue2 = ((Integer) this.zab.get(r7)).intValue();
            }
            int r3 = intValue - intValue2;
            if (r3 == 1) {
                int zaa2 = zaa(r7);
                int windowIndex = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getWindowIndex(zaa2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn == null || this.mDataHolder.getString(childDataMarkerColumn, zaa2, windowIndex) != null) {
                    r1 = 1;
                }
            } else {
                r1 = r3;
            }
        }
        return getEntry(zaa, r1);
    }

    protected String getChildDataMarkerColumn() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zab();
        return this.zab.size();
    }

    protected abstract T getEntry(int r1, int r2);

    protected abstract String getPrimaryDataMarkerColumn();

    final int zaa(int r4) {
        if (r4 < 0 || r4 >= this.zab.size()) {
            throw new IllegalArgumentException("Position " + r4 + " is out of bounds for this buffer");
        }
        return ((Integer) this.zab.get(r4)).intValue();
    }
}
