package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes2.dex */
public class UUIDSerializer extends StdScalarSerializer<UUID> {
    static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public UUIDSerializer() {
        super(UUID.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, UUID r7) {
        if (r7 == null) {
            return true;
        }
        return r7.getLeastSignificantBits() == 0 && r7.getMostSignificantBits() == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(UUID r10, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jsonGenerator.canWriteBinaryNatively() && !(jsonGenerator instanceof TokenBuffer)) {
            jsonGenerator.writeBinary(_asBytes(r10));
            return;
        }
        char[] cArr = new char[36];
        long mostSignificantBits = r10.getMostSignificantBits();
        _appendInt((int) (mostSignificantBits >> 32), cArr, 0);
        cArr[8] = '-';
        int r2 = (int) mostSignificantBits;
        _appendShort(r2 >>> 16, cArr, 9);
        cArr[13] = '-';
        _appendShort(r2, cArr, 14);
        cArr[18] = '-';
        long leastSignificantBits = r10.getLeastSignificantBits();
        _appendShort((int) (leastSignificantBits >>> 48), cArr, 19);
        cArr[23] = '-';
        _appendShort((int) (leastSignificantBits >>> 32), cArr, 24);
        _appendInt((int) leastSignificantBits, cArr, 28);
        jsonGenerator.writeString(cArr, 0, 36);
    }

    private static void _appendInt(int r1, char[] cArr, int r3) {
        _appendShort(r1 >> 16, cArr, r3);
        _appendShort(r1, cArr, r3 + 4);
    }

    private static void _appendShort(int r2, char[] cArr, int r4) {
        char[] cArr2 = HEX_CHARS;
        cArr[r4] = cArr2[(r2 >> 12) & 15];
        int r42 = r4 + 1;
        cArr[r42] = cArr2[(r2 >> 8) & 15];
        int r43 = r42 + 1;
        cArr[r43] = cArr2[(r2 >> 4) & 15];
        cArr[r43 + 1] = cArr2[r2 & 15];
    }

    private static final byte[] _asBytes(UUID r7) {
        byte[] bArr = new byte[16];
        long mostSignificantBits = r7.getMostSignificantBits();
        long leastSignificantBits = r7.getLeastSignificantBits();
        _appendInt((int) (mostSignificantBits >> 32), bArr, 0);
        _appendInt((int) mostSignificantBits, bArr, 4);
        _appendInt((int) (leastSignificantBits >> 32), bArr, 8);
        _appendInt((int) leastSignificantBits, bArr, 12);
        return bArr;
    }

    private static final void _appendInt(int r1, byte[] bArr, int r3) {
        bArr[r3] = (byte) (r1 >> 24);
        int r32 = r3 + 1;
        bArr[r32] = (byte) (r1 >> 16);
        int r33 = r32 + 1;
        bArr[r33] = (byte) (r1 >> 8);
        bArr[r33 + 1] = (byte) r1;
    }
}
