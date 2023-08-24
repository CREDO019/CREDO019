package org.apache.commons.lang3.builder;

/* loaded from: classes5.dex */
final class IDKey {

    /* renamed from: id */
    private final int f1571id;
    private final Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IDKey(Object obj) {
        this.f1571id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f1571id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IDKey) {
            IDKey iDKey = (IDKey) obj;
            return this.f1571id == iDKey.f1571id && this.value == iDKey.value;
        }
        return false;
    }
}
