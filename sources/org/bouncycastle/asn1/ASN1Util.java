package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class ASN1Util {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1TaggedObject checkTag(ASN1TaggedObject aSN1TaggedObject, int r3, int r4) {
        if (aSN1TaggedObject.hasTag(r3, r4)) {
            return aSN1TaggedObject;
        }
        String tagText = getTagText(r3, r4);
        String tagText2 = getTagText(aSN1TaggedObject);
        throw new IllegalStateException("Expected " + tagText + " tag but found " + tagText2);
    }

    static ASN1TaggedObjectParser checkTag(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r3, int r4) {
        if (aSN1TaggedObjectParser.hasTag(r3, r4)) {
            return aSN1TaggedObjectParser;
        }
        String tagText = getTagText(r3, r4);
        String tagText2 = getTagText(aSN1TaggedObjectParser);
        throw new IllegalStateException("Expected " + tagText + " tag but found " + tagText2);
    }

    public static ASN1Primitive getBaseUniversal(ASN1TaggedObject aSN1TaggedObject, int r1, int r2, boolean z, int r4) {
        return checkTag(aSN1TaggedObject, r1, r2).getBaseUniversal(z, r4);
    }

    public static ASN1Primitive getContextBaseUniversal(ASN1TaggedObject aSN1TaggedObject, int r2, boolean z, int r4) {
        return getBaseUniversal(aSN1TaggedObject, 128, r2, z, r4);
    }

    public static ASN1Object getExplicitBaseObject(ASN1TaggedObject aSN1TaggedObject, int r1, int r2) {
        return checkTag(aSN1TaggedObject, r1, r2).getExplicitBaseObject();
    }

    public static ASN1TaggedObject getExplicitBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r1, int r2) {
        return checkTag(aSN1TaggedObject, r1, r2).getExplicitBaseTagged();
    }

    public static ASN1Object getExplicitContextBaseObject(ASN1TaggedObject aSN1TaggedObject, int r2) {
        return getExplicitBaseObject(aSN1TaggedObject, 128, r2);
    }

    public static ASN1TaggedObject getExplicitContextBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r2) {
        return getExplicitBaseTagged(aSN1TaggedObject, 128, r2);
    }

    public static ASN1TaggedObject getImplicitBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r1, int r2, int r3, int r4) {
        return checkTag(aSN1TaggedObject, r1, r2).getImplicitBaseTagged(r3, r4);
    }

    public static ASN1TaggedObject getImplicitContextBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r2, int r3, int r4) {
        return getImplicitBaseTagged(aSN1TaggedObject, 128, r2, r3, r4);
    }

    public static String getTagText(int r2, int r3) {
        StringBuilder sb;
        String str;
        if (r2 == 64) {
            sb = new StringBuilder();
            str = "[APPLICATION ";
        } else if (r2 == 128) {
            sb = new StringBuilder();
            str = "[CONTEXT ";
        } else if (r2 != 192) {
            sb = new StringBuilder();
            str = "[UNIVERSAL ";
        } else {
            sb = new StringBuilder();
            str = "[PRIVATE ";
        }
        sb.append(str);
        sb.append(r3);
        sb.append("]");
        return sb.toString();
    }

    static String getTagText(ASN1Tag aSN1Tag) {
        return getTagText(aSN1Tag.getTagClass(), aSN1Tag.getTagNumber());
    }

    public static String getTagText(ASN1TaggedObject aSN1TaggedObject) {
        return getTagText(aSN1TaggedObject.getTagClass(), aSN1TaggedObject.getTagNo());
    }

    public static String getTagText(ASN1TaggedObjectParser aSN1TaggedObjectParser) {
        return getTagText(aSN1TaggedObjectParser.getTagClass(), aSN1TaggedObjectParser.getTagNo());
    }

    public static ASN1Encodable parseBaseUniversal(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2, boolean z, int r4) throws IOException {
        return checkTag(aSN1TaggedObjectParser, r1, r2).parseBaseUniversal(z, r4);
    }

    public static ASN1Encodable parseContextBaseUniversal(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2, boolean z, int r4) throws IOException {
        return parseBaseUniversal(aSN1TaggedObjectParser, 128, r2, z, r4);
    }

    public static ASN1Encodable parseExplicitBaseObject(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2) throws IOException {
        return checkTag(aSN1TaggedObjectParser, r1, r2).parseExplicitBaseObject();
    }

    public static ASN1TaggedObjectParser parseExplicitBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2) throws IOException {
        return checkTag(aSN1TaggedObjectParser, r1, r2).parseExplicitBaseTagged();
    }

    public static ASN1Encodable parseExplicitContextBaseObject(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2) throws IOException {
        return parseExplicitBaseObject(aSN1TaggedObjectParser, 128, r2);
    }

    public static ASN1TaggedObjectParser parseExplicitContextBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2) throws IOException {
        return parseExplicitBaseTagged(aSN1TaggedObjectParser, 128, r2);
    }

    public static ASN1TaggedObjectParser parseImplicitBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2, int r3, int r4) throws IOException {
        return checkTag(aSN1TaggedObjectParser, r1, r2).parseImplicitBaseTagged(r3, r4);
    }

    public static ASN1TaggedObjectParser parseImplicitContextBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2, int r3, int r4) throws IOException {
        return parseImplicitBaseTagged(aSN1TaggedObjectParser, 128, r2, r3, r4);
    }

    public static ASN1Primitive tryGetBaseUniversal(ASN1TaggedObject aSN1TaggedObject, int r1, int r2, boolean z, int r4) {
        if (aSN1TaggedObject.hasTag(r1, r2)) {
            return aSN1TaggedObject.getBaseUniversal(z, r4);
        }
        return null;
    }

    public static ASN1Primitive tryGetContextBaseUniversal(ASN1TaggedObject aSN1TaggedObject, int r2, boolean z, int r4) {
        return tryGetBaseUniversal(aSN1TaggedObject, 128, r2, z, r4);
    }

    public static ASN1Object tryGetExplicitBaseObject(ASN1TaggedObject aSN1TaggedObject, int r1, int r2) {
        if (aSN1TaggedObject.hasTag(r1, r2)) {
            return aSN1TaggedObject.getExplicitBaseObject();
        }
        return null;
    }

    public static ASN1TaggedObject tryGetExplicitBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r1, int r2) {
        if (aSN1TaggedObject.hasTag(r1, r2)) {
            return aSN1TaggedObject.getExplicitBaseTagged();
        }
        return null;
    }

    public static ASN1Object tryGetExplicitContextBaseObject(ASN1TaggedObject aSN1TaggedObject, int r2) {
        return tryGetExplicitBaseObject(aSN1TaggedObject, 128, r2);
    }

    public static ASN1TaggedObject tryGetExplicitContextBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r2) {
        return tryGetExplicitBaseTagged(aSN1TaggedObject, 128, r2);
    }

    public static ASN1TaggedObject tryGetImplicitBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r1, int r2, int r3, int r4) {
        if (aSN1TaggedObject.hasTag(r1, r2)) {
            return aSN1TaggedObject.getImplicitBaseTagged(r3, r4);
        }
        return null;
    }

    public static ASN1TaggedObject tryGetImplicitContextBaseTagged(ASN1TaggedObject aSN1TaggedObject, int r2, int r3, int r4) {
        return tryGetImplicitBaseTagged(aSN1TaggedObject, 128, r2, r3, r4);
    }

    public static ASN1Encodable tryParseBaseUniversal(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2, boolean z, int r4) throws IOException {
        if (aSN1TaggedObjectParser.hasTag(r1, r2)) {
            return aSN1TaggedObjectParser.parseBaseUniversal(z, r4);
        }
        return null;
    }

    public static ASN1Encodable tryParseContextBaseUniversal(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2, boolean z, int r4) throws IOException {
        return tryParseBaseUniversal(aSN1TaggedObjectParser, 128, r2, z, r4);
    }

    public static ASN1Encodable tryParseExplicitBaseObject(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2) throws IOException {
        if (aSN1TaggedObjectParser.hasTag(r1, r2)) {
            return aSN1TaggedObjectParser.parseExplicitBaseObject();
        }
        return null;
    }

    public static ASN1TaggedObjectParser tryParseExplicitBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2) throws IOException {
        if (aSN1TaggedObjectParser.hasTag(r1, r2)) {
            return aSN1TaggedObjectParser.parseExplicitBaseTagged();
        }
        return null;
    }

    public static ASN1Encodable tryParseExplicitContextBaseObject(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2) throws IOException {
        return tryParseExplicitBaseObject(aSN1TaggedObjectParser, 128, r2);
    }

    public static ASN1TaggedObjectParser tryParseExplicitContextBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2) throws IOException {
        return tryParseExplicitBaseTagged(aSN1TaggedObjectParser, 128, r2);
    }

    public static ASN1TaggedObjectParser tryParseImplicitBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r1, int r2, int r3, int r4) throws IOException {
        if (aSN1TaggedObjectParser.hasTag(r1, r2)) {
            return aSN1TaggedObjectParser.parseImplicitBaseTagged(r3, r4);
        }
        return null;
    }

    public static ASN1TaggedObjectParser tryParseImplicitContextBaseTagged(ASN1TaggedObjectParser aSN1TaggedObjectParser, int r2, int r3, int r4) throws IOException {
        return tryParseImplicitBaseTagged(aSN1TaggedObjectParser, 128, r2, r3, r4);
    }
}
