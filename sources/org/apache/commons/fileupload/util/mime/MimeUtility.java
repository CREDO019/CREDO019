package org.apache.commons.fileupload.util.mime;

import com.RNFetchBlob.RNFetchBlobConst;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public final class MimeUtility {
    private static final String BASE64_ENCODING_MARKER = "B";
    private static final String ENCODED_TOKEN_FINISHER = "?=";
    private static final String ENCODED_TOKEN_MARKER = "=?";
    private static final String LINEAR_WHITESPACE = " \t\r\n";
    private static final Map<String, String> MIME2JAVA;
    private static final String QUOTEDPRINTABLE_ENCODING_MARKER = "Q";
    private static final String US_ASCII_CHARSET = "US-ASCII";

    static {
        HashMap hashMap = new HashMap();
        MIME2JAVA = hashMap;
        hashMap.put("iso-2022-cn", "ISO2022CN");
        hashMap.put("iso-2022-kr", "ISO2022KR");
        hashMap.put("utf-8", "UTF8");
        hashMap.put(RNFetchBlobConst.RNFB_RESPONSE_UTF8, "UTF8");
        hashMap.put("ja_jp.iso2022-7", "ISO2022JP");
        hashMap.put("ja_jp.eucjp", "EUCJIS");
        hashMap.put("euc-kr", "KSC5601");
        hashMap.put("euckr", "KSC5601");
        hashMap.put("us-ascii", "ISO-8859-1");
        hashMap.put("x-us-ascii", "ISO-8859-1");
    }

    private MimeUtility() {
    }

    public static String decodeText(String str) throws UnsupportedEncodingException {
        if (str.indexOf(ENCODED_TOKEN_MARKER) < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        int r5 = 0;
        boolean z = false;
        int r7 = -1;
        int r8 = -1;
        while (r5 < length) {
            if (LINEAR_WHITESPACE.indexOf(str.charAt(r5)) != -1) {
                int r72 = r5;
                while (true) {
                    if (r72 >= length) {
                        int r12 = r72;
                        r7 = r5;
                        r5 = r12;
                        break;
                    } else if (LINEAR_WHITESPACE.indexOf(str.charAt(r72)) == -1) {
                        r8 = r72;
                        r7 = r5;
                        r5 = r8;
                        break;
                    } else {
                        r72++;
                    }
                }
            } else {
                int r9 = r5;
                while (r9 < length && LINEAR_WHITESPACE.indexOf(str.charAt(r9)) == -1) {
                    r9++;
                }
                String substring = str.substring(r5, r9);
                if (substring.startsWith(ENCODED_TOKEN_MARKER)) {
                    try {
                        String decodeWord = decodeWord(substring);
                        if (!z && r7 != -1) {
                            sb.append(str.substring(r7, r8));
                            r7 = -1;
                        }
                        sb.append(decodeWord);
                        z = true;
                        r5 = r9;
                    } catch (ParseException unused) {
                    }
                }
                if (r7 != -1) {
                    sb.append(str.substring(r7, r8));
                    r7 = -1;
                }
                sb.append(substring);
                r5 = r9;
                z = false;
            }
        }
        return sb.toString();
    }

    private static String decodeWord(String str) throws ParseException, UnsupportedEncodingException {
        if (!str.startsWith(ENCODED_TOKEN_MARKER)) {
            throw new ParseException("Invalid RFC 2047 encoded-word: " + str);
        }
        int indexOf = str.indexOf(63, 2);
        if (indexOf == -1) {
            throw new ParseException("Missing charset in RFC 2047 encoded-word: " + str);
        }
        String lowerCase = str.substring(2, indexOf).toLowerCase(Locale.ENGLISH);
        int r2 = indexOf + 1;
        int indexOf2 = str.indexOf(63, r2);
        if (indexOf2 == -1) {
            throw new ParseException("Missing encoding in RFC 2047 encoded-word: " + str);
        }
        String substring = str.substring(r2, indexOf2);
        int r0 = indexOf2 + 1;
        int indexOf3 = str.indexOf(ENCODED_TOKEN_FINISHER, r0);
        if (indexOf3 == -1) {
            throw new ParseException("Missing encoded text in RFC 2047 encoded-word: " + str);
        }
        String substring2 = str.substring(r0, indexOf3);
        if (substring2.length() == 0) {
            return "";
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(substring2.length());
            byte[] bytes = substring2.getBytes("US-ASCII");
            if (substring.equals(BASE64_ENCODING_MARKER)) {
                Base64Decoder.decode(bytes, byteArrayOutputStream);
            } else if (substring.equals(QUOTEDPRINTABLE_ENCODING_MARKER)) {
                QuotedPrintableDecoder.decode(bytes, byteArrayOutputStream);
            } else {
                throw new UnsupportedEncodingException("Unknown RFC 2047 encoding: " + substring);
            }
            return new String(byteArrayOutputStream.toByteArray(), javaCharset(lowerCase));
        } catch (IOException unused) {
            throw new UnsupportedEncodingException("Invalid RFC 2047 encoding");
        }
    }

    private static String javaCharset(String str) {
        if (str == null) {
            return null;
        }
        String str2 = MIME2JAVA.get(str.toLowerCase(Locale.ENGLISH));
        return str2 == null ? str : str2;
    }
}
