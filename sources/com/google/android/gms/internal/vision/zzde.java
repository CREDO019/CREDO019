package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzde<E> extends zzdb<E> {
    private final zzdf<E> zzlm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(zzdf<E> zzdfVar, int r3) {
        super(zzdfVar.size(), r3);
        this.zzlm = zzdfVar;
    }

    @Override // com.google.android.gms.internal.vision.zzdb
    protected final E get(int r2) {
        return this.zzlm.get(r2);
    }
}
