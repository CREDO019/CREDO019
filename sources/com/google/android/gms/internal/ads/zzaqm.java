package com.google.android.gms.internal.ads;

import android.provider.Settings;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqm extends zzarm {
    public zzaqm(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "kypbHNkssvP0JsUhutxcI0i2X1rFBFEIMdtXVFZfH9yzsagSauN+Hc+hylkiTIg3", "RsQqTTopQSSPcEVMkYGLNYEBC1gHD99rtwIfq5A0FgY=", zzamhVar, r12, 49);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzZ(3);
        try {
            int r1 = 1;
            boolean booleanValue = ((Boolean) this.zzf.invoke(null, this.zzb.zzb())).booleanValue();
            zzamh zzamhVar = this.zze;
            if (true == booleanValue) {
                r1 = 2;
            }
            zzamhVar.zzZ(r1);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
