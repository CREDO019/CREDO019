package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzapb;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzn implements View.OnTouchListener {
    final /* synthetic */ zzs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzs zzsVar) {
        this.zza = zzsVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        zzapb zzapbVar;
        zzapb zzapbVar2;
        zzs zzsVar = this.zza;
        zzapbVar = zzsVar.zzh;
        if (zzapbVar != null) {
            zzapbVar2 = zzsVar.zzh;
            zzapbVar2.zzd(motionEvent);
            return false;
        }
        return false;
    }
}
