package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfqg extends zzfqw {
    final /* synthetic */ zzfqp zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzfqn zzc;
    final /* synthetic */ TaskCompletionSource zzd;
    final /* synthetic */ zzfqi zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfqg(zzfqi zzfqiVar, TaskCompletionSource taskCompletionSource, zzfqp zzfqpVar, int r4, zzfqn zzfqnVar, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zze = zzfqiVar;
        this.zza = zzfqpVar;
        this.zzb = r4;
        this.zzc = zzfqnVar;
        this.zzd = taskCompletionSource2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.gms.internal.ads.zzfqs] */
    @Override // com.google.android.gms.internal.ads.zzfqw
    protected final void zza() {
        zzfqv zzfqvVar;
        String str;
        String str2;
        try {
            ?? zze = this.zze.zza.zze();
            zzfqp zzfqpVar = this.zza;
            str2 = this.zze.zzd;
            int r3 = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("sessionToken", zzfqpVar.zzb());
            bundle.putInt("displayMode", r3);
            bundle.putString("callerPackage", str2);
            bundle.putString("appId", zzfqpVar.zza());
            zze.zzg(bundle, new zzfqh(this.zze, this.zzc));
        } catch (RemoteException e) {
            zzfqvVar = zzfqi.zzb;
            str = this.zze.zzd;
            zzfqvVar.zzc(e, "switchDisplayMode overlay display to %d from: %s", Integer.valueOf(this.zzb), str);
            this.zzd.trySetException(new RuntimeException(e));
        }
    }
}
