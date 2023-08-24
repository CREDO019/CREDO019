package com.polidea.multiplatformbleadapter.utils;

import java.util.UUID;

/* loaded from: classes3.dex */
public class IdGeneratorKey {
    private final String deviceAddress;

    /* renamed from: id */
    private final int f1315id;
    private final UUID uuid;

    public IdGeneratorKey(String str, UUID r2, int r3) {
        this.deviceAddress = str;
        this.uuid = r2;
        this.f1315id = r3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IdGeneratorKey idGeneratorKey = (IdGeneratorKey) obj;
        if (this.f1315id == idGeneratorKey.f1315id && this.deviceAddress.equals(idGeneratorKey.deviceAddress)) {
            return this.uuid.equals(idGeneratorKey.uuid);
        }
        return false;
    }

    public int hashCode() {
        return (((this.deviceAddress.hashCode() * 31) + this.uuid.hashCode()) * 31) + this.f1315id;
    }
}
