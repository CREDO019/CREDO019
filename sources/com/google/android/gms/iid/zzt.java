package com.google.android.gms.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzt implements ServiceConnection {
    int state;
    final Messenger zzch;
    zzy zzci;
    final Queue<zzz<?>> zzcj;
    final SparseArray<zzz<?>> zzck;
    final /* synthetic */ zzr zzcl;

    private zzt(zzr zzrVar) {
        this.zzcl = zzrVar;
        this.state = 0;
        this.zzch = new Messenger(new com.google.android.gms.internal.gcm.zzj(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.iid.zzu
            private final zzt zzcm;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcm = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzcm.zzd(message);
            }
        }));
        this.zzcj = new ArrayDeque();
        this.zzck = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zze(zzz zzzVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        int r0 = this.state;
        if (r0 == 0) {
            this.zzcj.add(zzzVar);
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzcl.zzl;
            if (!connectionTracker.bindService(context, intent, this, 1)) {
                zzd(0, "Unable to bind to service");
            } else {
                scheduledExecutorService = this.zzcl.zzce;
                scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.android.gms.iid.zzv
                    private final zzt zzcm;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzcm = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcm.zzv();
                    }
                }, 30L, TimeUnit.SECONDS);
            }
            return true;
        } else if (r0 == 1) {
            this.zzcj.add(zzzVar);
            return true;
        } else if (r0 == 2) {
            this.zzcj.add(zzzVar);
            zzt();
            return true;
        } else {
            if (r0 != 3 && r0 != 4) {
                int r02 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(r02);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzd(Message message) {
        int r0 = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(r0);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzz<?> zzzVar = this.zzck.get(r0);
            if (zzzVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(r0);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zzck.remove(r0);
            zzu();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzzVar.zzd(new zzaa(4, "Not supported by GmsCore"));
            } else {
                zzzVar.zzh(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zzd(0, "Null service connection");
            return;
        }
        try {
            this.zzci = new zzy(iBinder);
            this.state = 2;
            zzt();
        } catch (RemoteException e) {
            zzd(0, e.getMessage());
        }
    }

    private final void zzt() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.zzcl.zzce;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.android.gms.iid.zzw
            private final zzt zzcm;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzcm = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final zzt zztVar = this.zzcm;
                while (true) {
                    synchronized (zztVar) {
                        if (zztVar.state != 2) {
                            return;
                        }
                        if (zztVar.zzcj.isEmpty()) {
                            zztVar.zzu();
                            return;
                        }
                        final zzz<?> poll = zztVar.zzcj.poll();
                        zztVar.zzck.put(poll.zzcp, poll);
                        scheduledExecutorService2 = zztVar.zzcl.zzce;
                        scheduledExecutorService2.schedule(new Runnable(zztVar, poll) { // from class: com.google.android.gms.iid.zzx
                            private final zzt zzcm;
                            private final zzz zzcn;

                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                this.zzcm = zztVar;
                                this.zzcn = poll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcm.zzg(this.zzcn.zzcp);
                            }
                        }, 30L, TimeUnit.SECONDS);
                        if (Log.isLoggable("MessengerIpcClient", 3)) {
                            String valueOf = String.valueOf(poll);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
                            sb.append("Sending ");
                            sb.append(valueOf);
                            Log.d("MessengerIpcClient", sb.toString());
                        }
                        context = zztVar.zzcl.zzl;
                        Messenger messenger = zztVar.zzch;
                        Message obtain = Message.obtain();
                        obtain.what = poll.what;
                        obtain.arg1 = poll.zzcp;
                        obtain.replyTo = messenger;
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("oneWay", poll.zzw());
                        bundle.putString("pkg", context.getPackageName());
                        bundle.putBundle("data", poll.zzcr);
                        obtain.setData(bundle);
                        try {
                            zzy zzyVar = zztVar.zzci;
                            if (zzyVar.zzad != null) {
                                zzyVar.zzad.send(obtain);
                            } else if (zzyVar.zzco != null) {
                                zzyVar.zzco.send(obtain);
                            } else {
                                throw new IllegalStateException("Both messengers are null");
                                break;
                            }
                        } catch (RemoteException e) {
                            zztVar.zzd(2, e.getMessage());
                        }
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzd(2, "Service disconnected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd(int r6, String str) {
        Context context;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        int r0 = this.state;
        if (r0 == 0) {
            throw new IllegalStateException();
        }
        if (r0 != 1 && r0 != 2) {
            if (r0 == 3) {
                this.state = 4;
                return;
            } else if (r0 == 4) {
                return;
            } else {
                int r7 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(r7);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.state = 4;
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        context = this.zzcl.zzl;
        connectionTracker.unbindService(context, this);
        zzaa zzaaVar = new zzaa(r6, str);
        for (zzz<?> zzzVar : this.zzcj) {
            zzzVar.zzd(zzaaVar);
        }
        this.zzcj.clear();
        for (int r62 = 0; r62 < this.zzck.size(); r62++) {
            this.zzck.valueAt(r62).zzd(zzaaVar);
        }
        this.zzck.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzu() {
        Context context;
        if (this.state == 2 && this.zzcj.isEmpty() && this.zzck.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
            context = this.zzcl.zzl;
            connectionTracker.unbindService(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzv() {
        if (this.state == 1) {
            zzd(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzg(int r5) {
        zzz<?> zzzVar = this.zzck.get(r5);
        if (zzzVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(r5);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzck.remove(r5);
            zzzVar.zzd(new zzaa(3, "Timed out waiting for response"));
            zzu();
        }
    }
}
