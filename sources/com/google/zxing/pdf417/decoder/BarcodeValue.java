package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
final class BarcodeValue {
    private final Map<Integer, Integer> values = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(int r3) {
        Integer num = this.values.get(Integer.valueOf(r3));
        if (num == null) {
            num = 0;
        }
        this.values.put(Integer.valueOf(r3), Integer.valueOf(num.intValue() + 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getValue() {
        ArrayList arrayList = new ArrayList();
        int r2 = -1;
        for (Map.Entry<Integer, Integer> entry : this.values.entrySet()) {
            if (entry.getValue().intValue() > r2) {
                r2 = entry.getValue().intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (entry.getValue().intValue() == r2) {
                arrayList.add(entry.getKey());
            }
        }
        return PDF417Common.toIntArray(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer getConfidence(int r2) {
        return this.values.get(Integer.valueOf(r2));
    }
}
