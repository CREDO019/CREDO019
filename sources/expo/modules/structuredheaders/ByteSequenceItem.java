package expo.modules.structuredheaders;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ByteSequenceItem implements Item<ByteBuffer> {
    private final Parameters params;
    private final byte[] value;

    private ByteSequenceItem(byte[] bArr, Parameters parameters) {
        Objects.requireNonNull(bArr, "value must not be null");
        this.value = bArr;
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static ByteSequenceItem valueOf(byte[] bArr) {
        return new ByteSequenceItem(bArr, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public ByteSequenceItem withParams(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new ByteSequenceItem(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append(':');
        sb.append(Base64.encodeToString(this.value, 0));
        sb.append(':');
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public ByteBuffer get() {
        return ByteBuffer.wrap(this.value);
    }
}
