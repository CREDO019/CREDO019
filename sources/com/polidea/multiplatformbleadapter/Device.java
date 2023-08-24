package com.polidea.multiplatformbleadapter;

import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class Device {

    /* renamed from: id */
    private String f1313id;
    private Integer mtu;
    private String name;
    private Integer rssi;
    private List<Service> services;

    public Device(String str, String str2) {
        this.f1313id = str;
        this.name = str2;
    }

    public String getId() {
        return this.f1313id;
    }

    public void setId(String str) {
        this.f1313id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getRssi() {
        return this.rssi;
    }

    public void setRssi(Integer num) {
        this.rssi = num;
    }

    public Integer getMtu() {
        return this.mtu;
    }

    public void setMtu(Integer num) {
        this.mtu = num;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> list) {
        this.services = list;
    }

    public Service getServiceByUUID(UUID r5) {
        List<Service> list = this.services;
        if (list == null) {
            return null;
        }
        for (Service service : list) {
            if (r5.equals(service.getUuid())) {
                return service;
            }
        }
        return null;
    }
}
