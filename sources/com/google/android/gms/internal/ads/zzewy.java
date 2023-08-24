package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewy implements zzgur {
    public static zzeuq zza(Context context, zzcfk zzcfkVar, zzcfl zzcflVar, Object obj, zzevi zzeviVar, zzewl zzewlVar, zzgul zzgulVar, zzgul zzgulVar2, zzgul zzgulVar3, zzgul zzgulVar4, zzgul zzgulVar5, zzgul zzgulVar6, zzgul zzgulVar7, zzgul zzgulVar8, zzgul zzgulVar9, Executor executor, zzfjc zzfjcVar, zzdxo zzdxoVar) {
        HashSet hashSet = new HashSet();
        hashSet.add((zzewe) obj);
        hashSet.add(zzeviVar);
        hashSet.add(zzewlVar);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeL)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeM)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar2.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeN)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar3.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeO)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar4.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeS)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar6.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeT)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar7.zzb());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcq)).booleanValue()) {
            hashSet.add((zzeun) zzgulVar9.zzb());
        }
        return new zzeuq(context, executor, hashSet, zzfjcVar, zzdxoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        throw null;
    }
}
