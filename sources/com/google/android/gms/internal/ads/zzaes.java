package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaes extends zzaeu {
    public final long zza;
    public final List zzb;
    public final List zzc;

    public zzaes(int r1, long j) {
        super(r1);
        this.zza = j;
        this.zzb = new ArrayList();
        this.zzc = new ArrayList();
    }

    @Override // com.google.android.gms.internal.ads.zzaeu
    public final String toString() {
        String zzf = zzf(this.zzd);
        String arrays = Arrays.toString(this.zzb.toArray());
        String arrays2 = Arrays.toString(this.zzc.toArray());
        return zzf + " leaves: " + arrays + " containers: " + arrays2;
    }

    public final zzaes zza(int r5) {
        int size = this.zzc.size();
        for (int r1 = 0; r1 < size; r1++) {
            zzaes zzaesVar = (zzaes) this.zzc.get(r1);
            if (zzaesVar.zzd == r5) {
                return zzaesVar;
            }
        }
        return null;
    }

    public final zzaet zzb(int r5) {
        int size = this.zzb.size();
        for (int r1 = 0; r1 < size; r1++) {
            zzaet zzaetVar = (zzaet) this.zzb.get(r1);
            if (zzaetVar.zzd == r5) {
                return zzaetVar;
            }
        }
        return null;
    }

    public final void zzc(zzaes zzaesVar) {
        this.zzc.add(zzaesVar);
    }

    public final void zzd(zzaet zzaetVar) {
        this.zzb.add(zzaetVar);
    }
}
