package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgvg extends CustomTabsServiceConnection {
    private final WeakReference zza;

    public zzgvg(zzbjw zzbjwVar, byte[] bArr) {
        this.zza = new WeakReference(zzbjwVar);
    }

    @Override // androidx.browser.customtabs.CustomTabsServiceConnection
    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbjw zzbjwVar = (zzbjw) this.zza.get();
        if (zzbjwVar != null) {
            zzbjwVar.zzc(customTabsClient);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzbjw zzbjwVar = (zzbjw) this.zza.get();
        if (zzbjwVar != null) {
            zzbjwVar.zzd();
        }
    }
}
