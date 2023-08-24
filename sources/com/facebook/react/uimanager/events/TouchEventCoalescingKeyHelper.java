package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;

/* loaded from: classes.dex */
public class TouchEventCoalescingKeyHelper {
    private final SparseIntArray mDownTimeToCoalescingKey = new SparseIntArray();

    public void addCoalescingKey(long j) {
        this.mDownTimeToCoalescingKey.put((int) j, 0);
    }

    public void incrementCoalescingKey(long j) {
        int r3 = (int) j;
        int r0 = this.mDownTimeToCoalescingKey.get(r3, -1);
        if (r0 == -1) {
            throw new RuntimeException("Tried to increment non-existent cookie");
        }
        this.mDownTimeToCoalescingKey.put(r3, r0 + 1);
    }

    public short getCoalescingKey(long j) {
        int r3 = this.mDownTimeToCoalescingKey.get((int) j, -1);
        if (r3 != -1) {
            return (short) (65535 & r3);
        }
        throw new RuntimeException("Tried to get non-existent cookie");
    }

    public void removeCoalescingKey(long j) {
        this.mDownTimeToCoalescingKey.delete((int) j);
    }

    public boolean hasCoalescingKey(long j) {
        return this.mDownTimeToCoalescingKey.get((int) j, -1) != -1;
    }
}
