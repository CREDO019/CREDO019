package com.polidea.multiplatformbleadapter;

import java.util.UUID;

/* loaded from: classes3.dex */
public class ScanResult {
    private AdvertisementData advertisementData;
    private String deviceId;
    private String deviceName;
    private boolean isConnectable;
    private int mtu;
    private UUID[] overflowServiceUUIDs;
    private int rssi;

    public ScanResult(String str, String str2, int r3, int r4, boolean z, UUID[] r6, AdvertisementData advertisementData) {
        this.deviceId = str;
        this.deviceName = str2;
        this.rssi = r3;
        this.mtu = r4;
        this.isConnectable = z;
        this.overflowServiceUUIDs = r6;
        this.advertisementData = advertisementData;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int r1) {
        this.rssi = r1;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int r1) {
        this.mtu = r1;
    }

    public boolean isConnectable() {
        return this.isConnectable;
    }

    public void setConnectable(boolean z) {
        this.isConnectable = z;
    }

    public UUID[] getOverflowServiceUUIDs() {
        return this.overflowServiceUUIDs;
    }

    public void setOverflowServiceUUIDs(UUID[] r1) {
        this.overflowServiceUUIDs = r1;
    }

    public AdvertisementData getAdvertisementData() {
        return this.advertisementData;
    }

    public void setAdvertisementData(AdvertisementData advertisementData) {
        this.advertisementData = advertisementData;
    }
}
