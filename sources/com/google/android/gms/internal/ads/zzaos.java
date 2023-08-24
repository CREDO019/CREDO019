package com.google.android.gms.internal.ads;

import java.io.File;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaos implements zzfoa {
    final /* synthetic */ zzfma zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaos(zzaou zzaouVar, zzfma zzfmaVar) {
        this.zza = zzfmaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfoa
    public final boolean zza(File file) {
        try {
            return this.zza.zza(file);
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }
}
