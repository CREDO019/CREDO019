package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcex {
    private final Map zza = new HashMap();
    private final List zzb = new ArrayList();
    private final Context zzc;
    private final zzcdu zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcex(Context context, zzcdu zzcduVar) {
        this.zzc = context;
        this.zzd = zzcduVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Map map, SharedPreferences sharedPreferences, String str, String str2) {
        if (map.containsKey(str) && ((Set) map.get(str)).contains(str2)) {
            this.zzd.zze();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc(String str) {
        SharedPreferences sharedPreferences;
        if (this.zza.containsKey(str)) {
            return;
        }
        if ("__default__".equals(str)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.zzc);
        } else {
            sharedPreferences = this.zzc.getSharedPreferences(str, 0);
        }
        zzcew zzcewVar = new zzcew(this, str);
        this.zza.put(str, zzcewVar);
        sharedPreferences.registerOnSharedPreferenceChangeListener(zzcewVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzd(zzcev zzcevVar) {
        this.zzb.add(zzcevVar);
    }
}
