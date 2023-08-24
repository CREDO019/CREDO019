package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.google.android.play.core.assetpacks.by */
/* loaded from: classes3.dex */
final class C2405by extends OutputStream {

    /* renamed from: a */
    private final C2438dd f573a = new C2438dd();

    /* renamed from: b */
    private final File f574b;

    /* renamed from: c */
    private final C2452dr f575c;

    /* renamed from: d */
    private long f576d;

    /* renamed from: e */
    private long f577e;

    /* renamed from: f */
    private FileOutputStream f578f;

    /* renamed from: g */
    private C2458dx f579g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2405by(File file, C2452dr c2452dr) {
        this.f574b = file;
        this.f575c = c2452dr;
    }

    @Override // java.io.OutputStream
    public final void write(int r3) throws IOException {
        write(new byte[]{(byte) r3});
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int r12, int r13) throws IOException {
        int min;
        while (r13 > 0) {
            if (this.f576d == 0 && this.f577e == 0) {
                int m895a = this.f573a.m895a(bArr, r12, r13);
                if (m895a == -1) {
                    return;
                }
                r12 += m895a;
                r13 -= m895a;
                C2458dx m897a = this.f573a.m897a();
                this.f579g = m897a;
                if (m897a.m855g()) {
                    this.f576d = 0L;
                    this.f575c.m871b(this.f579g.m854h(), this.f579g.m854h().length);
                    this.f577e = this.f579g.m854h().length;
                } else if (!this.f579g.m860b() || this.f579g.m862a()) {
                    byte[] m854h = this.f579g.m854h();
                    this.f575c.m871b(m854h, m854h.length);
                    this.f576d = this.f579g.m858d();
                } else {
                    this.f575c.m876a(this.f579g.m854h());
                    File file = new File(this.f574b, this.f579g.m859c());
                    file.getParentFile().mkdirs();
                    this.f576d = this.f579g.m858d();
                    this.f578f = new FileOutputStream(file);
                }
            }
            if (!this.f579g.m862a()) {
                if (this.f579g.m855g()) {
                    this.f575c.m879a(this.f577e, bArr, r12, r13);
                    this.f577e += r13;
                    min = r13;
                } else if (this.f579g.m860b()) {
                    min = (int) Math.min(r13, this.f576d);
                    this.f578f.write(bArr, r12, min);
                    long j = this.f576d - min;
                    this.f576d = j;
                    if (j == 0) {
                        this.f578f.close();
                    }
                } else {
                    min = (int) Math.min(r13, this.f576d);
                    this.f575c.m879a((this.f579g.m854h().length + this.f579g.m858d()) - this.f576d, bArr, r12, min);
                    this.f576d -= min;
                }
                r12 += min;
                r13 -= min;
            }
        }
    }
}
