package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.internal.ads.zzbrr;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzec extends zzbrr {
    final /* synthetic */ zzed zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzec(zzed zzedVar, zzeb zzebVar) {
        this.zza = zzedVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbrs
    public final void zzb(List list) throws RemoteException {
        int r2;
        ArrayList arrayList;
        synchronized (zzed.zzg(this.zza)) {
            zzed.zzk(this.zza, false);
            zzed.zzj(this.zza, true);
            arrayList = new ArrayList(zzed.zzi(this.zza));
            zzed.zzi(this.zza).clear();
        }
        InitializationStatus zzd = zzed.zzd(list);
        int size = arrayList.size();
        for (r2 = 0; r2 < size; r2++) {
            ((OnInitializationCompleteListener) arrayList.get(r2)).onInitializationComplete(zzd);
        }
    }
}
