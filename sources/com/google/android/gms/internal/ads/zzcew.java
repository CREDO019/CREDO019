package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcew implements SharedPreferences.OnSharedPreferenceChangeListener {
    final /* synthetic */ zzcex zza;
    private final String zzb;

    public zzcew(zzcex zzcexVar, String str) {
        this.zza = zzcexVar;
        this.zzb = str;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        List<zzcev> list;
        synchronized (this.zza) {
            list = this.zza.zzb;
            for (zzcev zzcevVar : list) {
                zzcevVar.zza.zzb(zzcevVar.zzb, sharedPreferences, this.zzb, str);
            }
        }
    }
}
