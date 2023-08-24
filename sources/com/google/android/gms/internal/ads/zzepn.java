package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzepn implements zzeun {
    private final zzfyy zza;
    private final zzdvj zzb;
    private final zzdzq zzc;
    private final zzepq zzd;

    public zzepn(zzfyy zzfyyVar, zzdvj zzdvjVar, zzdzq zzdzqVar, zzepq zzepqVar) {
        this.zza = zzfyyVar;
        this.zzb = zzdvjVar;
        this.zzc = zzdzqVar;
        this.zzd = zzepqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (zzfsu.zzd((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbk)) || this.zzd.zzb() || !this.zzc.zzt()) {
            return zzfyo.zzi(new zzepp(new Bundle(), null));
        }
        this.zzd.zza(true);
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzepm
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzepn.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzepp zzc() throws Exception {
        List<String> asList = Arrays.asList(((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbk)).split(";"));
        Bundle bundle = new Bundle();
        for (String str : asList) {
            try {
                zzfei zzc = this.zzb.zzc(str, new JSONObject());
                zzc.zzA();
                Bundle bundle2 = new Bundle();
                try {
                    zzbxl zzf = zzc.zzf();
                    if (zzf != null) {
                        bundle2.putString("sdk_version", zzf.toString());
                    }
                } catch (zzfds unused) {
                }
                try {
                    zzbxl zze = zzc.zze();
                    if (zze != null) {
                        bundle2.putString("adapter_version", zze.toString());
                    }
                } catch (zzfds unused2) {
                }
                bundle.putBundle(str, bundle2);
            } catch (zzfds unused3) {
            }
        }
        return new zzepp(bundle, null);
    }
}
