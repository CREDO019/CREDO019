package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbpp {
    public static final zzbpq zza = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbow
        @Override // com.google.android.gms.internal.ads.zzbpq
        public final void zza(Object obj, Map map) {
            zzcno zzcnoVar = (zzcno) obj;
            zzbpq zzbpqVar = zzbpp.zza;
            String str = (String) map.get("urls");
            if (TextUtils.isEmpty(str)) {
                com.google.android.gms.ads.internal.util.zze.zzj("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            HashMap hashMap = new HashMap();
            PackageManager packageManager = zzcnoVar.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                boolean z = true;
                if (packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) == null) {
                    z = false;
                }
                Boolean valueOf = Boolean.valueOf(z);
                hashMap.put(str2, valueOf);
                com.google.android.gms.ads.internal.util.zze.zza("/canOpenURLs;" + str2 + ";" + valueOf);
            }
            ((zzbsi) zzcnoVar).zzd("openableURLs", hashMap);
        }
    };
    public static final zzbpq zzb = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbox
        @Override // com.google.android.gms.internal.ads.zzbpq
        public final void zza(Object obj, Map map) {
            zzcno zzcnoVar = (zzcno) obj;
            zzbpq zzbpqVar = zzbpp.zza;
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgX)).booleanValue()) {
                com.google.android.gms.ads.internal.util.zze.zzj("canOpenAppGmsgHandler disabled.");
                return;
            }
            String str = (String) map.get("package_name");
            if (TextUtils.isEmpty(str)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Package name missing in canOpenApp GMSG.");
                return;
            }
            HashMap hashMap = new HashMap();
            Boolean valueOf = Boolean.valueOf(zzcnoVar.getContext().getPackageManager().getLaunchIntentForPackage(str) != null);
            hashMap.put(str, valueOf);
            com.google.android.gms.ads.internal.util.zze.zza("/canOpenApp;" + str + ";" + valueOf);
            ((zzbsi) zzcnoVar).zzd("openableApp", hashMap);
        }
    };
    public static final zzbpq zzc = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbop
        @Override // com.google.android.gms.internal.ads.zzbpq
        public final void zza(Object obj, Map map) {
            zzbpp.zzc((zzcno) obj, map);
        }
    };
    public static final zzbpq zzd = new zzbph();
    public static final zzbpq zze = new zzbpi();
    public static final zzbpq zzf = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbov
        @Override // com.google.android.gms.internal.ads.zzbpq
        public final void zza(Object obj, Map map) {
            zzcno zzcnoVar = (zzcno) obj;
            zzbpq zzbpqVar = zzbpp.zza;
            String str = (String) map.get("u");
            if (str == null) {
                com.google.android.gms.ads.internal.util.zze.zzj("URL missing from httpTrack GMSG.");
            } else {
                new com.google.android.gms.ads.internal.util.zzby(zzcnoVar.getContext(), ((zzcnw) zzcnoVar).zzp().zza, str).zzb();
            }
        }
    };
    public static final zzbpq zzg = new zzbpj();
    public static final zzbpq zzh = new zzbpk();
    public static final zzbpq zzi = new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbou
        @Override // com.google.android.gms.internal.ads.zzbpq
        public final void zza(Object obj, Map map) {
            zzcnv zzcnvVar = (zzcnv) obj;
            zzbpq zzbpqVar = zzbpp.zza;
            String str = (String) map.get("tx");
            String str2 = (String) map.get("ty");
            String str3 = (String) map.get("td");
            try {
                int parseInt = Integer.parseInt(str);
                int parseInt2 = Integer.parseInt(str2);
                int parseInt3 = Integer.parseInt(str3);
                zzapb zzK = zzcnvVar.zzK();
                if (zzK != null) {
                    zzK.zzc().zzl(parseInt, parseInt2, parseInt3);
                }
            } catch (NumberFormatException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final zzbpq zzj = new zzbpl();
    public static final zzbpq zzk = new zzbpm();
    public static final zzbpq zzl = new zzckn();
    public static final zzbpq zzm = new zzcko();
    public static final zzbpq zzn = new zzboo();
    public static final zzbqe zzo = new zzbqe();
    public static final zzbpq zzp = new zzbpn();
    public static final zzbpq zzq = new zzbpo();
    public static final zzbpq zzr = new zzboy();
    public static final zzbpq zzs = new zzboz();
    public static final zzbpq zzt = new zzbpa();
    public static final zzbpq zzu = new zzbpb();
    public static final zzbpq zzv = new zzbpc();
    public static final zzbpq zzw = new zzbpd();
    public static final zzbpq zzx = new zzbpe();
    public static final zzbpq zzy = new zzbpf();

    public static zzbpq zza(final zzdkl zzdklVar) {
        return new zzbpq() { // from class: com.google.android.gms.internal.ads.zzbot
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzcmn zzcmnVar = (zzcmn) obj;
                zzbpp.zzd(map, zzdkl.this);
                String str = (String) map.get("u");
                if (str == null) {
                    com.google.android.gms.ads.internal.util.zze.zzj("URL missing from click GMSG.");
                } else {
                    zzfyo.zzr(zzbpp.zzb(zzcmnVar, str), new zzbpg(zzcmnVar), zzcha.zza);
                }
            }
        };
    }

    public static zzfyx zzb(zzcmn zzcmnVar, String str) {
        Uri parse = Uri.parse(str);
        try {
            zzapb zzK = zzcmnVar.zzK();
            if (zzK != null && zzK.zzf(parse)) {
                parse = zzK.zza(parse, zzcmnVar.getContext(), zzcmnVar.zzH(), zzcmnVar.zzk());
            }
        } catch (zzapc unused) {
            com.google.android.gms.ads.internal.util.zze.zzj("Unable to append parameter to URL: ".concat(str));
        }
        final String zzb2 = zzceu.zzb(parse, zzcmnVar.getContext());
        long longValue = ((Long) zzbkn.zze.zze()).longValue();
        if (longValue <= 0 || longValue > 222508000) {
            return zzfyo.zzi(zzb2);
        }
        return zzfyo.zzf(zzfyo.zzm(zzfyo.zzf(zzfyf.zzv(zzcmnVar.zzT()), Throwable.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzboq
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                Throwable th = (Throwable) obj;
                zzbpq zzbpqVar = zzbpp.zza;
                if (((Boolean) zzbkn.zzk.zze()).booleanValue()) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "prepareClickUrl.attestation1");
                    return "failure_click_attok";
                }
                return "failure_click_attok";
            }
        }, zzcha.zzf), new zzfru() { // from class: com.google.android.gms.internal.ads.zzbor
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                String str2 = zzb2;
                String str3 = (String) obj;
                zzbpq zzbpqVar = zzbpp.zza;
                if (str3 != null) {
                    if (((Boolean) zzbkn.zzf.zze()).booleanValue()) {
                        String[] strArr = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
                        String host = Uri.parse(str2).getHost();
                        for (int r3 = 0; r3 < 3; r3++) {
                            if (!host.endsWith(strArr[r3])) {
                            }
                        }
                    }
                    String str4 = (String) zzbkn.zza.zze();
                    String str5 = (String) zzbkn.zzb.zze();
                    if (!TextUtils.isEmpty(str4)) {
                        str2 = str2.replace(str4, str3);
                    }
                    if (!TextUtils.isEmpty(str5)) {
                        Uri parse2 = Uri.parse(str2);
                        if (TextUtils.isEmpty(parse2.getQueryParameter(str5))) {
                            return parse2.buildUpon().appendQueryParameter(str5, str3).toString();
                        }
                    }
                }
                return str2;
            }
        }, zzcha.zzf), Throwable.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzbos
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                String str2 = zzb2;
                Throwable th = (Throwable) obj;
                zzbpq zzbpqVar = zzbpp.zza;
                if (((Boolean) zzbkn.zzk.zze()).booleanValue()) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "prepareClickUrl.attestation2");
                }
                return str2;
            }
        }, zzcha.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(14:(3:10|11|12)|(12:50|51|15|(10:17|(1:19)|20|(1:22)|23|(1:25)|26|(1:28)|29|(2:31|(1:33)))|34|35|36|(1:38)|39|40|42|43)|14|15|(0)|34|35|36|(0)|39|40|42|43|8) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ca, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00cb, code lost:
        com.google.android.gms.ads.internal.zzt.zzp().zzt(r0, r8.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00df, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e0, code lost:
        com.google.android.gms.ads.internal.util.zze.zzh("Error constructing openable urls response.", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void zzc(com.google.android.gms.internal.ads.zzcno r16, java.util.Map r17) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbpp.zzc(com.google.android.gms.internal.ads.zzcno, java.util.Map):void");
    }

    public static void zzd(Map map, zzdkl zzdklVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzip)).booleanValue() && map.containsKey("sc") && ((String) map.get("sc")).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) && zzdklVar != null) {
            zzdklVar.zzq();
        }
    }
}
