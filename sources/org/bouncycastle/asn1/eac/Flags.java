package org.bouncycastle.asn1.eac;

import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: classes5.dex */
public class Flags {
    int value;

    /* loaded from: classes5.dex */
    private static class StringJoiner {
        boolean First = true;

        /* renamed from: b */
        StringBuffer f1611b = new StringBuffer();
        String mSeparator;

        public StringJoiner(String str) {
            this.mSeparator = str;
        }

        public void add(String str) {
            if (this.First) {
                this.First = false;
            } else {
                this.f1611b.append(this.mSeparator);
            }
            this.f1611b.append(str);
        }

        public String toString() {
            return this.f1611b.toString();
        }
    }

    public Flags() {
        this.value = 0;
    }

    public Flags(int r1) {
        this.value = r1;
    }

    String decode(Hashtable hashtable) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            if (isSet(num.intValue())) {
                stringJoiner.add((String) hashtable.get(num));
            }
        }
        return stringJoiner.toString();
    }

    public int getFlags() {
        return this.value;
    }

    public boolean isSet(int r2) {
        return (r2 & this.value) != 0;
    }

    public void set(int r2) {
        this.value = r2 | this.value;
    }
}
