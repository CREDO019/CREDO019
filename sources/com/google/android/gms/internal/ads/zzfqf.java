package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfqf extends zzfqw {
    final /* synthetic */ zzfpz zza;
    final /* synthetic */ zzfqn zzb;
    final /* synthetic */ TaskCompletionSource zzc;
    final /* synthetic */ zzfqi zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfqf(zzfqi zzfqiVar, TaskCompletionSource taskCompletionSource, zzfpz zzfpzVar, zzfqn zzfqnVar, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzd = zzfqiVar;
        this.zza = zzfpzVar;
        this.zzb = zzfqnVar;
        this.zzc = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.gms.internal.ads.zzfqs] */
    @Override // com.google.android.gms.internal.ads.zzfqw
    protected final void zza() {
        zzfqv zzfqvVar;
        String str;
        String str2;
        try {
            ?? zze = this.zzd.zza.zze();
            zzfpz zzfpzVar = this.zza;
            str2 = this.zzd.zzd;
            Bundle bundle = new Bundle();
            bundle.putString("sessionToken", zzfpzVar.zzb());
            bundle.putString("callerPackage", str2);
            bundle.putString("appId", zzfpzVar.zza());
            zze.zze(bundle, new zzfqh(this.zzd, this.zzb));
        } catch (RemoteException e) {
            zzfqvVar = zzfqi.zzb;
            str = this.zzd.zzd;
            zzfqvVar.zzc(e, "dismiss overlay display from: %s", str);
            this.zzc.trySetException(new RuntimeException(e));
        }
    }
}
