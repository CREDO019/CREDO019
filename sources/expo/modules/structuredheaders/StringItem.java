package expo.modules.structuredheaders;

import java.util.Objects;
import kotlin.text.Typography;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes3.dex */
public class StringItem implements Item<String> {
    private final Parameters params;
    private final String value;

    private StringItem(String str, Parameters parameters) {
        Objects.requireNonNull(str, "value must not be null");
        this.value = checkParam(str);
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static StringItem valueOf(String str) {
        return new StringItem(str, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public StringItem withParams(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new StringItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(Typography.quote);
        for (int r1 = 0; r1 < this.value.length(); r1++) {
            char charAt = this.value.charAt(r1);
            if (charAt == '\\' || charAt == '\"') {
                sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
            }
            sb.append(charAt);
        }
        sb.append(Typography.quote);
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder(this.value.length() + 2)).toString();
    }

    @Override // androidx.core.util.Supplier
    public String get() {
        return this.value;
    }

    private static String checkParam(String str) {
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if (charAt < ' ' || charAt >= 127) {
                throw new IllegalArgumentException(String.format("Invalid character in String at position %d: '%c' (0x%04x)", Integer.valueOf(r1), Character.valueOf(charAt), Integer.valueOf(charAt)));
            }
        }
        return str;
    }
}
