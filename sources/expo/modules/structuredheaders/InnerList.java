package expo.modules.structuredheaders;

import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class InnerList implements ListElement<List<Item<? extends Object>>>, Parametrizable<List<Item<? extends Object>>> {
    private final Parameters params;
    private final List<Item<? extends Object>> value;

    private InnerList(List<Item<? extends Object>> list, Parameters parameters) {
        Objects.requireNonNull(list, "value must not be null");
        this.value = list;
        Objects.requireNonNull(parameters, "params must not be null");
        this.params = parameters;
    }

    public static InnerList valueOf(List<Item<? extends Object>> list) {
        return new InnerList(list, Parameters.EMPTY);
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    /* renamed from: withParams */
    public Parametrizable<List<Item<? extends Object>>> withParams2(Parameters parameters) {
        Objects.requireNonNull(parameters, "params must not be null");
        return parameters.isEmpty() ? this : new InnerList(this.value, parameters);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        sb.append('(');
        String str = "";
        for (Item<? extends Object> item : this.value) {
            sb.append(str);
            item.serializeTo(sb);
            str = " ";
        }
        sb.append(')');
        this.params.serializeTo(sb);
        return sb;
    }

    @Override // expo.modules.structuredheaders.Parametrizable
    public Parameters getParams() {
        return this.params;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public List<Item<? extends Object>> get() {
        return this.value;
    }
}
