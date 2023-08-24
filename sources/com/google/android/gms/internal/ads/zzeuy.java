package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeuy implements zzeun {
    private final zzfyy zza;
    private final Context zzb;

    public zzeuy(zzfyy zzfyyVar, Context context) {
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 39;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeux
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeuy.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeuw zzc() throws Exception {
        int r3;
        boolean z;
        int r7;
        TelephonyManager telephonyManager = (TelephonyManager) this.zzb.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        String networkOperator = telephonyManager.getNetworkOperator();
        int phoneType = telephonyManager.getPhoneType();
        com.google.android.gms.ads.internal.zzt.zzq();
        int r1 = -1;
        if (com.google.android.gms.ads.internal.util.zzs.zzx(this.zzb, "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.zzb.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                int ordinal = activeNetworkInfo.getDetailedState().ordinal();
                r3 = type;
                r1 = ordinal;
            } else {
                r3 = -1;
            }
            z = connectivityManager.isActiveNetworkMetered();
            r7 = r1;
        } else {
            r3 = -2;
            z = false;
            r7 = -1;
        }
        return new zzeuw(networkOperator, r3, com.google.android.gms.ads.internal.zzt.zzr().zzl(this.zzb), phoneType, z, r7);
    }
}
