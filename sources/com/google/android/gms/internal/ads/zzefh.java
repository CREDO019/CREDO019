package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import androidx.autofill.HintConstants;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefh extends zzefi {
    private static final SparseArray zzb;
    private final Context zzc;
    private final zzdce zzd;
    private final TelephonyManager zze;
    private final zzeez zzf;
    private int zzg;

    static {
        SparseArray sparseArray = new SparseArray();
        zzb = sparseArray;
        sparseArray.put(NetworkInfo.DetailedState.CONNECTED.ordinal(), zzbgy.CONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.AUTHENTICATING.ordinal(), zzbgy.CONNECTING);
        sparseArray.put(NetworkInfo.DetailedState.CONNECTING.ordinal(), zzbgy.CONNECTING);
        sparseArray.put(NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal(), zzbgy.CONNECTING);
        sparseArray.put(NetworkInfo.DetailedState.DISCONNECTING.ordinal(), zzbgy.DISCONNECTING);
        sparseArray.put(NetworkInfo.DetailedState.BLOCKED.ordinal(), zzbgy.DISCONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.DISCONNECTED.ordinal(), zzbgy.DISCONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.FAILED.ordinal(), zzbgy.DISCONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.IDLE.ordinal(), zzbgy.DISCONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.SCANNING.ordinal(), zzbgy.DISCONNECTED);
        sparseArray.put(NetworkInfo.DetailedState.SUSPENDED.ordinal(), zzbgy.SUSPENDED);
        sparseArray.put(NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal(), zzbgy.CONNECTING);
        sparseArray.put(NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal(), zzbgy.CONNECTING);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzefh(Context context, zzdce zzdceVar, zzeez zzeezVar, zzeev zzeevVar, com.google.android.gms.ads.internal.util.zzg zzgVar) {
        super(zzeevVar, zzgVar);
        this.zzc = context;
        this.zzd = zzdceVar;
        this.zzf = zzeezVar;
        this.zze = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzbgp zza(zzefh zzefhVar, Bundle bundle) {
        zzbgi zza = zzbgp.zza();
        int r1 = bundle.getInt("cnt", -2);
        int r5 = bundle.getInt("gnt", 0);
        int r2 = 2;
        if (r1 == -1) {
            zzefhVar.zzg = 2;
        } else {
            zzefhVar.zzg = 1;
            if (r1 == 0) {
                zza.zzb(2);
            } else if (r1 == 1) {
                zza.zzb(3);
            } else {
                zza.zzb(1);
            }
            switch (r5) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                    r2 = 3;
                    break;
                case 13:
                    r2 = 5;
                    break;
                default:
                    r2 = 1;
                    break;
            }
            zza.zza(r2);
        }
        return (zzbgp) zza.zzal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzbgy zzb(zzefh zzefhVar, Bundle bundle) {
        return (zzbgy) zzb.get(zzfdy.zza(zzfdy.zza(bundle, "device"), "network").getInt("active_network_state", -1), zzbgy.UNSPECIFIED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ byte[] zze(zzefh zzefhVar, boolean z, ArrayList arrayList, zzbgp zzbgpVar, zzbgy zzbgyVar) {
        zzbgt zzg = zzbgu.zzg();
        zzg.zza(arrayList);
        zzg.zzi(zzg(Settings.Global.getInt(zzefhVar.zzc.getContentResolver(), "airplane_mode_on", 0) != 0));
        zzg.zzj(com.google.android.gms.ads.internal.zzt.zzr().zzh(zzefhVar.zzc, zzefhVar.zze));
        zzg.zzf(zzefhVar.zzf.zze());
        zzg.zze(zzefhVar.zzf.zzb());
        zzg.zzb(zzefhVar.zzf.zza());
        zzg.zzc(zzbgyVar);
        zzg.zzd(zzbgpVar);
        zzg.zzk(zzefhVar.zzg);
        zzg.zzl(zzg(z));
        zzg.zzh(zzefhVar.zzf.zzd());
        zzg.zzg(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis());
        zzg.zzm(zzg(Settings.Global.getInt(zzefhVar.zzc.getContentResolver(), "wifi_on", 0) != 0));
        return ((zzbgu) zzg.zzal()).zzaw();
    }

    private static final int zzg(boolean z) {
        return z ? 2 : 1;
    }

    public final void zzd(boolean z) {
        zzfyo.zzr(this.zzd.zzb(), new zzefg(this, z), zzcha.zzf);
    }
}
