package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqx extends zzarm {
    private final zzapf zzi;
    private final long zzj;
    private final long zzk;

    public zzaqx(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r13, int r14, zzapf zzapfVar, long j, long j2) {
        super(zzaqbVar, "fJzM8V+ATbwMqso1FfvPLoBhYN4ojhn6bStsV+iGZGXUcAQ5UdVWi/JBwFYSjW+Z", "EqhZfeJdagDkdmNpQFhVQ7G0RKq67vK3QlT8DoNCRs8=", zzamhVar, r13, 11);
        this.zzi = zzapfVar;
        this.zzj = j;
        this.zzk = j2;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        zzapf zzapfVar = this.zzi;
        if (zzapfVar != null) {
            zzapd zzapdVar = new zzapd((String) this.zzf.invoke(null, zzapfVar.zzb(), Long.valueOf(this.zzj), Long.valueOf(this.zzk)));
            synchronized (this.zze) {
                this.zze.zzy(zzapdVar.zza.longValue());
                if (zzapdVar.zzb.longValue() >= 0) {
                    this.zze.zzP(zzapdVar.zzb.longValue());
                }
                if (zzapdVar.zzc.longValue() >= 0) {
                    this.zze.zzf(zzapdVar.zzc.longValue());
                }
            }
        }
    }
}
