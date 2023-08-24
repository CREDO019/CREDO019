package com.google.android.play.core.assetpacks;

import java.util.Arrays;

/* renamed from: com.google.android.play.core.assetpacks.dd */
/* loaded from: classes3.dex */
final class C2438dd {

    /* renamed from: a */
    private byte[] f671a = new byte[4096];

    /* renamed from: b */
    private int f672b;

    /* renamed from: c */
    private long f673c;

    /* renamed from: d */
    private long f674d;

    /* renamed from: e */
    private int f675e;

    /* renamed from: f */
    private int f676f;

    /* renamed from: g */
    private int f677g;

    /* renamed from: h */
    private boolean f678h;

    /* renamed from: i */
    private String f679i;

    public C2438dd() {
        m893c();
    }

    /* renamed from: a */
    private final int m896a(int r3, byte[] bArr, int r5, int r6) {
        int r0 = this.f672b;
        if (r0 < r3) {
            int min = Math.min(r6, r3 - r0);
            System.arraycopy(bArr, r5, this.f671a, this.f672b, min);
            int r4 = this.f672b + min;
            this.f672b = r4;
            if (r4 < r3) {
                return -1;
            }
            return min;
        }
        return 0;
    }

    /* renamed from: a */
    public final int m895a(byte[] bArr, int r11, int r12) {
        int m896a = m896a(30, bArr, r11, r12);
        if (m896a != -1) {
            if (this.f673c == -1) {
                long m901b = C2436db.m901b(this.f671a, 0);
                this.f673c = m901b;
                if (m901b == 67324752) {
                    this.f678h = false;
                    this.f674d = C2436db.m901b(this.f671a, 18);
                    this.f677g = C2436db.m899c(this.f671a, 8);
                    this.f675e = C2436db.m899c(this.f671a, 26);
                    int m899c = this.f675e + 30 + C2436db.m899c(this.f671a, 28);
                    this.f676f = m899c;
                    int length = this.f671a.length;
                    if (length < m899c) {
                        do {
                            length += length;
                        } while (length < m899c);
                        this.f671a = Arrays.copyOf(this.f671a, length);
                    }
                } else {
                    this.f678h = true;
                }
            }
            int m896a2 = m896a(this.f676f, bArr, r11 + m896a, r12 - m896a);
            if (m896a2 == -1) {
                return -1;
            }
            int r1 = m896a + m896a2;
            if (!this.f678h && this.f679i == null) {
                this.f679i = new String(this.f671a, 30, this.f675e);
            }
            return r1;
        }
        return -1;
    }

    /* renamed from: a */
    public final C2458dx m897a() {
        int r0 = this.f672b;
        int r1 = this.f676f;
        if (r0 < r1) {
            return C2458dx.m861a(this.f679i, this.f674d, this.f677g, true, Arrays.copyOf(this.f671a, r0), this.f678h);
        }
        C2458dx m861a = C2458dx.m861a(this.f679i, this.f674d, this.f677g, false, Arrays.copyOf(this.f671a, r1), this.f678h);
        m893c();
        return m861a;
    }

    /* renamed from: b */
    public final int m894b() {
        return this.f676f;
    }

    /* renamed from: c */
    public final void m893c() {
        this.f672b = 0;
        this.f675e = -1;
        this.f673c = -1L;
        this.f678h = false;
        this.f676f = 30;
        this.f674d = -1L;
        this.f677g = -1;
        this.f679i = null;
    }
}
