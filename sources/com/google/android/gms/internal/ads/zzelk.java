package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzelk implements zzegk {
    private final zzegm zza;
    private final zzegs zzb;
    private final zzfhp zzc;
    private final zzfyy zzd;

    public zzelk(zzfhp zzfhpVar, zzfyy zzfyyVar, zzegm zzegmVar, zzegs zzegsVar) {
        this.zzc = zzfhpVar;
        this.zzd = zzfyyVar;
        this.zzb = zzegsVar;
        this.zza = zzegmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String zze(String str, int r3) {
        return "Error from: " + str + ", code: " + r3;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        final zzegn zzegnVar;
        Iterator it = zzfcsVar.zzu.iterator();
        while (true) {
            if (!it.hasNext()) {
                zzegnVar = null;
                break;
            }
            try {
                zzegnVar = this.zza.zza((String) it.next(), zzfcsVar.zzw);
                break;
            } catch (zzfds unused) {
            }
        }
        if (zzegnVar == null) {
            return zzfyo.zzh(new zzejm("Unable to instantiate mediation adapter class."));
        }
        zzchf zzchfVar = new zzchf();
        zzegnVar.zzc.zza(new zzelj(this, zzegnVar, zzchfVar));
        if (zzfcsVar.zzN) {
            Bundle bundle = zzfdeVar.zza.zza.zzd.zzm;
            Bundle bundle2 = bundle.getBundle(AdMobAdapter.class.getName());
            if (bundle2 == null) {
                bundle2 = new Bundle();
                bundle.putBundle(AdMobAdapter.class.getName(), bundle2);
            }
            bundle2.putBoolean("render_test_ad_label", true);
        }
        zzfhp zzfhpVar = this.zzc;
        return zzfgz.zzd(new zzfgt() { // from class: com.google.android.gms.internal.ads.zzelh
            @Override // com.google.android.gms.internal.ads.zzfgt
            public final void zza() {
                zzelk.this.zzd(zzfdeVar, zzfcsVar, zzegnVar);
            }
        }, this.zzd, zzfhj.ADAPTER_LOAD_AD_SYN, zzfhpVar).zzb(zzfhj.ADAPTER_LOAD_AD_ACK).zzd(zzchfVar).zzb(zzfhj.ADAPTER_WRAP_ADAPTER).zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzeli
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                return zzelk.this.zzc(zzfdeVar, zzfcsVar, zzegnVar, (Void) obj);
            }
        }).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        return !zzfcsVar.zzu.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar, Void r4) throws Exception {
        return this.zzb.zza(zzfdeVar, zzfcsVar, zzegnVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws Exception {
        this.zzb.zzb(zzfdeVar, zzfcsVar, zzegnVar);
    }
}
