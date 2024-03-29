package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@16.0.0 */
/* loaded from: classes2.dex */
abstract class zzq<T> {
    final int zza;
    final TaskCompletionSource<T> zzb = new TaskCompletionSource<>();
    final int zzc;
    final Bundle zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(int r2, int r3, Bundle bundle) {
        this.zza = r2;
        this.zzc = r3;
        this.zzd = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setResult(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzp zzpVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzpVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setException(zzpVar);
    }

    public String toString() {
        int r0 = this.zzc;
        int r1 = this.zza;
        boolean zza = zza();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(r0);
        sb.append(" id=");
        sb.append(r1);
        sb.append(" oneWay=");
        sb.append(zza);
        sb.append("}");
        return sb.toString();
    }
}
