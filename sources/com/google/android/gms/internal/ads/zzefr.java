package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefr {
    private final zzbel zza;
    private final Context zzb;
    private final zzeev zzc;
    private final zzcgt zzd;
    private final String zze;
    private final zzfhz zzf;
    private final com.google.android.gms.ads.internal.util.zzg zzg = com.google.android.gms.ads.internal.zzt.zzp().zzh();

    public zzefr(Context context, zzcgt zzcgtVar, zzbel zzbelVar, zzeev zzeevVar, String str, zzfhz zzfhzVar) {
        this.zzb = context;
        this.zzd = zzcgtVar;
        this.zza = zzbelVar;
        this.zzc = zzeevVar;
        this.zze = str;
        this.zzf = zzfhzVar;
    }

    private static final void zzc(SQLiteDatabase sQLiteDatabase, ArrayList arrayList) {
        int size = arrayList.size();
        long j = 0;
        for (int r3 = 0; r3 < size; r3++) {
            zzbgu zzbguVar = (zzbgu) arrayList.get(r3);
            if (zzbguVar.zzw() == 2 && zzbguVar.zze() > j) {
                j = zzbguVar.zze();
            }
        }
        if (j != 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", Long.valueOf(j));
            sQLiteDatabase.update("offline_signal_statistics", contentValues, "statistic_name = 'last_successful_request_time'", null);
        }
    }

    public final void zzb(final boolean z) {
        try {
            this.zzc.zza(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzefn
                @Override // com.google.android.gms.internal.ads.zzfgs
                public final Object zza(Object obj) {
                    zzefr.this.zza(z, (SQLiteDatabase) obj);
                    return null;
                }
            });
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzg("Error in offline signals database startup: ".concat(String.valueOf(e.getMessage())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Void zza(boolean z, SQLiteDatabase sQLiteDatabase) throws Exception {
        if (!z) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhn)).booleanValue()) {
                ArrayList zzc = zzefk.zzc(sQLiteDatabase);
                zzbgv zza = zzbgz.zza();
                zza.zzb(this.zzb.getPackageName());
                zza.zzd(Build.MODEL);
                zza.zze(zzefk.zza(sQLiteDatabase, 0));
                zza.zza(zzc);
                zza.zzg(zzefk.zza(sQLiteDatabase, 1));
                zza.zzc(zzefk.zza(sQLiteDatabase, 3));
                zza.zzh(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis());
                zza.zzf(zzefk.zzb(sQLiteDatabase, 2));
                final zzbgz zzbgzVar = (zzbgz) zza.zzal();
                zzc(sQLiteDatabase, zzc);
                this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzefo
                    @Override // com.google.android.gms.internal.ads.zzbek
                    public final void zza(zzbga zzbgaVar) {
                        zzbgaVar.zzi(zzbgz.this);
                    }
                });
                zzbhk zza2 = zzbhl.zza();
                zza2.zza(this.zzd.zzb);
                zza2.zzc(this.zzd.zzc);
                zza2.zzb(true == this.zzd.zzd ? 0 : 2);
                final zzbhl zzbhlVar = (zzbhl) zza2.zzal();
                this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzefp
                    @Override // com.google.android.gms.internal.ads.zzbek
                    public final void zza(zzbga zzbgaVar) {
                        zzbhl zzbhlVar2 = zzbhl.this;
                        zzbfs zzbfsVar = (zzbfs) zzbgaVar.zzb().zzaz();
                        zzbfsVar.zzb(zzbhlVar2);
                        zzbgaVar.zzg(zzbfsVar);
                    }
                });
                this.zza.zzc(10004);
            } else {
                zzfhy zzb = zzfhy.zzb("oa_upload");
                zzb.zza("oa_failed_reqs", String.valueOf(zzefk.zza(sQLiteDatabase, 0)));
                zzb.zza("oa_total_reqs", String.valueOf(zzefk.zza(sQLiteDatabase, 1)));
                zzb.zza("oa_upload_time", String.valueOf(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()));
                zzb.zza("oa_last_successful_time", String.valueOf(zzefk.zzb(sQLiteDatabase, 2)));
                zzb.zza("oa_session_id", this.zzg.zzP() ? "" : this.zze);
                this.zzf.zzb(zzb);
                ArrayList zzc2 = zzefk.zzc(sQLiteDatabase);
                zzc(sQLiteDatabase, zzc2);
                int size = zzc2.size();
                for (int r2 = 0; r2 < size; r2++) {
                    zzbgu zzbguVar = (zzbgu) zzc2.get(r2);
                    zzfhy zzb2 = zzfhy.zzb("oa_signals");
                    zzb2.zza("oa_session_id", this.zzg.zzP() ? "" : this.zze);
                    zzbgp zzf = zzbguVar.zzf();
                    String valueOf = zzf.zzf() ? String.valueOf(zzf.zzh() - 1) : "-1";
                    String obj = zzfvj.zzb(zzbguVar.zzk(), new zzfru() { // from class: com.google.android.gms.internal.ads.zzefq
                        @Override // com.google.android.gms.internal.ads.zzfru
                        public final Object apply(Object obj2) {
                            return ((zzbfj) obj2).name();
                        }
                    }).toString();
                    zzb2.zza("oa_sig_ts", String.valueOf(zzbguVar.zze()));
                    zzb2.zza("oa_sig_status", String.valueOf(zzbguVar.zzw() - 1));
                    zzb2.zza("oa_sig_resp_lat", String.valueOf(zzbguVar.zzd()));
                    zzb2.zza("oa_sig_render_lat", String.valueOf(zzbguVar.zzc()));
                    zzb2.zza("oa_sig_formats", obj);
                    zzb2.zza("oa_sig_nw_type", valueOf);
                    zzb2.zza("oa_sig_wifi", String.valueOf(zzbguVar.zzx() - 1));
                    zzb2.zza("oa_sig_airplane", String.valueOf(zzbguVar.zzt() - 1));
                    zzb2.zza("oa_sig_data", String.valueOf(zzbguVar.zzu() - 1));
                    zzb2.zza("oa_sig_nw_resp", String.valueOf(zzbguVar.zza()));
                    zzb2.zza("oa_sig_offline", String.valueOf(zzbguVar.zzv() - 1));
                    zzb2.zza("oa_sig_nw_state", String.valueOf(zzbguVar.zzj().zza()));
                    if (zzf.zze() && zzf.zzf() && zzf.zzh() == 2) {
                        zzb2.zza("oa_sig_cell_type", String.valueOf(zzf.zzg() - 1));
                    }
                    this.zzf.zzb(zzb2);
                }
            }
            zzefk.zzf(sQLiteDatabase);
            return null;
        }
        this.zzb.deleteDatabase("OfflineUpload.db");
        return null;
    }
}
