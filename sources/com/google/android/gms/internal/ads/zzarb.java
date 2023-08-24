package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarb extends zzarm {
    public zzarb(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "Vzvp2EkF2pEbKPIdCn3PFXroHmxxEvOZYSjqA8kd9hBeO5S3wxnRIFg589Z0eHEB", "WecnIv7/+ap7EIcAgJwinTe6aq/WkhqHwfnV+h6mDgk=", zzamhVar, r12, 73);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        try {
            int r1 = 1;
            boolean booleanValue = ((Boolean) this.zzf.invoke(null, this.zzb.zzb())).booleanValue();
            zzamh zzamhVar = this.zze;
            if (true == booleanValue) {
                r1 = 2;
            }
            zzamhVar.zzad(r1);
        } catch (InvocationTargetException unused) {
            this.zze.zzad(3);
        }
    }
}
