package expo.modules.structuredheaders;

import java.util.Objects;

/* loaded from: classes3.dex */
public class IntegerItem implements NumberItem<Long> {
    private static final long MAX = 999999999999999L;
    private static final long MIN = -999999999999999L;
    private final Parameters params;
    private final long value;

    @Override // expo.modules.structuredheaders.NumberItem
    public int getDivisor() {
        return 1;
    }

    private IntegerItem(long j, Parameters parameters) {
        if (j < MIN || j > MAX) {
            throw new IllegalArgumentException("value must be in the range from -999999999999999 to 999999999999999");
        }
        this.value = j;
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static IntegerItem valueOf(long j) {
        return new IntegerItem(j, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public IntegerItem withParams(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new IntegerItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(Long.toString(this.value));
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public Long get() {
        return Long.valueOf(this.value);
    }

    @Override // expo.modules.structuredheaders.LongSupplier
    public long getAsLong() {
        return this.value;
    }
}
