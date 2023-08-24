package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzjk extends RuntimeException {
    private final List<String> zzaai;

    public zzjk(zzic zzicVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzaai = null;
    }
}
