package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefk {
    public static int zza(SQLiteDatabase sQLiteDatabase, int r3) {
        int r0 = 0;
        if (r3 == 2) {
            return 0;
        }
        Cursor zzh = zzh(sQLiteDatabase, r3);
        if (zzh.getCount() > 0) {
            zzh.moveToNext();
            r0 = zzh.getInt(zzh.getColumnIndexOrThrow("value"));
        }
        zzh.close();
        return r0;
    }

    public static long zzb(SQLiteDatabase sQLiteDatabase, int r3) {
        long j;
        Cursor zzh = zzh(sQLiteDatabase, 2);
        if (zzh.getCount() > 0) {
            zzh.moveToNext();
            j = zzh.getLong(zzh.getColumnIndexOrThrow("value"));
        } else {
            j = 0;
        }
        zzh.close();
        return j;
    }

    public static ArrayList zzc(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query("offline_signal_contents", new String[]{"serialized_proto_data"}, null, null, null, null, null);
        while (query.moveToNext()) {
            try {
                arrayList.add(zzbgu.zzi(query.getBlob(query.getColumnIndexOrThrow("serialized_proto_data"))));
            } catch (zzgoz e) {
                com.google.android.gms.ads.internal.util.zze.zzg("Unable to deserialize proto from offline signals database:");
                com.google.android.gms.ads.internal.util.zze.zzg(e.getMessage());
            }
        }
        query.close();
        return arrayList;
    }

    public static void zzd(SQLiteDatabase sQLiteDatabase, long j, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(j));
        contentValues.put("serialized_proto_data", bArr);
        if (sQLiteDatabase.update("offline_signal_contents", contentValues, "timestamp = ?", new String[]{String.valueOf(j)}) == 0) {
            sQLiteDatabase.insert("offline_signal_contents", null, contentValues);
        }
    }

    public static void zze(SQLiteDatabase sQLiteDatabase) {
        zzi(sQLiteDatabase, "failed_requests", 0);
        zzi(sQLiteDatabase, "total_requests", 0);
        zzi(sQLiteDatabase, "completed_requests", 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put("statistic_name", "last_successful_request_time");
        contentValues.put("value", (Long) 0L);
        sQLiteDatabase.insert("offline_signal_statistics", null, contentValues);
    }

    public static void zzf(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("offline_signal_contents", null, null);
        zzj(sQLiteDatabase, "failed_requests", 0);
        zzj(sQLiteDatabase, "total_requests", 0);
        zzj(sQLiteDatabase, "completed_requests", 0);
    }

    public static void zzg(SQLiteDatabase sQLiteDatabase, boolean z, boolean z2) {
        if (!z2) {
            sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", "total_requests"));
            return;
        }
        sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", "completed_requests"));
        if (z) {
            return;
        }
        sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", "failed_requests"));
    }

    private static Cursor zzh(SQLiteDatabase sQLiteDatabase, int r10) {
        String[] strArr = {"value"};
        String[] strArr2 = new String[1];
        if (r10 == 0) {
            strArr2[0] = "failed_requests";
        } else if (r10 == 1) {
            strArr2[0] = "total_requests";
        } else if (r10 != 2) {
            strArr2[0] = "completed_requests";
        } else {
            strArr2[0] = "last_successful_request_time";
        }
        return sQLiteDatabase.query("offline_signal_statistics", strArr, "statistic_name = ?", strArr2, null, null, null);
    }

    private static void zzi(SQLiteDatabase sQLiteDatabase, String str, int r3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("statistic_name", str);
        contentValues.put("value", (Integer) 0);
        sQLiteDatabase.insert("offline_signal_statistics", null, contentValues);
    }

    private static void zzj(SQLiteDatabase sQLiteDatabase, String str, int r5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", (Integer) 0);
        sQLiteDatabase.update("offline_signal_statistics", contentValues, "statistic_name = ?", new String[]{str});
    }
}
