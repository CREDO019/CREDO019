package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqv extends zzarm {
    private static volatile Long zzi;
    private static final Object zzj = new Object();

    public zzaqv(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "vwafhzajmko+XbfuSejKABEanPVybMYr0Xs0X58XDfD46RrNvhHs28KurUcFj7Vb", "F5L8q737MMzYnugLKIPW4LyN3bjV9SheOjBtsYe5lqc=", zzamhVar, r12, 22);
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
            this.zze.zzx(zzi.longValue());
        }
    }
}
