package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzbj implements zzau {
    private static final Map<String, zzbj> zzgh = new ArrayMap();
    private final Object zzfm;
    private volatile Map<String, ?> zzfn;
    private final List<zzar> zzfo;
    private final SharedPreferences zzgi;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzgj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbj zzb(Context context, String str) {
        zzbj zzbjVar;
        SharedPreferences sharedPreferences;
        if ((!zzan.zzs() || str.startsWith("direct_boot:")) ? true : zzan.isUserUnlocked(context)) {
            synchronized (zzbj.class) {
                Map<String, zzbj> map = zzgh;
                zzbjVar = map.get(str);
                if (zzbjVar == null) {
                    if (str.startsWith("direct_boot:")) {
                        if (zzan.zzs()) {
                            context = context.createDeviceProtectedStorageContext();
                        }
                        sharedPreferences = context.getSharedPreferences(str.substring(12), 0);
                    } else {
                        sharedPreferences = context.getSharedPreferences(str, 0);
                    }
                    zzbjVar = new zzbj(sharedPreferences);
                    map.put(str, zzbjVar);
                }
            }
            return zzbjVar;
        }
        return null;
    }

    private zzbj(SharedPreferences sharedPreferences) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener(this) { // from class: com.google.android.gms.internal.vision.zzbm
            private final zzbj zzha;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzha = this;
            }

            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                this.zzha.zza(sharedPreferences2, str);
            }
        };
        this.zzgj = onSharedPreferenceChangeListener;
        this.zzfm = new Object();
        this.zzfo = new ArrayList();
        this.zzgi = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // com.google.android.gms.internal.vision.zzau
    public final Object zzb(String str) {
        Map<String, ?> map = this.zzfn;
        if (map == null) {
            synchronized (this.zzfm) {
                map = this.zzfn;
                if (map == null) {
                    map = this.zzgi.getAll();
                    this.zzfn = map;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zzx() {
        synchronized (zzbj.class) {
            for (zzbj zzbjVar : zzgh.values()) {
                zzbjVar.zzgi.unregisterOnSharedPreferenceChangeListener(zzbjVar.zzgj);
            }
            zzgh.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzfm) {
            this.zzfn = null;
            zzbe.zzab();
        }
        synchronized (this) {
            for (zzar zzarVar : this.zzfo) {
                zzarVar.zzz();
            }
        }
    }
}
