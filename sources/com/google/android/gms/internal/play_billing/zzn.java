package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes3.dex */
final class zzn<E> extends zzl<E> {
    private final zzp<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzp<E> zzpVar, int r3) {
        super(zzpVar.size(), r3);
        this.zza = zzpVar;
    }

    @Override // com.google.android.gms.internal.play_billing.zzl
    protected final E zza(int r2) {
        return this.zza.get(r2);
    }
}
