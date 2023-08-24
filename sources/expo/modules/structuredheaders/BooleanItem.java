package expo.modules.structuredheaders;

import java.util.Objects;

/* loaded from: classes3.dex */
public class BooleanItem implements Item<Boolean> {
    private final Parameters params;
    private final boolean value;
    private static final BooleanItem TRUE = new BooleanItem(true, Parameters.EMPTY);
    private static final BooleanItem FALSE = new BooleanItem(false, Parameters.EMPTY);

    private BooleanItem(boolean z, Parameters parameters) {
        this.value = z;
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static BooleanItem valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public BooleanItem withParams(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new BooleanItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(this.value ? "?1" : "?0");
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public Boolean get() {
        return Boolean.valueOf(this.value);
    }
}
