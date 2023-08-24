package com.google.common.p016io;

import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.BaseEncoding */
/* loaded from: classes3.dex */
public abstract class BaseEncoding {
    private static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");

    public abstract boolean canDecode(CharSequence charSequence);

    abstract int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException;

    public abstract InputStream decodingStream(Reader reader);

    abstract void encodeTo(Appendable appendable, byte[] bArr, int r3, int r4) throws IOException;

    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding lowerCase();

    abstract int maxDecodedSize(int r1);

    abstract int maxEncodedSize(int r1);

    public abstract BaseEncoding omitPadding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c);

    public abstract BaseEncoding withSeparator(String str, int r2);

    BaseEncoding() {
    }

    /* renamed from: com.google.common.io.BaseEncoding$DecodingException */
    /* loaded from: classes3.dex */
    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }

        DecodingException(Throwable th) {
            super(th);
        }
    }

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public final String encode(byte[] bArr, int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r4 + r5, bArr.length);
        StringBuilder sb = new StringBuilder(maxEncodedSize(r5));
        try {
            encodeTo(sb, bArr, r4, r5);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() { // from class: com.google.common.io.BaseEncoding.1
            @Override // com.google.common.p016io.ByteSink
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    private static byte[] extract(byte[] bArr, int r3) {
        if (r3 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[r3];
        System.arraycopy(bArr, 0, bArr2, 0, r3);
        return bArr2;
    }

    public final byte[] decode(CharSequence charSequence) {
        try {
            return decodeChecked(charSequence);
        } catch (DecodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    final byte[] decodeChecked(CharSequence charSequence) throws DecodingException {
        CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
        byte[] bArr = new byte[maxDecodedSize(trimTrailingPadding.length())];
        return extract(bArr, decodeTo(bArr, trimTrailingPadding));
    }

    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() { // from class: com.google.common.io.BaseEncoding.2
            @Override // com.google.common.p016io.ByteSource
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    CharSequence trimTrailingPadding(CharSequence charSequence) {
        return (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.common.io.BaseEncoding$Alphabet */
    /* loaded from: classes3.dex */
    public static final class Alphabet {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String str, char[] cArr) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                int log2 = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                this.bitsPerChar = log2;
                int min = Math.min(8, Integer.lowestOneBit(log2));
                try {
                    this.charsPerChunk = 8 / min;
                    this.bytesPerChunk = log2 / min;
                    this.mask = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int r4 = 0; r4 < cArr.length; r4++) {
                        char c = cArr[r4];
                        Preconditions.checkArgument(c < 128, "Non-ASCII character: %s", c);
                        Preconditions.checkArgument(bArr[c] == -1, "Duplicate character: %s", c);
                        bArr[c] = (byte) r4;
                    }
                    this.decodabet = bArr;
                    boolean[] zArr = new boolean[this.charsPerChunk];
                    for (int r3 = 0; r3 < this.bytesPerChunk; r3++) {
                        zArr[IntMath.divide(r3 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                    }
                    this.validPadding = zArr;
                } catch (ArithmeticException e) {
                    String str2 = new String(cArr);
                    throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e);
                }
            } catch (ArithmeticException e2) {
                int length = cArr.length;
                StringBuilder sb = new StringBuilder(35);
                sb.append("Illegal alphabet length ");
                sb.append(length);
                throw new IllegalArgumentException(sb.toString(), e2);
            }
        }

        char encode(int r2) {
            return this.chars[r2];
        }

        boolean isValidPaddingStartPosition(int r3) {
            return this.validPadding[r3 % this.charsPerChunk];
        }

        boolean canDecode(char c) {
            return c <= 127 && this.decodabet[c] != -1;
        }

        int decode(char c) throws DecodingException {
            if (c > 127) {
                String valueOf = String.valueOf(Integer.toHexString(c));
                throw new DecodingException(valueOf.length() != 0 ? "Unrecognized character: 0x".concat(valueOf) : new String("Unrecognized character: 0x"));
            }
            byte b = this.decodabet[c];
            if (b == -1) {
                if (c <= ' ' || c == 127) {
                    String valueOf2 = String.valueOf(Integer.toHexString(c));
                    throw new DecodingException(valueOf2.length() != 0 ? "Unrecognized character: 0x".concat(valueOf2) : new String("Unrecognized character: 0x"));
                }
                StringBuilder sb = new StringBuilder(25);
                sb.append("Unrecognized character: ");
                sb.append(c);
                throw new DecodingException(sb.toString());
            }
            return b;
        }

        private boolean hasLowerCase() {
            for (char c : this.chars) {
                if (Ascii.isLowerCase(c)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char c : this.chars) {
                if (Ascii.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        }

        Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int r1 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (r1 < cArr2.length) {
                    cArr[r1] = Ascii.toUpperCase(cArr2[r1]);
                    r1++;
                } else {
                    return new Alphabet(String.valueOf(this.name).concat(".upperCase()"), cArr);
                }
            }
        }

        Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int r1 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (r1 < cArr2.length) {
                    cArr[r1] = Ascii.toLowerCase(cArr2[r1]);
                    r1++;
                } else {
                    return new Alphabet(String.valueOf(this.name).concat(".lowerCase()"), cArr);
                }
            }
        }

        public boolean matches(char c) {
            byte[] bArr = this.decodabet;
            return c < bArr.length && bArr[c] != -1;
        }

        public String toString() {
            return this.name;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof Alphabet) {
                return Arrays.equals(this.chars, ((Alphabet) obj).chars);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.io.BaseEncoding$StandardBaseEncoding */
    /* loaded from: classes3.dex */
    public static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet alphabet;
        @CheckForNull
        @LazyInit
        private transient BaseEncoding lowerCase;
        @CheckForNull
        final Character paddingChar;
        @CheckForNull
        @LazyInit
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String str, String str2, @CheckForNull Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        StandardBaseEncoding(Alphabet alphabet, @CheckForNull Character ch) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet);
            Preconditions.checkArgument(ch == null || !alphabet.matches(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.paddingChar = ch;
        }

        @Override // com.google.common.p016io.BaseEncoding
        int maxEncodedSize(int r4) {
            return this.alphabet.charsPerChunk * IntMath.divide(r4, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public OutputStream encodingStream(final Writer writer) {
            Preconditions.checkNotNull(writer);
            return new OutputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.1
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                @Override // java.io.OutputStream
                public void write(int r3) throws IOException {
                    this.bitBuffer = (r3 & 255) | (this.bitBuffer << 8);
                    this.bitBufferLength += 8;
                    while (this.bitBufferLength >= StandardBaseEncoding.this.alphabet.bitsPerChar) {
                        writer.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.this.alphabet.bitsPerChar)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }

                @Override // java.io.OutputStream, java.io.Flushable
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    if (this.bitBufferLength > 0) {
                        writer.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer << (StandardBaseEncoding.this.alphabet.bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (this.writtenChars % StandardBaseEncoding.this.alphabet.charsPerChunk != 0) {
                                writer.write(StandardBaseEncoding.this.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    writer.close();
                }
            };
        }

        @Override // com.google.common.p016io.BaseEncoding
        void encodeTo(Appendable appendable, byte[] bArr, int r7, int r8) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(r7, r7 + r8, bArr.length);
            int r0 = 0;
            while (r0 < r8) {
                encodeChunkTo(appendable, bArr, r7 + r0, Math.min(this.alphabet.bytesPerChunk, r8 - r0));
                r0 += this.alphabet.bytesPerChunk;
            }
        }

        void encodeChunkTo(Appendable appendable, byte[] bArr, int r10, int r11) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(r10, r10 + r11, bArr.length);
            int r1 = 0;
            Preconditions.checkArgument(r11 <= this.alphabet.bytesPerChunk);
            long j = 0;
            for (int r0 = 0; r0 < r11; r0++) {
                j = (j | (bArr[r10 + r0] & 255)) << 8;
            }
            int r9 = ((r11 + 1) * 8) - this.alphabet.bitsPerChar;
            while (r1 < r11 * 8) {
                appendable.append(this.alphabet.encode(((int) (j >>> (r9 - r1))) & this.alphabet.mask));
                r1 += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (r1 < this.alphabet.bytesPerChunk * 8) {
                    appendable.append(this.paddingChar.charValue());
                    r1 += this.alphabet.bitsPerChar;
                }
            }
        }

        @Override // com.google.common.p016io.BaseEncoding
        int maxDecodedSize(int r5) {
            return (int) (((this.alphabet.bitsPerChar * r5) + 7) / 8);
        }

        @Override // com.google.common.p016io.BaseEncoding
        CharSequence trimTrailingPadding(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            Character ch = this.paddingChar;
            if (ch == null) {
                return charSequence;
            }
            char charValue = ch.charValue();
            int length = charSequence.length() - 1;
            while (length >= 0 && charSequence.charAt(length) == charValue) {
                length--;
            }
            return charSequence.subSequence(0, length + 1);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                for (int r0 = 0; r0 < trimTrailingPadding.length(); r0++) {
                    if (!this.alphabet.canDecode(trimTrailingPadding.charAt(r0))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.google.common.p016io.BaseEncoding
        int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int length = trimTrailingPadding.length();
                StringBuilder sb = new StringBuilder(32);
                sb.append("Invalid input length ");
                sb.append(length);
                throw new DecodingException(sb.toString());
            }
            int r1 = 0;
            int r2 = 0;
            while (r1 < trimTrailingPadding.length()) {
                long j = 0;
                int r6 = 0;
                for (int r5 = 0; r5 < this.alphabet.charsPerChunk; r5++) {
                    j <<= this.alphabet.bitsPerChar;
                    if (r1 + r5 < trimTrailingPadding.length()) {
                        j |= this.alphabet.decode(trimTrailingPadding.charAt(r6 + r1));
                        r6++;
                    }
                }
                int r52 = (this.alphabet.bytesPerChunk * 8) - (r6 * this.alphabet.bitsPerChar);
                int r62 = (this.alphabet.bytesPerChunk - 1) * 8;
                while (r62 >= r52) {
                    bArr[r2] = (byte) ((j >>> r62) & 255);
                    r62 -= 8;
                    r2++;
                }
                r1 += this.alphabet.charsPerChunk;
            }
            return r2;
        }

        @Override // com.google.common.p016io.BaseEncoding
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.2
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int readChars = 0;
                boolean hitPadding = false;

                @Override // java.io.InputStream
                public int read() throws IOException {
                    while (true) {
                        int read = reader.read();
                        if (read == -1) {
                            if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                                return -1;
                            }
                            int r1 = this.readChars;
                            StringBuilder sb = new StringBuilder(32);
                            sb.append("Invalid input length ");
                            sb.append(r1);
                            throw new DecodingException(sb.toString());
                        }
                        this.readChars++;
                        char c = (char) read;
                        if (StandardBaseEncoding.this.paddingChar != null && StandardBaseEncoding.this.paddingChar.charValue() == c) {
                            if (this.hitPadding || (this.readChars != 1 && StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars - 1))) {
                                this.hitPadding = true;
                            }
                        } else if (this.hitPadding) {
                            int r2 = this.readChars;
                            StringBuilder sb2 = new StringBuilder(61);
                            sb2.append("Expected padding character but found '");
                            sb2.append(c);
                            sb2.append("' at index ");
                            sb2.append(r2);
                            throw new DecodingException(sb2.toString());
                        } else {
                            int r12 = this.bitBuffer << StandardBaseEncoding.this.alphabet.bitsPerChar;
                            this.bitBuffer = r12;
                            this.bitBuffer = StandardBaseEncoding.this.alphabet.decode(c) | r12;
                            int r0 = this.bitBufferLength + StandardBaseEncoding.this.alphabet.bitsPerChar;
                            this.bitBufferLength = r0;
                            if (r0 >= 8) {
                                int r02 = r0 - 8;
                                this.bitBufferLength = r02;
                                return (this.bitBuffer >> r02) & 255;
                            }
                        }
                    }
                    int r13 = this.readChars;
                    StringBuilder sb3 = new StringBuilder(41);
                    sb3.append("Padding cannot start at index ");
                    sb3.append(r13);
                    throw new DecodingException(sb3.toString());
                }

                @Override // java.io.InputStream
                public int read(byte[] bArr, int r5, int r6) throws IOException {
                    int r62 = r6 + r5;
                    Preconditions.checkPositionIndexes(r5, r62, bArr.length);
                    int r0 = r5;
                    while (r0 < r62) {
                        int read = read();
                        if (read == -1) {
                            int r02 = r0 - r5;
                            if (r02 == 0) {
                                return -1;
                            }
                            return r02;
                        }
                        bArr[r0] = (byte) read;
                        r0++;
                    }
                    return r0 - r5;
                }

                @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    reader.close();
                }
            };
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : newInstance(this.alphabet, null);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding withPadChar(char c) {
            Character ch;
            return (8 % this.alphabet.bitsPerChar == 0 || ((ch = this.paddingChar) != null && ch.charValue() == c)) ? this : newInstance(this.alphabet, Character.valueOf(c));
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding withSeparator(String str, int r7) {
            for (int r1 = 0; r1 < str.length(); r1++) {
                Preconditions.checkArgument(!this.alphabet.matches(str.charAt(r1)), "Separator (%s) cannot contain alphabet characters", str);
            }
            Character ch = this.paddingChar;
            if (ch != null) {
                Preconditions.checkArgument(str.indexOf(ch.charValue()) < 0, "Separator (%s) cannot contain padding character", str);
            }
            return new SeparatedBaseEncoding(this, str, r7);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet upperCase = this.alphabet.upperCase();
                baseEncoding = upperCase == this.alphabet ? this : newInstance(upperCase, this.paddingChar);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet lowerCase = this.alphabet.lowerCase();
                baseEncoding = lowerCase == this.alphabet ? this : newInstance(lowerCase, this.paddingChar);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        BaseEncoding newInstance(Alphabet alphabet, @CheckForNull Character ch) {
            return new StandardBaseEncoding(alphabet, ch);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.paddingChar);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof StandardBaseEncoding) {
                StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
                return this.alphabet.equals(standardBaseEncoding.alphabet) && Objects.equal(this.paddingChar, standardBaseEncoding.paddingChar);
            }
            return false;
        }

        public int hashCode() {
            return this.alphabet.hashCode() ^ Objects.hashCode(this.paddingChar);
        }
    }

    /* renamed from: com.google.common.io.BaseEncoding$Base16Encoding */
    /* loaded from: classes3.dex */
    static final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        Base16Encoding(String str, String str2) {
            this(new Alphabet(str, str2.toCharArray()));
        }

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, null);
            this.encoding = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int r1 = 0; r1 < 256; r1++) {
                this.encoding[r1] = alphabet.encode(r1 >>> 4);
                this.encoding[r1 | 256] = alphabet.encode(r1 & 15);
            }
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding, com.google.common.p016io.BaseEncoding
        void encodeTo(Appendable appendable, byte[] bArr, int r6, int r7) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(r6, r6 + r7, bArr.length);
            for (int r0 = 0; r0 < r7; r0++) {
                int r1 = bArr[r6 + r0] & 255;
                appendable.append(this.encoding[r1]);
                appendable.append(this.encoding[r1 | 256]);
            }
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding, com.google.common.p016io.BaseEncoding
        int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 == 1) {
                int length = charSequence.length();
                StringBuilder sb = new StringBuilder(32);
                sb.append("Invalid input length ");
                sb.append(length);
                throw new DecodingException(sb.toString());
            }
            int r0 = 0;
            int r1 = 0;
            while (r0 < charSequence.length()) {
                bArr[r1] = (byte) ((this.alphabet.decode(charSequence.charAt(r0)) << 4) | this.alphabet.decode(charSequence.charAt(r0 + 1)));
                r0 += 2;
                r1++;
            }
            return r1;
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding
        BaseEncoding newInstance(Alphabet alphabet, @CheckForNull Character ch) {
            return new Base16Encoding(alphabet);
        }
    }

    /* renamed from: com.google.common.io.BaseEncoding$Base64Encoding */
    /* loaded from: classes3.dex */
    static final class Base64Encoding extends StandardBaseEncoding {
        Base64Encoding(String str, String str2, @CheckForNull Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        private Base64Encoding(Alphabet alphabet, @CheckForNull Character ch) {
            super(alphabet, ch);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding, com.google.common.p016io.BaseEncoding
        void encodeTo(Appendable appendable, byte[] bArr, int r7, int r8) throws IOException {
            Preconditions.checkNotNull(appendable);
            int r0 = r7 + r8;
            Preconditions.checkPositionIndexes(r7, r0, bArr.length);
            while (r8 >= 3) {
                int r1 = r7 + 1;
                int r2 = r1 + 1;
                int r72 = ((bArr[r7] & 255) << 16) | ((bArr[r1] & 255) << 8) | (bArr[r2] & 255);
                appendable.append(this.alphabet.encode(r72 >>> 18));
                appendable.append(this.alphabet.encode((r72 >>> 12) & 63));
                appendable.append(this.alphabet.encode((r72 >>> 6) & 63));
                appendable.append(this.alphabet.encode(r72 & 63));
                r8 -= 3;
                r7 = r2 + 1;
            }
            if (r7 < r0) {
                encodeChunkTo(appendable, bArr, r7, r0 - r7);
            }
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding, com.google.common.p016io.BaseEncoding
        int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int length = trimTrailingPadding.length();
                StringBuilder sb = new StringBuilder(32);
                sb.append("Invalid input length ");
                sb.append(length);
                throw new DecodingException(sb.toString());
            }
            int r0 = 0;
            int r1 = 0;
            while (r0 < trimTrailingPadding.length()) {
                int r3 = r0 + 1;
                int r4 = r3 + 1;
                int decode = (this.alphabet.decode(trimTrailingPadding.charAt(r0)) << 18) | (this.alphabet.decode(trimTrailingPadding.charAt(r3)) << 12);
                int r2 = r1 + 1;
                bArr[r1] = (byte) (decode >>> 16);
                if (r4 < trimTrailingPadding.length()) {
                    int r32 = r4 + 1;
                    int decode2 = decode | (this.alphabet.decode(trimTrailingPadding.charAt(r4)) << 6);
                    r1 = r2 + 1;
                    bArr[r2] = (byte) ((decode2 >>> 8) & 255);
                    if (r32 < trimTrailingPadding.length()) {
                        r4 = r32 + 1;
                        r2 = r1 + 1;
                        bArr[r1] = (byte) ((decode2 | this.alphabet.decode(trimTrailingPadding.charAt(r32))) & 255);
                    } else {
                        r0 = r32;
                    }
                }
                r1 = r2;
                r0 = r4;
            }
            return r1;
        }

        @Override // com.google.common.p016io.BaseEncoding.StandardBaseEncoding
        BaseEncoding newInstance(Alphabet alphabet, @CheckForNull Character ch) {
            return new Base64Encoding(alphabet, ch);
        }
    }

    static Reader ignoringReader(final Reader reader, final String str) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(str);
        return new Reader() { // from class: com.google.common.io.BaseEncoding.3
            @Override // java.io.Reader
            public int read() throws IOException {
                int read;
                do {
                    read = reader.read();
                    if (read == -1) {
                        break;
                    }
                } while (str.indexOf((char) read) >= 0);
                return read;
            }

            @Override // java.io.Reader
            public int read(char[] cArr, int r2, int r3) throws IOException {
                throw new UnsupportedOperationException();
            }

            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                reader.close();
            }
        };
    }

    static Appendable separatingAppendable(Appendable appendable, String str, int r3) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(r3 > 0);
        return new Appendable(r3, appendable, str) { // from class: com.google.common.io.BaseEncoding.4
            int charsUntilSeparator;
            final /* synthetic */ int val$afterEveryChars;
            final /* synthetic */ Appendable val$delegate;
            final /* synthetic */ String val$separator;

            {
                this.val$afterEveryChars = r3;
                this.val$delegate = appendable;
                this.val$separator = str;
                this.charsUntilSeparator = r3;
            }

            @Override // java.lang.Appendable
            public Appendable append(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    this.val$delegate.append(this.val$separator);
                    this.charsUntilSeparator = this.val$afterEveryChars;
                }
                this.val$delegate.append(c);
                this.charsUntilSeparator--;
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(@CheckForNull CharSequence charSequence, int r2, int r32) {
                throw new UnsupportedOperationException();
            }

            @Override // java.lang.Appendable
            public Appendable append(@CheckForNull CharSequence charSequence) {
                throw new UnsupportedOperationException();
            }
        };
    }

    static Writer separatingWriter(final Writer writer, String str, int r2) {
        final Appendable separatingAppendable = separatingAppendable(writer, str, r2);
        return new Writer() { // from class: com.google.common.io.BaseEncoding.5
            @Override // java.io.Writer
            public void write(int r22) throws IOException {
                separatingAppendable.append((char) r22);
            }

            @Override // java.io.Writer
            public void write(char[] cArr, int r22, int r3) throws IOException {
                throw new UnsupportedOperationException();
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() throws IOException {
                writer.flush();
            }

            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                writer.close();
            }
        };
    }

    /* renamed from: com.google.common.io.BaseEncoding$SeparatedBaseEncoding */
    /* loaded from: classes3.dex */
    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;

        SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int r3) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = r3;
            Preconditions.checkArgument(r3 > 0, "Cannot add a separator after every %s chars", r3);
        }

        @Override // com.google.common.p016io.BaseEncoding
        CharSequence trimTrailingPadding(CharSequence charSequence) {
            return this.delegate.trimTrailingPadding(charSequence);
        }

        @Override // com.google.common.p016io.BaseEncoding
        int maxEncodedSize(int r5) {
            int maxEncodedSize = this.delegate.maxEncodedSize(r5);
            return maxEncodedSize + (this.separator.length() * IntMath.divide(Math.max(0, maxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        @Override // com.google.common.p016io.BaseEncoding
        public OutputStream encodingStream(Writer writer) {
            return this.delegate.encodingStream(separatingWriter(writer, this.separator, this.afterEveryChars));
        }

        @Override // com.google.common.p016io.BaseEncoding
        void encodeTo(Appendable appendable, byte[] bArr, int r6, int r7) throws IOException {
            this.delegate.encodeTo(separatingAppendable(appendable, this.separator, this.afterEveryChars), bArr, r6, r7);
        }

        @Override // com.google.common.p016io.BaseEncoding
        int maxDecodedSize(int r2) {
            return this.delegate.maxDecodedSize(r2);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            StringBuilder sb = new StringBuilder();
            for (int r1 = 0; r1 < charSequence.length(); r1++) {
                char charAt = charSequence.charAt(r1);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.canDecode(sb);
        }

        @Override // com.google.common.p016io.BaseEncoding
        int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            StringBuilder sb = new StringBuilder(charSequence.length());
            for (int r1 = 0; r1 < charSequence.length(); r1++) {
                char charAt = charSequence.charAt(r1);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.decodeTo(bArr, sb);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(ignoringReader(reader, this.separator));
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding withPadChar(char c) {
            return this.delegate.withPadChar(c).withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding withSeparator(String str, int r2) {
            throw new UnsupportedOperationException("Already have a separator");
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.p016io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            String str = this.separator;
            int r2 = this.afterEveryChars;
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31 + String.valueOf(str).length());
            sb.append(valueOf);
            sb.append(".withSeparator(\"");
            sb.append(str);
            sb.append("\", ");
            sb.append(r2);
            sb.append(")");
            return sb.toString();
        }
    }
}