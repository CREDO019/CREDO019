package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* renamed from: com.google.android.play.core.assetpacks.bm */
/* loaded from: classes3.dex */
final class C2393bm extends FilterInputStream {

    /* renamed from: a */
    private final C2438dd f501a;

    /* renamed from: b */
    private byte[] f502b;

    /* renamed from: c */
    private long f503c;

    /* renamed from: d */
    private boolean f504d;

    /* renamed from: e */
    private boolean f505e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2393bm(InputStream inputStream) {
        super(inputStream);
        this.f501a = new C2438dd();
        this.f502b = new byte[4096];
        this.f504d = false;
        this.f505e = false;
    }

    /* renamed from: a */
    private final int m966a(byte[] bArr, int r2, int r3) throws IOException {
        return Math.max(0, super.read(bArr, r2, r3));
    }

    /* renamed from: a */
    private final boolean m967a(int r5) throws IOException {
        int m966a = m966a(this.f502b, 0, r5);
        if (m966a != r5) {
            int r2 = r5 - m966a;
            if (m966a(this.f502b, m966a, r2) != r2) {
                this.f501a.m895a(this.f502b, 0, m966a);
                return false;
            }
        }
        this.f501a.m895a(this.f502b, 0, r5);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C2458dx m968a() throws IOException {
        byte[] bArr;
        if (this.f503c <= 0) {
            if (this.f504d) {
            }
            return new C2458dx(null, -1L, -1, false, false, null);
        }
        do {
            bArr = this.f502b;
        } while (read(bArr, 0, bArr.length) != -1);
        if (!this.f504d || this.f505e) {
            return new C2458dx(null, -1L, -1, false, false, null);
        }
        if (!m967a(30)) {
            this.f504d = true;
            return this.f501a.m897a();
        }
        C2458dx m897a = this.f501a.m897a();
        if (m897a.m855g()) {
            this.f505e = true;
            return m897a;
        } else if (m897a.m858d() != BodyPartID.bodyIdMax) {
            int m894b = this.f501a.m894b() - 30;
            long j = m894b;
            int length = this.f502b.length;
            if (j > length) {
                do {
                    length += length;
                } while (length < j);
                this.f502b = Arrays.copyOf(this.f502b, length);
            }
            if (!m967a(m894b)) {
                this.f504d = true;
                return this.f501a.m897a();
            }
            C2458dx m897a2 = this.f501a.m897a();
            this.f503c = m897a2.m858d();
            return m897a2;
        } else {
            throw new C2402bv("Files bigger than 4GiB are not supported.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean m965b() {
        return this.f504d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean m964c() {
        return this.f505e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final long m963d() {
        return this.f503c;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int r7, int r8) throws IOException {
        long j = this.f503c;
        if (j <= 0 || this.f504d) {
            return -1;
        }
        int m966a = m966a(bArr, r7, (int) Math.min(j, r8));
        this.f503c -= m966a;
        if (m966a == 0) {
            this.f504d = true;
            return 0;
        }
        return m966a;
    }
}
