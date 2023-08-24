package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
abstract class zzz<T> {
    final int what;
    final int zzcp;
    final TaskCompletionSource<T> zzcq = new TaskCompletionSource<>();
    final Bundle zzcr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(int r2, int r3, Bundle bundle) {
        this.zzcp = r2;
        this.what = r3;
        this.zzcr = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzh(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzw();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(zzaa zzaaVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzaaVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcq.setException(zzaaVar);
    }

    public String toString() {
        int r0 = this.what;
        int r1 = this.zzcp;
        zzw();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(r0);
        sb.append(" id=");
        sb.append(r1);
        sb.append(" oneWay=false}");
        return sb.toString();
    }
}
