package com.google.android.gms.internal.clearcut;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes2.dex */
public final class zzej<FieldDescriptorType> extends zzei<FieldDescriptorType, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzej(int r2) {
        super(r2, null);
    }

    @Override // com.google.android.gms.internal.clearcut.zzei
    public final void zzv() {
        if (!isImmutable()) {
            for (int r0 = 0; r0 < zzdr(); r0++) {
                Map.Entry<FieldDescriptorType, Object> zzak = zzak(r0);
                if (((zzca) zzak.getKey()).zzaw()) {
                    zzak.setValue(Collections.unmodifiableList((List) zzak.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzds()) {
                if (((zzca) entry.getKey()).zzaw()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzv();
    }
}
