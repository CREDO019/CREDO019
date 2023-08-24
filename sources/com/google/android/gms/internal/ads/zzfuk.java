package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfuk {
    private static final zzfuk zza = new zzfui();
    private static final zzfuk zzb = new zzfuj(-1);
    private static final zzfuk zzc = new zzfuj(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfuk(zzfui zzfuiVar) {
    }

    public static zzfuk zzj() {
        return zza;
    }

    public abstract int zza();

    public abstract zzfuk zzb(int r1, int r2);

    public abstract zzfuk zzc(Object obj, Object obj2, Comparator comparator);

    public abstract zzfuk zzd(boolean z, boolean z2);

    public abstract zzfuk zze(boolean z, boolean z2);
}
