package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.BitSet;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes5.dex */
public class QCodec extends RFC1522Codec implements StringEncoder, StringDecoder {
    private static final byte BLANK = 32;
    private static final BitSet PRINTABLE_CHARS;
    private static final byte UNDERSCORE = 95;
    private final Charset charset;
    private boolean encodeBlanks;

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected String getEncoding() {
        return "Q";
    }

    static {
        BitSet bitSet = new BitSet(256);
        PRINTABLE_CHARS = bitSet;
        bitSet.set(32);
        bitSet.set(33);
        bitSet.set(34);
        bitSet.set(35);
        bitSet.set(36);
        bitSet.set(37);
        bitSet.set(38);
        bitSet.set(39);
        bitSet.set(40);
        bitSet.set(41);
        bitSet.set(42);
        bitSet.set(43);
        bitSet.set(44);
        bitSet.set(45);
        bitSet.set(46);
        bitSet.set(47);
        for (int r0 = 48; r0 <= 57; r0++) {
            PRINTABLE_CHARS.set(r0);
        }
        BitSet bitSet2 = PRINTABLE_CHARS;
        bitSet2.set(58);
        bitSet2.set(59);
        bitSet2.set(60);
        bitSet2.set(62);
        bitSet2.set(64);
        for (int r02 = 65; r02 <= 90; r02++) {
            PRINTABLE_CHARS.set(r02);
        }
        BitSet bitSet3 = PRINTABLE_CHARS;
        bitSet3.set(91);
        bitSet3.set(92);
        bitSet3.set(93);
        bitSet3.set(94);
        bitSet3.set(96);
        for (int r03 = 97; r03 <= 122; r03++) {
            PRINTABLE_CHARS.set(r03);
        }
        BitSet bitSet4 = PRINTABLE_CHARS;
        bitSet4.set(123);
        bitSet4.set(124);
        bitSet4.set(125);
        bitSet4.set(126);
    }

    public QCodec() {
        this(Charsets.UTF_8);
    }

    public QCodec(Charset charset) {
        this.encodeBlanks = false;
        this.charset = charset;
    }

    public QCodec(String str) {
        this(Charset.forName(str));
    }

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected byte[] doEncoding(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] encodeQuotedPrintable = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, bArr);
        if (this.encodeBlanks) {
            for (int r0 = 0; r0 < encodeQuotedPrintable.length; r0++) {
                if (encodeQuotedPrintable[r0] == 32) {
                    encodeQuotedPrintable[r0] = UNDERSCORE;
                }
            }
        }
        return encodeQuotedPrintable;
    }

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected byte[] doDecoding(byte[] bArr) throws DecoderException {
        boolean z;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        int r2 = 0;
        while (true) {
            if (r2 >= length) {
                z = false;
                break;
            } else if (bArr[r2] == 95) {
                z = true;
                break;
            } else {
                r2++;
            }
        }
        if (z) {
            byte[] bArr2 = new byte[bArr.length];
            for (int r1 = 0; r1 < bArr.length; r1++) {
                byte b = bArr[r1];
                if (b != 95) {
                    bArr2[r1] = b;
                } else {
                    bArr2[r1] = 32;
                }
            }
            return QuotedPrintableCodec.decodeQuotedPrintable(bArr2);
        }
        return QuotedPrintableCodec.decodeQuotedPrintable(bArr);
    }

    public String encode(String str, Charset charset) throws EncoderException {
        if (str == null) {
            return null;
        }
        return encodeText(str, charset);
    }

    public String encode(String str, String str2) throws EncoderException {
        if (str == null) {
            return null;
        }
        try {
            return encodeText(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new EncoderException(e.getMessage(), e);
        }
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) throws EncoderException {
        if (str == null) {
            return null;
        }
        return encode(str, getCharset());
    }

    @Override // org.apache.commons.codec.StringDecoder
    public String decode(String str) throws DecoderException {
        if (str == null) {
            return null;
        }
        try {
            return decodeText(str);
        } catch (UnsupportedEncodingException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be encoded using Q codec");
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be decoded using Q codec");
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getDefaultCharset() {
        return this.charset.name();
    }

    public boolean isEncodeBlanks() {
        return this.encodeBlanks;
    }

    public void setEncodeBlanks(boolean z) {
        this.encodeBlanks = z;
    }
}
