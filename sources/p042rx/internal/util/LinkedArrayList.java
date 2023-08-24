package p042rx.internal.util;

import java.util.ArrayList;
import java.util.List;

/* renamed from: rx.internal.util.LinkedArrayList */
/* loaded from: classes6.dex */
public class LinkedArrayList {
    final int capacityHint;
    Object[] head;
    int indexInTail;
    volatile int size;
    Object[] tail;

    public LinkedArrayList(int r1) {
        this.capacityHint = r1;
    }

    public void add(Object obj) {
        if (this.size == 0) {
            Object[] objArr = new Object[this.capacityHint + 1];
            this.head = objArr;
            this.tail = objArr;
            objArr[0] = obj;
            this.indexInTail = 1;
            this.size = 1;
            return;
        }
        int r0 = this.indexInTail;
        int r3 = this.capacityHint;
        if (r0 == r3) {
            Object[] objArr2 = new Object[r3 + 1];
            objArr2[0] = obj;
            this.tail[r3] = objArr2;
            this.tail = objArr2;
            this.indexInTail = 1;
            this.size++;
            return;
        }
        this.tail[r0] = obj;
        this.indexInTail = r0 + 1;
        this.size++;
    }

    public Object[] head() {
        return this.head;
    }

    public Object[] tail() {
        return this.tail;
    }

    public int size() {
        return this.size;
    }

    public int indexInTail() {
        return this.indexInTail;
    }

    public int capacityHint() {
        return this.capacityHint;
    }

    List<Object> toList() {
        int r0 = this.capacityHint;
        int r1 = this.size;
        ArrayList arrayList = new ArrayList(r1 + 1);
        Object[] head = head();
        int r5 = 0;
        while (true) {
            int r6 = 0;
            while (r5 < r1) {
                arrayList.add(head[r6]);
                r5++;
                r6++;
                if (r6 == r0) {
                    break;
                }
            }
            return arrayList;
            head = head[r0];
        }
    }

    public String toString() {
        return toList().toString();
    }
}
