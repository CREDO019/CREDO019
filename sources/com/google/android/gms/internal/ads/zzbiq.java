package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbiq {
    private final int zza;
    private final String zzb;
    private final Object zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbiq(int r1, String str, Object obj, zzbip zzbipVar) {
        this.zza = r1;
        this.zzb = str;
        this.zzc = obj;
        com.google.android.gms.ads.internal.client.zzay.zza().zzd(this);
    }

    public static zzbiq zzf(int r1, String str, float f) {
        return new zzbin(1, str, Float.valueOf(f));
    }

    public static zzbiq zzg(int r1, String str, int r3) {
        return new zzbil(1, str, Integer.valueOf(r3));
    }

    public static zzbiq zzh(int r0, String str, long j) {
        return new zzbim(1, str, Long.valueOf(j));
    }

    public static zzbiq zzi(int r1, String str, Boolean bool) {
        return new zzbik(r1, str, bool);
    }

    public static zzbiq zzj(int r1, String str, String str2) {
        return new zzbio(1, str, str2);
    }

    public static zzbiq zzk(int r1, String str) {
        zzbiq zzj = zzj(1, "gads:sdk_core_constants:experiment_id", null);
        com.google.android.gms.ads.internal.client.zzay.zza().zzc(zzj);
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zza(JSONObject jSONObject);

    public abstract Object zzb(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzc(SharedPreferences sharedPreferences);

    public abstract void zzd(SharedPreferences.Editor editor, Object obj);

    public final int zze() {
        return this.zza;
    }

    public final Object zzl() {
        return com.google.android.gms.ads.internal.client.zzay.zzc().zzb(this);
    }

    public final Object zzm() {
        return this.zzc;
    }

    public final String zzn() {
        return this.zzb;
    }
}
