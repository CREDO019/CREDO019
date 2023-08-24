package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzaq implements zzau {
    private static final Map<Uri, zzaq> zzfj = new ArrayMap();
    private static final String[] zzfp = {"key", "value"};
    private final Uri uri;
    private final ContentResolver zzfk;
    private final ContentObserver zzfl;
    private final Object zzfm;
    private volatile Map<String, String> zzfn;
    private final List<zzar> zzfo;

    private zzaq(ContentResolver contentResolver, Uri uri) {
        zzas zzasVar = new zzas(this, null);
        this.zzfl = zzasVar;
        this.zzfm = new Object();
        this.zzfo = new ArrayList();
        this.zzfk = contentResolver;
        this.uri = uri;
        contentResolver.registerContentObserver(uri, false, zzasVar);
    }

    public static zzaq zza(ContentResolver contentResolver, Uri uri) {
        zzaq zzaqVar;
        synchronized (zzaq.class) {
            Map<Uri, zzaq> map = zzfj;
            zzaqVar = map.get(uri);
            if (zzaqVar == null) {
                try {
                    zzaq zzaqVar2 = new zzaq(contentResolver, uri);
                    try {
                        map.put(uri, zzaqVar2);
                    } catch (SecurityException unused) {
                    }
                    zzaqVar = zzaqVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzaqVar;
    }

    private final Map<String, String> zzu() {
        Map<String, String> map = this.zzfn;
        if (map == null) {
            synchronized (this.zzfm) {
                map = this.zzfn;
                if (map == null) {
                    map = zzw();
                    this.zzfn = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public final void zzv() {
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

    private final Map<String, String> zzw() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzat.zza(new zzaw(this) { // from class: com.google.android.gms.internal.vision.zzap
                    private final zzaq zzfi;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zzfi = this;
                    }

                    @Override // com.google.android.gms.internal.vision.zzaw
                    public final Object zzt() {
                        return this.zzfi.zzy();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zzx() {
        synchronized (zzaq.class) {
            for (zzaq zzaqVar : zzfj.values()) {
                zzaqVar.zzfk.unregisterContentObserver(zzaqVar.zzfl);
            }
            zzfj.clear();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzau
    public final /* synthetic */ Object zzb(String str) {
        return zzu().get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map zzy() {
        Map hashMap;
        Cursor query = this.zzfk.query(this.uri, zzfp, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                hashMap = new ArrayMap(count);
            } else {
                hashMap = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                hashMap.put(query.getString(0), query.getString(1));
            }
            return hashMap;
        } finally {
            query.close();
        }
    }
}
