package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcy {
    private final zzbcn zza;
    private final int zzb;
    private String zzc;
    private final int zzd;

    public zzbcy(int r1, int r2, int r3) {
        this.zzb = r1;
        r2 = (r2 > 64 || r2 < 0) ? 64 : 64;
        if (r3 <= 0) {
            this.zzd = 1;
        } else {
            this.zzd = r3;
        }
        this.zza = new zzbcw(r2);
    }

    public final String zza(ArrayList arrayList, ArrayList arrayList2) {
        Collections.sort(arrayList2, new zzbcx(this));
        HashSet hashSet = new HashSet();
        loop0: for (int r4 = 0; r4 < arrayList2.size(); r4++) {
            String[] split = Normalizer.normalize((CharSequence) arrayList.get(((zzbcm) arrayList2.get(r4)).zze()), Normalizer.Form.NFKC).toLowerCase(Locale.US).split("\n");
            if (split.length != 0) {
                for (String str : split) {
                    if (str.contains("'")) {
                        StringBuilder sb = new StringBuilder(str);
                        int r11 = 1;
                        boolean z = false;
                        while (true) {
                            int r13 = r11 + 2;
                            if (r13 > sb.length()) {
                                break;
                            }
                            if (sb.charAt(r11) == '\'') {
                                if (sb.charAt(r11 - 1) != ' ') {
                                    int r12 = r11 + 1;
                                    if ((sb.charAt(r12) == 's' || sb.charAt(r12) == 'S') && (r13 == sb.length() || sb.charAt(r13) == ' ')) {
                                        sb.insert(r11, ' ');
                                        r11 = r13;
                                        z = true;
                                    }
                                }
                                sb.setCharAt(r11, ' ');
                                z = true;
                            }
                            r11++;
                        }
                        String sb2 = z ? sb.toString() : null;
                        if (sb2 != null) {
                            this.zzc = sb2;
                            str = sb2;
                        }
                    }
                    String[] zzb = zzbcr.zzb(str, true);
                    if (zzb.length >= this.zzd) {
                        for (int r8 = 0; r8 < zzb.length; r8++) {
                            String str2 = "";
                            for (int r10 = 0; r10 < this.zzd; r10++) {
                                int r112 = r8 + r10;
                                if (r112 >= zzb.length) {
                                    break;
                                }
                                if (r10 > 0) {
                                    str2 = str2.concat(" ");
                                }
                                str2 = str2.concat(String.valueOf(zzb[r112]));
                            }
                            hashSet.add(str2);
                            if (hashSet.size() >= this.zzb) {
                                break loop0;
                            }
                        }
                        if (hashSet.size() >= this.zzb) {
                            break loop0;
                        }
                    }
                }
                continue;
            }
        }
        zzbcp zzbcpVar = new zzbcp();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzbcpVar.zzb.write(this.zza.zzb((String) it.next()));
            } catch (IOException e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Error while writing hash to byteStream", e);
            }
        }
        return zzbcpVar.toString();
    }
}
