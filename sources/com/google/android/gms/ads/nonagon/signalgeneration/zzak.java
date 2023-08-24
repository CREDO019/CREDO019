package com.google.android.gms.ads.nonagon.signalgeneration;

import android.util.JsonReader;
import com.google.android.gms.ads.internal.client.zzaw;
import com.google.android.gms.internal.ads.zzcba;
import com.google.android.gms.internal.ads.zzebq;
import com.google.android.gms.internal.ads.zzfxv;
import com.google.android.gms.internal.ads.zzfyo;
import com.google.android.gms.internal.ads.zzfyx;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzak implements zzfxv {
    private final Executor zza;
    private final zzebq zzb;

    public zzak(Executor executor, zzebq zzebqVar) {
        this.zza = executor;
        this.zzb = zzebqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfxv
    public final /* bridge */ /* synthetic */ zzfyx zza(Object obj) throws Exception {
        final zzcba zzcbaVar = (zzcba) obj;
        return zzfyo.zzn(this.zzb.zzb(zzcbaVar), new zzfxv() { // from class: com.google.android.gms.ads.nonagon.signalgeneration.zzaj
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj2) {
                zzcba zzcbaVar2 = zzcba.this;
                zzam zzamVar = new zzam(new JsonReader(new InputStreamReader((InputStream) obj2)));
                try {
                    zzamVar.zzb = zzaw.zzb().zzh(zzcbaVar2.zza).toString();
                } catch (JSONException unused) {
                    zzamVar.zzb = "{}";
                }
                return zzfyo.zzi(zzamVar);
            }
        }, this.zza);
    }
}
