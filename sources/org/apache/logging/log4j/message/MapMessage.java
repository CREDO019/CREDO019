package org.apache.logging.log4j.message;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.EnglishEnums;

/* loaded from: classes5.dex */
public class MapMessage implements MultiformatMessage {
    private static final long serialVersionUID = -5031471831131487120L;
    private final SortedMap<String, String> data;

    /* loaded from: classes5.dex */
    public enum MapFormat {
        XML,
        JSON,
        JAVA
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return "";
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return null;
    }

    protected void validate(String str, String str2) {
    }

    public MapMessage() {
        this.data = new TreeMap();
    }

    public MapMessage(Map<String, String> map) {
        this.data = map instanceof SortedMap ? (SortedMap) map : new TreeMap(map);
    }

    @Override // org.apache.logging.log4j.message.MultiformatMessage
    public String[] getFormats() {
        String[] strArr = new String[MapFormat.values().length];
        MapFormat[] values = MapFormat.values();
        int length = values.length;
        int r3 = 0;
        int r4 = 0;
        while (r3 < length) {
            strArr[r4] = values[r3].name();
            r3++;
            r4++;
        }
        return strArr;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return this.data.values().toArray();
    }

    public Map<String, String> getData() {
        return Collections.unmodifiableMap(this.data);
    }

    public void clear() {
        this.data.clear();
    }

    public void put(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No value provided for key " + str);
        }
        validate(str, str2);
        this.data.put(str, str2);
    }

    public void putAll(Map<String, String> map) {
        this.data.putAll(map);
    }

    public String get(String str) {
        return this.data.get(str);
    }

    public String remove(String str) {
        return this.data.remove(str);
    }

    public String asString() {
        return asString((MapFormat) null);
    }

    public String asString(String str) {
        try {
            return asString((MapFormat) EnglishEnums.valueOf(MapFormat.class, str));
        } catch (IllegalArgumentException unused) {
            return asString();
        }
    }

    private String asString(MapFormat mapFormat) {
        StringBuilder sb = new StringBuilder();
        if (mapFormat == null) {
            appendMap(sb);
        } else {
            int r3 = C50891.$SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat[mapFormat.ordinal()];
            if (r3 == 1) {
                asXml(sb);
            } else if (r3 == 2) {
                asJson(sb);
            } else if (r3 == 3) {
                asJava(sb);
            } else {
                appendMap(sb);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.logging.log4j.message.MapMessage$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C50891 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat;

        static {
            int[] r0 = new int[MapFormat.values().length];
            $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat = r0;
            try {
                r0[MapFormat.XML.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat[MapFormat.JSON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$logging$log4j$message$MapMessage$MapFormat[MapFormat.JAVA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void asXml(StringBuilder sb) {
        sb.append("<Map>\n");
        for (Map.Entry<String, String> entry : this.data.entrySet()) {
            sb.append("  <Entry key=\"");
            sb.append(entry.getKey());
            sb.append("\">");
            sb.append(entry.getValue());
            sb.append("</Entry>\n");
        }
        sb.append("</Map>");
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        return asString();
    }

    @Override // org.apache.logging.log4j.message.MultiformatMessage
    public String getFormattedMessage(String[] strArr) {
        MapFormat[] values;
        if (strArr == null || strArr.length == 0) {
            return asString();
        }
        for (String str : strArr) {
            for (MapFormat mapFormat : MapFormat.values()) {
                if (mapFormat.name().equalsIgnoreCase(str)) {
                    return asString(mapFormat);
                }
            }
        }
        return asString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendMap(StringBuilder sb) {
        boolean z = true;
        for (Map.Entry<String, String> entry : this.data.entrySet()) {
            if (!z) {
                sb.append(' ');
            }
            z = false;
            sb.append(entry.getKey());
            sb.append("=\"");
            sb.append(entry.getValue());
            sb.append(Typography.quote);
        }
    }

    protected void asJson(StringBuilder sb) {
        sb.append('{');
        boolean z = true;
        for (Map.Entry<String, String> entry : this.data.entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(Typography.quote);
            sb.append(entry.getKey());
            sb.append("\":");
            sb.append(Typography.quote);
            sb.append(entry.getValue());
            sb.append(Typography.quote);
        }
        sb.append('}');
    }

    protected void asJava(StringBuilder sb) {
        sb.append('{');
        boolean z = true;
        for (Map.Entry<String, String> entry : this.data.entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(entry.getKey());
            sb.append("=\"");
            sb.append(entry.getValue());
            sb.append(Typography.quote);
        }
        sb.append('}');
    }

    public MapMessage newInstance(Map<String, String> map) {
        return new MapMessage(map);
    }

    public String toString() {
        return asString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.data.equals(((MapMessage) obj).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }
}
