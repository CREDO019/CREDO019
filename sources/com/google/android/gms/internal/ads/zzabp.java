package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabp extends zzabr {
    private long zzb;
    private long[] zzc;
    private long[] zzd;

    public zzabp() {
        super(new zzze());
        this.zzb = C1856C.TIME_UNSET;
        this.zzc = new long[0];
        this.zzd = new long[0];
    }

    private static Double zzg(zzed zzedVar) {
        return Double.valueOf(Double.longBitsToDouble(zzedVar.zzr()));
    }

    private static String zzi(zzed zzedVar) {
        int zzo = zzedVar.zzo();
        int zzc = zzedVar.zzc();
        zzedVar.zzG(zzo);
        return new String(zzedVar.zzH(), zzc, zzo);
    }

    private static HashMap zzj(zzed zzedVar) {
        int zzn = zzedVar.zzn();
        HashMap hashMap = new HashMap(zzn);
        for (int r2 = 0; r2 < zzn; r2++) {
            String zzi = zzi(zzedVar);
            Object zzh = zzh(zzedVar, zzedVar.zzk());
            if (zzh != null) {
                hashMap.put(zzi, zzh);
            }
        }
        return hashMap;
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zza(zzed zzedVar) {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zzb(zzed zzedVar, long j) {
        if (zzedVar.zzk() == 2 && "onMetaData".equals(zzi(zzedVar)) && zzedVar.zza() != 0 && zzedVar.zzk() == 8) {
            HashMap zzj = zzj(zzedVar);
            Object obj = zzj.get("duration");
            if (obj instanceof Double) {
                double doubleValue = ((Double) obj).doubleValue();
                if (doubleValue > 0.0d) {
                    this.zzb = (long) (doubleValue * 1000000.0d);
                }
            }
            Object obj2 = zzj.get("keyframes");
            if (obj2 instanceof Map) {
                Map map = (Map) obj2;
                Object obj3 = map.get("filepositions");
                Object obj4 = map.get("times");
                if ((obj3 instanceof List) && (obj4 instanceof List)) {
                    List list = (List) obj3;
                    List list2 = (List) obj4;
                    int size = list2.size();
                    this.zzc = new long[size];
                    this.zzd = new long[size];
                    for (int r3 = 0; r3 < size; r3++) {
                        Object obj5 = list.get(r3);
                        Object obj6 = list2.get(r3);
                        if (!(obj6 instanceof Double) || !(obj5 instanceof Double)) {
                            this.zzc = new long[0];
                            this.zzd = new long[0];
                            break;
                        }
                        this.zzc[r3] = (long) (((Double) obj6).doubleValue() * 1000000.0d);
                        this.zzd[r3] = ((Double) obj5).longValue();
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final long zzc() {
        return this.zzb;
    }

    public final long[] zzd() {
        return this.zzd;
    }

    public final long[] zze() {
        return this.zzc;
    }

    private static Object zzh(zzed zzedVar, int r5) {
        if (r5 == 0) {
            return zzg(zzedVar);
        }
        if (r5 == 1) {
            return Boolean.valueOf(zzedVar.zzk() == 1);
        } else if (r5 != 2) {
            if (r5 != 3) {
                if (r5 != 8) {
                    if (r5 != 10) {
                        if (r5 != 11) {
                            return null;
                        }
                        Date date = new Date((long) zzg(zzedVar).doubleValue());
                        zzedVar.zzG(2);
                        return date;
                    }
                    int zzn = zzedVar.zzn();
                    ArrayList arrayList = new ArrayList(zzn);
                    for (int r0 = 0; r0 < zzn; r0++) {
                        Object zzh = zzh(zzedVar, zzedVar.zzk());
                        if (zzh != null) {
                            arrayList.add(zzh);
                        }
                    }
                    return arrayList;
                }
                return zzj(zzedVar);
            }
            HashMap hashMap = new HashMap();
            while (true) {
                String zzi = zzi(zzedVar);
                int zzk = zzedVar.zzk();
                if (zzk == 9) {
                    return hashMap;
                }
                Object zzh2 = zzh(zzedVar, zzk);
                if (zzh2 != null) {
                    hashMap.put(zzi, zzh2);
                }
            }
        } else {
            return zzi(zzedVar);
        }
    }
}
