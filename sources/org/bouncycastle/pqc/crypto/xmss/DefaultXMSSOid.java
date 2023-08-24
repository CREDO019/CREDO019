package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class DefaultXMSSOid implements XMSSOid {
    private static final Map<String, DefaultXMSSOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(createKey("SHA-256", 32, 16, 67, 10), new DefaultXMSSOid(1, "XMSS_SHA2_10_256"));
        hashMap.put(createKey("SHA-256", 32, 16, 67, 16), new DefaultXMSSOid(2, "XMSS_SHA2_16_256"));
        hashMap.put(createKey("SHA-256", 32, 16, 67, 20), new DefaultXMSSOid(3, "XMSS_SHA2_20_256"));
        hashMap.put(createKey("SHA-512", 64, 16, 131, 10), new DefaultXMSSOid(4, "XMSS_SHA2_10_512"));
        hashMap.put(createKey("SHA-512", 64, 16, 131, 16), new DefaultXMSSOid(5, "XMSS_SHA2_16_512"));
        hashMap.put(createKey("SHA-512", 64, 16, 131, 20), new DefaultXMSSOid(6, "XMSS_SHA2_20_512"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 10), new DefaultXMSSOid(7, "XMSS_SHAKE_10_256"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 16), new DefaultXMSSOid(8, "XMSS_SHAKE_16_256"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 20), new DefaultXMSSOid(9, "XMSS_SHAKE_20_256"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 10), new DefaultXMSSOid(10, "XMSS_SHAKE_10_512"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 16), new DefaultXMSSOid(11, "XMSS_SHAKE_16_512"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 20), new DefaultXMSSOid(12, "XMSS_SHAKE_20_512"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }

    private DefaultXMSSOid(int r1, String str) {
        this.oid = r1;
        this.stringRepresentation = str;
    }

    private static String createKey(String str, int r2, int r3, int r4, int r5) {
        Objects.requireNonNull(str, "algorithmName == null");
        return str + "-" + r2 + "-" + r3 + "-" + r4 + "-" + r5;
    }

    public static DefaultXMSSOid lookup(String str, int r2, int r3, int r4, int r5) {
        Objects.requireNonNull(str, "algorithmName == null");
        return oidLookupTable.get(createKey(str, r2, r3, r4, r5));
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.oid;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.stringRepresentation;
    }
}