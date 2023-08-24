package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.ads.query.QueryInfo;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.ads.zzcgg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzp {
    public static final zzp zza = new zzp();

    protected zzp() {
    }

    public final zzl zza(Context context, zzdr zzdrVar) {
        Context context2;
        List list;
        zzc zzcVar;
        String str;
        Date zzo = zzdrVar.zzo();
        long time = zzo != null ? zzo.getTime() : -1L;
        String zzl = zzdrVar.zzl();
        int zza2 = zzdrVar.zza();
        Set zzr = zzdrVar.zzr();
        if (zzr.isEmpty()) {
            context2 = context;
            list = null;
        } else {
            list = Collections.unmodifiableList(new ArrayList(zzr));
            context2 = context;
        }
        boolean zzt = zzdrVar.zzt(context2);
        Bundle zzf = zzdrVar.zzf(AdMobAdapter.class);
        AdInfo zzi = zzdrVar.zzi();
        if (zzi != null) {
            QueryInfo queryInfo = zzi.getQueryInfo();
            zzcVar = new zzc(zzdrVar.zzi().getAdString(), queryInfo != null ? queryInfo.zza().zzc() : "");
        } else {
            zzcVar = null;
        }
        String zzm = zzdrVar.zzm();
        SearchAdRequest zzj = zzdrVar.zzj();
        zzfb zzfbVar = zzj != null ? new zzfb(zzj) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzaw.zzb();
            str = zzcgg.zzp(Thread.currentThread().getStackTrace(), packageName);
        } else {
            str = null;
        }
        boolean zzs = zzdrVar.zzs();
        RequestConfiguration zzc = zzed.zzf().zzc();
        return new zzl(8, time, zzf, zza2, list, zzt, Math.max(zzdrVar.zzc(), zzc.getTagForChildDirectedTreatment()), false, zzm, zzfbVar, null, zzl, zzdrVar.zzg(), zzdrVar.zze(), Collections.unmodifiableList(new ArrayList(zzdrVar.zzq())), zzdrVar.zzn(), str, zzs, zzcVar, Math.max(-1, zzc.getTagForUnderAgeOfConsent()), (String) Collections.max(Arrays.asList(null, zzc.getMaxAdContentRating()), new Comparator() { // from class: com.google.android.gms.ads.internal.client.zzo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return RequestConfiguration.zza.indexOf((String) obj) - RequestConfiguration.zza.indexOf((String) obj2);
            }
        }), zzdrVar.zzp(), zzdrVar.zzb(), zzdrVar.zzk());
    }
}
