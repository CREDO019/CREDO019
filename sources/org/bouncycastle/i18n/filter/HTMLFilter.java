package org.bouncycastle.i18n.filter;

/* loaded from: classes5.dex */
public class HTMLFilter implements Filter {
    @Override // org.bouncycastle.i18n.filter.Filter
    public String doFilter(String str) {
        int r1;
        String str2;
        StringBuffer stringBuffer = new StringBuffer(str);
        int r4 = 0;
        while (r4 < stringBuffer.length()) {
            char charAt = stringBuffer.charAt(r4);
            if (charAt == '\"') {
                r1 = r4 + 1;
                str2 = "&#34";
            } else if (charAt == '#') {
                r1 = r4 + 1;
                str2 = "&#35";
            } else if (charAt == '+') {
                r1 = r4 + 1;
                str2 = "&#43";
            } else if (charAt == '-') {
                r1 = r4 + 1;
                str2 = "&#45";
            } else if (charAt == '>') {
                r1 = r4 + 1;
                str2 = "&#62";
            } else if (charAt == ';') {
                r1 = r4 + 1;
                str2 = "&#59";
            } else if (charAt != '<') {
                switch (charAt) {
                    case '%':
                        r1 = r4 + 1;
                        str2 = "&#37";
                        break;
                    case '&':
                        r1 = r4 + 1;
                        str2 = "&#38";
                        break;
                    case '\'':
                        r1 = r4 + 1;
                        str2 = "&#39";
                        break;
                    case '(':
                        r1 = r4 + 1;
                        str2 = "&#40";
                        break;
                    case ')':
                        r1 = r4 + 1;
                        str2 = "&#41";
                        break;
                    default:
                        r4 -= 3;
                        continue;
                        r4 += 4;
                }
            } else {
                r1 = r4 + 1;
                str2 = "&#60";
            }
            stringBuffer.replace(r4, r1, str2);
            r4 += 4;
        }
        return stringBuffer.toString();
    }

    @Override // org.bouncycastle.i18n.filter.Filter
    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}
