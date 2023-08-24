package com.google.android.play.core.assetpacks;

import java.util.Arrays;

/* renamed from: com.google.android.play.core.assetpacks.dx */
/* loaded from: classes3.dex */
final class C2458dx {

    /* renamed from: a */
    private final String f740a;

    /* renamed from: b */
    private final long f741b;

    /* renamed from: c */
    private final int f742c;

    /* renamed from: d */
    private final boolean f743d;

    /* renamed from: e */
    private final boolean f744e;

    /* renamed from: f */
    private final byte[] f745f;

    C2458dx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2458dx(String str, long j, int r4, boolean z, boolean z2, byte[] bArr) {
        this();
        this.f740a = str;
        this.f741b = j;
        this.f742c = r4;
        this.f743d = z;
        this.f744e = z2;
        this.f745f = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2458dx m861a(String str, long j, int r12, boolean z, byte[] bArr, boolean z2) {
        return new C2458dx(str, j, r12, z, z2, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m862a() {
        if (m859c() == null) {
            return false;
        }
        return m859c().endsWith("/");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean m860b() {
        return m857e() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m859c() {
        return this.f740a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public long m858d() {
        return this.f741b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m857e() {
        return this.f742c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2458dx) {
            C2458dx c2458dx = (C2458dx) obj;
            String str = this.f740a;
            if (str != null ? str.equals(c2458dx.m859c()) : c2458dx.m859c() == null) {
                if (this.f741b == c2458dx.m858d() && this.f742c == c2458dx.m857e() && this.f743d == c2458dx.m856f() && this.f744e == c2458dx.m855g()) {
                    if (Arrays.equals(this.f745f, c2458dx instanceof C2458dx ? c2458dx.f745f : c2458dx.m854h())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean m856f() {
        return this.f743d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean m855g() {
        return this.f744e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public byte[] m854h() {
        return this.f745f;
    }

    public int hashCode() {
        String str = this.f740a;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.f741b;
        return ((((((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.f742c) * 1000003) ^ (true != this.f743d ? 1237 : 1231)) * 1000003) ^ (true == this.f744e ? 1231 : 1237)) * 1000003) ^ Arrays.hashCode(this.f745f);
    }

    public String toString() {
        String str = this.f740a;
        long j = this.f741b;
        int r3 = this.f742c;
        boolean z = this.f743d;
        boolean z2 = this.f744e;
        String arrays = Arrays.toString(this.f745f);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 126 + String.valueOf(arrays).length());
        sb.append("ZipEntry{name=");
        sb.append(str);
        sb.append(", size=");
        sb.append(j);
        sb.append(", compressionMethod=");
        sb.append(r3);
        sb.append(", isPartial=");
        sb.append(z);
        sb.append(", isEndOfArchive=");
        sb.append(z2);
        sb.append(", headerBytes=");
        sb.append(arrays);
        sb.append("}");
        return sb.toString();
    }
}
