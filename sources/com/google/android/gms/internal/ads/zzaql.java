package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaql extends zzarm {
    private final Activity zzi;
    private final View zzj;

    public zzaql(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, View view, Activity activity) {
        super(zzaqbVar, "SCpciX5wB77gNVd3QxtagyOGzU5F2jwXT8/fqGicur4e//OvUztB6/kDTY8ZtC7H", "1Cs3HyJ4gdHs1TY1t1mRXKXwLGulk9WztHFAT7PZCzg=", zzamhVar, r12, 62);
        this.zzj = view;
        this.zzi = activity;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzj == null) {
            return;
        }
        boolean booleanValue = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzce)).booleanValue();
        Object[] objArr = (Object[]) this.zzf.invoke(null, this.zzj, this.zzi, Boolean.valueOf(booleanValue));
        synchronized (this.zze) {
            this.zze.zzc(((Long) objArr[0]).longValue());
            this.zze.zze(((Long) objArr[1]).longValue());
            if (booleanValue) {
                this.zze.zzd((String) objArr[2]);
            }
        }
    }
}
