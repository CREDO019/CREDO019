package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgql {
    private final ArrayDeque zza = new ArrayDeque();

    private zzgql() {
    }

    public static /* bridge */ /* synthetic */ zzgnf zza(zzgql zzgqlVar, zzgnf zzgnfVar, zzgnf zzgnfVar2) {
        zzgqlVar.zzb(zzgnfVar);
        zzgqlVar.zzb(zzgnfVar2);
        zzgnf zzgnfVar3 = (zzgnf) zzgqlVar.zza.pop();
        while (!zzgqlVar.zza.isEmpty()) {
            zzgnfVar3 = new zzgqp((zzgnf) zzgqlVar.zza.pop(), zzgnfVar3);
        }
        return zzgnfVar3;
    }

    private final void zzb(zzgnf zzgnfVar) {
        zzgnf zzgnfVar2;
        zzgnf zzgnfVar3;
        if (zzgnfVar.zzh()) {
            int zzc = zzc(zzgnfVar.zzd());
            int zzc2 = zzgqp.zzc(zzc + 1);
            if (this.zza.isEmpty() || ((zzgnf) this.zza.peek()).zzd() >= zzc2) {
                this.zza.push(zzgnfVar);
                return;
            }
            int zzc3 = zzgqp.zzc(zzc);
            zzgnf zzgnfVar4 = (zzgnf) this.zza.pop();
            while (!this.zza.isEmpty() && ((zzgnf) this.zza.peek()).zzd() < zzc3) {
                zzgnfVar4 = new zzgqp((zzgnf) this.zza.pop(), zzgnfVar4);
            }
            zzgqp zzgqpVar = new zzgqp(zzgnfVar4, zzgnfVar);
            while (!this.zza.isEmpty()) {
                if (((zzgnf) this.zza.peek()).zzd() >= zzgqp.zzc(zzc(zzgqpVar.zzd()) + 1)) {
                    break;
                }
                zzgqpVar = new zzgqp((zzgnf) this.zza.pop(), zzgqpVar);
            }
            this.zza.push(zzgqpVar);
        } else if (!(zzgnfVar instanceof zzgqp)) {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(String.valueOf(zzgnfVar.getClass()))));
        } else {
            zzgqp zzgqpVar2 = (zzgqp) zzgnfVar;
            zzgnfVar2 = zzgqpVar2.zzd;
            zzb(zzgnfVar2);
            zzgnfVar3 = zzgqpVar2.zze;
            zzb(zzgnfVar3);
        }
    }

    private static final int zzc(int r1) {
        int binarySearch = Arrays.binarySearch(zzgqp.zza, r1);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    public /* synthetic */ zzgql(zzgqk zzgqkVar) {
    }
}
