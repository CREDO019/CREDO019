package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzaka implements Comparable {
    private final zzakl zza;
    private final int zzb;
    private final String zzc;
    private final int zzd;
    private final Object zze;
    private final zzake zzf;
    private Integer zzg;
    private zzakd zzh;
    private boolean zzi;
    private zzajj zzj;
    private zzajz zzk;
    private final zzajo zzl;

    public zzaka(int r3, String str, zzake zzakeVar) {
        Uri parse;
        String host;
        this.zza = zzakl.zza ? new zzakl() : null;
        this.zze = new Object();
        int r0 = 0;
        this.zzi = false;
        this.zzj = null;
        this.zzb = r3;
        this.zzc = str;
        this.zzf = zzakeVar;
        this.zzl = new zzajo();
        if (!TextUtils.isEmpty(str) && (parse = Uri.parse(str)) != null && (host = parse.getHost()) != null) {
            r0 = host.hashCode();
        }
        this.zzd = r0;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.zzg.intValue() - ((zzaka) obj).zzg.intValue();
    }

    public final String toString() {
        String hexString = Integer.toHexString(this.zzd);
        zzw();
        String str = this.zzc;
        Integer num = this.zzg;
        return "[ ] " + str + " " + "0x".concat(String.valueOf(hexString)) + " NORMAL " + num;
    }

    public final int zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzl.zzb();
    }

    public final int zzc() {
        return this.zzd;
    }

    public final zzajj zzd() {
        return this.zzj;
    }

    public final zzaka zze(zzajj zzajjVar) {
        this.zzj = zzajjVar;
        return this;
    }

    public final zzaka zzf(zzakd zzakdVar) {
        this.zzh = zzakdVar;
        return this;
    }

    public final zzaka zzg(int r1) {
        this.zzg = Integer.valueOf(r1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract zzakg zzh(zzajw zzajwVar);

    public final String zzj() {
        String str = this.zzc;
        if (this.zzb != 0) {
            String num = Integer.toString(1);
            return num + "-" + str;
        }
        return str;
    }

    public final String zzk() {
        return this.zzc;
    }

    public Map zzl() throws zzaji {
        return Collections.emptyMap();
    }

    public final void zzm(String str) {
        if (zzakl.zza) {
            this.zza.zza(str, Thread.currentThread().getId());
        }
    }

    public final void zzn(zzakj zzakjVar) {
        zzake zzakeVar;
        synchronized (this.zze) {
            zzakeVar = this.zzf;
        }
        if (zzakeVar != null) {
            zzakeVar.zza(zzakjVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zzo(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzp(String str) {
        zzakd zzakdVar = this.zzh;
        if (zzakdVar != null) {
            zzakdVar.zzb(this);
        }
        if (zzakl.zza) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzajy(this, str, id));
                return;
            }
            this.zza.zza(str, id);
            this.zza.zzb(toString());
        }
    }

    public final void zzq() {
        synchronized (this.zze) {
            this.zzi = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzr() {
        zzajz zzajzVar;
        synchronized (this.zze) {
            zzajzVar = this.zzk;
        }
        if (zzajzVar != null) {
            zzajzVar.zza(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzs(zzakg zzakgVar) {
        zzajz zzajzVar;
        synchronized (this.zze) {
            zzajzVar = this.zzk;
        }
        if (zzajzVar != null) {
            zzajzVar.zzb(this, zzakgVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzt(int r2) {
        zzakd zzakdVar = this.zzh;
        if (zzakdVar != null) {
            zzakdVar.zzc(this, r2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzu(zzajz zzajzVar) {
        synchronized (this.zze) {
            this.zzk = zzajzVar;
        }
    }

    public final boolean zzv() {
        boolean z;
        synchronized (this.zze) {
            z = this.zzi;
        }
        return z;
    }

    public final boolean zzw() {
        synchronized (this.zze) {
        }
        return false;
    }

    public byte[] zzx() throws zzaji {
        return null;
    }

    public final zzajo zzy() {
        return this.zzl;
    }
}
