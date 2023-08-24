package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfrg {
    private static final Map zza = new HashMap();
    private final Context zzb;
    private final zzfqv zzc;
    private boolean zzh;
    private final Intent zzi;
    private ServiceConnection zzm;
    private IInterface zzn;
    private final zzfqd zzo;
    private final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    private final Object zzg = new Object();
    private final IBinder.DeathRecipient zzk = new IBinder.DeathRecipient() { // from class: com.google.android.gms.internal.ads.zzfqy
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            zzfrg.zzh(zzfrg.this);
        }
    };
    private final AtomicInteger zzl = new AtomicInteger(0);
    private final String zzd = "OverlayDisplayService";
    private final WeakReference zzj = new WeakReference(null);

    public zzfrg(Context context, zzfqv zzfqvVar, String str, Intent intent, zzfqd zzfqdVar, zzfrb zzfrbVar, byte[] bArr) {
        this.zzb = context;
        this.zzc = zzfqvVar;
        this.zzi = intent;
        this.zzo = zzfqdVar;
    }

    public static /* synthetic */ void zzh(zzfrg zzfrgVar) {
        zzfrgVar.zzc.zzd("reportBinderDeath", new Object[0]);
        zzfrb zzfrbVar = (zzfrb) zzfrgVar.zzj.get();
        if (zzfrbVar == null) {
            zzfrgVar.zzc.zzd("%s : Binder has died.", zzfrgVar.zzd);
            for (zzfqw zzfqwVar : zzfrgVar.zze) {
                zzfqwVar.zzc(zzfrgVar.zzs());
            }
            zzfrgVar.zze.clear();
        } else {
            zzfrgVar.zzc.zzd("calling onBinderDied", new Object[0]);
            zzfrbVar.zza();
        }
        zzfrgVar.zzt();
    }

    public static /* bridge */ /* synthetic */ void zzn(zzfrg zzfrgVar) {
        zzfrgVar.zzc.zzd("linkToDeath", new Object[0]);
        try {
            zzfrgVar.zzn.asBinder().linkToDeath(zzfrgVar.zzk, 0);
        } catch (RemoteException e) {
            zzfrgVar.zzc.zzc(e, "linkToDeath failed", new Object[0]);
        }
    }

    public static /* bridge */ /* synthetic */ void zzo(zzfrg zzfrgVar) {
        zzfrgVar.zzc.zzd("unlinkToDeath", new Object[0]);
        zzfrgVar.zzn.asBinder().unlinkToDeath(zzfrgVar.zzk, 0);
    }

    private final RemoteException zzs() {
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    public final void zzt() {
        synchronized (this.zzg) {
            for (TaskCompletionSource taskCompletionSource : this.zzf) {
                taskCompletionSource.trySetException(zzs());
            }
            this.zzf.clear();
        }
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            if (!map.containsKey(this.zzd)) {
                HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                handlerThread.start();
                map.put(this.zzd, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.zzd);
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzn;
    }

    public final void zzp(zzfqw zzfqwVar, final TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzg) {
            this.zzf.add(taskCompletionSource);
            taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.internal.ads.zzfqx
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    zzfrg.this.zzq(taskCompletionSource, task);
                }
            });
        }
        synchronized (this.zzg) {
            if (this.zzl.getAndIncrement() > 0) {
                this.zzc.zza("Already connected to the service.", new Object[0]);
            }
        }
        zzc().post(new zzfqz(this, zzfqwVar.zzb(), zzfqwVar));
    }

    public final /* synthetic */ void zzq(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
    }

    public final void zzr() {
        synchronized (this.zzg) {
            if (this.zzl.get() > 0 && this.zzl.decrementAndGet() > 0) {
                this.zzc.zzd("Leaving the connection open for other ongoing calls.", new Object[0]);
                return;
            }
            zzc().post(new zzfra(this));
        }
    }

    public static /* bridge */ /* synthetic */ void zzm(zzfrg zzfrgVar, zzfqw zzfqwVar) {
        if (zzfrgVar.zzn != null || zzfrgVar.zzh) {
            if (zzfrgVar.zzh) {
                zzfrgVar.zzc.zzd("Waiting to bind to the service.", new Object[0]);
                zzfrgVar.zze.add(zzfqwVar);
                return;
            }
            zzfqwVar.run();
            return;
        }
        zzfrgVar.zzc.zzd("Initiate binding to the service.", new Object[0]);
        zzfrgVar.zze.add(zzfqwVar);
        zzfrf zzfrfVar = new zzfrf(zzfrgVar, null);
        zzfrgVar.zzm = zzfrfVar;
        zzfrgVar.zzh = true;
        if (zzfrgVar.zzb.bindService(zzfrgVar.zzi, zzfrfVar, 1)) {
            return;
        }
        zzfrgVar.zzc.zzd("Failed to bind to the service.", new Object[0]);
        zzfrgVar.zzh = false;
        for (zzfqw zzfqwVar2 : zzfrgVar.zze) {
            zzfqwVar2.zzc(new zzfrh());
        }
        zzfrgVar.zze.clear();
    }
}
