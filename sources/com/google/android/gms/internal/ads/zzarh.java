package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarh extends zzarm {
    private static volatile Long zzi;
    private static final Object zzj = new Object();

    public zzarh(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "YKKxGLeU2zJMORJ3CXggTVER1rcusEu7lPKzdwQPcOhmeA2WpnQ11LfNiSgl6FV6", "MxHq/OY7GLM9L/Opnjlc3PuDp6q7R1CL8i73K0Ziu0Y=", zzamhVar, r12, 33);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (zzi == null) {
            synchronized (zzj) {
                if (zzi == null) {
                    zzi = (Long) this.zzf.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zze) {
            this.zze.zzU(zzi.longValue());
        }
    }
}
