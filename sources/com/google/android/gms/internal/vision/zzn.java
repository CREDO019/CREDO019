package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzn<T> {
    private static String PREFIX = "com.google.android.gms.vision.dynamite";
    private final String tag;
    private final String zzde;
    private final String zzdf;
    private final boolean zzdg;
    private T zzdj;
    private final Context zze;
    private final Object lock = new Object();
    private boolean zzdh = false;
    private boolean zzdi = false;

    public zzn(Context context, String str, String str2) {
        boolean z = false;
        this.zze = context;
        this.tag = str;
        String str3 = PREFIX;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 1 + String.valueOf(str2).length());
        sb.append(str3);
        sb.append(".");
        sb.append(str2);
        this.zzde = sb.toString();
        this.zzdf = str2;
        if (context != null) {
            zzbe.maybeInit(context);
            zzdg zza = zzdg.zza("barcode", Boolean.valueOf(zzkv.zzjp()), "face", Boolean.TRUE, "ica", Boolean.valueOf(zzkv.zzjq()), "ocr", Boolean.TRUE);
            if (zza.containsKey(str2) && ((Boolean) zza.get(str2)).booleanValue()) {
                z = true;
            }
        }
        this.zzdg = z;
    }

    protected abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException;

    protected abstract void zzn() throws RemoteException;

    public final boolean isOperational() {
        return zzp() != null;
    }

    public final void zzo() {
        synchronized (this.lock) {
            if (this.zzdj == null) {
                return;
            }
            try {
                zzn();
            } catch (RemoteException e) {
                Log.e(this.tag, "Could not finalize native handle", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T zzp() {
        DynamiteModule zza;
        synchronized (this.lock) {
            T t = this.zzdj;
            if (t != null) {
                return t;
            }
            try {
                zza = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzde);
            } catch (DynamiteModule.LoadingException unused) {
                Log.d(this.tag, "Cannot load feature, fall back to load dynamite module.");
                zza = zzr.zza(this.zze, this.zzdf, this.zzdg);
                if (zza == null && this.zzdg && !this.zzdh) {
                    String str = this.tag;
                    String valueOf = String.valueOf(this.zzdf);
                    Log.d(str, valueOf.length() != 0 ? "Broadcasting download intent for dependency ".concat(valueOf) : new String("Broadcasting download intent for dependency "));
                    String str2 = this.zzdf;
                    Intent intent = new Intent();
                    intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                    intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", str2);
                    intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                    this.zze.sendBroadcast(intent);
                    this.zzdh = true;
                }
            }
            if (zza != null) {
                try {
                    this.zzdj = zza(zza, this.zze);
                } catch (RemoteException | DynamiteModule.LoadingException e) {
                    Log.e(this.tag, "Error creating remote native handle", e);
                }
            }
            boolean z = this.zzdi;
            if (!z && this.zzdj == null) {
                Log.w(this.tag, "Native handle not yet available. Reverting to no-op handle.");
                this.zzdi = true;
            } else if (z && this.zzdj != null) {
                Log.w(this.tag, "Native handle is now available.");
            }
            return this.zzdj;
        }
    }
}
