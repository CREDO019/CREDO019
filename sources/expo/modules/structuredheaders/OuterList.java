package expo.modules.structuredheaders;

import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class OuterList implements Type<List<ListElement<? extends Object>>> {
    private final List<ListElement<? extends Object>> value;

    private OuterList(List<ListElement<? extends Object>> list) {
        Objects.requireNonNull(list, "value must not be null");
        this.value = list;
    }

    public static OuterList valueOf(List<ListElement<? extends Object>> list) {
        return new OuterList(list);
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        String str = "";
        for (ListElement<? extends Object> listElement : this.value) {
            sb.append(str);
            listElement.serializeTo(sb);
            str = ", ";
        }
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }

    @Override // androidx.core.util.Supplier
    public List<ListElement<? extends Object>> get() {
        return this.value;
    }
}
