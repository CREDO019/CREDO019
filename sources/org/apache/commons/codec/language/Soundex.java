package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes5.dex */
public class Soundex implements StringEncoder {
    @Deprecated
    private int maxLength;
    private final char[] soundexMapping;
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    private static final char[] US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();
    public static final Soundex US_ENGLISH = new Soundex();

    public Soundex() {
        this.maxLength = 4;
        this.soundexMapping = US_ENGLISH_MAPPING;
    }

    public Soundex(char[] cArr) {
        this.maxLength = 4;
        char[] cArr2 = new char[cArr.length];
        this.soundexMapping = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
    }

    public Soundex(String str) {
        this.maxLength = 4;
        this.soundexMapping = str.toCharArray();
    }

    public int difference(String str, String str2) throws EncoderException {
        return SoundexUtils.difference(this, str, str2);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
        }
        return soundex((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return soundex(str);
    }

    private char getMappingCode(String str, int r6) {
        char charAt;
        char map = map(str.charAt(r6));
        if (r6 > 1 && map != '0' && ('H' == (charAt = str.charAt(r6 - 1)) || 'W' == charAt)) {
            char charAt2 = str.charAt(r6 - 2);
            if (map(charAt2) == map || 'H' == charAt2 || 'W' == charAt2) {
                return (char) 0;
            }
        }
        return map;
    }

    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    private char[] getSoundexMapping() {
        return this.soundexMapping;
    }

    private char map(char c) {
        int r0 = c - 'A';
        if (r0 < 0 || r0 >= getSoundexMapping().length) {
            throw new IllegalArgumentException("The character is not mapped: " + c);
        }
        return getSoundexMapping()[r0];
    }

    @Deprecated
    public void setMaxLength(int r1) {
        this.maxLength = r1;
    }

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        String clean = SoundexUtils.clean(str);
        if (clean.length() == 0) {
            return clean;
        }
        char[] cArr = {'0', '0', '0', '0'};
        cArr[0] = clean.charAt(0);
        char mappingCode = getMappingCode(clean, 0);
        int r3 = 1;
        int r4 = 1;
        while (r3 < clean.length() && r4 < 4) {
            int r5 = r3 + 1;
            char mappingCode2 = getMappingCode(clean, r3);
            if (mappingCode2 != 0) {
                if (mappingCode2 != '0' && mappingCode2 != mappingCode) {
                    cArr[r4] = mappingCode2;
                    r4++;
                }
                mappingCode = mappingCode2;
            }
            r3 = r5;
        }
        return new String(cArr);
    }
}
