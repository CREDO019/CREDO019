package com.google.android.gms.ads.internal.client;

import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbnz;
import com.google.android.gms.internal.ads.zzboa;
import com.google.android.gms.internal.ads.zzbyx;
import com.google.android.gms.internal.ads.zzccv;
import com.google.android.gms.internal.ads.zzcgg;
import com.google.android.gms.internal.ads.zzcgt;
import java.util.Random;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaw {
    private static final zzaw zza = new zzaw();
    private final zzcgg zzb;
    private final zzau zzc;
    private final String zzd;
    private final zzcgt zze;
    private final Random zzf;

    protected zzaw() {
        zzcgg zzcggVar = new zzcgg();
        zzau zzauVar = new zzau(new zzk(), new zzi(), new zzek(), new zzbnz(), new zzccv(), new zzbyx(), new zzboa());
        String zzd = zzcgg.zzd();
        zzcgt zzcgtVar = new zzcgt(0, (int) ModuleDescriptor.MODULE_VERSION, true, false, false);
        Random random = new Random();
        this.zzb = zzcggVar;
        this.zzc = zzauVar;
        this.zzd = zzd;
        this.zze = zzcgtVar;
        this.zzf = random;
    }

    public static zzau zza() {
        return zza.zzc;
    }

    public static zzcgg zzb() {
        return zza.zzb;
    }

    public static zzcgt zzc() {
        return zza.zze;
    }

    public static String zzd() {
        return zza.zzd;
    }

    public static Random zze() {
        return zza.zzf;
    }
}
