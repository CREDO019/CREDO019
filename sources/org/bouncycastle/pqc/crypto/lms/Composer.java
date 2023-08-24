package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.util.Encodable;

/* loaded from: classes3.dex */
public class Composer {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    private Composer() {
    }

    public static Composer compose() {
        return new Composer();
    }

    public Composer bool(boolean z) {
        this.bos.write(z ? 1 : 0);
        return this;
    }

    public byte[] build() {
        return this.bos.toByteArray();
    }

    public Composer bytes(Encodable encodable) {
        try {
            this.bos.write(encodable.getEncoded());
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Composer bytes(byte[] bArr) {
        try {
            this.bos.write(bArr);
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Composer bytes(byte[] bArr, int r3, int r4) {
        try {
            this.bos.write(bArr, r3, r4);
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Composer bytes(Encodable[] encodableArr) {
        try {
            for (Encodable encodable : encodableArr) {
                this.bos.write(encodable.getEncoded());
            }
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Composer bytes(byte[][] bArr) {
        try {
            for (byte[] bArr2 : bArr) {
                this.bos.write(bArr2);
            }
            return this;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Composer bytes(byte[][] bArr, int r4, int r5) {
        while (r4 != r5) {
            try {
                this.bos.write(bArr[r4]);
                r4++;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return this;
    }

    public Composer pad(int r2, int r3) {
        while (r3 >= 0) {
            try {
                this.bos.write(r2);
                r3--;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return this;
    }

    public Composer padUntil(int r2, int r3) {
        while (this.bos.size() < r3) {
            this.bos.write(r2);
        }
        return this;
    }

    public Composer u16str(int r3) {
        int r32 = r3 & 65535;
        this.bos.write((byte) (r32 >>> 8));
        this.bos.write((byte) r32);
        return this;
    }

    public Composer u32str(int r3) {
        this.bos.write((byte) (r3 >>> 24));
        this.bos.write((byte) (r3 >>> 16));
        this.bos.write((byte) (r3 >>> 8));
        this.bos.write((byte) r3);
        return this;
    }

    public Composer u64str(long j) {
        u32str((int) (j >>> 32));
        u32str((int) j);
        return this;
    }
}
