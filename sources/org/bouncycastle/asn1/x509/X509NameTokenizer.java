package org.bouncycastle.asn1.x509;

/* loaded from: classes5.dex */
public class X509NameTokenizer {
    private StringBuffer buf;
    private int index;
    private char separator;
    private String value;

    public X509NameTokenizer(String str) {
        this(str, ',');
    }

    public X509NameTokenizer(String str, char c) {
        this.buf = new StringBuffer();
        this.value = str;
        this.index = -1;
        this.separator = c;
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    public String nextToken() {
        if (this.index == this.value.length()) {
            return null;
        }
        int r0 = this.index + 1;
        this.buf.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (r0 != this.value.length()) {
            char charAt = this.value.charAt(r0);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                }
            } else if (!z && !z2) {
                if (charAt == '\\') {
                    this.buf.append(charAt);
                    z = true;
                } else if (charAt == this.separator) {
                    break;
                } else {
                    this.buf.append(charAt);
                }
                r0++;
            }
            this.buf.append(charAt);
            z = false;
            r0++;
        }
        this.index = r0;
        return this.buf.toString();
    }
}