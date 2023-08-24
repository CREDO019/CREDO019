package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbiz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static List zza() {
        ArrayList arrayList = new ArrayList();
        zzc(arrayList, zzbka.zzc("gad:dynamite_module:experiment_id", ""));
        zzc(arrayList, zzbkl.zza);
        zzc(arrayList, zzbkl.zzb);
        zzc(arrayList, zzbkl.zzc);
        zzc(arrayList, zzbkl.zzd);
        zzc(arrayList, zzbkl.zze);
        zzc(arrayList, zzbkl.zzu);
        zzc(arrayList, zzbkl.zzf);
        zzc(arrayList, zzbkl.zzm);
        zzc(arrayList, zzbkl.zzn);
        zzc(arrayList, zzbkl.zzo);
        zzc(arrayList, zzbkl.zzp);
        zzc(arrayList, zzbkl.zzq);
        zzc(arrayList, zzbkl.zzr);
        zzc(arrayList, zzbkl.zzs);
        zzc(arrayList, zzbkl.zzt);
        zzc(arrayList, zzbkl.zzg);
        zzc(arrayList, zzbkl.zzh);
        zzc(arrayList, zzbkl.zzi);
        zzc(arrayList, zzbkl.zzj);
        zzc(arrayList, zzbkl.zzk);
        zzc(arrayList, zzbkl.zzl);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List zzb() {
        ArrayList arrayList = new ArrayList();
        zzc(arrayList, zzbky.zza);
        return arrayList;
    }

    private static void zzc(List list, zzbka zzbkaVar) {
        String str = (String) zzbkaVar.zze();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        list.add(str);
    }
}
