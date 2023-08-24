package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzrp extends zzrh {
    private final HashMap zza = new HashMap();
    private Handler zzb;
    private zzfx zzc;

    @Override // com.google.android.gms.internal.ads.zzrh
    protected final void zzj() {
        for (zzro zzroVar : this.zza.values()) {
            zzroVar.zza.zzi(zzroVar.zzb);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzrh
    protected final void zzl() {
        for (zzro zzroVar : this.zza.values()) {
            zzroVar.zza.zzk(zzroVar.zzb);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrh
    public void zzn(zzfx zzfxVar) {
        this.zzc = zzfxVar;
        this.zzb = zzel.zzD(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrh
    public void zzq() {
        for (zzro zzroVar : this.zza.values()) {
            zzroVar.zza.zzp(zzroVar.zzb);
            zzroVar.zza.zzs(zzroVar.zzc);
            zzroVar.zza.zzr(zzroVar.zzc);
        }
        this.zza.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzsg zzv(Object obj, zzsg zzsgVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public void zzw() throws IOException {
        for (zzro zzroVar : this.zza.values()) {
            zzroVar.zza.zzw();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zzx(Object obj, zzsi zzsiVar, zzcn zzcnVar);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzy(final Object obj, zzsi zzsiVar) {
        zzdd.zzd(!this.zza.containsKey(obj));
        zzsh zzshVar = new zzsh() { // from class: com.google.android.gms.internal.ads.zzrm
            @Override // com.google.android.gms.internal.ads.zzsh
            public final void zza(zzsi zzsiVar2, zzcn zzcnVar) {
                zzrp.this.zzx(obj, zzsiVar2, zzcnVar);
            }
        };
        zzrn zzrnVar = new zzrn(this, obj);
        this.zza.put(obj, new zzro(zzsiVar, zzshVar, zzrnVar));
        Handler handler = this.zzb;
        Objects.requireNonNull(handler);
        zzsiVar.zzh(handler, zzrnVar);
        Handler handler2 = this.zzb;
        Objects.requireNonNull(handler2);
        zzsiVar.zzg(handler2, zzrnVar);
        zzsiVar.zzm(zzshVar, this.zzc, zzb());
        if (zzt()) {
            return;
        }
        zzsiVar.zzi(zzshVar);
    }
}
