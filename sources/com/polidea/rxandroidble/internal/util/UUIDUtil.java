package com.polidea.rxandroidble.internal.util;

import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Deprecated
/* loaded from: classes3.dex */
public class UUIDUtil {
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int DATA_TYPE_SERVICE_DATA = 22;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final String UUID_BASE_FORMAT = "%08x-0000-1000-8000-00805f9b34fb";
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    public List<UUID> extractUUIDs(byte[] bArr) {
        int r1;
        ArrayList arrayList = new ArrayList();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() > 2 && (r1 = order.get() & 255) != 0) {
            switch (order.get()) {
                case 2:
                case 3:
                    while (r1 >= 2) {
                        arrayList.add(UUID.fromString(String.format(UUID_BASE_FORMAT, Short.valueOf(order.getShort()))));
                        r1 -= 2;
                    }
                    break;
                case 4:
                case 5:
                    while (r1 >= 4) {
                        arrayList.add(UUID.fromString(String.format(UUID_BASE_FORMAT, Integer.valueOf(order.getInt()))));
                        r1 -= 4;
                    }
                    break;
                case 6:
                case 7:
                    while (r1 >= 16) {
                        arrayList.add(new UUID(order.getLong(), order.getLong()));
                        r1 -= 16;
                    }
                    break;
                default:
                    order.position(order.position() + Math.min(r1 - 1, order.remaining()));
                    break;
            }
        }
        return arrayList;
    }

    public Set<UUID> toDistinctSet(UUID[] r2) {
        if (r2 == null) {
            r2 = new UUID[0];
        }
        return new HashSet(Arrays.asList(r2));
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.polidea.rxandroidble.scan.ScanRecord parseFromBytes(byte[] r15) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.polidea.rxandroidble.internal.util.UUIDUtil.parseFromBytes(byte[]):com.polidea.rxandroidble.scan.ScanRecord");
    }

    private static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        } else if (length == 16) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        } else {
            if (length == 2) {
                j = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
            } else {
                j = ((bArr[3] & 255) << 24) + (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
            }
            ParcelUuid parcelUuid = BASE_UUID;
            return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
        }
    }

    private static int parseServiceUuid(byte[] bArr, int r2, int r3, int r4, List<ParcelUuid> list) {
        while (r3 > 0) {
            list.add(parseUuidFrom(extractBytes(bArr, r2, r4)));
            r3 -= r4;
            r2 += r4;
        }
        return r2;
    }

    private static byte[] extractBytes(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4];
        System.arraycopy(bArr, r3, bArr2, 0, r4);
        return bArr2;
    }
}
