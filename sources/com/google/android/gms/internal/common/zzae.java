package com.google.android.gms.internal.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
final class zzae extends zzz {
    private final zzag zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(zzag zzagVar, int r3) {
        super(zzagVar.size(), r3);
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.internal.common.zzz
    protected final Object zza(int r2) {
        return this.zza.get(r2);
    }
}
