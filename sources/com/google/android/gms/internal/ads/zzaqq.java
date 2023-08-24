package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqq extends zzarm {
    private static volatile Long zzi;
    private static final Object zzj = new Object();

    public zzaqq(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "tUt0vz6lOby0Kor5zovCxCU8AEhO9JFrPQ+FoHildCv7G6/grOfOnSHI07/MbEco", "fLYJ/dhEHiKfuxbMUjXGagNO9QZ/DvGDpPbugCyxqbI=", zzamhVar, r12, 44);
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
            this.zze.zzn(zzi.longValue());
        }
    }
}
