package com.learnium.RNDeviceInfo;

import androidx.core.p005os.EnvironmentCompat;

/* loaded from: classes3.dex */
public enum DeviceType {
    HANDSET("Handset"),
    TABLET("Tablet"),
    TV("Tv"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
    
    private final String value;

    DeviceType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
