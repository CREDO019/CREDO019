package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbuc {
    private static final Charset zzc = Charset.forName("UTF-8");
    public static final zzbtz zza = new zzbub();
    public static final zzbtx zzb = new zzbtx() { // from class: com.google.android.gms.internal.ads.zzbua
        @Override // com.google.android.gms.internal.ads.zzbtx
        public final Object zza(JSONObject jSONObject) {
            return zzbuc.zza(jSONObject);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InputStream zza(JSONObject jSONObject) throws JSONException {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(zzc));
    }
}
