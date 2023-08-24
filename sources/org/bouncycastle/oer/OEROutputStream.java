package org.bouncycastle.oer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public class OEROutputStream {
    private static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
    protected PrintWriter debugOutput = null;
    private final OutputStream out;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.bouncycastle.oer.OEROutputStream$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C53851 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType;

        static {
            int[] r0 = new int[OERDefinition.BaseType.values().length];
            $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType = r0;
            try {
                r0[OERDefinition.BaseType.SEQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.SEQ_OF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.CHOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.OCTET_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.UTF8_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.BIT_STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.EXTENSION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.ENUM_ITEM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.BOOLEAN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OEROutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public static int byteLength(long j) {
        int r1 = 8;
        while (r1 > 0 && ((-72057594037927936L) & j) == 0) {
            j <<= 8;
            r1--;
        }
        return r1;
    }

    public static OEROutputStream create(OutputStream outputStream) {
        return new OEROutputStream(outputStream);
    }

    private void encodeLength(long j) throws IOException {
        if (j <= 127) {
            this.out.write((int) j);
            return;
        }
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(BigInteger.valueOf(j));
        this.out.write(asUnsignedByteArray.length | 128);
        this.out.write(asUnsignedByteArray);
    }

    private void encodeQuantity(long j) throws IOException {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(BigInteger.valueOf(j));
        this.out.write(asUnsignedByteArray.length);
        this.out.write(asUnsignedByteArray);
    }

    protected void debugPrint(String str) {
        if (this.debugOutput == null) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int r2 = -1;
        for (int r3 = 0; r3 != stackTrace.length; r3++) {
            StackTraceElement stackTraceElement = stackTrace[r3];
            if (stackTraceElement.getMethodName().equals("debugPrint")) {
                r2 = 0;
            } else if (stackTraceElement.getClassName().contains("OERInput")) {
                r2++;
            }
        }
        while (true) {
            PrintWriter printWriter = this.debugOutput;
            if (r2 <= 0) {
                printWriter.append((CharSequence) str).append((CharSequence) "\n");
                this.debugOutput.flush();
                return;
            }
            printWriter.append((CharSequence) "    ");
            r2--;
        }
    }

    public void write(ASN1Encodable aSN1Encodable, OERDefinition.Element element) throws IOException {
        int r0;
        int r6;
        Enumeration objects;
        int size;
        int tagNo;
        ASN1Primitive aSN1Primitive;
        String str;
        byte[] longToBigEndian;
        String appendLabel;
        if (aSN1Encodable == OEROptional.ABSENT) {
            return;
        }
        if (aSN1Encodable instanceof OEROptional) {
            write(((OEROptional) aSN1Encodable).get(), element);
            return;
        }
        ASN1Primitive aSN1Primitive2 = aSN1Encodable.toASN1Primitive();
        int r2 = 6;
        switch (C53851.$SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[element.baseType.ordinal()]) {
            case 1:
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1Primitive2);
                if (element.extensionsInDefinition) {
                    r0 = element.hasPopulatedExtension() ? bits[7] | 0 : 0;
                } else {
                    r0 = 0;
                    r2 = 7;
                }
                for (int r4 = 0; r4 < element.children.size(); r4++) {
                    OERDefinition.Element element2 = element.children.get(r4);
                    if (r2 < 0) {
                        this.out.write(r0);
                        r0 = 0;
                        r2 = 7;
                    }
                    ASN1Encodable objectAt = aSN1Sequence.getObjectAt(r4);
                    if (element2.explicit && (objectAt instanceof OEROptional)) {
                        throw new IllegalStateException("absent sequence element that is required by oer definition");
                    }
                    if (!element2.explicit) {
                        ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(r4);
                        if (element2.getDefaultValue() == null) {
                            if (objectAt != OEROptional.ABSENT) {
                                r6 = bits[r2];
                                r0 |= r6;
                            }
                            r2--;
                        } else if (objectAt2 instanceof OEROptional) {
                            OEROptional oEROptional = (OEROptional) objectAt2;
                            if (oEROptional.isDefined() && !oEROptional.get().equals(element2.defaultValue)) {
                                r6 = bits[r2];
                                r0 |= r6;
                            }
                            r2--;
                        } else {
                            if (!element2.getDefaultValue().equals(objectAt2)) {
                                r6 = bits[r2];
                                r0 |= r6;
                            }
                            r2--;
                        }
                    }
                }
                if (r2 != 7) {
                    this.out.write(r0);
                }
                for (int r5 = 0; r5 < element.children.size(); r5++) {
                    ASN1Encodable objectAt3 = aSN1Sequence.getObjectAt(r5);
                    OERDefinition.Element element3 = element.children.get(r5);
                    if (element3.getDefaultValue() == null || !element3.getDefaultValue().equals(objectAt3)) {
                        write(objectAt3, element3);
                    }
                }
                this.out.flush();
                debugPrint(element.appendLabel(""));
                return;
            case 2:
                if (aSN1Primitive2 instanceof ASN1Set) {
                    ASN1Set aSN1Set = (ASN1Set) aSN1Primitive2;
                    objects = aSN1Set.getObjects();
                    size = aSN1Set.size();
                } else if (!(aSN1Primitive2 instanceof ASN1Sequence)) {
                    throw new IllegalStateException("encodable at for SEQ_OF is not a container");
                } else {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Primitive2;
                    objects = aSN1Sequence2.getObjects();
                    size = aSN1Sequence2.size();
                }
                encodeQuantity(size);
                while (objects.hasMoreElements()) {
                    write((ASN1Encodable) objects.nextElement(), element.getFirstChid());
                }
                this.out.flush();
                debugPrint(element.appendLabel(""));
                return;
            case 3:
                ASN1Primitive aSN1Primitive3 = aSN1Primitive2.toASN1Primitive();
                BitBuilder bitBuilder = new BitBuilder();
                if (aSN1Primitive3 instanceof ASN1ApplicationSpecific) {
                    ASN1ApplicationSpecific aSN1ApplicationSpecific = (ASN1ApplicationSpecific) aSN1Primitive3;
                    tagNo = aSN1ApplicationSpecific.getApplicationTag();
                    bitBuilder.writeBit(0).writeBit(1);
                    aSN1Primitive = aSN1ApplicationSpecific.getEnclosedObject();
                } else if (!(aSN1Primitive3 instanceof ASN1TaggedObject)) {
                    throw new IllegalStateException("only support tagged objects");
                } else {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive3;
                    int tagClass = aSN1TaggedObject.getTagClass();
                    bitBuilder.writeBit(tagClass & 128).writeBit(tagClass & 64);
                    tagNo = aSN1TaggedObject.getTagNo();
                    aSN1Primitive = aSN1TaggedObject.getBaseObject().toASN1Primitive();
                }
                if (tagNo <= 63) {
                    bitBuilder.writeBits(tagNo, 6);
                } else {
                    bitBuilder.writeBits(255L, 6);
                    bitBuilder.write7BitBytes(tagNo);
                }
                if (this.debugOutput != null) {
                    if (aSN1Primitive instanceof ASN1ApplicationSpecific) {
                        str = "AS";
                    } else {
                        str = aSN1Primitive instanceof ASN1TaggedObject ? "CS" : "CS";
                    }
                    debugPrint(element.appendLabel(str));
                }
                bitBuilder.writeAndClear(this.out);
                write(aSN1Primitive, element.children.get(tagNo));
                this.out.flush();
                return;
            case 4:
                BigInteger value = aSN1Primitive2 instanceof ASN1Integer ? ASN1Integer.getInstance(aSN1Primitive2).getValue() : ASN1Enumerated.getInstance(aSN1Primitive2).getValue();
                for (OERDefinition.Element element4 : element.children) {
                    if (element4.enumValue.equals(value)) {
                        if (value.compareTo(BigInteger.valueOf(127L)) > 0) {
                            byte[] byteArray = value.toByteArray();
                            this.out.write((byteArray.length & 255) | 128);
                            this.out.write(byteArray);
                        } else {
                            this.out.write(value.intValue() & 127);
                        }
                        this.out.flush();
                        debugPrint(element.appendLabel(element.rangeExpression()));
                        return;
                    }
                }
                throw new IllegalArgumentException("enum value " + value + " " + Hex.toHexString(value.toByteArray()) + " no in defined child list");
            case 5:
                ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Primitive2);
                int intBytesForRange = element.intBytesForRange();
                if (intBytesForRange > 0) {
                    byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(intBytesForRange, aSN1Integer.getValue());
                    if (intBytesForRange != 1 && intBytesForRange != 2 && intBytesForRange != 4 && intBytesForRange != 8) {
                        throw new IllegalStateException("unknown uint length " + intBytesForRange);
                    }
                    this.out.write(asUnsignedByteArray);
                } else if (intBytesForRange < 0) {
                    BigInteger value2 = aSN1Integer.getValue();
                    if (intBytesForRange == -8) {
                        longToBigEndian = Pack.longToBigEndian(BigIntegers.longValueExact(value2));
                    } else if (intBytesForRange == -4) {
                        longToBigEndian = Pack.intToBigEndian(BigIntegers.intValueExact(value2));
                    } else if (intBytesForRange == -2) {
                        longToBigEndian = Pack.shortToBigEndian(BigIntegers.shortValueExact(value2));
                    } else if (intBytesForRange != -1) {
                        throw new IllegalStateException("unknown twos compliment length");
                    } else {
                        longToBigEndian = new byte[]{BigIntegers.byteValueExact(value2)};
                    }
                    this.out.write(longToBigEndian);
                } else {
                    boolean isLowerRangeZero = element.isLowerRangeZero();
                    BigInteger value3 = aSN1Integer.getValue();
                    byte[] asUnsignedByteArray2 = isLowerRangeZero ? BigIntegers.asUnsignedByteArray(value3) : value3.toByteArray();
                    encodeLength(asUnsignedByteArray2.length);
                    this.out.write(asUnsignedByteArray2);
                }
                appendLabel = element.appendLabel(element.rangeExpression());
                debugPrint(appendLabel);
                this.out.flush();
                return;
            case 6:
                byte[] octets = ASN1OctetString.getInstance(aSN1Primitive2).getOctets();
                if (!element.isFixedLength()) {
                    encodeLength(octets.length);
                }
                this.out.write(octets);
                appendLabel = element.appendLabel(element.rangeExpression());
                debugPrint(appendLabel);
                this.out.flush();
                return;
            case 7:
                byte[] uTF8ByteArray = Strings.toUTF8ByteArray(ASN1UTF8String.getInstance(aSN1Primitive2).getString());
                encodeLength(uTF8ByteArray.length);
                this.out.write(uTF8ByteArray);
                appendLabel = element.appendLabel("");
                debugPrint(appendLabel);
                this.out.flush();
                return;
            case 8:
                DERBitString dERBitString = DERBitString.getInstance((Object) aSN1Primitive2);
                byte[] bytes = dERBitString.getBytes();
                if (!element.isFixedLength()) {
                    int padBits = dERBitString.getPadBits();
                    encodeLength(bytes.length + 1);
                    this.out.write(padBits);
                }
                this.out.write(bytes);
                appendLabel = element.appendLabel(element.rangeExpression());
                debugPrint(appendLabel);
                this.out.flush();
                return;
            case 9:
            case 11:
            default:
                return;
            case 10:
                byte[] octets2 = ASN1OctetString.getInstance(aSN1Primitive2).getOctets();
                if (!element.isFixedLength()) {
                    encodeLength(octets2.length);
                }
                this.out.write(octets2);
                appendLabel = element.appendLabel(element.rangeExpression());
                debugPrint(appendLabel);
                this.out.flush();
                return;
            case 12:
                debugPrint(element.label);
                if (ASN1Boolean.getInstance(aSN1Primitive2).isTrue()) {
                    this.out.write(255);
                } else {
                    this.out.write(0);
                }
                this.out.flush();
                return;
        }
    }
}
