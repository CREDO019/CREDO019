package org.apache.commons.fileupload;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.fileupload.util.mime.MimeUtility;

/* loaded from: classes5.dex */
public class ParameterParser {
    private char[] chars = null;
    private int pos = 0;
    private int len = 0;

    /* renamed from: i1 */
    private int f1559i1 = 0;

    /* renamed from: i2 */
    private int f1560i2 = 0;
    private boolean lowerCaseNames = false;

    private boolean hasChar() {
        return this.pos < this.len;
    }

    private String getToken(boolean z) {
        while (true) {
            int r0 = this.f1559i1;
            if (r0 >= this.f1560i2 || !Character.isWhitespace(this.chars[r0])) {
                break;
            }
            this.f1559i1++;
        }
        while (true) {
            int r02 = this.f1560i2;
            if (r02 <= this.f1559i1 || !Character.isWhitespace(this.chars[r02 - 1])) {
                break;
            }
            this.f1560i2--;
        }
        if (z) {
            int r5 = this.f1560i2;
            int r03 = this.f1559i1;
            if (r5 - r03 >= 2) {
                char[] cArr = this.chars;
                if (cArr[r03] == '\"' && cArr[r5 - 1] == '\"') {
                    this.f1559i1 = r03 + 1;
                    this.f1560i2 = r5 - 1;
                }
            }
        }
        int r04 = this.f1560i2;
        int r1 = this.f1559i1;
        if (r04 > r1) {
            return new String(this.chars, r1, r04 - r1);
        }
        return null;
    }

    private boolean isOneOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String parseToken(char[] cArr) {
        int r0 = this.pos;
        this.f1559i1 = r0;
        this.f1560i2 = r0;
        while (hasChar() && !isOneOf(this.chars[this.pos], cArr)) {
            this.f1560i2++;
            this.pos++;
        }
        return getToken(false);
    }

    private String parseQuotedToken(char[] cArr) {
        int r0 = this.pos;
        this.f1559i1 = r0;
        this.f1560i2 = r0;
        boolean z = false;
        boolean z2 = false;
        while (hasChar()) {
            char c = this.chars[this.pos];
            if (!z && isOneOf(c, cArr)) {
                break;
            }
            if (!z2 && c == '\"') {
                z = !z;
            }
            z2 = !z2 && c == '\\';
            this.f1560i2++;
            this.pos++;
        }
        return getToken(true);
    }

    public boolean isLowerCaseNames() {
        return this.lowerCaseNames;
    }

    public void setLowerCaseNames(boolean z) {
        this.lowerCaseNames = z;
    }

    public Map<String, String> parse(String str, char[] cArr) {
        if (cArr == null || cArr.length == 0) {
            return new HashMap();
        }
        char c = cArr[0];
        if (str != null) {
            int length = str.length();
            for (char c2 : cArr) {
                int indexOf = str.indexOf(c2);
                if (indexOf != -1 && indexOf < length) {
                    c = c2;
                    length = indexOf;
                }
            }
        }
        return parse(str, c);
    }

    public Map<String, String> parse(String str, char c) {
        if (str == null) {
            return new HashMap();
        }
        return parse(str.toCharArray(), c);
    }

    public Map<String, String> parse(char[] cArr, char c) {
        if (cArr == null) {
            return new HashMap();
        }
        return parse(cArr, 0, cArr.length, c);
    }

    public Map<String, String> parse(char[] cArr, int r8, int r9, char c) {
        if (cArr == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        this.chars = cArr;
        this.pos = r8;
        this.len = r9;
        while (hasChar()) {
            String parseToken = parseToken(new char[]{'=', c});
            String str = null;
            if (hasChar()) {
                int r4 = this.pos;
                if (cArr[r4] == '=') {
                    this.pos = r4 + 1;
                    str = parseQuotedToken(new char[]{c});
                    if (str != null) {
                        try {
                            str = MimeUtility.decodeText(str);
                        } catch (UnsupportedEncodingException unused) {
                        }
                    }
                }
            }
            if (hasChar()) {
                int r92 = this.pos;
                if (cArr[r92] == c) {
                    this.pos = r92 + 1;
                }
            }
            if (parseToken != null && parseToken.length() > 0) {
                if (this.lowerCaseNames) {
                    parseToken = parseToken.toLowerCase(Locale.ENGLISH);
                }
                hashMap.put(parseToken, str);
            }
        }
        return hashMap;
    }
}
