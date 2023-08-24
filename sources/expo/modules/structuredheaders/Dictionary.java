package expo.modules.structuredheaders;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes3.dex */
public class Dictionary implements Type<Map<String, ListElement<? extends Object>>> {
    private final Map<String, ListElement<? extends Object>> value;

    private Dictionary(Map<String, ListElement<? extends Object>> map) {
        this.value = Collections.unmodifiableMap(C4561Utils.checkKeys(map));
    }

    public static Dictionary valueOf(Map<String, ListElement<? extends Object>> map) {
        return new Dictionary(map);
    }

    @Override // androidx.core.util.Supplier
    public Map<String, ListElement<? extends Object>> get() {
        return this.value;
    }

    @Override // expo.modules.structuredheaders.Type
    public StringBuilder serializeTo(StringBuilder sb) {
        String str = "";
        for (Map.Entry<String, ListElement<? extends Object>> entry : this.value.entrySet()) {
            sb.append(str);
            ListElement<? extends Object> value = entry.getValue();
            sb.append(entry.getKey());
            if (Boolean.TRUE.equals(value.get())) {
                value.getParams().serializeTo(sb);
            } else {
                sb.append("=");
                value.serializeTo(sb);
            }
            str = ", ";
        }
        return sb;
    }

    @Override // expo.modules.structuredheaders.Type
    public String serialize() {
        return serializeTo(new StringBuilder()).toString();
    }
}
