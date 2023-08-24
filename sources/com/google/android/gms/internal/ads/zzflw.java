package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzflw extends zzflr {
    public zzflw(zzflk zzflkVar, HashSet hashSet, JSONObject jSONObject, long j, byte[] bArr) {
        super(zzflkVar, hashSet, jSONObject, j, null);
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        if (zzfle.zzj(this.zzb, this.zzd.zza())) {
            return null;
        }
        this.zzd.zze(this.zzb);
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfls, android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        onPostExecute((String) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfls
    public final void zza(String str) {
        zzfko zza;
        if (!TextUtils.isEmpty(str) && (zza = zzfko.zza()) != null) {
            for (zzfkd zzfkdVar : zza.zzc()) {
                if (this.zza.contains(zzfkdVar.zzh())) {
                    zzfkdVar.zzg().zze(str, this.zzc);
                }
            }
        }
        super.onPostExecute(str);
    }
}
