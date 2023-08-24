package com.google.android.gms.internal.vision;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzbi<T> extends zzbe<T> {
    private final /* synthetic */ zzbh zzgg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbi(zzbk zzbkVar, String str, Object obj, zzbh zzbhVar) {
        super(zzbkVar, str, obj, null);
        this.zzgg = zzbhVar;
    }

    @Override // com.google.android.gms.internal.vision.zzbe
    final T zza(Object obj) {
        if (obj instanceof String) {
            try {
                return (T) this.zzgg.zzb(Base64.decode((String) obj, 3));
            } catch (IOException | IllegalArgumentException unused) {
            }
        }
        String zzac = super.zzac();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzac).length() + 27 + String.valueOf(valueOf).length());
        sb.append("Invalid byte[] value for ");
        sb.append(zzac);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
