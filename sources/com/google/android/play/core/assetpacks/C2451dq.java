package com.google.android.play.core.assetpacks;

/* renamed from: com.google.android.play.core.assetpacks.dq */
/* loaded from: classes3.dex */
final class C2451dq {

    /* renamed from: a */
    private final int f718a;

    /* renamed from: b */
    private final String f719b;

    /* renamed from: c */
    private final long f720c;

    /* renamed from: d */
    private final long f721d;

    /* renamed from: e */
    private final int f722e;

    C2451dq() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2451dq(int r1, String str, long j, long j2, int r7) {
        this();
        this.f718a = r1;
        this.f719b = str;
        this.f720c = j;
        this.f721d = j2;
        this.f722e = r7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m886a() {
        return this.f718a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m885b() {
        return this.f719b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public long m884c() {
        return this.f720c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public long m883d() {
        return this.f721d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m882e() {
        return this.f722e;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2451dq) {
            C2451dq c2451dq = (C2451dq) obj;
            if (this.f718a == c2451dq.m886a() && ((str = this.f719b) != null ? str.equals(c2451dq.m885b()) : c2451dq.m885b() == null) && this.f720c == c2451dq.m884c() && this.f721d == c2451dq.m883d() && this.f722e == c2451dq.m882e()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int r0 = (this.f718a ^ 1000003) * 1000003;
        String str = this.f719b;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.f720c;
        long j2 = this.f721d;
        return ((((((r0 ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.f722e;
    }

    public String toString() {
        int r0 = this.f718a;
        String str = this.f719b;
        long j = this.f720c;
        long j2 = this.f721d;
        int r6 = this.f722e;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 157);
        sb.append("SliceCheckpoint{fileExtractionStatus=");
        sb.append(r0);
        sb.append(", filePath=");
        sb.append(str);
        sb.append(", fileOffset=");
        sb.append(j);
        sb.append(", remainingBytes=");
        sb.append(j2);
        sb.append(", previousChunk=");
        sb.append(r6);
        sb.append("}");
        return sb.toString();
    }
}
