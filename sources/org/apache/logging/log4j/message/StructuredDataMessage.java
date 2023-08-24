package org.apache.logging.log4j.message;

import java.util.Map;
import org.apache.logging.log4j.util.EnglishEnums;

/* loaded from: classes5.dex */
public class StructuredDataMessage extends MapMessage {
    private static final int HASHVAL = 31;
    private static final int MAX_LENGTH = 32;
    private static final long serialVersionUID = 1703221292892071920L;

    /* renamed from: id */
    private StructuredDataId f1584id;
    private String message;
    private String type;

    /* loaded from: classes5.dex */
    public enum Format {
        XML,
        FULL
    }

    public StructuredDataMessage(String str, String str2, String str3) {
        this.f1584id = new StructuredDataId(str, null, null);
        this.message = str2;
        this.type = str3;
    }

    public StructuredDataMessage(String str, String str2, String str3, Map<String, String> map) {
        super(map);
        this.f1584id = new StructuredDataId(str, null, null);
        this.message = str2;
        this.type = str3;
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2) {
        this.f1584id = structuredDataId;
        this.message = str;
        this.type = str2;
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, Map<String, String> map) {
        super(map);
        this.f1584id = structuredDataId;
        this.message = str;
        this.type = str2;
    }

    private StructuredDataMessage(StructuredDataMessage structuredDataMessage, Map<String, String> map) {
        super(map);
        this.f1584id = structuredDataMessage.f1584id;
        this.message = structuredDataMessage.message;
        this.type = structuredDataMessage.type;
    }

    protected StructuredDataMessage() {
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.MultiformatMessage
    public String[] getFormats() {
        String[] strArr = new String[Format.values().length];
        Format[] values = Format.values();
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

    public StructuredDataId getId() {
        return this.f1584id;
    }

    protected void setId(String str) {
        this.f1584id = new StructuredDataId(str, null, null);
    }

    protected void setId(StructuredDataId structuredDataId) {
        this.f1584id = structuredDataId;
    }

    public String getType() {
        return this.type;
    }

    protected void setType(String str) {
        if (str.length() > 32) {
            throw new IllegalArgumentException("structured data type exceeds maximum length of 32 characters: " + str);
        }
        this.type = str;
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.message;
    }

    protected void setMessageFormat(String str) {
        this.message = str;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    protected void validate(String str, String str2) {
        validateKey(str);
    }

    private void validateKey(String str) {
        char[] charArray;
        if (str.length() > 32) {
            throw new IllegalArgumentException("Structured data keys are limited to 32 characters. key: " + str);
        }
        for (char c : str.toCharArray()) {
            if (c < '!' || c > '~' || c == '=' || c == ']' || c == '\"') {
                throw new IllegalArgumentException("Structured data keys must contain printable US ASCII charactersand may not contain a space, =, ], or \"");
            }
        }
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String asString() {
        return asString(Format.FULL, null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String asString(String str) {
        try {
            return asString((Format) EnglishEnums.valueOf(Format.class, str), null);
        } catch (IllegalArgumentException unused) {
            return asString();
        }
    }

    public final String asString(Format format, StructuredDataId structuredDataId) {
        String format2;
        StringBuilder sb = new StringBuilder();
        boolean equals = Format.FULL.equals(format);
        if (equals) {
            if (getType() == null) {
                return sb.toString();
            }
            sb.append(getType());
            sb.append(' ');
        }
        StructuredDataId id = getId();
        if (id != null) {
            structuredDataId = id.makeId(structuredDataId);
        }
        if (structuredDataId == null || structuredDataId.getName() == null) {
            return sb.toString();
        }
        sb.append('[');
        sb.append(structuredDataId);
        sb.append(' ');
        appendMap(sb);
        sb.append(']');
        if (equals && (format2 = getFormat()) != null) {
            sb.append(' ');
            sb.append(format2);
        }
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        return asString(Format.FULL, null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage, org.apache.logging.log4j.message.MultiformatMessage
    public String getFormattedMessage(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (Format.XML.name().equalsIgnoreCase(str)) {
                    return asXml();
                }
                if (Format.FULL.name().equalsIgnoreCase(str)) {
                    return asString(Format.FULL, null);
                }
            }
            return asString(null, null);
        }
        return asString(Format.FULL, null);
    }

    private String asXml() {
        StringBuilder sb = new StringBuilder();
        StructuredDataId id = getId();
        if (id == null || id.getName() == null || this.type == null) {
            return sb.toString();
        }
        sb.append("<StructuredData>\n");
        sb.append("<type>");
        sb.append(this.type);
        sb.append("</type>\n");
        sb.append("<id>");
        sb.append(id);
        sb.append("</id>\n");
        super.asXml(sb);
        sb.append("</StructuredData>\n");
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public String toString() {
        return asString(null, null);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public MapMessage newInstance(Map<String, String> map) {
        return new StructuredDataMessage(this, map);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StructuredDataMessage structuredDataMessage = (StructuredDataMessage) obj;
        if (super.equals(obj)) {
            String str = this.type;
            if (str == null ? structuredDataMessage.type == null : str.equals(structuredDataMessage.type)) {
                StructuredDataId structuredDataId = this.f1584id;
                if (structuredDataId == null ? structuredDataMessage.f1584id == null : structuredDataId.equals(structuredDataMessage.f1584id)) {
                    String str2 = this.message;
                    return str2 == null ? structuredDataMessage.message == null : str2.equals(structuredDataMessage.message);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.type;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        StructuredDataId structuredDataId = this.f1584id;
        int hashCode3 = (hashCode2 + (structuredDataId != null ? structuredDataId.hashCode() : 0)) * 31;
        String str2 = this.message;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }
}
