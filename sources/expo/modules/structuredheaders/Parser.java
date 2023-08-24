package expo.modules.structuredheaders;

import android.util.Base64;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class Parser {
    private static char EOD = 65535;
    private final CharBuffer input;
    private final List<Integer> startPositions;

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public Parser(String str) {
        this(Collections.singletonList(str));
        Objects.requireNonNull(str, "input must not be null");
    }

    public Parser(String... strArr) {
        this(Arrays.asList(strArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.StringBuilder] */
    public Parser(Iterable<String> iterable) {
        List<Integer> emptyList = Collections.emptyList();
        Objects.requireNonNull(iterable, "fieldLines must not be null");
        String str = null;
        ?? r2 = 0;
        for (String str2 : iterable) {
            if (str == null) {
                str = checkASCII(str2);
            } else {
                if (r2 == 0) {
                    r2 = new StringBuilder();
                    r2.append(str);
                }
                emptyList = emptyList.size() == 0 ? new ArrayList<>() : emptyList;
                emptyList.add(Integer.valueOf(r2.length()));
                r2.append(",");
                r2.append(checkASCII(str2));
            }
        }
        if (str == null && r2 == 0) {
            throw new ParseException("Empty input", "", 0);
        }
        this.input = CharBuffer.wrap(r2 != 0 ? r2 : str);
        this.startPositions = emptyList;
    }

    private static String checkASCII(String str) {
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if (charAt < 0 || charAt > 127) {
                throw new ParseException(String.format("Invalid character in field line at position %d: '%c' (0x%04x) (input: %s)", Integer.valueOf(r1), Character.valueOf(charAt), Integer.valueOf(charAt), str), str, r1);
            }
        }
        return str;
    }

    private NumberItem<? extends Object> internalParseBareIntegerOrDecimal() {
        int r1;
        int r7;
        StringBuilder sb = new StringBuilder(20);
        if (checkNextChar('-')) {
            r1 = -1;
            advance();
        } else {
            r1 = 1;
        }
        if (!checkNextChar("0123456789")) {
            throw complaint("Illegal start for Integer or Decimal: '" + ((Object) this.input) + "'");
        }
        boolean z = false;
        boolean z2 = false;
        while (hasRemaining() && !z) {
            char peek = peek();
            if (C4561Utils.isDigit(peek)) {
                sb.append(peek);
                advance();
            } else if (z2 || peek != '.') {
                z = true;
            } else if (sb.length() > 12) {
                throw complaint("Illegal position for decimal point in Decimal after '" + ((Object) sb) + "'");
            } else {
                sb.append(peek);
                advance();
                z2 = true;
            }
            int length = sb.length();
            if (z2) {
                r7 = 16;
                continue;
            } else {
                r7 = 15;
                continue;
            }
            if (length > r7) {
                backout();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(z2 ? "Decimal" : "Integer");
                sb2.append(" too long: ");
                sb2.append(sb.length());
                sb2.append(" characters");
                throw complaint(sb2.toString());
            }
        }
        if (!z2) {
            return IntegerItem.valueOf(r1 * Long.parseLong(sb.toString()));
        }
        int indexOf = sb.indexOf(".");
        int length2 = (sb.length() - indexOf) - 1;
        if (length2 < 1) {
            backout();
            throw complaint("Decimal must not end in '.'");
        }
        if (length2 == 1) {
            sb.append("00");
        } else if (length2 == 2) {
            sb.append(SessionDescription.SUPPORTED_SDP_VERSION);
        } else if (length2 > 3) {
            backout();
            throw complaint("Maximum number of fractional digits is 3, found: " + length2 + ", in: " + ((Object) sb));
        }
        sb.deleteCharAt(indexOf);
        return DecimalItem.valueOf(r1 * Long.parseLong(sb.toString()));
    }

    private NumberItem<? extends Object> internalParseIntegerOrDecimal() {
        return internalParseBareIntegerOrDecimal().withParams(internalParseParameters());
    }

    private StringItem internalParseBareString() {
        if (getOrEOD() != '\"') {
            throw complaint("String must start with double quote: '" + ((Object) this.input) + "'");
        }
        StringBuilder sb = new StringBuilder(length());
        while (hasRemaining()) {
            if (this.startPositions.contains(Integer.valueOf(position()))) {
                throw complaint("String crosses field line boundary at position " + position());
            }
            char c = get();
            if (c == '\\') {
                char orEOD = getOrEOD();
                if (orEOD == EOD) {
                    throw complaint("Incomplete escape sequence at position " + position());
                } else if (orEOD != '\"' && orEOD != '\\') {
                    backout();
                    throw complaint("Invalid escape sequence character '" + orEOD + "' at position " + position());
                } else {
                    sb.append(orEOD);
                }
            } else if (c == '\"') {
                return StringItem.valueOf(sb.toString());
            } else {
                if (c < ' ' || c >= 127) {
                    throw complaint("Invalid character in String at position " + position());
                }
                sb.append(c);
            }
        }
        throw complaint("Closing DQUOTE missing");
    }

    private StringItem internalParseString() {
        return internalParseBareString().withParams(internalParseParameters());
    }

    private TokenItem internalParseBareToken() {
        char orEOD = getOrEOD();
        if (orEOD != '*' && !C4561Utils.isAlpha(orEOD)) {
            throw complaint("Token must start with ALPHA or *: '" + ((Object) this.input) + "'");
        }
        StringBuilder sb = new StringBuilder(length());
        sb.append(orEOD);
        boolean z = false;
        while (hasRemaining() && !z) {
            char peek = peek();
            if (peek <= ' ' || peek >= 127 || "\"(),;<=>?@[\\]{}".indexOf(peek) >= 0) {
                z = true;
            } else {
                advance();
                sb.append(peek);
            }
        }
        return TokenItem.valueOf(sb.toString());
    }

    private TokenItem internalParseToken() {
        return internalParseBareToken().withParams(internalParseParameters());
    }

    private static boolean isBase64Char(char c) {
        return C4561Utils.isAlpha(c) || C4561Utils.isDigit(c) || c == '+' || c == '/' || c == '=';
    }

    private ByteSequenceItem internalParseBareByteSequence() {
        if (getOrEOD() != ':') {
            throw complaint("Byte Sequence must start with colon: " + ((Object) this.input));
        }
        StringBuilder sb = new StringBuilder(length());
        boolean z = false;
        while (hasRemaining() && !z) {
            char c = get();
            if (c == ':') {
                z = true;
            } else if (!isBase64Char(c)) {
                throw complaint("Invalid Byte Sequence Character '" + c + "' at position " + position());
            } else {
                sb.append(c);
            }
        }
        if (!z) {
            throw complaint("Byte Sequence must end with COLON: '" + ((Object) sb) + "'");
        }
        try {
            return ByteSequenceItem.valueOf(Base64.decode(sb.toString(), 0));
        } catch (IllegalArgumentException e) {
            throw complaint(e.getMessage(), e);
        }
    }

    private ByteSequenceItem internalParseByteSequence() {
        return internalParseBareByteSequence().withParams(internalParseParameters());
    }

    private BooleanItem internalParseBareBoolean() {
        char orEOD = getOrEOD();
        if (orEOD != EOD) {
            if (orEOD != '?') {
                backout();
                throw complaint(String.format("Boolean must start with question mark, got '%c'", Character.valueOf(orEOD)));
            }
            char orEOD2 = getOrEOD();
            if (orEOD2 != EOD) {
                if (orEOD2 != '0' && orEOD2 != '1') {
                    backout();
                    throw complaint(String.format("Expected '0' or '1' in Boolean, found '%c'", Character.valueOf(orEOD2)));
                }
                return BooleanItem.valueOf(orEOD2 == '1');
            }
            throw complaint("Missing data in Boolean");
        }
        throw complaint("Missing data in Boolean");
    }

    private BooleanItem internalParseBoolean() {
        return internalParseBareBoolean().withParams(internalParseParameters());
    }

    private String internalParseKey() {
        char orEOD = getOrEOD();
        if (orEOD == EOD) {
            throw complaint("Missing data in Key");
        }
        if (orEOD != '*' && !C4561Utils.isLcAlpha(orEOD)) {
            backout();
            throw complaint("Key must start with LCALPHA or '*': " + format(orEOD));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(orEOD);
        boolean z = false;
        while (hasRemaining() && !z) {
            char peek = peek();
            if (C4561Utils.isLcAlpha(peek) || C4561Utils.isDigit(peek) || peek == '_' || peek == '-' || peek == '.' || peek == '*') {
                sb.append(peek);
                advance();
            } else {
                z = true;
            }
        }
        return sb.toString();
    }

    private Parameters internalParseParameters() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z = false;
        while (hasRemaining() && !z) {
            if (peek() != ';') {
                z = true;
            } else {
                advance();
                removeLeadingSP();
                String internalParseKey = internalParseKey();
                Item<? extends Object> valueOf = BooleanItem.valueOf(true);
                if (peek() == '=') {
                    advance();
                    valueOf = internalParseBareItem();
                }
                linkedHashMap.put(internalParseKey, valueOf);
            }
        }
        return Parameters.valueOf(linkedHashMap);
    }

    private Item<? extends Object> internalParseBareItem() {
        if (!hasRemaining()) {
            throw complaint("Empty string found when parsing Bare Item");
        }
        char peek = peek();
        if (C4561Utils.isDigit(peek) || peek == '-') {
            return internalParseBareIntegerOrDecimal();
        }
        if (peek == '\"') {
            return internalParseBareString();
        }
        if (peek == '?') {
            return internalParseBareBoolean();
        }
        if (peek == '*' || C4561Utils.isAlpha(peek)) {
            return internalParseBareToken();
        }
        if (peek == ':') {
            return internalParseBareByteSequence();
        }
        throw complaint("Unexpected start character in Bare Item: " + format(peek));
    }

    private Item<? extends Object> internalParseItem() {
        return internalParseBareItem().withParams(internalParseParameters());
    }

    private ListElement<? extends Object> internalParseItemOrInnerList() {
        return peek() == '(' ? internalParseInnerList() : internalParseItem();
    }

    private List<ListElement<? extends Object>> internalParseOuterList() {
        ArrayList arrayList = new ArrayList();
        while (hasRemaining()) {
            arrayList.add(internalParseItemOrInnerList());
            removeLeadingOWS();
            if (!hasRemaining()) {
                return arrayList;
            }
            char c = get();
            if (c != ',') {
                backout();
                throw complaint("Expected COMMA in List, got: " + format(c));
            }
            removeLeadingOWS();
            if (!hasRemaining()) {
                throw complaint("Found trailing COMMA in List");
            }
        }
        return arrayList;
    }

    private List<Item<? extends Object>> internalParseBareInnerList() {
        if (getOrEOD() != '(') {
            throw complaint("Inner List must start with '(': " + ((Object) this.input));
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (hasRemaining() && !z) {
            removeLeadingSP();
            if (peek() == ')') {
                advance();
                z = true;
            } else {
                arrayList.add(internalParseItem());
                char peek = peek();
                if (peek == EOD) {
                    throw complaint("Missing data in Inner List");
                }
                if (peek != ' ' && peek != ')') {
                    throw complaint("Expected SP or ')' in Inner List, got: " + format(peek));
                }
            }
        }
        if (z) {
            return arrayList;
        }
        throw complaint("Inner List must end with ')': " + ((Object) this.input));
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [expo.modules.structuredheaders.InnerList] */
    private InnerList internalParseInnerList() {
        List<Item<? extends Object>> internalParseBareInnerList = internalParseBareInnerList();
        return InnerList.valueOf(internalParseBareInnerList).withParams2(internalParseParameters());
    }

    private Dictionary internalParseDictionary() {
        ListElement<? extends Object> withParams;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z = false;
        while (hasRemaining() && !z) {
            String internalParseKey = internalParseKey();
            if (peek() == '=') {
                advance();
                withParams = internalParseItemOrInnerList();
            } else {
                withParams = BooleanItem.valueOf(true).withParams(internalParseParameters());
            }
            linkedHashMap.put(internalParseKey, withParams);
            removeLeadingOWS();
            if (hasRemaining()) {
                char c = get();
                if (c != ',') {
                    backout();
                    throw complaint("Expected COMMA in Dictionary, found: " + format(c));
                }
                removeLeadingOWS();
                if (!hasRemaining()) {
                    throw complaint("Found trailing COMMA in Dictionary");
                }
            } else {
                z = true;
            }
        }
        return Dictionary.valueOf(linkedHashMap);
    }

    protected static IntegerItem parseInteger(String str) {
        Parser parser = new Parser(str);
        NumberItem<? extends Object> internalParseIntegerOrDecimal = parser.internalParseIntegerOrDecimal();
        if (!(internalParseIntegerOrDecimal instanceof IntegerItem)) {
            throw parser.complaint("String parsed as Integer '" + str + "' is a Decimal");
        }
        parser.assertEmpty("Extra characters in string parsed as Integer");
        return (IntegerItem) internalParseIntegerOrDecimal;
    }

    protected static DecimalItem parseDecimal(String str) {
        Parser parser = new Parser(str);
        NumberItem<? extends Object> internalParseIntegerOrDecimal = parser.internalParseIntegerOrDecimal();
        if (!(internalParseIntegerOrDecimal instanceof DecimalItem)) {
            throw parser.complaint("String parsed as Decimal '" + str + "' is an Integer");
        }
        parser.assertEmpty("Extra characters in string parsed as Decimal");
        return (DecimalItem) internalParseIntegerOrDecimal;
    }

    public OuterList parseList() {
        removeLeadingSP();
        List<ListElement<? extends Object>> internalParseOuterList = internalParseOuterList();
        removeLeadingSP();
        assertEmpty("Extra characters in string parsed as List");
        return OuterList.valueOf(internalParseOuterList);
    }

    public Dictionary parseDictionary() {
        removeLeadingSP();
        Dictionary internalParseDictionary = internalParseDictionary();
        removeLeadingSP();
        assertEmpty("Extra characters in string parsed as Dictionary");
        return internalParseDictionary;
    }

    public Item<? extends Object> parseItem() {
        removeLeadingSP();
        Item<? extends Object> internalParseItem = internalParseItem();
        removeLeadingSP();
        assertEmpty("Extra characters in string parsed as Item");
        return internalParseItem;
    }

    public static OuterList parseList(String str) {
        Parser parser = new Parser(str);
        List<ListElement<? extends Object>> internalParseOuterList = parser.internalParseOuterList();
        parser.assertEmpty("Extra characters in string parsed as List");
        return OuterList.valueOf(internalParseOuterList);
    }

    public static Parametrizable<? extends Object> parseItemOrInnerList(String str) {
        Parser parser = new Parser(str);
        ListElement<? extends Object> internalParseItemOrInnerList = parser.internalParseItemOrInnerList();
        parser.assertEmpty("Extra characters in string parsed as Item or Inner List");
        return internalParseItemOrInnerList;
    }

    public static InnerList parseInnerList(String str) {
        Parser parser = new Parser(str);
        InnerList internalParseInnerList = parser.internalParseInnerList();
        parser.assertEmpty("Extra characters in string parsed as Inner List");
        return internalParseInnerList;
    }

    public static Dictionary parseDictionary(String str) {
        Parser parser = new Parser(str);
        Dictionary internalParseDictionary = parser.internalParseDictionary();
        parser.assertEmpty("Extra characters in string parsed as Dictionary");
        return internalParseDictionary;
    }

    public static Item<? extends Object> parseItem(String str) {
        Parser parser = new Parser(str);
        Item<? extends Object> parseItem = parser.parseItem();
        parser.assertEmpty("Extra characters in string parsed as Item");
        return parseItem;
    }

    public static Item<? extends Object> parseBareItem(String str) {
        Parser parser = new Parser(str);
        Item<? extends Object> internalParseBareItem = parser.internalParseBareItem();
        parser.assertEmpty("Extra characters in string parsed as Bare Item");
        return internalParseBareItem;
    }

    public static Parameters parseParameters(String str) {
        Parser parser = new Parser(str);
        Parameters internalParseParameters = parser.internalParseParameters();
        parser.assertEmpty("Extra characters in string parsed as Parameters");
        return internalParseParameters;
    }

    public static String parseKey(String str) {
        Parser parser = new Parser(str);
        String internalParseKey = parser.internalParseKey();
        parser.assertEmpty("Extra characters in string parsed as Key");
        return internalParseKey;
    }

    public static NumberItem<? extends Object> parseIntegerOrDecimal(String str) {
        Parser parser = new Parser(str);
        NumberItem<? extends Object> internalParseIntegerOrDecimal = parser.internalParseIntegerOrDecimal();
        parser.assertEmpty("Extra characters in string parsed as Integer or Decimal");
        return internalParseIntegerOrDecimal;
    }

    public static StringItem parseString(String str) {
        Parser parser = new Parser(str);
        StringItem internalParseString = parser.internalParseString();
        parser.assertEmpty("Extra characters in string parsed as String");
        return internalParseString;
    }

    public static TokenItem parseToken(String str) {
        Parser parser = new Parser(str);
        TokenItem internalParseToken = parser.internalParseToken();
        parser.assertEmpty("Extra characters in string parsed as Token");
        return internalParseToken;
    }

    public static ByteSequenceItem parseByteSequence(String str) {
        Parser parser = new Parser(str);
        ByteSequenceItem internalParseByteSequence = parser.internalParseByteSequence();
        parser.assertEmpty("Extra characters in string parsed as Byte Sequence");
        return internalParseByteSequence;
    }

    public static BooleanItem parseBoolean(String str) {
        Parser parser = new Parser(str);
        BooleanItem internalParseBoolean = parser.internalParseBoolean();
        parser.assertEmpty("Extra characters at position %d in string parsed as Boolean: '%s'");
        return internalParseBoolean;
    }

    private void assertEmpty(String str) {
        if (hasRemaining()) {
            throw complaint(String.format(str, Integer.valueOf(position()), this.input));
        }
    }

    private void advance() {
        CharBuffer charBuffer = this.input;
        charBuffer.position(charBuffer.position() + 1);
    }

    private void backout() {
        CharBuffer charBuffer = this.input;
        charBuffer.position(charBuffer.position() - 1);
    }

    private boolean checkNextChar(char c) {
        return hasRemaining() && this.input.charAt(0) == c;
    }

    private boolean checkNextChar(String str) {
        return hasRemaining() && str.indexOf(this.input.charAt(0)) >= 0;
    }

    private char get() {
        return this.input.get();
    }

    private char getOrEOD() {
        return hasRemaining() ? get() : EOD;
    }

    private boolean hasRemaining() {
        return this.input.hasRemaining();
    }

    private int length() {
        return this.input.length();
    }

    private char peek() {
        return hasRemaining() ? this.input.charAt(0) : EOD;
    }

    private int position() {
        return this.input.position();
    }

    private void removeLeadingSP() {
        while (checkNextChar(' ')) {
            advance();
        }
    }

    private void removeLeadingOWS() {
        while (checkNextChar(" \t")) {
            advance();
        }
    }

    private ParseException complaint(String str) {
        return new ParseException(str, this.input);
    }

    private ParseException complaint(String str, Throwable th) {
        return new ParseException(str, this.input, th);
    }

    private static String format(char c) {
        String str;
        if (c == '\t') {
            str = "HTAB";
        } else {
            str = "'" + c + "'";
        }
        return String.format("%s (\\u%04x)", str, Integer.valueOf(c));
    }
}
