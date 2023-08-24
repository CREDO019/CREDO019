package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzari extends zzarm {
    public zzari(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "/a8iouG/Y9ItWmHyK+NdI47yR9GXTao7DXYLnnbCwVxe2vjd2Eu6bHNA7uqUktHm", "2ehMwY4NjAVCxKNUUqdAHAq3dStVMVXOFsI0kFp0ZW0=", zzamhVar, r12, 48);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzae(3);
        boolean booleanValue = ((Boolean) this.zzf.invoke(null, this.zzb.zzb())).booleanValue();
        synchronized (this.zze) {
            if (booleanValue) {
                this.zze.zzae(2);
            } else {
                this.zze.zzae(1);
            }
        }
    }
}
