package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfsm implements zzfsr {
    final /* synthetic */ zzfrr zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfsm(zzfrr zzfrrVar) {
        this.zza = zzfrrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfsr
    public final /* synthetic */ Iterator zza(zzfss zzfssVar, CharSequence charSequence) {
        return new zzfsl(this, zzfssVar, charSequence);
    }
}
