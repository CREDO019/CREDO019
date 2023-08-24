package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbat extends Surface {
    private static boolean zza;
    private static boolean zzb;
    private final zzbar zzc;
    private boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbat(zzbar zzbarVar, SurfaceTexture surfaceTexture, boolean z, zzbas zzbasVar) {
        super(surfaceTexture);
        this.zzc = zzbarVar;
    }

    public static zzbat zza(Context context, boolean z) {
        if (zzban.zza >= 17) {
            boolean z2 = true;
            if (z && !zzb(context)) {
                z2 = false;
            }
            zzazy.zze(z2);
            return new zzbar().zza(z);
        }
        throw new UnsupportedOperationException("Unsupported prior to API level 17");
    }

    public static synchronized boolean zzb(Context context) {
        boolean z;
        synchronized (zzbat.class) {
            if (!zzb) {
                if (zzban.zza >= 17) {
                    boolean z2 = false;
                    String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
                    if (eglQueryString != null && eglQueryString.contains("EGL_EXT_protected_content") && (zzban.zza != 24 || ((!zzban.zzd.startsWith("SM-G950") && !zzban.zzd.startsWith("SM-G955")) || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")))) {
                        z2 = true;
                    }
                    zza = z2;
                }
                zzb = true;
            }
            z = zza;
        }
        return z;
    }

    @Override // android.view.Surface
    public final void release() {
        super.release();
        synchronized (this.zzc) {
            if (!this.zzd) {
                this.zzc.zzb();
                this.zzd = true;
            }
        }
    }
}
