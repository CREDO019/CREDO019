package com.google.zxing.client.result;

/* loaded from: classes3.dex */
public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x020e, code lost:
        if (r1.equals("10") == false) goto L12;
     */
    @Override // com.google.zxing.client.result.ResultParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r24) {
        /*
            Method dump skipped, instructions count: 866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int r4, String str) {
        if (str.charAt(r4) != '(') {
            return null;
        }
        String substring = str.substring(r4 + 1);
        StringBuilder sb = new StringBuilder();
        for (int r0 = 0; r0 < substring.length(); r0++) {
            char charAt = substring.charAt(r0);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int r3, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(r3);
        for (int r4 = 0; r4 < substring.length(); r4++) {
            char charAt = substring.charAt(r4);
            if (charAt == '(') {
                if (findAIvalue(r4, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
