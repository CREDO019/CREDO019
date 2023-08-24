package com.google.android.gms.internal.vision;

import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public enum zzes implements zzgw {
    CONTACT_INFO(1),
    EMAIL(2),
    ISBN(3),
    PHONE(4),
    PRODUCT(5),
    SMS(6),
    TEXT(7),
    URL(8),
    WIFI(9),
    GEO(10),
    CALENDAR_EVENT(11),
    DRIVER_LICENSE(12);
    
    private static final zzgv<zzes> zzgy = new zzgv<zzes>() { // from class: com.google.android.gms.internal.vision.zzer
        @Override // com.google.android.gms.internal.vision.zzgv
        public final /* synthetic */ zzes zzg(int r1) {
            return zzes.zzac(r1);
        }
    };
    private final int value;

    @Override // com.google.android.gms.internal.vision.zzgw
    public final int zzag() {
        return this.value;
    }

    public static zzes zzac(int r0) {
        switch (r0) {
            case 1:
                return CONTACT_INFO;
            case 2:
                return EMAIL;
            case 3:
                return ISBN;
            case 4:
                return PHONE;
            case 5:
                return PRODUCT;
            case 6:
                return SMS;
            case 7:
                return TEXT;
            case 8:
                return URL;
            case 9:
                return WIFI;
            case 10:
                return GEO;
            case 11:
                return CALENDAR_EVENT;
            case 12:
                return DRIVER_LICENSE;
            default:
                return null;
        }
    }

    public static zzgy zzah() {
        return zzeu.zzhb;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    zzes(int r3) {
        this.value = r3;
    }
}
