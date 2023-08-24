package expo.modules.structuredheaders;

import java.nio.CharBuffer;

/* loaded from: classes3.dex */
public class ParseException extends IllegalArgumentException {
    private static final long serialVersionUID = -5222947525946866985L;
    private final String data;
    private final int position;

    public ParseException(String str, String str2, int r3, Throwable th) {
        super(str, th);
        this.position = r3;
        this.data = str2;
    }

    public ParseException(String str, String str2, int r4) {
        this(str, str2, r4, null);
    }

    public ParseException(String str, CharBuffer charBuffer, Throwable th) {
        this(str, asString(charBuffer), charBuffer.position(), th);
    }

    public ParseException(String str, CharBuffer charBuffer) {
        this(str, asString(charBuffer), charBuffer.position(), null);
    }

    public String getData() {
        return this.data;
    }

    public int getPosition() {
        return this.position;
    }

    public String getDiagnostics() {
        StringBuilder sb = new StringBuilder();
        sb.append(">>");
        sb.append(this.data);
        sb.append("<<");
        sb.append('\n');
        sb.append("  ");
        for (int r3 = 0; r3 < this.position; r3++) {
            sb.append('-');
        }
        sb.append("^ ");
        if (this.position < this.data.length()) {
            sb.append(String.format("(0x%02x) ", Integer.valueOf(this.data.charAt(this.position))));
        }
        sb.append(super.getMessage());
        sb.append('\n');
        return sb.toString();
    }

    private static String asString(CharBuffer charBuffer) {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 < charBuffer.position() + charBuffer.remaining(); r1++) {
            sb.append(charBuffer.get(r1));
        }
        return sb.toString();
    }
}
