package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefz extends SQLiteOpenHelper {
    private final Context zza;
    private final zzfyy zzb;

    public zzefz(Context context, zzfyy zzfyyVar) {
        super(context, "AdMobOfflineBufferedPings.db", (SQLiteDatabase.CursorFactory) null, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhe)).intValue());
        this.zza = context;
        this.zzb = zzfyyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void zzb(zzcgs zzcgsVar, SQLiteDatabase sQLiteDatabase) throws Exception {
        zzj(sQLiteDatabase, zzcgsVar);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(SQLiteDatabase sQLiteDatabase, String str, zzcgs zzcgsVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_state", (Integer) 1);
        sQLiteDatabase.update("offline_buffered_pings", contentValues, "gws_query_id = ?", new String[]{str});
        zzj(sQLiteDatabase, zzcgsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzi(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.delete("offline_buffered_pings", "gws_query_id = ? AND event_state = ?", new String[]{str, Integer.toString(0)});
    }

    private static void zzj(SQLiteDatabase sQLiteDatabase, zzcgs zzcgsVar) {
        sQLiteDatabase.beginTransaction();
        try {
            String[] strArr = {ImagesContract.URL};
            Cursor query = sQLiteDatabase.query("offline_buffered_pings", strArr, "event_state = 1", null, null, null, "timestamp ASC", null);
            int count = query.getCount();
            String[] strArr2 = new String[count];
            int r5 = 0;
            while (query.moveToNext()) {
                int columnIndex = query.getColumnIndex(ImagesContract.URL);
                if (columnIndex != -1) {
                    strArr2[r5] = query.getString(columnIndex);
                }
                r5++;
            }
            query.close();
            sQLiteDatabase.delete("offline_buffered_pings", "event_state = ?", new String[]{Integer.toString(1)});
            sQLiteDatabase.setTransactionSuccessful();
            for (int r4 = 0; r4 < count; r4++) {
                zzcgsVar.zza(strArr2[r4]);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE offline_buffered_pings (timestamp INTEGER PRIMARY_KEY, gws_query_id TEXT, url TEXT, event_state INTEGER)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int r2, int r3) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_buffered_pings");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int r2, int r3) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_buffered_pings");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Void zza(zzegb zzegbVar, SQLiteDatabase sQLiteDatabase) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(zzegbVar.zza));
        contentValues.put("gws_query_id", zzegbVar.zzb);
        contentValues.put(ImagesContract.URL, zzegbVar.zzc);
        contentValues.put("event_state", Integer.valueOf(zzegbVar.zzd - 1));
        sQLiteDatabase.insert("offline_buffered_pings", null, contentValues);
        com.google.android.gms.ads.internal.zzt.zzq();
        com.google.android.gms.ads.internal.util.zzbr zzw = com.google.android.gms.ads.internal.util.zzs.zzw(this.zza);
        if (zzw != null) {
            try {
                zzw.zze(ObjectWrapper.wrap(this.zza));
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzb("Failed to schedule offline ping sender.", e);
            }
        }
        return null;
    }

    public final void zzc(final String str) {
        zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzefw
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                zzefz.zzi((SQLiteDatabase) obj, str);
                return null;
            }
        });
    }

    public final void zzd(final zzegb zzegbVar) {
        zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzefu
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                zzefz.this.zza(zzegbVar, (SQLiteDatabase) obj);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(zzfgs zzfgsVar) {
        zzfyo.zzr(this.zzb.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzefs
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzefz.this.getWritableDatabase();
            }
        }), new zzefy(this, zzfgsVar), this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(final SQLiteDatabase sQLiteDatabase, final zzcgs zzcgsVar, final String str) {
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeft
            @Override // java.lang.Runnable
            public final void run() {
                zzefz.zzf(sQLiteDatabase, str, zzcgsVar);
            }
        });
    }

    public final void zzh(final zzcgs zzcgsVar, final String str) {
        zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzefx
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                zzefz.this.zzg((SQLiteDatabase) obj, zzcgsVar, str);
                return null;
            }
        });
    }
}
