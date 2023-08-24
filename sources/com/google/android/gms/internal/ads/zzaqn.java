package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqn extends zzarm {
    private static final zzarn zzi = new zzarn();
    private final Context zzj;

    public zzaqn(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, Context context) {
        super(zzaqbVar, "F65Z/VGvH3HDSqbisIa05H0FMwZRHR3bv6841vtkw2aGLMzg81raAO8ZiFdKkdju", "44x6GMcLLfXIM1YaNbXK6PVfUUKLz6aX2MRhfiwtXAQ=", zzamhVar, r12, 29);
        this.zzj = context;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzm(ExifInterface.LONGITUDE_EAST);
        AtomicReference zza = zzi.zza(this.zzj.getPackageName());
        if (zza.get() == null) {
            synchronized (zza) {
                if (zza.get() == null) {
                    zza.set((String) this.zzf.invoke(null, this.zzj));
                }
            }
        }
        String str = (String) zza.get();
        synchronized (this.zze) {
            this.zze.zzm(zzanm.zza(str.getBytes(), true));
        }
    }
}
