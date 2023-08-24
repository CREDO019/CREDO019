package org.apache.logging.log4j.message;

import com.amplitude.api.Constants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class StructuredDataId implements Serializable {

    /* renamed from: AT */
    private static final String f1583AT = "@";
    private static final int MAX_LENGTH = 32;
    public static final int RESERVED = -1;
    private static final long serialVersionUID = 9031746276396249990L;
    private final int enterpriseNumber;
    private final String name;
    private final String[] optional;
    private final String[] required;
    public static final StructuredDataId TIME_QUALITY = new StructuredDataId("timeQuality", null, new String[]{"tzKnown", "isSynced", "syncAccuracy"});
    public static final StructuredDataId ORIGIN = new StructuredDataId("origin", null, new String[]{"ip", "enterpriseId", "software", "swVersion"});
    public static final StructuredDataId META = new StructuredDataId("meta", null, new String[]{"sequenceId", "sysUpTime", Constants.AMP_TRACKING_OPTION_LANGUAGE});

    /* JADX INFO: Access modifiers changed from: protected */
    public StructuredDataId(String str, String[] strArr, String[] strArr2) {
        int r3;
        if (str == null) {
            r3 = -1;
        } else if (str.length() > 32) {
            throw new IllegalArgumentException(String.format("Length of id %s exceeds maximum of %d characters", str, 32));
        } else {
            r3 = str.indexOf(f1583AT);
        }
        if (r3 > 0) {
            this.name = str.substring(0, r3);
            this.enterpriseNumber = Integer.parseInt(str.substring(r3 + 1));
        } else {
            this.name = str;
            this.enterpriseNumber = -1;
        }
        this.required = strArr;
        this.optional = strArr2;
    }

    public StructuredDataId(String str, int r4, String[] strArr, String[] strArr2) {
        if (str == null) {
            throw new IllegalArgumentException("No structured id name was supplied");
        }
        if (str.contains(f1583AT)) {
            throw new IllegalArgumentException("Structured id name cannot contain an '@'");
        }
        if (r4 <= 0) {
            throw new IllegalArgumentException("No enterprise number was supplied");
        }
        this.name = str;
        this.enterpriseNumber = r4;
        if (r4 >= 0) {
            str = str + f1583AT + r4;
        }
        if (str.length() > 32) {
            throw new IllegalArgumentException("Length of id exceeds maximum of 32 characters: " + str);
        }
        this.required = strArr;
        this.optional = strArr2;
    }

    public StructuredDataId makeId(StructuredDataId structuredDataId) {
        return structuredDataId == null ? this : makeId(structuredDataId.getName(), structuredDataId.getEnterpriseNumber());
    }

    public StructuredDataId makeId(String str, int r6) {
        String[] strArr;
        if (r6 <= 0) {
            return this;
        }
        String str2 = this.name;
        String[] strArr2 = null;
        if (str2 != null) {
            strArr2 = this.required;
            strArr = this.optional;
            str = str2;
        } else {
            strArr = null;
        }
        return new StructuredDataId(str, r6, strArr2, strArr);
    }

    public String[] getRequired() {
        return this.required;
    }

    public String[] getOptional() {
        return this.optional;
    }

    public String getName() {
        return this.name;
    }

    public int getEnterpriseNumber() {
        return this.enterpriseNumber;
    }

    public boolean isReserved() {
        return this.enterpriseNumber <= 0;
    }

    public String toString() {
        if (isReserved()) {
            return this.name;
        }
        return this.name + f1583AT + this.enterpriseNumber;
    }
}
