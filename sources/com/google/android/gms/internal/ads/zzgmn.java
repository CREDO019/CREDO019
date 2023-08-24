package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgmn;
import com.google.android.gms.internal.ads.zzgmo;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgmn<MessageType extends zzgmo<MessageType, BuilderType>, BuilderType extends zzgmn<MessageType, BuilderType>> implements zzgpw {
    @Override // 
    /* renamed from: zzaf */
    public abstract zzgmn clone();

    protected abstract zzgmn zzag(zzgmo zzgmoVar);

    @Override // com.google.android.gms.internal.ads.zzgpw
    public final /* bridge */ /* synthetic */ zzgpw zzah(zzgpx zzgpxVar) {
        if (!zzbh().getClass().isInstance(zzgpxVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zzag((zzgmo) zzgpxVar);
    }
}
