package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public enum zzbgy implements zzgop {
    UNSPECIFIED(0),
    CONNECTING(1),
    CONNECTED(2),
    DISCONNECTING(3),
    DISCONNECTED(4),
    SUSPENDED(5);
    
    private static final zzgoq zzg = new zzgoq() { // from class: com.google.android.gms.internal.ads.zzbgw
    };
    private final int zzi;

    zzbgy(int r3) {
        this.zzi = r3;
    }

    public static zzbgy zzb(int r1) {
        if (r1 != 0) {
            if (r1 != 1) {
                if (r1 != 2) {
                    if (r1 != 3) {
                        if (r1 != 4) {
                            if (r1 != 5) {
                                return null;
                            }
                            return SUSPENDED;
                        }
                        return DISCONNECTED;
                    }
                    return DISCONNECTING;
                }
                return CONNECTED;
            }
            return CONNECTING;
        }
        return UNSPECIFIED;
    }

    public static zzgor zzc() {
        return zzbgx.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzi);
    }

    public final int zza() {
        return this.zzi;
    }
}
