package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzety implements zzeun {
    private final zzces zza;
    private final zzfyy zzb;
    private final Context zzc;

    public zzety(zzces zzcesVar, zzfyy zzfyyVar, Context context) {
        this.zza = zzcesVar;
        this.zzb = zzfyyVar;
        this.zzc = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 34;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zzb.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzetx
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzety.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzetz zzc() throws Exception {
        if (this.zza.zzu(this.zzc)) {
            String zze = this.zza.zze(this.zzc);
            String str = zze == null ? "" : zze;
            String zzc = this.zza.zzc(this.zzc);
            String str2 = zzc == null ? "" : zzc;
            String zza = this.zza.zza(this.zzc);
            String str3 = zza == null ? "" : zza;
            String zzb = this.zza.zzb(this.zzc);
            return new zzetz(str, str2, str3, zzb == null ? "" : zzb, "TIME_OUT".equals(str2) ? (Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzad) : null);
        }
        return new zzetz(null, null, null, null, null);
    }
}
