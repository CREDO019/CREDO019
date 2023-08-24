package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgoe {
    private static final zzgoe zzb = new zzgoe(true);
    final zzgrd zza = new zzgqt(16);
    private boolean zzc;
    private boolean zzd;

    private zzgoe() {
    }

    public static zzgoe zza() {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void zzd(com.google.android.gms.internal.ads.zzgod r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.ads.zzgrx r0 = r4.zzb()
            com.google.android.gms.internal.ads.zzgox.zze(r5)
            com.google.android.gms.internal.ads.zzgrx r1 = com.google.android.gms.internal.ads.zzgrx.DOUBLE
            com.google.android.gms.internal.ads.zzgry r1 = com.google.android.gms.internal.ads.zzgry.INT
            com.google.android.gms.internal.ads.zzgry r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L41;
                case 1: goto L3e;
                case 2: goto L3b;
                case 3: goto L38;
                case 4: goto L35;
                case 5: goto L32;
                case 6: goto L29;
                case 7: goto L20;
                case 8: goto L17;
                default: goto L16;
            }
        L16:
            goto L46
        L17:
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzgpx
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzgpc
            if (r0 == 0) goto L46
            goto L45
        L20:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzgop
            if (r0 == 0) goto L46
            goto L45
        L29:
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzgnf
            if (r0 != 0) goto L45
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L46
            goto L45
        L32:
            boolean r0 = r5 instanceof java.lang.String
            goto L43
        L35:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L43
        L38:
            boolean r0 = r5 instanceof java.lang.Double
            goto L43
        L3b:
            boolean r0 = r5 instanceof java.lang.Float
            goto L43
        L3e:
            boolean r0 = r5 instanceof java.lang.Long
            goto L43
        L41:
            boolean r0 = r5 instanceof java.lang.Integer
        L43:
            if (r0 == 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            int r3 = r4.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            r2 = 1
            com.google.android.gms.internal.ads.zzgrx r4 = r4.zzb()
            com.google.android.gms.internal.ads.zzgry r4 = r4.zza()
            r1[r2] = r4
            r4 = 2
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r1[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgoe.zzd(com.google.android.gms.internal.ads.zzgod, java.lang.Object):void");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzgoe zzgoeVar = new zzgoe();
        for (int r1 = 0; r1 < this.zza.zzb(); r1++) {
            Map.Entry zzg = this.zza.zzg(r1);
            zzgoeVar.zzc((zzgod) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzgoeVar.zzc((zzgod) entry.getKey(), entry.getValue());
        }
        zzgoeVar.zzd = this.zzd;
        return zzgoeVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzgoe) {
            return this.zza.equals(((zzgoe) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzgod zzgodVar, Object obj) {
        if (zzgodVar.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int r1 = 0; r1 < size; r1++) {
                zzd(zzgodVar, arrayList.get(r1));
            }
            obj = arrayList;
        } else {
            zzd(zzgodVar, obj);
        }
        if (obj instanceof zzgpc) {
            this.zzd = true;
        }
        this.zza.put(zzgodVar, obj);
    }

    private zzgoe(boolean z) {
        zzb();
        zzb();
    }
}
