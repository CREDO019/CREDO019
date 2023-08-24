package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.query.AdInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzcgn;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdq {
    private Date zzg;
    private String zzh;
    private String zzk;
    private String zzl;
    private boolean zzn;
    private AdInfo zzo;
    private String zzp;
    private final HashSet zza = new HashSet();
    private final Bundle zzb = new Bundle();
    private final HashMap zzc = new HashMap();
    private final HashSet zzd = new HashSet();
    private final Bundle zze = new Bundle();
    private final HashSet zzf = new HashSet();
    private final List zzi = new ArrayList();
    private int zzj = -1;
    private int zzm = -1;
    private int zzq = 60000;

    @Deprecated
    public final void zzA(Date date) {
        this.zzg = date;
    }

    public final void zzB(String str) {
        this.zzh = str;
    }

    @Deprecated
    public final void zzC(int r1) {
        this.zzj = r1;
    }

    public final void zzD(int r1) {
        this.zzq = r1;
    }

    @Deprecated
    public final void zzE(boolean z) {
        this.zzn = z;
    }

    public final void zzF(List list) {
        this.zzi.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (TextUtils.isEmpty(str)) {
                zzcgn.zzj("neighboring content URL should not be null or empty");
            } else {
                this.zzi.add(str);
            }
        }
    }

    public final void zzG(String str) {
        this.zzk = str;
    }

    public final void zzH(String str) {
        this.zzl = str;
    }

    @Deprecated
    public final void zzI(boolean z) {
        this.zzm = z ? 1 : 0;
    }

    public final void zzq(String str) {
        this.zzf.add(str);
    }

    public final void zzr(Class cls, Bundle bundle) {
        if (this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzb.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        Bundle bundle2 = this.zzb.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        Preconditions.checkNotNull(bundle2);
        bundle2.putBundle(cls.getName(), bundle);
    }

    public final void zzs(String str, String str2) {
        this.zze.putString(str, str2);
    }

    public final void zzt(String str) {
        this.zza.add(str);
    }

    public final void zzu(Class cls, Bundle bundle) {
        this.zzb.putBundle(cls.getName(), bundle);
    }

    @Deprecated
    public final void zzv(NetworkExtras networkExtras) {
        this.zzc.put(networkExtras.getClass(), networkExtras);
    }

    public final void zzw(String str) {
        this.zzd.add(str);
    }

    public final void zzx(String str) {
        this.zzd.remove("B3EEABB8EE11C2BE770B684D95219ECB");
    }

    public final void zzy(AdInfo adInfo) {
        this.zzo = adInfo;
    }

    public final void zzz(String str) {
        this.zzp = str;
    }
}
