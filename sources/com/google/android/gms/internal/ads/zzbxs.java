package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.C2114R;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import java.util.Map;
import org.bouncycastle.i18n.ErrorBundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbxs extends zzbya {
    private final Map zza;
    private final Context zzb;
    private final String zzc;
    private final long zzd;
    private final long zze;
    private final String zzf;
    private final String zzg;

    public zzbxs(zzcmn zzcmnVar, Map map) {
        super(zzcmnVar, "createCalendarEvent");
        this.zza = map;
        this.zzb = zzcmnVar.zzk();
        this.zzc = zze("description");
        this.zzf = zze(ErrorBundle.SUMMARY_ENTRY);
        this.zzd = zzd("start_ticks");
        this.zze = zzd("end_ticks");
        this.zzg = zze(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID);
    }

    private final long zzd(String str) {
        String str2 = (String) this.zza.get(str);
        if (str2 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private final String zze(String str) {
        return TextUtils.isEmpty((CharSequence) this.zza.get(str)) ? "" : (String) this.zza.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Intent zzb() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.zzc);
        data.putExtra("eventLocation", this.zzg);
        data.putExtra("description", this.zzf);
        long j = this.zzd;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zze;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(268435456);
        return data;
    }

    public final void zzc() {
        if (this.zzb == null) {
            zzg("Activity context is not available.");
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        if (!new zzbii(this.zzb).zzb()) {
            zzg("This feature is not available on the device.");
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        AlertDialog.Builder zzG = com.google.android.gms.ads.internal.util.zzs.zzG(this.zzb);
        Resources zzd = com.google.android.gms.ads.internal.zzt.zzp().zzd();
        zzG.setTitle(zzd != null ? zzd.getString(C2114R.string.f260s5) : "Create calendar event");
        zzG.setMessage(zzd != null ? zzd.getString(C2114R.string.f261s6) : "Allow Ad to create a calendar event?");
        zzG.setPositiveButton(zzd != null ? zzd.getString(C2114R.string.f258s3) : "Accept", new zzbxq(this));
        zzG.setNegativeButton(zzd != null ? zzd.getString(C2114R.string.f259s4) : "Decline", new zzbxr(this));
        zzG.create().show();
    }
}
