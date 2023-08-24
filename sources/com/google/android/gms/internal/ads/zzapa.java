package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzapa extends zzaoz {
    protected zzapa(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzapa zzs(String str, Context context, boolean z) {
        zzq(context, false);
        return new zzapa(context, str, false);
    }

    @Deprecated
    public static zzapa zzt(String str, Context context, boolean z, int r3) {
        zzq(context, z);
        return new zzapa(context, str, z);
    }

    @Override // com.google.android.gms.internal.ads.zzaoz
    protected final List zzo(zzaqb zzaqbVar, Context context, zzamh zzamhVar, zzama zzamaVar) {
        if (zzaqbVar.zzk() == null || !this.zzt) {
            return super.zzo(zzaqbVar, context, zzamhVar, null);
        }
        int zza = zzaqbVar.zza();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zzo(zzaqbVar, context, zzamhVar, null));
        arrayList.add(new zzaqs(zzaqbVar, "f39Hbu/3ZHOuknzzffAN3L/wmMd3z47Qz3PAKZYAx1YBPOpdL/44XYH2Sf+BtSh+", "LWwi57CIM0frlO/aZZoO3fCsCmO9IloxmiaKJl7K70k=", zzamhVar, zza, 24));
        return arrayList;
    }
}
