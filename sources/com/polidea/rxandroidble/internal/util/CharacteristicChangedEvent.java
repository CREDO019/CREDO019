package com.polidea.rxandroidble.internal.util;

import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes3.dex */
public class CharacteristicChangedEvent extends CharacteristicNotificationId {
    public final byte[] data;

    public CharacteristicChangedEvent(UUID r1, Integer num, byte[] bArr) {
        super(r1, num);
        this.data = bArr;
    }

    @Override // android.util.Pair
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CharacteristicChangedEvent)) {
            return (obj instanceof CharacteristicNotificationId) && super.equals(obj);
        } else if (super.equals(obj)) {
            return Arrays.equals(this.data, ((CharacteristicChangedEvent) obj).data);
        } else {
            return false;
        }
    }

    @Override // android.util.Pair
    public int hashCode() {
        return (super.hashCode() * 31) + Arrays.hashCode(this.data);
    }

    @Override // com.polidea.rxandroidble.internal.util.CharacteristicNotificationId, android.util.Pair
    public String toString() {
        return "CharacteristicChangedEvent{UUID=" + ((UUID) this.first).toString() + ", instanceId=" + ((Integer) this.second).toString() + ", data=" + Arrays.toString(this.data) + '}';
    }
}
