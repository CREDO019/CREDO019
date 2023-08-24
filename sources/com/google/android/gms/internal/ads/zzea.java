package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzea extends BroadcastReceiver {
    final /* synthetic */ zzeb zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzea(zzeb zzebVar, zzdz zzdzVar) {
        this.zza = zzebVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int r0 = 2;
        if (connectivityManager != null) {
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    int type = activeNetworkInfo.getType();
                    if (type != 0) {
                        if (type != 1) {
                            if (type != 4 && type != 5) {
                                if (type != 6) {
                                    r0 = type != 9 ? 8 : 7;
                                }
                                r0 = 5;
                            }
                        }
                    }
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                            r0 = 3;
                            break;
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 14:
                        case 15:
                        case 17:
                            r0 = 4;
                            break;
                        case 13:
                            r0 = 5;
                            break;
                        case 16:
                        case 19:
                        default:
                            r0 = 6;
                            break;
                        case 18:
                            break;
                        case 20:
                            if (zzel.zza >= 29) {
                                r0 = 9;
                                break;
                            }
                            break;
                    }
                } else {
                    r0 = 1;
                }
            } catch (SecurityException unused) {
            }
            if (zzel.zza >= 31 || r0 != 5) {
                zzeb.zzc(this.zza, r0);
            }
            zzeb zzebVar = this.zza;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
                Objects.requireNonNull(telephonyManager);
                zzdy zzdyVar = new zzdy(zzebVar);
                telephonyManager.registerTelephonyCallback(context.getMainExecutor(), zzdyVar);
                telephonyManager.unregisterTelephonyCallback(zzdyVar);
                return;
            } catch (RuntimeException unused2) {
                zzeb.zzc(zzebVar, 5);
                return;
            }
        }
        r0 = 0;
        if (zzel.zza >= 31) {
        }
        zzeb.zzc(this.zza, r0);
    }
}
