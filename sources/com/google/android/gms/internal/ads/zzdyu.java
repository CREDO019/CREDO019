package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import com.google.android.gms.ads.RequestConfiguration;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyu extends zzbqo {
    private final zzdyx zza;
    private final zzdys zzb;
    private final Map zzc = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdyu(zzdyx zzdyxVar, zzdys zzdysVar) {
        this.zza = zzdyxVar;
        this.zzb = zzdysVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static com.google.android.gms.ads.internal.client.zzl zzc(Map map) {
        char c;
        com.google.android.gms.ads.internal.client.zzm zzmVar = new com.google.android.gms.ads.internal.client.zzm();
        String str = (String) map.get("ad_request");
        if (str == null) {
            return zzmVar.zza();
        }
        JsonReader jsonReader = new JsonReader(new StringReader(Uri.decode(str)));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                switch (nextName.hashCode()) {
                    case -1289032093:
                        if (nextName.equals("extras")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -839117230:
                        if (nextName.equals("isTestDevice")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -733436947:
                        if (nextName.equals("tagForUnderAgeOfConsent")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -99890337:
                        if (nextName.equals("httpTimeoutMillis")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 523149226:
                        if (nextName.equals("keywords")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 597632527:
                        if (nextName.equals("maxAdContentRating")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1411582723:
                        if (nextName.equals("tagForChildDirectedTreatment")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        jsonReader.beginObject();
                        Bundle bundle = new Bundle();
                        while (jsonReader.hasNext()) {
                            bundle.putString(jsonReader.nextName(), jsonReader.nextString());
                        }
                        jsonReader.endObject();
                        zzmVar.zzb(bundle);
                        break;
                    case 1:
                        jsonReader.beginArray();
                        ArrayList arrayList = new ArrayList();
                        while (jsonReader.hasNext()) {
                            arrayList.add(jsonReader.nextString());
                        }
                        jsonReader.endArray();
                        zzmVar.zze(arrayList);
                        break;
                    case 2:
                        zzmVar.zzd(jsonReader.nextBoolean());
                        break;
                    case 3:
                        if (jsonReader.nextBoolean()) {
                            zzmVar.zzg(1);
                            break;
                        } else {
                            zzmVar.zzg(0);
                            break;
                        }
                    case 4:
                        if (jsonReader.nextBoolean()) {
                            zzmVar.zzh(1);
                            break;
                        } else {
                            zzmVar.zzh(0);
                            break;
                        }
                    case 5:
                        String nextString = jsonReader.nextString();
                        if (!RequestConfiguration.zza.contains(nextString)) {
                            break;
                        } else {
                            zzmVar.zzf(nextString);
                            break;
                        }
                    case 6:
                        zzmVar.zzc(jsonReader.nextInt());
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
            }
            jsonReader.endObject();
        } catch (IOException unused) {
            com.google.android.gms.ads.internal.util.zze.zze("Ad Request json was malformed, parsing ended early.");
        }
        com.google.android.gms.ads.internal.client.zzl zza = zzmVar.zza();
        Bundle bundle2 = zza.zzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle2 == null) {
            bundle2 = zza.zzc;
            zza.zzm.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle2);
        }
        return new com.google.android.gms.ads.internal.client.zzl(zza.zza, zza.zzb, bundle2, zza.zzd, zza.zze, zza.zzf, zza.zzg, zza.zzh, zza.zzi, zza.zzj, zza.zzk, zza.zzl, zza.zzm, zza.zzn, zza.zzo, zza.zzp, zza.zzq, zza.zzr, zza.zzs, zza.zzt, zza.zzu, zza.zzv, zza.zzw, zza.zzx);
    }

    @Override // com.google.android.gms.internal.ads.zzbqp
    public final void zze() {
        this.zzc.clear();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a5, code lost:
        if (r0.equals("create_interstitial_ad") != false) goto L26;
     */
    @Override // com.google.android.gms.internal.ads.zzbqp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzf(java.lang.String r12) throws android.os.RemoteException {
        /*
            Method dump skipped, instructions count: 764
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdyu.zzf(java.lang.String):void");
    }
}
