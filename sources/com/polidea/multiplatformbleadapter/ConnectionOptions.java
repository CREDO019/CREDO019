package com.polidea.multiplatformbleadapter;

/* loaded from: classes3.dex */
public class ConnectionOptions {
    private boolean autoConnect;
    private int connectionPriority;
    private RefreshGattMoment refreshGattMoment;
    private int requestMTU;
    private Long timeoutInMillis;

    public ConnectionOptions(Boolean bool, int r2, RefreshGattMoment refreshGattMoment, Long l, int r5) {
        this.autoConnect = bool.booleanValue();
        this.requestMTU = r2;
        this.refreshGattMoment = refreshGattMoment;
        this.timeoutInMillis = l;
        this.connectionPriority = r5;
    }

    public Boolean getAutoConnect() {
        return Boolean.valueOf(this.autoConnect);
    }

    public int getRequestMTU() {
        return this.requestMTU;
    }

    public RefreshGattMoment getRefreshGattMoment() {
        return this.refreshGattMoment;
    }

    public Long getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    public int getConnectionPriority() {
        return this.connectionPriority;
    }
}
