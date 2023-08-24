package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class PsshAtomUtil {
    private static final String TAG = "PsshAtomUtil";

    private PsshAtomUtil() {
    }

    public static byte[] buildPsshAtom(UUID r1, byte[] bArr) {
        return buildPsshAtom(r1, null, bArr);
    }

    public static byte[] buildPsshAtom(UUID r5, UUID[] r6, byte[] bArr) {
        int length = (bArr != null ? bArr.length : 0) + 32;
        if (r6 != null) {
            length += (r6.length * 16) + 4;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(Atom.TYPE_pssh);
        allocate.putInt(r6 != null ? 16777216 : 0);
        allocate.putLong(r5.getMostSignificantBits());
        allocate.putLong(r5.getLeastSignificantBits());
        if (r6 != null) {
            allocate.putInt(r6.length);
            for (UUID r1 : r6) {
                allocate.putLong(r1.getMostSignificantBits());
                allocate.putLong(r1.getLeastSignificantBits());
            }
        }
        if (bArr != null && bArr.length != 0) {
            allocate.putInt(bArr.length);
            allocate.put(bArr);
        }
        return allocate.array();
    }

    public static boolean isPsshAtom(byte[] bArr) {
        return parsePsshAtom(bArr) != null;
    }

    public static UUID parseUuid(byte[] bArr) {
        PsshAtom parsePsshAtom = parsePsshAtom(bArr);
        if (parsePsshAtom == null) {
            return null;
        }
        return parsePsshAtom.uuid;
    }

    public static int parseVersion(byte[] bArr) {
        PsshAtom parsePsshAtom = parsePsshAtom(bArr);
        if (parsePsshAtom == null) {
            return -1;
        }
        return parsePsshAtom.version;
    }

    public static byte[] parseSchemeSpecificData(byte[] bArr, UUID r4) {
        PsshAtom parsePsshAtom = parsePsshAtom(bArr);
        if (parsePsshAtom == null) {
            return null;
        }
        if (!r4.equals(parsePsshAtom.uuid)) {
            Log.m1132w(TAG, "UUID mismatch. Expected: " + r4 + ", got: " + parsePsshAtom.uuid + ".");
            return null;
        }
        return parsePsshAtom.schemeData;
    }

    private static PsshAtom parsePsshAtom(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (parsableByteArray.limit() < 32) {
            return null;
        }
        parsableByteArray.setPosition(0);
        if (parsableByteArray.readInt() == parsableByteArray.bytesLeft() + 4 && parsableByteArray.readInt() == 1886614376) {
            int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
            if (parseFullAtomVersion > 1) {
                Log.m1132w(TAG, "Unsupported pssh version: " + parseFullAtomVersion);
                return null;
            }
            UUID r4 = new UUID(parsableByteArray.readLong(), parsableByteArray.readLong());
            if (parseFullAtomVersion == 1) {
                parsableByteArray.skipBytes(parsableByteArray.readUnsignedIntToInt() * 16);
            }
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt != parsableByteArray.bytesLeft()) {
                return null;
            }
            byte[] bArr2 = new byte[readUnsignedIntToInt];
            parsableByteArray.readBytes(bArr2, 0, readUnsignedIntToInt);
            return new PsshAtom(r4, parseFullAtomVersion, bArr2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PsshAtom {
        private final byte[] schemeData;
        private final UUID uuid;
        private final int version;

        public PsshAtom(UUID r1, int r2, byte[] bArr) {
            this.uuid = r1;
            this.version = r2;
            this.schemeData = bArr;
        }
    }
}
