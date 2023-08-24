package androidx.room.util;

import java.nio.ByteBuffer;
import java.util.UUID;

/* loaded from: classes.dex */
public final class UUIDUtil {
    private UUIDUtil() {
    }

    public static UUID convertByteToUUID(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public static byte[] convertUUIDToByte(UUID r3) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[16]);
        wrap.putLong(r3.getMostSignificantBits());
        wrap.putLong(r3.getLeastSignificantBits());
        return wrap.array();
    }
}
