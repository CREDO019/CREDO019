package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhy implements zzhv {
    @Override // com.google.android.gms.internal.vision.zzhv
    public final Map<?, ?> zzk(Object obj) {
        return (zzhw) obj;
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final zzht<?, ?> zzp(Object obj) {
        zzhu zzhuVar = (zzhu) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final Map<?, ?> zzl(Object obj) {
        return (zzhw) obj;
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final boolean zzm(Object obj) {
        return !((zzhw) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final Object zzn(Object obj) {
        ((zzhw) obj).zzdp();
        return obj;
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final Object zzo(Object obj) {
        return zzhw.zzhc().zzhd();
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final Object zzc(Object obj, Object obj2) {
        zzhw zzhwVar = (zzhw) obj;
        zzhw zzhwVar2 = (zzhw) obj2;
        if (!zzhwVar2.isEmpty()) {
            if (!zzhwVar.isMutable()) {
                zzhwVar = zzhwVar.zzhd();
            }
            zzhwVar.zza(zzhwVar2);
        }
        return zzhwVar;
    }

    @Override // com.google.android.gms.internal.vision.zzhv
    public final int zzb(int r1, Object obj, Object obj2) {
        zzhw zzhwVar = (zzhw) obj;
        zzhu zzhuVar = (zzhu) obj2;
        if (zzhwVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzhwVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw new NoSuchMethodError();
        }
        return 0;
    }
}
