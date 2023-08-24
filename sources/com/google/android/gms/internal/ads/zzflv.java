package com.google.android.gms.internal.ads;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzflv extends zzflr {
    public zzflv(zzflk zzflkVar, HashSet hashSet, JSONObject jSONObject, long j, byte[] bArr) {
        super(zzflkVar, hashSet, jSONObject, j, null);
    }

    private final void zzc(String str) {
        zzfko zza = zzfko.zza();
        if (zza != null) {
            for (zzfkd zzfkdVar : zza.zzc()) {
                if (this.zza.contains(zzfkdVar.zzh())) {
                    zzfkdVar.zzg().zzd(str, this.zzc);
                }
            }
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfls, android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        zzc(str);
        super.onPostExecute(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfls
    public final void zza(String str) {
        zzc(str);
        super.onPostExecute(str);
    }
}
