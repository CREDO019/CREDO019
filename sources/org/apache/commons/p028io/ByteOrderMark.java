package org.apache.commons.p028io;

import java.io.Serializable;

/* renamed from: org.apache.commons.io.ByteOrderMark */
/* loaded from: classes5.dex */
public class ByteOrderMark implements Serializable {
    public static final char UTF_BOM = 65279;
    private static final long serialVersionUID = 1;
    private final int[] bytes;
    private final String charsetName;
    public static final ByteOrderMark UTF_8 = new ByteOrderMark("UTF-8", 239, 187, 191);
    public static final ByteOrderMark UTF_16BE = new ByteOrderMark("UTF-16BE", 254, 255);
    public static final ByteOrderMark UTF_16LE = new ByteOrderMark("UTF-16LE", 255, 254);
    public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", 0, 0, 254, 255);
    public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", 255, 254, 0, 0);

    public ByteOrderMark(String str, int... r4) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("No charsetName specified");
        }
        if (r4 == null || r4.length == 0) {
            throw new IllegalArgumentException("No bytes specified");
        }
        this.charsetName = str;
        int[] r3 = new int[r4.length];
        this.bytes = r3;
        System.arraycopy(r4, 0, r3, 0, r4.length);
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public int length() {
        return this.bytes.length;
    }

    public int get(int r2) {
        return this.bytes[r2];
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[this.bytes.length];
        int r1 = 0;
        while (true) {
            int[] r2 = this.bytes;
            if (r1 >= r2.length) {
                return bArr;
            }
            bArr[r1] = (byte) r2[r1];
            r1++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOrderMark)) {
            return false;
        }
        ByteOrderMark byteOrderMark = (ByteOrderMark) obj;
        if (this.bytes.length != byteOrderMark.length()) {
            return false;
        }
        int r0 = 0;
        while (true) {
            int[] r2 = this.bytes;
            if (r0 >= r2.length) {
                return true;
            }
            if (r2[r0] != byteOrderMark.get(r0)) {
                return false;
            }
            r0++;
        }
    }

    public int hashCode() {
        int hashCode = getClass().hashCode();
        for (int r4 : this.bytes) {
            hashCode += r4;
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append(this.charsetName);
        sb.append(": ");
        for (int r1 = 0; r1 < this.bytes.length; r1++) {
            if (r1 > 0) {
                sb.append(",");
            }
            sb.append("0x");
            sb.append(Integer.toHexString(this.bytes[r1] & 255).toUpperCase());
        }
        sb.append(']');
        return sb.toString();
    }
}
