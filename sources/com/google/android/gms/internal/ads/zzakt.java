package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzakt {
    long zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final List zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzakt(java.lang.String r14, com.google.android.gms.internal.ads.zzajj r15) {
        /*
            r13 = this;
            java.lang.String r2 = r15.zzb
            long r3 = r15.zzc
            long r5 = r15.zzd
            long r7 = r15.zze
            long r9 = r15.zzf
            java.util.List r0 = r15.zzh
            if (r0 == 0) goto L10
        Le:
            r11 = r0
            goto L44
        L10:
            java.util.Map r15 = r15.zzg
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r15.size()
            r0.<init>(r1)
            java.util.Set r15 = r15.entrySet()
            java.util.Iterator r15 = r15.iterator()
        L23:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto Le
            java.lang.Object r1 = r15.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            com.google.android.gms.internal.ads.zzajs r11 = new com.google.android.gms.internal.ads.zzajs
            java.lang.Object r12 = r1.getKey()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            r11.<init>(r12, r1)
            r0.add(r11)
            goto L23
        L44:
            r0 = r13
            r1 = r14
            r0.<init>(r1, r2, r3, r5, r7, r9, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakt.<init>(java.lang.String, com.google.android.gms.internal.ads.zzajj):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzakt zza(zzaku zzakuVar) throws IOException {
        if (zzakw.zze(zzakuVar) != 538247942) {
            throw new IOException();
        }
        String zzh = zzakw.zzh(zzakuVar);
        String zzh2 = zzakw.zzh(zzakuVar);
        long zzf = zzakw.zzf(zzakuVar);
        long zzf2 = zzakw.zzf(zzakuVar);
        long zzf3 = zzakw.zzf(zzakuVar);
        long zzf4 = zzakw.zzf(zzakuVar);
        int zze = zzakw.zze(zzakuVar);
        if (zze < 0) {
            throw new IOException("readHeaderList size=" + zze);
        }
        List emptyList = zze == 0 ? Collections.emptyList() : new ArrayList();
        for (int r1 = 0; r1 < zze; r1++) {
            emptyList.add(new zzajs(zzakw.zzh(zzakuVar).intern(), zzakw.zzh(zzakuVar).intern()));
        }
        return new zzakt(zzh, zzh2, zzf, zzf2, zzf3, zzf4, emptyList);
    }

    private zzakt(String str, String str2, long j, long j2, long j3, long j4, List list) {
        this.zzb = str;
        this.zzc = true == "".equals(str2) ? null : str2;
        this.zzd = j;
        this.zze = j2;
        this.zzf = j3;
        this.zzg = j4;
        this.zzh = list;
    }
}
