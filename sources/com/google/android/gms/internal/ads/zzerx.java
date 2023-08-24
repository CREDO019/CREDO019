package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.ViewGroup;
import android.view.Window;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzerx implements zzeun {
    private final zzfyy zza;
    private final ViewGroup zzb;
    private final Context zzc;
    private final Set zzd;

    public zzerx(zzfyy zzfyyVar, ViewGroup viewGroup, Context context, Set set) {
        this.zza = zzfyyVar;
        this.zzd = set;
        this.zzb = viewGroup;
        this.zzc = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 22;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzerw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzerx.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzery zzc() throws Exception {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeU)).booleanValue() || this.zzb == null || !this.zzd.contains("banner")) {
            Boolean bool = null;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeV)).booleanValue() && this.zzd.contains("native")) {
                Context context = this.zzc;
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    Window window = activity.getWindow();
                    boolean z = true;
                    if (window == null || (window.getAttributes().flags & 16777216) == 0) {
                        try {
                            if ((activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).flags & 512) == 0) {
                                z = false;
                            }
                            bool = Boolean.valueOf(z);
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    } else {
                        bool = true;
                    }
                    return new zzery(bool);
                }
            }
            return new zzery(null);
        }
        return new zzery(Boolean.valueOf(this.zzb.isHardwareAccelerated()));
    }
}
