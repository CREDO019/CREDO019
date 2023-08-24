package com.google.android.gms.internal.ads;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqz extends zzarm {
    private static volatile String zzi;
    private static final Object zzj = new Object();

    public zzaqz(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "DPPujNohd+oH/T6ZUMbZzyhfnSIvMDgRP7rWCRWsFdFXxrCKVlDjhvyLuEdzf2o9", "/wkWLWdulpNS6Pk1iA7T64KnscjeHNN2RH4sHzd4WPM=", zzamhVar, r12, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzA(ExifInterface.LONGITUDE_EAST);
        if (zzi == null) {
            synchronized (zzj) {
                if (zzi == null) {
                    zzi = (String) this.zzf.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zze) {
            this.zze.zzA(zzi);
        }
    }
}
