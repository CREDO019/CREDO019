package org.bouncycastle.i18n.filter;

/* loaded from: classes5.dex */
public class SQLFilter implements Filter {
    @Override // org.bouncycastle.i18n.filter.Filter
    public String doFilter(String str) {
        int r1;
        String str2;
        StringBuffer stringBuffer = new StringBuffer(str);
        int r4 = 0;
        while (r4 < stringBuffer.length()) {
            char charAt = stringBuffer.charAt(r4);
            if (charAt == '\n') {
                r1 = r4 + 1;
                str2 = "\\n";
            } else if (charAt == '\r') {
                r1 = r4 + 1;
                str2 = "\\r";
            } else if (charAt == '\"') {
                r1 = r4 + 1;
                str2 = "\\\"";
            } else if (charAt == '\'') {
                r1 = r4 + 1;
                str2 = "\\'";
            } else if (charAt == '-') {
                r1 = r4 + 1;
                str2 = "\\-";
            } else if (charAt == '/') {
                r1 = r4 + 1;
                str2 = "\\/";
            } else if (charAt == ';') {
                r1 = r4 + 1;
                str2 = "\\;";
            } else if (charAt == '=') {
                r1 = r4 + 1;
                str2 = "\\=";
            } else if (charAt != '\\') {
                r4++;
            } else {
                r1 = r4 + 1;
                str2 = "\\\\";
            }
            stringBuffer.replace(r4, r1, str2);
            r4 = r1;
            r4++;
        }
        return stringBuffer.toString();
    }

    @Override // org.bouncycastle.i18n.filter.Filter
    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}
