package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzii<T> implements zzir<T> {
    private final zzic zzyy;
    private final boolean zzyz;
    private final zzjj<?, ?> zzzi;
    private final zzgf<?> zzzj;

    private zzii(zzjj<?, ?> zzjjVar, zzgf<?> zzgfVar, zzic zzicVar) {
        this.zzzi = zzjjVar;
        this.zzyz = zzgfVar.zze(zzicVar);
        this.zzzj = zzgfVar;
        this.zzyy = zzicVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzii<T> zza(zzjj<?, ?> zzjjVar, zzgf<?> zzgfVar, zzic zzicVar) {
        return new zzii<>(zzjjVar, zzgfVar, zzicVar);
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final T newInstance() {
        return (T) this.zzyy.zzgj().zzgb();
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final boolean equals(T t, T t2) {
        if (this.zzzi.zzv(t).equals(this.zzzi.zzv(t2))) {
            if (this.zzyz) {
                return this.zzzj.zze(t).equals(this.zzzj.zze(t2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final int hashCode(T t) {
        int hashCode = this.zzzi.zzv(t).hashCode();
        return this.zzyz ? (hashCode * 53) + this.zzzj.zze(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zzd(T t, T t2) {
        zzit.zza(this.zzzi, t, t2);
        if (this.zzyz) {
            zzit.zza(this.zzzj, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zza(T t, zzkg zzkgVar) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzzj.zze(t).iterator();
        while (it.hasNext()) {
            Map.Entry<?, Object> next = it.next();
            zzgk zzgkVar = (zzgk) next.getKey();
            if (zzgkVar.zzft() != zzkd.MESSAGE || zzgkVar.zzfu() || zzgkVar.zzfv()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzhf) {
                zzkgVar.zza(zzgkVar.zzag(), (Object) ((zzhf) next).zzgw().zzdk());
            } else {
                zzkgVar.zza(zzgkVar.zzag(), next.getValue());
            }
        }
        zzjj<?, ?> zzjjVar = this.zzzi;
        zzjjVar.zzc(zzjjVar.zzv(t), zzkgVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00be A[EDGE_INSN: B:57:0x00be->B:33:0x00be ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.vision.zzir
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.vision.zzfb r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.vision.zzgs r0 = (com.google.android.gms.internal.vision.zzgs) r0
            com.google.android.gms.internal.vision.zzjm r1 = r0.zzwd
            com.google.android.gms.internal.vision.zzjm r2 = com.google.android.gms.internal.vision.zzjm.zzig()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.vision.zzjm r1 = com.google.android.gms.internal.vision.zzjm.zzih()
            r0.zzwd = r1
        L11:
            com.google.android.gms.internal.vision.zzgs$zze r11 = (com.google.android.gms.internal.vision.zzgs.zze) r11
            com.google.android.gms.internal.vision.zzgi r11 = r11.zzgk()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lc9
            int r4 = com.google.android.gms.internal.vision.zzez.zza(r12, r13, r15)
            int r13 = r15.zzro
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L65
            r3 = r13 & 7
            if (r3 != r5) goto L60
            com.google.android.gms.internal.vision.zzgf<?> r2 = r10.zzzj
            com.google.android.gms.internal.vision.zzgd r3 = r15.zzcm
            com.google.android.gms.internal.vision.zzic r5 = r10.zzyy
            int r6 = r13 >>> 3
            java.lang.Object r2 = r2.zza(r3, r5, r6)
            r8 = r2
            com.google.android.gms.internal.vision.zzgs$zzg r8 = (com.google.android.gms.internal.vision.zzgs.zzg) r8
            if (r8 == 0) goto L55
            com.google.android.gms.internal.vision.zzin r13 = com.google.android.gms.internal.vision.zzin.zzho()
            com.google.android.gms.internal.vision.zzic r2 = r8.zzxa
            java.lang.Class r2 = r2.getClass()
            com.google.android.gms.internal.vision.zzir r13 = r13.zzf(r2)
            int r13 = com.google.android.gms.internal.vision.zzez.zza(r13, r12, r4, r14, r15)
            com.google.android.gms.internal.vision.zzgs$zzd r2 = r8.zzxb
            java.lang.Object r3 = r15.zzrq
            r11.zza(r2, r3)
            goto L5e
        L55:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.vision.zzez.zza(r2, r3, r4, r5, r6, r7)
        L5e:
            r2 = r8
            goto L19
        L60:
            int r13 = com.google.android.gms.internal.vision.zzez.zza(r13, r12, r4, r14, r15)
            goto L19
        L65:
            r13 = 0
            r3 = r0
        L67:
            if (r4 >= r14) goto Lbe
            int r4 = com.google.android.gms.internal.vision.zzez.zza(r12, r4, r15)
            int r6 = r15.zzro
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto La0
            r9 = 3
            if (r7 == r9) goto L79
            goto Lb5
        L79:
            if (r2 == 0) goto L95
            com.google.android.gms.internal.vision.zzin r6 = com.google.android.gms.internal.vision.zzin.zzho()
            com.google.android.gms.internal.vision.zzic r7 = r2.zzxa
            java.lang.Class r7 = r7.getClass()
            com.google.android.gms.internal.vision.zzir r6 = r6.zzf(r7)
            int r4 = com.google.android.gms.internal.vision.zzez.zza(r6, r12, r4, r14, r15)
            com.google.android.gms.internal.vision.zzgs$zzd r6 = r2.zzxb
            java.lang.Object r7 = r15.zzrq
            r11.zza(r6, r7)
            goto L67
        L95:
            if (r8 != r5) goto Lb5
            int r4 = com.google.android.gms.internal.vision.zzez.zze(r12, r4, r15)
            java.lang.Object r3 = r15.zzrq
            com.google.android.gms.internal.vision.zzfh r3 = (com.google.android.gms.internal.vision.zzfh) r3
            goto L67
        La0:
            if (r8 != 0) goto Lb5
            int r4 = com.google.android.gms.internal.vision.zzez.zza(r12, r4, r15)
            int r13 = r15.zzro
            com.google.android.gms.internal.vision.zzgf<?> r2 = r10.zzzj
            com.google.android.gms.internal.vision.zzgd r6 = r15.zzcm
            com.google.android.gms.internal.vision.zzic r7 = r10.zzyy
            java.lang.Object r2 = r2.zza(r6, r7, r13)
            com.google.android.gms.internal.vision.zzgs$zzg r2 = (com.google.android.gms.internal.vision.zzgs.zzg) r2
            goto L67
        Lb5:
            r7 = 12
            if (r6 == r7) goto Lbe
            int r4 = com.google.android.gms.internal.vision.zzez.zza(r6, r12, r4, r14, r15)
            goto L67
        Lbe:
            if (r3 == 0) goto Lc6
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.zzb(r13, r3)
        Lc6:
            r13 = r4
            goto L19
        Lc9:
            if (r13 != r14) goto Lcc
            return
        Lcc:
            com.google.android.gms.internal.vision.zzhc r11 = com.google.android.gms.internal.vision.zzhc.zzgs()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzii.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzfb):void");
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zza(T t, zzis zzisVar, zzgd zzgdVar) throws IOException {
        boolean z;
        zzjj<?, ?> zzjjVar = this.zzzi;
        zzgf<?> zzgfVar = this.zzzj;
        Object zzw = zzjjVar.zzw(t);
        zzgi<?> zzf = zzgfVar.zzf(t);
        do {
            try {
                if (zzisVar.zzdu() == Integer.MAX_VALUE) {
                    return;
                }
                int tag = zzisVar.getTag();
                if (tag == 11) {
                    int r4 = 0;
                    Object obj = null;
                    zzfh zzfhVar = null;
                    while (zzisVar.zzdu() != Integer.MAX_VALUE) {
                        int tag2 = zzisVar.getTag();
                        if (tag2 == 16) {
                            r4 = zzisVar.zzee();
                            obj = zzgfVar.zza(zzgdVar, this.zzyy, r4);
                        } else if (tag2 == 26) {
                            if (obj != null) {
                                zzgfVar.zza(zzisVar, obj, zzgdVar, zzf);
                            } else {
                                zzfhVar = zzisVar.zzed();
                            }
                        } else if (!zzisVar.zzdv()) {
                            break;
                        }
                    }
                    if (zzisVar.getTag() != 12) {
                        throw zzhc.zzgq();
                    } else if (zzfhVar != null) {
                        if (obj != null) {
                            zzgfVar.zza(zzfhVar, obj, zzgdVar, zzf);
                        } else {
                            zzjjVar.zza((zzjj<?, ?>) zzw, r4, zzfhVar);
                        }
                    }
                } else if ((tag & 7) == 2) {
                    Object zza = zzgfVar.zza(zzgdVar, this.zzyy, tag >>> 3);
                    if (zza != null) {
                        zzgfVar.zza(zzisVar, zza, zzgdVar, zzf);
                    } else {
                        z = zzjjVar.zza((zzjj<?, ?>) zzw, zzisVar);
                        continue;
                    }
                } else {
                    z = zzisVar.zzdv();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzjjVar.zzg(t, zzw);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final void zzg(T t) {
        this.zzzi.zzg(t);
        this.zzzj.zzg(t);
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final boolean zzt(T t) {
        return this.zzzj.zze(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.vision.zzir
    public final int zzr(T t) {
        zzjj<?, ?> zzjjVar = this.zzzi;
        int zzx = zzjjVar.zzx(zzjjVar.zzv(t)) + 0;
        return this.zzyz ? zzx + this.zzzj.zze(t).zzfo() : zzx;
    }
}
