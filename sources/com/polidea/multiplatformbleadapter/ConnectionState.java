package com.polidea.multiplatformbleadapter;

/* loaded from: classes3.dex */
public enum ConnectionState {
    CONNECTING("connecting"),
    CONNECTED("connected"),
    DISCONNECTING("disconnecting"),
    DISCONNECTED("disconnected");
    
    public final String value;

    ConnectionState(String str) {
        this.value = str;
    }
}
