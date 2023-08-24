package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.WindowManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxl implements zzxk {
    private final WindowManager zza;

    private zzxl(WindowManager windowManager) {
        this.zza = windowManager;
    }

    public static zzxk zzc(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            return new zzxl(windowManager);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzxk
    public final void zza() {
    }

    @Override // com.google.android.gms.internal.ads.zzxk
    public final void zzb(zzxi zzxiVar) {
        zzxo.zzb(zzxiVar.zza, this.zza.getDefaultDisplay());
    }
}
