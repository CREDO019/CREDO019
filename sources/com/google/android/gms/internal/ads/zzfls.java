package com.google.android.gms.internal.ads;

import android.os.AsyncTask;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfls extends AsyncTask {
    private zzflt zza;
    protected final zzflk zzd;

    public zzfls(zzflk zzflkVar, byte[] bArr) {
        this.zzd = zzflkVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: zza */
    public void onPostExecute(String str) {
        zzflt zzfltVar = this.zza;
        if (zzfltVar != null) {
            zzfltVar.zza(this);
        }
    }

    public final void zzb(zzflt zzfltVar) {
        this.zza = zzfltVar;
    }
}
