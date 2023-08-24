package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxg extends Surface {
    private static int zzb;
    private static boolean zzc;
    public final boolean zza;
    private final zzxe zzd;
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzxg(zzxe zzxeVar, SurfaceTexture surfaceTexture, boolean z, zzxf zzxfVar) {
        super(surfaceTexture);
        this.zzd = zzxeVar;
        this.zza = z;
    }

    public static zzxg zza(Context context, boolean z) {
        boolean z2 = true;
        if (z && !zzb(context)) {
            z2 = false;
        }
        zzdd.zzf(z2);
        return new zzxe().zza(z ? zzb : 0);
    }

    public static synchronized boolean zzb(Context context) {
        int r7;
        String eglQueryString;
        synchronized (zzxg.class) {
            if (!zzc) {
                int r5 = 2;
                if (zzel.zza >= 24 && ((zzel.zza >= 26 || (!"samsung".equals(zzel.zzc) && !"XT1650".equals(zzel.zzd))) && ((zzel.zza >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && eglQueryString.contains("EGL_EXT_protected_content")))) {
                    String eglQueryString2 = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    if (eglQueryString2 != null && eglQueryString2.contains("EGL_KHR_surfaceless_context")) {
                        r5 = 1;
                    }
                    zzb = r5;
                    zzc = true;
                }
                r5 = 0;
                zzb = r5;
                zzc = true;
            }
            r7 = zzb;
        }
        return r7 != 0;
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.zzd) {
            if (!this.zze) {
                this.zzd.zzb();
                this.zze = true;
            }
        }
    }
}
