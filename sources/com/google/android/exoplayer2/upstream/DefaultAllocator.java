package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class DefaultAllocator implements Allocator {
    private static final int AVAILABLE_EXTRA_CAPACITY = 100;
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean z, int r3) {
        this(z, r3, 0);
    }

    public DefaultAllocator(boolean z, int r6, int r7) {
        Assertions.checkArgument(r6 > 0);
        Assertions.checkArgument(r7 >= 0);
        this.trimOnReset = z;
        this.individualAllocationSize = r6;
        this.availableCount = r7;
        this.availableAllocations = new Allocation[r7 + 100];
        if (r7 > 0) {
            this.initialAllocationBlock = new byte[r7 * r6];
            for (int r1 = 0; r1 < r7; r1++) {
                this.availableAllocations[r1] = new Allocation(this.initialAllocationBlock, r1 * r6);
            }
            return;
        }
        this.initialAllocationBlock = null;
    }

    public synchronized void reset() {
        if (this.trimOnReset) {
            setTargetBufferSize(0);
        }
    }

    public synchronized void setTargetBufferSize(int r2) {
        boolean z = r2 < this.targetBufferSize;
        this.targetBufferSize = r2;
        if (z) {
            trim();
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized Allocation allocate() {
        Allocation allocation;
        this.allocatedCount++;
        int r0 = this.availableCount;
        if (r0 > 0) {
            Allocation[] allocationArr = this.availableAllocations;
            int r02 = r0 - 1;
            this.availableCount = r02;
            allocation = (Allocation) Assertions.checkNotNull(allocationArr[r02]);
            this.availableAllocations[this.availableCount] = null;
        } else {
            allocation = new Allocation(new byte[this.individualAllocationSize], 0);
            int r1 = this.allocatedCount;
            Allocation[] allocationArr2 = this.availableAllocations;
            if (r1 > allocationArr2.length) {
                this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, allocationArr2.length * 2);
            }
        }
        return allocation;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void release(Allocation allocation) {
        Allocation[] allocationArr = this.availableAllocations;
        int r1 = this.availableCount;
        this.availableCount = r1 + 1;
        allocationArr[r1] = allocation;
        this.allocatedCount--;
        notifyAll();
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void release(Allocator.AllocationNode allocationNode) {
        while (allocationNode != null) {
            Allocation[] allocationArr = this.availableAllocations;
            int r1 = this.availableCount;
            this.availableCount = r1 + 1;
            allocationArr[r1] = allocationNode.getAllocation();
            this.allocatedCount--;
            allocationNode = allocationNode.next();
        }
        notifyAll();
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized void trim() {
        int r1 = 0;
        int max = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);
        int r2 = this.availableCount;
        if (max >= r2) {
            return;
        }
        if (this.initialAllocationBlock != null) {
            int r22 = r2 - 1;
            while (r1 <= r22) {
                Allocation allocation = (Allocation) Assertions.checkNotNull(this.availableAllocations[r1]);
                if (allocation.data == this.initialAllocationBlock) {
                    r1++;
                } else {
                    Allocation allocation2 = (Allocation) Assertions.checkNotNull(this.availableAllocations[r22]);
                    if (allocation2.data != this.initialAllocationBlock) {
                        r22--;
                    } else {
                        Allocation[] allocationArr = this.availableAllocations;
                        allocationArr[r1] = allocation2;
                        allocationArr[r22] = allocation;
                        r22--;
                        r1++;
                    }
                }
            }
            max = Math.max(max, r1);
            if (max >= this.availableCount) {
                return;
            }
        }
        Arrays.fill(this.availableAllocations, max, this.availableCount, (Object) null);
        this.availableCount = max;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    @Override // com.google.android.exoplayer2.upstream.Allocator
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }
}
