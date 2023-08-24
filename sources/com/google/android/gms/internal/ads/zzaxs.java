package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaxs {
    private final zzauv[] zza;
    private final zzauw zzb;
    private zzauv zzc;

    public zzaxs(zzauv[] zzauvVarArr, zzauw zzauwVar) {
        this.zza = zzauvVarArr;
        this.zzb = zzauwVar;
    }

    public final void zza() {
        if (this.zzc != null) {
            this.zzc = null;
        }
    }

    public final zzauv zzb(zzauu zzauuVar, Uri uri) throws IOException, InterruptedException {
        zzauv zzauvVar = this.zzc;
        if (zzauvVar != null) {
            return zzauvVar;
        }
        zzauv[] zzauvVarArr = this.zza;
        int length = zzauvVarArr.length;
        int r2 = 0;
        while (true) {
            if (r2 >= length) {
                break;
            }
            zzauv zzauvVar2 = zzauvVarArr[r2];
            try {
            } catch (EOFException unused) {
            } catch (Throwable th) {
                zzauuVar.zze();
                throw th;
            }
            if (zzauvVar2.zzg(zzauuVar)) {
                this.zzc = zzauvVar2;
                zzauuVar.zze();
                break;
            }
            continue;
            zzauuVar.zze();
            r2++;
        }
        zzauv zzauvVar3 = this.zzc;
        if (zzauvVar3 == null) {
            String zzk = zzban.zzk(this.zza);
            throw new zzayq("None of the available extractors (" + zzk + ") could read the stream.", uri);
        }
        zzauvVar3.zzd(this.zzb);
        return this.zzc;
    }
}
