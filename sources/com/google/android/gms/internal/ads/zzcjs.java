package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcjs implements zzazi {
    private final zzazx zza;
    private final Context zzb;
    private final zzazi zzc;
    private final String zzd;
    private final int zze;
    private InputStream zzg;
    private boolean zzh;
    private Uri zzi;
    private volatile zzbdx zzj;
    private final zzcka zzr;
    private boolean zzk = false;
    private boolean zzl = false;
    private boolean zzm = false;
    private boolean zzn = false;
    private long zzo = 0;
    private final AtomicLong zzq = new AtomicLong(-1);
    private zzfyx zzp = null;
    private final boolean zzf = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue();

    public zzcjs(Context context, zzazi zzaziVar, String str, int r4, zzazx zzazxVar, zzcka zzckaVar, byte[] bArr) {
        this.zzb = context;
        this.zzc = zzaziVar;
        this.zza = zzazxVar;
        this.zzr = zzckaVar;
        this.zzd = str;
        this.zze = r4;
    }

    private final void zzl(zzazk zzazkVar) {
        zzazx zzazxVar = this.zza;
        if (zzazxVar != null) {
            ((zzcke) zzazxVar).zzk(this, zzazkVar);
        }
    }

    private final boolean zzm() {
        if (this.zzf) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdu)).booleanValue() || this.zzm) {
                return ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdv)).booleanValue() && !this.zzn;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final int zza(byte[] bArr, int r3, int r4) throws IOException {
        int zza;
        zzazx zzazxVar;
        if (!this.zzh) {
            throw new IOException("Attempt to read closed CacheDataSource.");
        }
        InputStream inputStream = this.zzg;
        if (inputStream != null) {
            zza = inputStream.read(bArr, r3, r4);
        } else {
            zza = this.zzc.zza(bArr, r3, r4);
        }
        if ((!this.zzf || this.zzg != null) && (zzazxVar = this.zza) != null) {
            ((zzcke) zzazxVar).zzW(this, zza);
        }
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01e8  */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.StringBuilder] */
    @Override // com.google.android.gms.internal.ads.zzazi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzb(com.google.android.gms.internal.ads.zzazk r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 527
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcjs.zzb(com.google.android.gms.internal.ads.zzazk):long");
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final Uri zzc() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final void zzd() throws IOException {
        if (!this.zzh) {
            throw new IOException("Attempt to close an already closed CacheDataSource.");
        }
        this.zzh = false;
        this.zzi = null;
        InputStream inputStream = this.zzg;
        if (inputStream != null) {
            IOUtils.closeQuietly(inputStream);
            this.zzg = null;
            return;
        }
        this.zzc.zzd();
    }

    public final long zze() {
        return this.zzo;
    }

    public final long zzf() {
        if (this.zzj == null) {
            return -1L;
        }
        if (this.zzq.get() != -1) {
            return this.zzq.get();
        }
        synchronized (this) {
            if (this.zzp == null) {
                this.zzp = zzcha.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzcjr
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return zzcjs.this.zzg();
                    }
                });
            }
        }
        if (this.zzp.isDone()) {
            try {
                this.zzq.compareAndSet(-1L, ((Long) this.zzp.get()).longValue());
                return this.zzq.get();
            } catch (InterruptedException | ExecutionException unused) {
                return -1L;
            }
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Long zzg() throws Exception {
        return Long.valueOf(com.google.android.gms.ads.internal.zzt.zzc().zza(this.zzj));
    }

    public final boolean zzh() {
        return this.zzk;
    }

    public final boolean zzi() {
        return this.zzn;
    }

    public final boolean zzj() {
        return this.zzm;
    }

    public final boolean zzk() {
        return this.zzl;
    }
}
