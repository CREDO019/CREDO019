package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdq implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Context zza;
    private final SharedPreferences zzb;
    private final com.google.android.gms.ads.internal.util.zzg zzc;
    private final zzces zzd;
    private String zze = "-1";
    private int zzf = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcdq(Context context, com.google.android.gms.ads.internal.util.zzg zzgVar, zzces zzcesVar) {
        this.zzb = PreferenceManager.getDefaultSharedPreferences(context);
        this.zzc = zzgVar;
        this.zza = context;
        this.zzd = zzcesVar;
    }

    private final void zzb(String str, int r6) {
        Context context;
        boolean z = false;
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzat)).booleanValue() ? str.isEmpty() || str.charAt(0) != '1' : r6 == 0 || str.isEmpty() || (str.charAt(0) != '1' && !str.equals("-1"))) {
            z = true;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzar)).booleanValue()) {
            this.zzc.zzH(z);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue() && z && (context = this.zza) != null) {
                context.deleteDatabase("OfflineUpload.db");
            }
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzam)).booleanValue()) {
            this.zzd.zzt();
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        char c;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzav)).booleanValue()) {
            if (zzcdp.zza(str, "gad_has_consent_for_cookies")) {
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzat)).booleanValue()) {
                    int r9 = sharedPreferences.getInt("gad_has_consent_for_cookies", -1);
                    if (r9 != this.zzc.zzb()) {
                        this.zzc.zzH(true);
                    }
                    this.zzc.zzE(r9);
                    return;
                }
                return;
            } else if (zzcdp.zza(str, "IABTCF_gdprApplies") || zzcdp.zza(str, "IABTCF_TCString") || zzcdp.zza(str, "IABTCF_PurposeConsents")) {
                String string = sharedPreferences.getString(str, "-1");
                if (string != null && !string.equals(this.zzc.zzn(str))) {
                    this.zzc.zzH(true);
                }
                this.zzc.zzF(str, string);
                return;
            } else {
                return;
            }
        }
        String string2 = sharedPreferences.getString("IABTCF_PurposeConsents", "-1");
        int r92 = sharedPreferences.getInt("gad_has_consent_for_cookies", -1);
        String valueOf = String.valueOf(str);
        int hashCode = valueOf.hashCode();
        if (hashCode != -2004976699) {
            if (hashCode == -527267622 && valueOf.equals("gad_has_consent_for_cookies")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (valueOf.equals("IABTCF_PurposeConsents")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            if (string2.equals("-1") || this.zze.equals(string2)) {
                return;
            }
            this.zze = string2;
            zzb(string2, r92);
        } else if (c != 1) {
        } else {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzat)).booleanValue() || r92 == -1 || this.zzf == r92) {
                return;
            }
            this.zzf = r92;
            zzb(string2, r92);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        this.zzb.registerOnSharedPreferenceChangeListener(this);
        onSharedPreferenceChanged(this.zzb, "gad_has_consent_for_cookies");
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzav)).booleanValue()) {
            onSharedPreferenceChanged(this.zzb, "IABTCF_gdprApplies");
            onSharedPreferenceChanged(this.zzb, "IABTCF_TCString");
            return;
        }
        onSharedPreferenceChanged(this.zzb, "IABTCF_PurposeConsents");
    }
}
