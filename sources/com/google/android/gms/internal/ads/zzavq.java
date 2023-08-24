package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavq extends zzavs {
    public final long zza;
    public final List zzb;
    public final List zzc;

    public zzavq(int r1, long j) {
        super(r1);
        this.zza = j;
        this.zzb = new ArrayList();
        this.zzc = new ArrayList();
    }

    @Override // com.google.android.gms.internal.ads.zzavs
    public final String toString() {
        String zzg = zzg(this.zzaR);
        String arrays = Arrays.toString(this.zzb.toArray());
        String arrays2 = Arrays.toString(this.zzc.toArray());
        return zzg + " leaves: " + arrays + " containers: " + arrays2;
    }

    public final zzavq zza(int r5) {
        int size = this.zzc.size();
        for (int r1 = 0; r1 < size; r1++) {
            zzavq zzavqVar = (zzavq) this.zzc.get(r1);
            if (zzavqVar.zzaR == r5) {
                return zzavqVar;
            }
        }
        return null;
    }

    public final zzavr zzb(int r5) {
        int size = this.zzb.size();
        for (int r1 = 0; r1 < size; r1++) {
            zzavr zzavrVar = (zzavr) this.zzb.get(r1);
            if (zzavrVar.zzaR == r5) {
                return zzavrVar;
            }
        }
        return null;
    }

    public final void zzc(zzavq zzavqVar) {
        this.zzc.add(zzavqVar);
    }

    public final void zzd(zzavr zzavrVar) {
        this.zzb.add(zzavrVar);
    }
}
