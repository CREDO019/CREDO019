package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzcxh {
    private final zzcza zza;
    private final View zzb;
    private final zzfct zzc;
    private final zzcmn zzd;

    public zzcxh(View view, zzcmn zzcmnVar, zzcza zzczaVar, zzfct zzfctVar) {
        this.zzb = view;
        this.zzd = zzcmnVar;
        this.zza = zzczaVar;
        this.zzc = zzfctVar;
    }

    public static final zzdke zzf(final Context context, final zzcgt zzcgtVar, final zzfcs zzfcsVar, final zzfdn zzfdnVar) {
        return new zzdke(new zzdem() { // from class: com.google.android.gms.internal.ads.zzcxf
            @Override // com.google.android.gms.internal.ads.zzdem
            public final void zzn() {
                com.google.android.gms.ads.internal.zzt.zzt().zzn(context, zzcgtVar.zza, zzfcsVar.zzD.toString(), zzfdnVar.zzf);
            }
        }, zzcha.zzf);
    }

    public static final Set zzg(zzcyr zzcyrVar) {
        return Collections.singleton(new zzdke(zzcyrVar, zzcha.zzf));
    }

    public static final zzdke zzh(zzcyp zzcypVar) {
        return new zzdke(zzcypVar, zzcha.zze);
    }

    public final View zza() {
        return this.zzb;
    }

    public final zzcmn zzb() {
        return this.zzd;
    }

    public final zzcza zzc() {
        return this.zza;
    }

    public zzdek zzd(Set set) {
        return new zzdek(set);
    }

    public final zzfct zze() {
        return this.zzc;
    }
}
