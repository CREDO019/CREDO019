package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeuu implements zzeun {
    private final zzfyy zza;
    private final Context zzb;

    public zzeuu(zzfyy zzfyyVar, Context context) {
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    private static ResolveInfo zzd(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 38;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeut
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeuu.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(1:3)(1:60)|4|(1:6)(1:59)|7|(3:9|(2:12|10)|13)|14|(3:53|54|(14:56|17|18|19|(9:21|22|23|(1:25)(2:36|(3:39|(3:42|(2:45|46)(1:44)|40)|47))|26|27|(1:35)(1:31)|32|33)|49|23|(0)(0)|26|27|(1:29)|35|32|33))|16|17|18|19|(0)|49|23|(0)(0)|26|27|(0)|35|32|33) */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00aa A[Catch: Exception -> 0x00c3, TRY_LEAVE, TryCatch #1 {Exception -> 0x00c3, blocks: (B:26:0x009c, B:28:0x00aa), top: B:57:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzeus zzc() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeuu.zzc():com.google.android.gms.internal.ads.zzeus");
    }
}
