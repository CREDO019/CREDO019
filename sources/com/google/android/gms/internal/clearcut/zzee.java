package com.google.android.gms.internal.clearcut;

/* loaded from: classes2.dex */
final class zzee {
    private final String info;
    private int position = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzee(String str) {
        this.info = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasNext() {
        return this.position < this.info.length();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int next() {
        String str = this.info;
        int r1 = this.position;
        this.position = r1 + 1;
        char charAt = str.charAt(r1);
        if (charAt < 55296) {
            return charAt;
        }
        int r0 = charAt & 8191;
        int r2 = 13;
        while (true) {
            String str2 = this.info;
            int r4 = this.position;
            this.position = r4 + 1;
            char charAt2 = str2.charAt(r4);
            if (charAt2 < 55296) {
                return r0 | (charAt2 << r2);
            }
            r0 |= (charAt2 & 8191) << r2;
            r2 += 13;
        }
    }
}
