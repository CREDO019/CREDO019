package com.polidea.multiplatformbleadapter;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import okhttp3.internal.p026ws.WebSocketProtocol;

/* loaded from: classes3.dex */
public class AdvertisementData {
    private static final long BLUETOOTH_BASE_UUID_LSB = -9223371485494954757L;
    private static final int BLUETOOTH_BASE_UUID_MSB = 4096;
    private String localName;
    private byte[] manufacturerData;
    private Map<UUID, byte[]> serviceData;
    private List<UUID> serviceUUIDs;
    private List<UUID> solicitedServiceUUIDs;
    private Integer txPowerLevel;

    public String getLocalName() {
        return this.localName;
    }

    public byte[] getManufacturerData() {
        return this.manufacturerData;
    }

    public Map<UUID, byte[]> getServiceData() {
        return this.serviceData;
    }

    public List<UUID> getServiceUUIDs() {
        return this.serviceUUIDs;
    }

    public Integer getTxPowerLevel() {
        return this.txPowerLevel;
    }

    public List<UUID> getSolicitedServiceUUIDs() {
        return this.solicitedServiceUUIDs;
    }

    private AdvertisementData() {
    }

    public AdvertisementData(byte[] bArr, Map<UUID, byte[]> map, List<UUID> list, String str, Integer num, List<UUID> list2) {
        this.manufacturerData = bArr;
        this.serviceData = map;
        this.serviceUUIDs = list;
        this.localName = str;
        this.txPowerLevel = num;
        this.solicitedServiceUUIDs = list2;
    }

    public static AdvertisementData parseScanResponseData(byte[] bArr) {
        int r1;
        AdvertisementData advertisementData = new AdvertisementData();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() >= 2 && (r1 = order.get() & 255) != 0) {
            int r12 = r1 - 1;
            int r2 = order.get() & 255;
            if (order.remaining() < r12) {
                break;
            }
            parseAdvertisementData(advertisementData, r2, r12, order.slice().order(ByteOrder.LITTLE_ENDIAN));
            order.position(order.position() + r12);
        }
        return advertisementData;
    }

    private static void parseAdvertisementData(AdvertisementData advertisementData, int r4, int r5, ByteBuffer byteBuffer) {
        if (r4 == 255) {
            parseManufacturerData(advertisementData, r5, byteBuffer);
            return;
        }
        switch (r4) {
            case 2:
            case 3:
                parseServiceUUIDs(advertisementData, r5, byteBuffer, 2);
                return;
            case 4:
            case 5:
                parseServiceUUIDs(advertisementData, r5, byteBuffer, 4);
                return;
            case 6:
            case 7:
                parseServiceUUIDs(advertisementData, r5, byteBuffer, 16);
                return;
            case 8:
            case 9:
                parseLocalName(advertisementData, r4, r5, byteBuffer);
                return;
            case 10:
                parseTxPowerLevel(advertisementData, r5, byteBuffer);
                return;
            default:
                switch (r4) {
                    case 20:
                        parseSolicitedServiceUUIDs(advertisementData, r5, byteBuffer, 2);
                        return;
                    case 21:
                        parseSolicitedServiceUUIDs(advertisementData, r5, byteBuffer, 16);
                        return;
                    case 22:
                        parseServiceData(advertisementData, r5, byteBuffer, 2);
                        return;
                    default:
                        switch (r4) {
                            case 31:
                                parseSolicitedServiceUUIDs(advertisementData, r5, byteBuffer, 4);
                                return;
                            case 32:
                                parseServiceData(advertisementData, r5, byteBuffer, 4);
                                return;
                            case 33:
                                parseServiceData(advertisementData, r5, byteBuffer, 16);
                                return;
                            default:
                                return;
                        }
                }
        }
    }

    private static void parseLocalName(AdvertisementData advertisementData, int r2, int r3, ByteBuffer byteBuffer) {
        if (advertisementData.localName == null || r2 == 9) {
            byte[] bArr = new byte[r3];
            byteBuffer.get(bArr, 0, r3);
            advertisementData.localName = new String(bArr, Charset.forName("UTF-8"));
        }
    }

    private static UUID parseUUID(ByteBuffer byteBuffer, int r9) {
        long j;
        long j2;
        long j3 = BLUETOOTH_BASE_UUID_LSB;
        if (r9 == 2) {
            j = byteBuffer.getShort() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        } else if (r9 != 4) {
            if (r9 == 16) {
                j3 = byteBuffer.getLong();
                j2 = byteBuffer.getLong();
                return new UUID(j2, j3);
            }
            byteBuffer.position(byteBuffer.position() + r9);
            return null;
        } else {
            j = byteBuffer.getInt();
        }
        j2 = (j << 32) + PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        return new UUID(j2, j3);
    }

    private static void parseSolicitedServiceUUIDs(AdvertisementData advertisementData, int r3, ByteBuffer byteBuffer, int r5) {
        if (advertisementData.solicitedServiceUUIDs == null) {
            advertisementData.solicitedServiceUUIDs = new ArrayList();
        }
        while (byteBuffer.remaining() >= r5 && byteBuffer.position() < r3) {
            advertisementData.solicitedServiceUUIDs.add(parseUUID(byteBuffer, r5));
        }
    }

    private static void parseServiceUUIDs(AdvertisementData advertisementData, int r3, ByteBuffer byteBuffer, int r5) {
        if (advertisementData.serviceUUIDs == null) {
            advertisementData.serviceUUIDs = new ArrayList();
        }
        while (byteBuffer.remaining() >= r5 && byteBuffer.position() < r3) {
            advertisementData.serviceUUIDs.add(parseUUID(byteBuffer, r5));
        }
    }

    private static void parseServiceData(AdvertisementData advertisementData, int r3, ByteBuffer byteBuffer, int r5) {
        if (r3 < r5) {
            return;
        }
        if (advertisementData.serviceData == null) {
            advertisementData.serviceData = new HashMap();
        }
        UUID parseUUID = parseUUID(byteBuffer, r5);
        int r32 = r3 - r5;
        byte[] bArr = new byte[r32];
        byteBuffer.get(bArr, 0, r32);
        advertisementData.serviceData.put(parseUUID, bArr);
    }

    private static void parseTxPowerLevel(AdvertisementData advertisementData, int r2, ByteBuffer byteBuffer) {
        if (r2 != 1) {
            return;
        }
        advertisementData.txPowerLevel = Integer.valueOf(byteBuffer.get());
    }

    private static void parseManufacturerData(AdvertisementData advertisementData, int r2, ByteBuffer byteBuffer) {
        if (r2 < 2) {
            return;
        }
        byte[] bArr = new byte[r2];
        advertisementData.manufacturerData = bArr;
        byteBuffer.get(bArr, 0, r2);
    }
}
