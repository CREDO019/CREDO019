package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcq {
    private final int zza;
    private final zzbcn zzb = new zzbcs();

    public zzbcq(int r2) {
        this.zza = r2;
    }

    public final String zza(ArrayList arrayList) {
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int r3 = 0; r3 < size; r3++) {
            sb.append(((String) arrayList.get(r3)).toLowerCase(Locale.US));
            sb.append('\n');
        }
        String[] split = sb.toString().split("\n");
        if (split.length == 0) {
            return "";
        }
        zzbcp zzbcpVar = new zzbcp();
        PriorityQueue priorityQueue = new PriorityQueue(this.zza, new zzbco(this));
        for (String str : split) {
            String[] zzb = zzbcr.zzb(str, false);
            if (zzb.length != 0) {
                zzbcv.zzc(zzb, this.zza, 6, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzbcpVar.zzb.write(this.zzb.zzb(((zzbcu) it.next()).zzb));
            } catch (IOException e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Error while writing hash to byteStream", e);
            }
        }
        return zzbcpVar.toString();
    }
}
