package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxm implements DisplayManager.DisplayListener, zzxk {
    private final DisplayManager zza;
    private zzxi zzb;

    private zzxm(DisplayManager displayManager) {
        this.zza = displayManager;
    }

    public static zzxk zzc(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        if (displayManager != null) {
            return new zzxm(displayManager);
        }
        return null;
    }

    private final Display zzd() {
        return this.zza.getDisplay(0);
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int r1) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int r2) {
        zzxi zzxiVar = this.zzb;
        if (zzxiVar == null || r2 != 0) {
            return;
        }
        zzxo.zzb(zzxiVar.zza, zzd());
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzxk
    public final void zza() {
        this.zza.unregisterDisplayListener(this);
        this.zzb = null;
    }

    @Override // com.google.android.gms.internal.ads.zzxk
    public final void zzb(zzxi zzxiVar) {
        this.zzb = zzxiVar;
        this.zza.registerDisplayListener(this, zzel.zzD(null));
        zzxo.zzb(zzxiVar.zza, zzd());
    }
}
