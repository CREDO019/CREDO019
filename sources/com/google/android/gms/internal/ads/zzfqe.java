package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfqe extends zzfqw {
    final /* synthetic */ zzfqk zza;
    final /* synthetic */ zzfqn zzb;
    final /* synthetic */ TaskCompletionSource zzc;
    final /* synthetic */ zzfqi zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfqe(zzfqi zzfqiVar, TaskCompletionSource taskCompletionSource, zzfqk zzfqkVar, zzfqn zzfqnVar, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzd = zzfqiVar;
        this.zza = zzfqkVar;
        this.zzb = zzfqnVar;
        this.zzc = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [android.os.IInterface, com.google.android.gms.internal.ads.zzfqs] */
    @Override // com.google.android.gms.internal.ads.zzfqw
    protected final void zza() {
        zzfqv zzfqvVar;
        String str;
        String str2;
        String str3;
        try {
            ?? zze = this.zzd.zza.zze();
            zzfqi zzfqiVar = this.zzd;
            str2 = zzfqiVar.zzd;
            zzfqk zzfqkVar = this.zza;
            str3 = zzfqiVar.zzd;
            Bundle bundle = new Bundle();
            bundle.putBinder("windowToken", zzfqkVar.zze());
            bundle.putString("adFieldEnifd", zzfqkVar.zzf());
            bundle.putInt("layoutGravity", zzfqkVar.zzc());
            bundle.putFloat("layoutVerticalMargin", zzfqkVar.zza());
            bundle.putInt("displayMode", 0);
            bundle.putInt("windowWidthPx", zzfqkVar.zzd());
            bundle.putBoolean("stableSessionToken", false);
            bundle.putString("callerPackage", str3);
            if (zzfqkVar.zzg() != null) {
                bundle.putString("appId", zzfqkVar.zzg());
            }
            zze.zzf(str2, bundle, new zzfqh(this.zzd, this.zzb));
        } catch (RemoteException e) {
            zzfqvVar = zzfqi.zzb;
            str = this.zzd.zzd;
            zzfqvVar.zzc(e, "show overlay display from: %s", str);
            this.zzc.trySetException(new RuntimeException(e));
        }
    }
}
