package com.polidea.rxandroidble.internal.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.polidea.rxandroidble.scan.ScanRecord;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ScanRecordImplCompat implements ScanRecord {
    private final int advertiseFlags;
    private final byte[] bytes;
    private final String deviceName;
    private final SparseArray<byte[]> manufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> serviceData;
    private final List<ParcelUuid> serviceUuids;
    private final int txPowerLevel;

    public ScanRecordImplCompat(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int r4, int r5, String str, byte[] bArr) {
        this.serviceUuids = list;
        this.manufacturerSpecificData = sparseArray;
        this.serviceData = map;
        this.deviceName = str;
        this.advertiseFlags = r4;
        this.txPowerLevel = r5;
        this.bytes = bArr;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public int getAdvertiseFlags() {
        return this.advertiseFlags;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public List<ParcelUuid> getServiceUuids() {
        return this.serviceUuids;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.manufacturerSpecificData;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public byte[] getManufacturerSpecificData(int r2) {
        return this.manufacturerSpecificData.get(r2);
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.serviceData;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.serviceData.get(parcelUuid);
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public int getTxPowerLevel() {
        return this.txPowerLevel;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public String getDeviceName() {
        return this.deviceName;
    }

    @Override // com.polidea.rxandroidble.scan.ScanRecord
    public byte[] getBytes() {
        return this.bytes;
    }
}