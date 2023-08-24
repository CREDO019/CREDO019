package com.google.common.primitives;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class ParseRequest {
    final int radix;
    final String rawValue;

    private ParseRequest(String str, int r2) {
        this.rawValue = str;
        this.radix = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParseRequest fromString(String str) {
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        char charAt = str.charAt(0);
        int r2 = 16;
        if (str.startsWith("0x") || str.startsWith("0X")) {
            str = str.substring(2);
        } else if (charAt == '#') {
            str = str.substring(1);
        } else if (charAt != '0' || str.length() <= 1) {
            r2 = 10;
        } else {
            str = str.substring(1);
            r2 = 8;
        }
        return new ParseRequest(str, r2);
    }
}
