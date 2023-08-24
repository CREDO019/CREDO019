package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes2.dex */
public class UUIDDeserializer extends FromStringDeserializer<UUID> {
    static final int[] HEX_DIGITS;
    private static final long serialVersionUID = 1;

    static {
        int[] r0 = new int[127];
        HEX_DIGITS = r0;
        Arrays.fill(r0, -1);
        for (int r1 = 0; r1 < 10; r1++) {
            HEX_DIGITS[r1 + 48] = r1;
        }
        for (int r02 = 0; r02 < 6; r02++) {
            int[] r12 = HEX_DIGITS;
            int r3 = r02 + 10;
            r12[r02 + 97] = r3;
            r12[r02 + 65] = r3;
        }
    }

    public UUIDDeserializer() {
        super(UUID.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public UUID _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        if (str.length() != 36) {
            if (str.length() == 24) {
                return _fromBytes(Base64Variants.getDefaultVariant().decode(str), deserializationContext);
            }
            return _badFormat(str, deserializationContext);
        }
        if (str.charAt(8) != '-' || str.charAt(13) != '-' || str.charAt(18) != '-' || str.charAt(23) != '-') {
            _badFormat(str, deserializationContext);
        }
        int shortFromChars = shortFromChars(str, 24, deserializationContext);
        return new UUID((intFromChars(str, 0, deserializationContext) << 32) + ((shortFromChars(str, 9, deserializationContext) << 16) | shortFromChars(str, 14, deserializationContext)), ((intFromChars(str, 28, deserializationContext) << 32) >>> 32) | ((shortFromChars | (shortFromChars(str, 19, deserializationContext) << 16)) << 32));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException {
        if (obj instanceof byte[]) {
            return _fromBytes((byte[]) obj, deserializationContext);
        }
        super._deserializeEmbedded(obj, deserializationContext);
        return null;
    }

    private UUID _badFormat(String str, DeserializationContext deserializationContext) throws IOException {
        return (UUID) deserializationContext.handleWeirdStringValue(handledType(), str, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    int intFromChars(String str, int r4, DeserializationContext deserializationContext) throws JsonMappingException {
        return (byteFromChars(str, r4, deserializationContext) << 24) + (byteFromChars(str, r4 + 2, deserializationContext) << 16) + (byteFromChars(str, r4 + 4, deserializationContext) << 8) + byteFromChars(str, r4 + 6, deserializationContext);
    }

    int shortFromChars(String str, int r3, DeserializationContext deserializationContext) throws JsonMappingException {
        return (byteFromChars(str, r3, deserializationContext) << 8) + byteFromChars(str, r3 + 2, deserializationContext);
    }

    int byteFromChars(String str, int r8, DeserializationContext deserializationContext) throws JsonMappingException {
        char charAt = str.charAt(r8);
        int r1 = r8 + 1;
        char charAt2 = str.charAt(r1);
        if (charAt <= 127 && charAt2 <= 127) {
            int[] r4 = HEX_DIGITS;
            int r42 = r4[charAt2] | (r4[charAt] << 4);
            if (r42 >= 0) {
                return r42;
            }
        }
        if (charAt > 127 || HEX_DIGITS[charAt] < 0) {
            return _badChar(str, r8, deserializationContext, charAt);
        }
        return _badChar(str, r1, deserializationContext, charAt2);
    }

    int _badChar(String str, int r5, DeserializationContext deserializationContext, char c) throws JsonMappingException {
        throw deserializationContext.weirdStringException(str, handledType(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", Character.valueOf(c), Integer.toHexString(c)));
    }

    private UUID _fromBytes(byte[] bArr, DeserializationContext deserializationContext) throws JsonMappingException {
        if (bArr.length != 16) {
            JsonParser parser = deserializationContext.getParser();
            throw InvalidFormatException.from(parser, "Can only construct UUIDs from byte[16]; got " + bArr.length + " bytes", bArr, handledType());
        }
        return new UUID(_long(bArr, 0), _long(bArr, 8));
    }

    private static long _long(byte[] bArr, int r4) {
        return ((_int(bArr, r4 + 4) << 32) >>> 32) | (_int(bArr, r4) << 32);
    }

    private static int _int(byte[] bArr, int r3) {
        return (bArr[r3 + 3] & 255) | (bArr[r3] << Ascii.CAN) | ((bArr[r3 + 1] & 255) << 16) | ((bArr[r3 + 2] & 255) << 8);
    }
}
