package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.Base64;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfmy {
    private final Context zza;
    private final Executor zzb;
    private final zzfmf zzc;
    private final zzfmh zzd;
    private final zzfmx zze;
    private final zzfmx zzf;
    private Task zzg;
    private Task zzh;

    zzfmy(Context context, Executor executor, zzfmf zzfmfVar, zzfmh zzfmhVar, zzfmv zzfmvVar, zzfmw zzfmwVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzfmfVar;
        this.zzd = zzfmhVar;
        this.zze = zzfmvVar;
        this.zzf = zzfmwVar;
    }

    public static zzfmy zze(Context context, Executor executor, zzfmf zzfmfVar, zzfmh zzfmhVar) {
        final zzfmy zzfmyVar = new zzfmy(context, executor, zzfmfVar, zzfmhVar, new zzfmv(), new zzfmw());
        if (zzfmyVar.zzd.zzd()) {
            zzfmyVar.zzg = zzfmyVar.zzh(new Callable() { // from class: com.google.android.gms.internal.ads.zzfms
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return zzfmy.this.zzc();
                }
            });
        } else {
            zzfmyVar.zzg = Tasks.forResult(zzfmyVar.zze.zza());
        }
        zzfmyVar.zzh = zzfmyVar.zzh(new Callable() { // from class: com.google.android.gms.internal.ads.zzfmt
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzfmy.this.zzd();
            }
        });
        return zzfmyVar;
    }

    private static zzamx zzg(Task task, zzamx zzamxVar) {
        return !task.isSuccessful() ? zzamxVar : (zzamx) task.getResult();
    }

    private final Task zzh(Callable callable) {
        return Tasks.call(this.zzb, callable).addOnFailureListener(this.zzb, new OnFailureListener() { // from class: com.google.android.gms.internal.ads.zzfmu
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzfmy.this.zzf(exc);
            }
        });
    }

    public final zzamx zza() {
        return zzg(this.zzg, this.zze.zza());
    }

    public final zzamx zzb() {
        return zzg(this.zzh, this.zzf.zza());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzamx zzc() throws Exception {
        Context context = this.zza;
        zzamh zza = zzamx.zza();
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        String id = advertisingIdInfo.getId();
        if (id != null && id.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            UUID fromString = UUID.fromString(id);
            byte[] bArr = new byte[16];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.putLong(fromString.getMostSignificantBits());
            wrap.putLong(fromString.getLeastSignificantBits());
            id = Base64.encodeToString(bArr, 11);
        }
        if (id != null) {
            zza.zzr(id);
            zza.zzq(advertisingIdInfo.isLimitAdTrackingEnabled());
            zza.zzaa(6);
        }
        return (zzamx) zza.zzal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzamx zzd() throws Exception {
        Context context = this.zza;
        return zzfmn.zza(context, context.getPackageName(), Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Exception exc) {
        if (exc instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        this.zzc.zzc(2025, -1L, exc);
    }
}
