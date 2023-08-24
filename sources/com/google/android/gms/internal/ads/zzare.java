package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzare extends zzarm {
    private final StackTraceElement[] zzi;

    public zzare(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, StackTraceElement[] stackTraceElementArr) {
        super(zzaqbVar, "mKZoVC4c2F/JqX9WIT6IUJ7O1rqDja2RmF+/au0SoJW0hEJbfdMFQna3+PNp9GA3", "5YZBRBaKVoc53PJNiLp/sxiQ4sgkDdqNOlYuo9Kj11A=", zzamhVar, r12, 45);
        this.zzi = stackTraceElementArr;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        StackTraceElement[] stackTraceElementArr = this.zzi;
        if (stackTraceElementArr != null) {
            zzapt zzaptVar = new zzapt((String) this.zzf.invoke(null, stackTraceElementArr));
            synchronized (this.zze) {
                this.zze.zzE(zzaptVar.zza.longValue());
                if (zzaptVar.zzb.booleanValue()) {
                    this.zze.zzab(true != zzaptVar.zzc.booleanValue() ? 2 : 1);
                } else {
                    this.zze.zzab(3);
                }
            }
        }
    }
}
