package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zziv<FieldDescriptorType> extends zziw<FieldDescriptorType, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zziv(int r2) {
        super(r2, null);
    }

    @Override // com.google.android.gms.internal.vision.zziw
    public final void zzdp() {
        if (!isImmutable()) {
            for (int r0 = 0; r0 < zzhx(); r0++) {
                Map.Entry<FieldDescriptorType, Object> zzbu = zzbu(r0);
                if (((zzgk) zzbu.getKey()).zzfu()) {
                    zzbu.setValue(Collections.unmodifiableList((List) zzbu.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzhy()) {
                if (((zzgk) entry.getKey()).zzfu()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzdp();
    }
}
