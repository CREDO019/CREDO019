package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.ads.zzcgg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdr {
    private final Date zza;
    private final String zzb;
    private final List zzc;
    private final int zzd;
    private final Set zze;
    private final Bundle zzf;
    private final Map zzg;
    private final String zzh;
    private final String zzi;
    @NotOnlyInitialized
    private final SearchAdRequest zzj;
    private final int zzk;
    private final Set zzl;
    private final Bundle zzm;
    private final Set zzn;
    private final boolean zzo;
    private final AdInfo zzp;
    private final String zzq;
    private final int zzr;

    public zzdr(zzdq zzdqVar, SearchAdRequest searchAdRequest) {
        Date date;
        String str;
        List list;
        int r0;
        HashSet hashSet;
        Bundle bundle;
        HashMap hashMap;
        String str2;
        String str3;
        int r3;
        HashSet hashSet2;
        Bundle bundle2;
        HashSet hashSet3;
        boolean z;
        AdInfo adInfo;
        String str4;
        int r2;
        date = zzdqVar.zzg;
        this.zza = date;
        str = zzdqVar.zzh;
        this.zzb = str;
        list = zzdqVar.zzi;
        this.zzc = list;
        r0 = zzdqVar.zzj;
        this.zzd = r0;
        hashSet = zzdqVar.zza;
        this.zze = Collections.unmodifiableSet(hashSet);
        bundle = zzdqVar.zzb;
        this.zzf = bundle;
        hashMap = zzdqVar.zzc;
        this.zzg = Collections.unmodifiableMap(hashMap);
        str2 = zzdqVar.zzk;
        this.zzh = str2;
        str3 = zzdqVar.zzl;
        this.zzi = str3;
        this.zzj = searchAdRequest;
        r3 = zzdqVar.zzm;
        this.zzk = r3;
        hashSet2 = zzdqVar.zzd;
        this.zzl = Collections.unmodifiableSet(hashSet2);
        bundle2 = zzdqVar.zze;
        this.zzm = bundle2;
        hashSet3 = zzdqVar.zzf;
        this.zzn = Collections.unmodifiableSet(hashSet3);
        z = zzdqVar.zzn;
        this.zzo = z;
        adInfo = zzdqVar.zzo;
        this.zzp = adInfo;
        str4 = zzdqVar.zzp;
        this.zzq = str4;
        r2 = zzdqVar.zzq;
        this.zzr = r2;
    }

    @Deprecated
    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zzr;
    }

    public final int zzc() {
        return this.zzk;
    }

    public final Bundle zzd(Class cls) {
        Bundle bundle = this.zzf.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final Bundle zze() {
        return this.zzm;
    }

    public final Bundle zzf(Class cls) {
        return this.zzf.getBundle(cls.getName());
    }

    public final Bundle zzg() {
        return this.zzf;
    }

    @Deprecated
    public final NetworkExtras zzh(Class cls) {
        return (NetworkExtras) this.zzg.get(cls);
    }

    public final AdInfo zzi() {
        return this.zzp;
    }

    public final SearchAdRequest zzj() {
        return this.zzj;
    }

    public final String zzk() {
        return this.zzq;
    }

    public final String zzl() {
        return this.zzb;
    }

    public final String zzm() {
        return this.zzh;
    }

    public final String zzn() {
        return this.zzi;
    }

    @Deprecated
    public final Date zzo() {
        return this.zza;
    }

    public final List zzp() {
        return new ArrayList(this.zzc);
    }

    public final Set zzq() {
        return this.zzn;
    }

    public final Set zzr() {
        return this.zze;
    }

    @Deprecated
    public final boolean zzs() {
        return this.zzo;
    }

    public final boolean zzt(Context context) {
        RequestConfiguration zzc = zzed.zzf().zzc();
        zzaw.zzb();
        String zzx = zzcgg.zzx(context);
        return this.zzl.contains(zzx) || zzc.getTestDeviceIds().contains(zzx);
    }
}
