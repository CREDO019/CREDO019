package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfqi {
    private static final zzfqv zzb = new zzfqv("OverlayDisplayService");
    private static final Intent zzc = new Intent("com.google.android.play.core.lmd.BIND_OVERLAY_DISPLAY_SERVICE").setPackage("com.android.vending");
    final zzfrg zza;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfqi(Context context) {
        if (zzfrj.zza(context)) {
            this.zza = new zzfrg(context.getApplicationContext(), zzb, "OverlayDisplayService", zzc, zzfqd.zza, null, null);
        } else {
            this.zza = null;
        }
        this.zzd = context.getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        if (this.zza == null) {
            return;
        }
        zzb.zzd("unbind LMD display overlay service", new Object[0]);
        this.zza.zzr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(zzfpz zzfpzVar, zzfqn zzfqnVar) {
        if (this.zza == null) {
            zzb.zzb("error: %s", "Play Store not found.");
            return;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzp(new zzfqf(this, taskCompletionSource, zzfpzVar, zzfqnVar, taskCompletionSource), taskCompletionSource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(zzfqk zzfqkVar, zzfqn zzfqnVar) {
        if (this.zza == null) {
            zzb.zzb("error: %s", "Play Store not found.");
        } else if (zzfqkVar.zzg() != null) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.zza.zzp(new zzfqe(this, taskCompletionSource, zzfqkVar, zzfqnVar, taskCompletionSource), taskCompletionSource);
        } else {
            zzb.zzb("Failed to convert OverlayDisplayShowRequest when to create a new session: appId cannot be null.", new Object[0]);
            zzfql zzc2 = zzfqm.zzc();
            zzc2.zzb(8160);
            zzfqnVar.zza(zzc2.zzc());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf(zzfqp zzfqpVar, zzfqn zzfqnVar, int r13) {
        if (this.zza == null) {
            zzb.zzb("error: %s", "Play Store not found.");
            return;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzp(new zzfqg(this, taskCompletionSource, zzfqpVar, r13, zzfqnVar, taskCompletionSource), taskCompletionSource);
    }
}
