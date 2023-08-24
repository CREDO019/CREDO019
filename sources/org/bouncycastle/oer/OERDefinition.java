package org.bouncycastle.oer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes5.dex */
public class OERDefinition {
    private static final BigInteger[] uIntMax = {new BigInteger("256"), new BigInteger("65536"), new BigInteger("4294967296"), new BigInteger("18446744073709551616")};
    private static final BigInteger[][] sIntRange = {new BigInteger[]{new BigInteger("-128"), new BigInteger("127")}, new BigInteger[]{new BigInteger("-32768"), new BigInteger("32767")}, new BigInteger[]{new BigInteger("-2147483648"), new BigInteger("2147483647")}, new BigInteger[]{new BigInteger("-9223372036854775808"), new BigInteger("9223372036854775807")}};

    /* loaded from: classes5.dex */
    public enum BaseType {
        SEQ,
        SEQ_OF,
        CHOICE,
        ENUM,
        INT,
        OCTET_STRING,
        UTF8_STRING,
        BIT_STRING,
        NULL,
        EXTENSION,
        ENUM_ITEM,
        BOOLEAN,
        IS0646String,
        PrintableString,
        NumericString,
        BMPString,
        UniversalString,
        IA5String,
        VisibleString
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        protected final BaseType baseType;
        protected ASN1Encodable defaultValue;
        protected BigInteger enumValue;
        protected String label;
        protected BigInteger lowerBound;
        protected Builder placeholderValue;
        protected BigInteger upperBound;
        protected ArrayList<Builder> children = new ArrayList<>();
        protected boolean explicit = false;

        public Builder(BaseType baseType) {
            this.baseType = baseType;
        }

        private Builder wrap(boolean z, Object obj) {
            if (obj instanceof Builder) {
                return ((Builder) obj).explicit(z);
            }
            if (obj instanceof BaseType) {
                return new Builder((BaseType) obj).explicit(z);
            }
            throw new IllegalStateException("Unable to wrap item in builder");
        }

        public Element build() {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            if (this.baseType == BaseType.ENUM) {
                HashSet hashSet = new HashSet();
                int r4 = 0;
                for (int r1 = 0; r1 < this.children.size(); r1++) {
                    Builder builder = this.children.get(r1);
                    if (builder.enumValue == null) {
                        builder.enumValue = BigInteger.valueOf(r4);
                        r4++;
                    }
                    if (hashSet.contains(builder.enumValue)) {
                        throw new IllegalStateException("duplicate enum value at index " + r1);
                    }
                    hashSet.add(builder.enumValue);
                }
            }
            Iterator<Builder> it = this.children.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Builder next = it.next();
                if (!z2 && next.baseType == BaseType.EXTENSION) {
                    if (!next.children.isEmpty() || this.baseType == BaseType.CHOICE) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                arrayList.add(next.build());
            }
            BaseType baseType = this.baseType;
            ASN1Encodable aSN1Encodable = this.defaultValue;
            if (aSN1Encodable == null && this.explicit) {
                z = true;
            }
            return new Element(baseType, arrayList, z, this.label, this.lowerBound, this.upperBound, z2, this.enumValue, aSN1Encodable);
        }

        public Builder copy() {
            Builder builder = new Builder(this.baseType);
            Iterator<Builder> it = this.children.iterator();
            while (it.hasNext()) {
                builder.children.add(it.next().copy());
            }
            builder.explicit = this.explicit;
            builder.label = this.label;
            builder.upperBound = this.upperBound;
            builder.lowerBound = this.lowerBound;
            builder.defaultValue = this.defaultValue;
            builder.enumValue = this.enumValue;
            return builder;
        }

        public Builder defaultValue(ASN1Encodable aSN1Encodable) {
            Builder copy = copy();
            copy.defaultValue = aSN1Encodable;
            return copy;
        }

        public Builder enumValue(BigInteger bigInteger) {
            Builder copy = copy();
            this.enumValue = bigInteger;
            return copy;
        }

        public Builder explicit(boolean z) {
            Builder copy = copy();
            copy.explicit = z;
            return copy;
        }

        public Builder fixedSize(long j) {
            Builder copy = copy();
            copy.upperBound = BigInteger.valueOf(j);
            copy.lowerBound = BigInteger.valueOf(j);
            return copy;
        }

        public Builder items(Object... objArr) {
            Builder copy = copy();
            for (int r2 = 0; r2 != objArr.length; r2++) {
                Object obj = objArr[r2];
                if (obj instanceof OptionalList) {
                    for (Object obj2 : (List) obj) {
                        copy.children.add(wrap(false, obj2));
                    }
                } else if (obj.getClass().isArray()) {
                    items((Object[]) obj);
                } else {
                    copy.children.add(wrap(true, obj));
                }
            }
            return copy;
        }

        public Builder label(String str) {
            Builder copy = copy();
            if (str != null) {
                copy.label = str;
            }
            copy.explicit = this.explicit;
            return copy;
        }

        public Builder labelPrefix(String str) {
            Builder copy = copy();
            copy.label = str + " " + this.label;
            return copy;
        }

        public Builder range(long j, long j2, ASN1Encodable aSN1Encodable) {
            Builder copy = copy();
            copy.lowerBound = BigInteger.valueOf(j);
            copy.upperBound = BigInteger.valueOf(j2);
            copy.defaultValue = aSN1Encodable;
            return copy;
        }

        public Builder range(BigInteger bigInteger, BigInteger bigInteger2) {
            Builder copy = copy();
            copy.lowerBound = bigInteger;
            copy.upperBound = bigInteger2;
            return copy;
        }

        public Builder rangeToMAXFrom(long j) {
            Builder copy = copy();
            copy.lowerBound = BigInteger.valueOf(j);
            copy.upperBound = null;
            return copy;
        }

        public Builder rangeZeroTo(long j) {
            Builder copy = copy();
            copy.upperBound = BigInteger.valueOf(j);
            copy.lowerBound = BigInteger.ZERO;
            return copy;
        }

        public Builder unbounded() {
            Builder copy = copy();
            copy.lowerBound = null;
            copy.upperBound = null;
            return copy;
        }
    }

    /* loaded from: classes5.dex */
    public static class Element {
        public final BaseType baseType;
        public final List<Element> children;
        public final ASN1Encodable defaultValue;
        public final BigInteger enumValue;
        public final boolean explicit;
        public final boolean extensionsInDefinition;
        public final String label;
        public final BigInteger lowerBound;
        private List<Element> optionalChildrenInOrder;
        public final BigInteger upperBound;

        public Element(BaseType baseType, List<Element> list, boolean z, String str, BigInteger bigInteger, BigInteger bigInteger2, boolean z2, BigInteger bigInteger3, ASN1Encodable aSN1Encodable) {
            this.baseType = baseType;
            this.children = list;
            this.explicit = z;
            this.label = str;
            this.lowerBound = bigInteger;
            this.upperBound = bigInteger2;
            this.extensionsInDefinition = z2;
            this.enumValue = bigInteger3;
            this.defaultValue = aSN1Encodable;
        }

        public String appendLabel(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            String str2 = this.label;
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(this.explicit ? " (E)" : "");
            sb.append("] ");
            sb.append(str);
            return sb.toString();
        }

        public boolean canBeNegative() {
            return this.lowerBound != null && BigInteger.ZERO.compareTo(this.lowerBound) > 0;
        }

        public ASN1Encodable getDefaultValue() {
            return this.defaultValue;
        }

        public Element getFirstChid() {
            return this.children.get(0);
        }

        public boolean hasDefaultChildren() {
            for (Element element : this.children) {
                if (element.defaultValue != null) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasPopulatedExtension() {
            for (Element element : this.children) {
                if (element.baseType == BaseType.EXTENSION) {
                    return true;
                }
            }
            return false;
        }

        public int intBytesForRange() {
            if (this.lowerBound != null && this.upperBound != null) {
                int r2 = 1;
                if (BigInteger.ZERO.equals(this.lowerBound)) {
                    int r0 = 0;
                    while (r0 < OERDefinition.uIntMax.length) {
                        if (this.upperBound.compareTo(OERDefinition.uIntMax[r0]) < 0) {
                            return r2;
                        }
                        r0++;
                        r2 *= 2;
                    }
                } else {
                    int r02 = 0;
                    int r3 = 1;
                    while (r02 < OERDefinition.sIntRange.length) {
                        if (this.lowerBound.compareTo(OERDefinition.sIntRange[r02][0]) >= 0 && this.upperBound.compareTo(OERDefinition.sIntRange[r02][1]) < 0) {
                            return -r3;
                        }
                        r02++;
                        r3 *= 2;
                    }
                }
            }
            return 0;
        }

        public boolean isFixedLength() {
            BigInteger bigInteger = this.lowerBound;
            return bigInteger != null && bigInteger.equals(this.upperBound);
        }

        public boolean isLowerRangeZero() {
            return BigInteger.ZERO.equals(this.lowerBound);
        }

        public boolean isUnbounded() {
            return this.upperBound == null && this.lowerBound == null;
        }

        public boolean isUnsignedWithRange() {
            return isLowerRangeZero() && this.upperBound != null && BigInteger.ZERO.compareTo(this.upperBound) < 0;
        }

        public List<Element> optionalOrDefaultChildrenInOrder() {
            List<Element> list;
            synchronized (this) {
                if (this.optionalChildrenInOrder == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Element element : this.children) {
                        if (!element.explicit || element.getDefaultValue() != null) {
                            arrayList.add(element);
                        }
                    }
                    this.optionalChildrenInOrder = Collections.unmodifiableList(arrayList);
                }
                list = this.optionalChildrenInOrder;
            }
            return list;
        }

        public String rangeExpression() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            BigInteger bigInteger = this.lowerBound;
            sb.append(bigInteger != null ? bigInteger.toString() : "MIN");
            sb.append(" ... ");
            BigInteger bigInteger2 = this.upperBound;
            sb.append(bigInteger2 != null ? bigInteger2.toString() : "MAX");
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes5.dex */
    public static class MutableBuilder extends Builder {
        private boolean frozen;

        public MutableBuilder(BaseType baseType) {
            super(baseType);
            this.frozen = false;
        }

        public void addItemsAndFreeze(Builder... builderArr) {
            if (this.frozen) {
                throw new IllegalStateException("build cannot be modified and must be copied only");
            }
            for (int r0 = 0; r0 != builderArr.length; r0++) {
                this.children.add(builderArr[r0]);
            }
            this.frozen = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class OptionalList extends ArrayList<Object> {
        public OptionalList(List<Object> list) {
            addAll(list);
        }
    }

    public static Builder bitString(long j) {
        return new Builder(BaseType.BIT_STRING).fixedSize(j);
    }

    public static Builder choice(Object... objArr) {
        return new Builder(BaseType.CHOICE).items(objArr);
    }

    public static Builder enumItem(String str) {
        return new Builder(BaseType.ENUM_ITEM).label(str);
    }

    public static Builder enumItem(String str, BigInteger bigInteger) {
        return new Builder(BaseType.ENUM_ITEM).enumValue(bigInteger).label(str);
    }

    public static Builder enumeration(Object... objArr) {
        return new Builder(BaseType.ENUM).items(objArr);
    }

    public static Builder extension() {
        return new Builder(BaseType.EXTENSION).label("extension");
    }

    public static Builder integer() {
        return new Builder(BaseType.INT);
    }

    public static Builder integer(long j) {
        return new Builder(BaseType.INT).defaultValue(new ASN1Integer(j));
    }

    public static Builder integer(long j, long j2) {
        return new Builder(BaseType.INT).range(BigInteger.valueOf(j), BigInteger.valueOf(j2));
    }

    public static Builder integer(long j, long j2, ASN1Encodable aSN1Encodable) {
        return new Builder(BaseType.INT).range(j, j2, aSN1Encodable);
    }

    public static Builder integer(BigInteger bigInteger, BigInteger bigInteger2) {
        return new Builder(BaseType.INT).range(bigInteger, bigInteger2);
    }

    public static Builder nullValue() {
        return new Builder(BaseType.NULL);
    }

    public static Builder octets() {
        return new Builder(BaseType.OCTET_STRING).unbounded();
    }

    public static Builder octets(int r3) {
        return new Builder(BaseType.OCTET_STRING).fixedSize(r3);
    }

    public static Builder octets(int r3, int r4) {
        return new Builder(BaseType.OCTET_STRING).range(BigInteger.valueOf(r3), BigInteger.valueOf(r4));
    }

    public static Builder opaque() {
        return new Builder(BaseType.OCTET_STRING).unbounded();
    }

    public static List<Object> optional(Object... objArr) {
        return new OptionalList(Arrays.asList(objArr));
    }

    public static Builder placeholder() {
        return new Builder(null);
    }

    public static Builder seq() {
        return new Builder(BaseType.SEQ);
    }

    public static Builder seq(Object... objArr) {
        return new Builder(BaseType.SEQ).items(objArr);
    }

    public static Builder seqof(Object... objArr) {
        return new Builder(BaseType.SEQ_OF).items(objArr);
    }

    public static Builder utf8String() {
        return new Builder(BaseType.UTF8_STRING);
    }

    public static Builder utf8String(int r3) {
        return new Builder(BaseType.UTF8_STRING).rangeToMAXFrom(r3);
    }

    public static Builder utf8String(int r3, int r4) {
        return new Builder(BaseType.UTF8_STRING).range(BigInteger.valueOf(r3), BigInteger.valueOf(r4));
    }
}
