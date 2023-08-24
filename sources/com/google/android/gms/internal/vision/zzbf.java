package com.google.android.gms.internal.vision;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzbf extends zzbe<Boolean> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbf(zzbk zzbkVar, String str, Boolean bool) {
        super(zzbkVar, str, bool, null);
    }

    @Override // com.google.android.gms.internal.vision.zzbe
    final /* synthetic */ Boolean zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzal.zzeu.matcher(str).matches()) {
                return true;
            }
            if (zzal.zzev.matcher(str).matches()) {
                return false;
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
