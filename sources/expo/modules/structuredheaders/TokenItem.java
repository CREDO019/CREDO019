package expo.modules.structuredheaders;

import java.util.Objects;

/* loaded from: classes3.dex */
public class TokenItem implements Item<String> {
    private final Parameters params;
    private final String value;

    private TokenItem(String str, Parameters parameters) {
        Objects.requireNonNull(str, "value must not be null");
        this.value = checkParam(str);
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static TokenItem valueOf(String str) {
        return new TokenItem(str, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public TokenItem withParams(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new TokenItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(this.value);
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public String get() {
        return this.value;
    }

    private static String checkParam(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Token can not be empty");
        }
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if ((r1 == 0 && charAt != '*' && !C4561Utils.isAlpha(charAt)) || charAt <= ' ' || charAt >= 127 || "\"(),;<=>?@[\\]{}".indexOf(charAt) >= 0) {
                throw new IllegalArgumentException(String.format("Invalid character in Token at position %d: '%c' (0x%04x)", Integer.valueOf(r1), Character.valueOf(charAt), Integer.valueOf(charAt)));
            }
        }
        return str;
    }
}
