package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdx {
    private Context zza;
    private Clock zzb;
    private com.google.android.gms.ads.internal.util.zzg zzc;
    private zzces zzd;

    private zzcdx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcdx(zzcdw zzcdwVar) {
    }

    public final zzcdx zza(com.google.android.gms.ads.internal.util.zzg zzgVar) {
        this.zzc = zzgVar;
        return this;
    }

    public final zzcdx zzb(Context context) {
        Objects.requireNonNull(context);
        this.zza = context;
        return this;
    }

    public final zzcdx zzc(Clock clock) {
        Objects.requireNonNull(clock);
        this.zzb = clock;
        return this;
    }

    public final zzcdx zzd(zzces zzcesVar) {
        this.zzd = zzcesVar;
        return this;
    }

    public final zzcet zze() {
        zzguz.zzc(this.zza, Context.class);
        zzguz.zzc(this.zzb, Clock.class);
        zzguz.zzc(this.zzc, com.google.android.gms.ads.internal.util.zzg.class);
        zzguz.zzc(this.zzd, zzces.class);
        return new zzcdz(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}
