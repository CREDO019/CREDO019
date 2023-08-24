package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzgh extends zzgf<zzgs.zzd> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final boolean zze(zzic zzicVar) {
        return zzicVar instanceof zzgs.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final zzgi<zzgs.zzd> zze(Object obj) {
        return ((zzgs.zze) obj).zzwk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final zzgi<zzgs.zzd> zzf(Object obj) {
        return ((zzgs.zze) obj).zzgk();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final void zzg(Object obj) {
        zze(obj).zzdp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final <UT, UB> UB zza(zzis zzisVar, Object obj, zzgd zzgdVar, zzgi<zzgs.zzd> zzgiVar, UB ub, zzjj<UT, UB> zzjjVar) throws IOException {
        Object zza;
        ArrayList arrayList;
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        int r0 = zzgVar.zzxb.number;
        if (zzgVar.zzxb.zzwi && zzgVar.zzxb.zzwj) {
            switch (zzgg.zzrr[zzgVar.zzxb.zzwh.ordinal()]) {
                case 1:
                    arrayList = new ArrayList();
                    zzisVar.zza(arrayList);
                    break;
                case 2:
                    arrayList = new ArrayList();
                    zzisVar.zzb(arrayList);
                    break;
                case 3:
                    arrayList = new ArrayList();
                    zzisVar.zzd(arrayList);
                    break;
                case 4:
                    arrayList = new ArrayList();
                    zzisVar.zzc(arrayList);
                    break;
                case 5:
                    arrayList = new ArrayList();
                    zzisVar.zze(arrayList);
                    break;
                case 6:
                    arrayList = new ArrayList();
                    zzisVar.zzf(arrayList);
                    break;
                case 7:
                    arrayList = new ArrayList();
                    zzisVar.zzg(arrayList);
                    break;
                case 8:
                    arrayList = new ArrayList();
                    zzisVar.zzh(arrayList);
                    break;
                case 9:
                    arrayList = new ArrayList();
                    zzisVar.zzk(arrayList);
                    break;
                case 10:
                    arrayList = new ArrayList();
                    zzisVar.zzm(arrayList);
                    break;
                case 11:
                    arrayList = new ArrayList();
                    zzisVar.zzn(arrayList);
                    break;
                case 12:
                    arrayList = new ArrayList();
                    zzisVar.zzo(arrayList);
                    break;
                case 13:
                    arrayList = new ArrayList();
                    zzisVar.zzp(arrayList);
                    break;
                case 14:
                    arrayList = new ArrayList();
                    zzisVar.zzl(arrayList);
                    ub = (UB) zzit.zza(r0, arrayList, zzgVar.zzxb.zzwg, ub, zzjjVar);
                    break;
                default:
                    String valueOf = String.valueOf(zzgVar.zzxb.zzwh);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
                    sb.append("Type cannot be packed: ");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
            }
            zzgiVar.zza((zzgi<zzgs.zzd>) zzgVar.zzxb, arrayList);
        } else {
            Object obj2 = null;
            if (zzgVar.zzxb.zzwh == zzka.zzabz) {
                int zzdy = zzisVar.zzdy();
                if (zzgVar.zzxb.zzwg.zzg(zzdy) == null) {
                    return (UB) zzit.zza(r0, zzdy, ub, zzjjVar);
                }
                obj2 = Integer.valueOf(zzdy);
            } else {
                switch (zzgg.zzrr[zzgVar.zzxb.zzwh.ordinal()]) {
                    case 1:
                        obj2 = Double.valueOf(zzisVar.readDouble());
                        break;
                    case 2:
                        obj2 = Float.valueOf(zzisVar.readFloat());
                        break;
                    case 3:
                        obj2 = Long.valueOf(zzisVar.zzdx());
                        break;
                    case 4:
                        obj2 = Long.valueOf(zzisVar.zzdw());
                        break;
                    case 5:
                        obj2 = Integer.valueOf(zzisVar.zzdy());
                        break;
                    case 6:
                        obj2 = Long.valueOf(zzisVar.zzdz());
                        break;
                    case 7:
                        obj2 = Integer.valueOf(zzisVar.zzea());
                        break;
                    case 8:
                        obj2 = Boolean.valueOf(zzisVar.zzeb());
                        break;
                    case 9:
                        obj2 = Integer.valueOf(zzisVar.zzee());
                        break;
                    case 10:
                        obj2 = Integer.valueOf(zzisVar.zzeg());
                        break;
                    case 11:
                        obj2 = Long.valueOf(zzisVar.zzeh());
                        break;
                    case 12:
                        obj2 = Integer.valueOf(zzisVar.zzei());
                        break;
                    case 13:
                        obj2 = Long.valueOf(zzisVar.zzej());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        obj2 = zzisVar.zzed();
                        break;
                    case 16:
                        obj2 = zzisVar.readString();
                        break;
                    case 17:
                        obj2 = zzisVar.zzb(zzgVar.zzxa.getClass(), zzgdVar);
                        break;
                    case 18:
                        obj2 = zzisVar.zza(zzgVar.zzxa.getClass(), zzgdVar);
                        break;
                }
            }
            if (zzgVar.zzxb.zzwi) {
                zzgiVar.zzb((zzgi<zzgs.zzd>) zzgVar.zzxb, obj2);
            } else {
                int r5 = zzgg.zzrr[zzgVar.zzxb.zzwh.ordinal()];
                if ((r5 == 17 || r5 == 18) && (zza = zzgiVar.zza((zzgi<zzgs.zzd>) zzgVar.zzxb)) != null) {
                    obj2 = zzgt.zzb(zza, obj2);
                }
                zzgiVar.zza((zzgi<zzgs.zzd>) zzgVar.zzxb, obj2);
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final int zza(Map.Entry<?, ?> entry) {
        return ((zzgs.zzd) entry.getKey()).number;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final void zza(zzkg zzkgVar, Map.Entry<?, ?> entry) throws IOException {
        zzgs.zzd zzdVar = (zzgs.zzd) entry.getKey();
        if (zzdVar.zzwi) {
            switch (zzgg.zzrr[zzdVar.zzwh.ordinal()]) {
                case 1:
                    zzit.zza(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 2:
                    zzit.zzb(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 3:
                    zzit.zzc(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 4:
                    zzit.zzd(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 5:
                    zzit.zzh(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 6:
                    zzit.zzf(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 7:
                    zzit.zzk(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 8:
                    zzit.zzn(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 9:
                    zzit.zzi(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 10:
                    zzit.zzl(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 11:
                    zzit.zzg(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 12:
                    zzit.zzj(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 13:
                    zzit.zze(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 14:
                    zzit.zzh(zzdVar.number, (List) entry.getValue(), zzkgVar, zzdVar.zzwj);
                    return;
                case 15:
                    zzit.zzb(zzdVar.number, (List) entry.getValue(), zzkgVar);
                    return;
                case 16:
                    zzit.zza(zzdVar.number, (List) entry.getValue(), zzkgVar);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list == null || list.isEmpty()) {
                        return;
                    }
                    zzit.zzb(zzdVar.number, (List) entry.getValue(), zzkgVar, zzin.zzho().zzf(list.get(0).getClass()));
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 == null || list2.isEmpty()) {
                        return;
                    }
                    zzit.zza(zzdVar.number, (List) entry.getValue(), zzkgVar, zzin.zzho().zzf(list2.get(0).getClass()));
                    return;
                default:
                    return;
            }
        }
        switch (zzgg.zzrr[zzdVar.zzwh.ordinal()]) {
            case 1:
                zzkgVar.zza(zzdVar.number, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                zzkgVar.zza(zzdVar.number, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                zzkgVar.zzi(zzdVar.number, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzkgVar.zza(zzdVar.number, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                zzkgVar.zzh(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                zzkgVar.zzc(zzdVar.number, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                zzkgVar.zzk(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                zzkgVar.zza(zzdVar.number, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                zzkgVar.zzi(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                zzkgVar.zzr(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                zzkgVar.zzj(zzdVar.number, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                zzkgVar.zzj(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzkgVar.zzb(zzdVar.number, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                zzkgVar.zzh(zzdVar.number, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzkgVar.zza(zzdVar.number, (zzfh) entry.getValue());
                return;
            case 16:
                zzkgVar.zza(zzdVar.number, (String) entry.getValue());
                return;
            case 17:
                zzkgVar.zzb(zzdVar.number, entry.getValue(), zzin.zzho().zzf(entry.getValue().getClass()));
                return;
            case 18:
                zzkgVar.zza(zzdVar.number, entry.getValue(), zzin.zzho().zzf(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final Object zza(zzgd zzgdVar, zzic zzicVar, int r3) {
        return zzgdVar.zza(zzicVar, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final void zza(zzis zzisVar, Object obj, zzgd zzgdVar, zzgi<zzgs.zzd> zzgiVar) throws IOException {
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        zzgiVar.zza((zzgi<zzgs.zzd>) zzgVar.zzxb, zzisVar.zza(zzgVar.zzxa.getClass(), zzgdVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzgf
    public final void zza(zzfh zzfhVar, Object obj, zzgd zzgdVar, zzgi<zzgs.zzd> zzgiVar) throws IOException {
        byte[] bArr;
        zzgs.zzg zzgVar = (zzgs.zzg) obj;
        zzic zzgb = zzgVar.zzxa.zzgj().zzgb();
        int size = zzfhVar.size();
        if (size == 0) {
            bArr = zzgt.zzxc;
        } else {
            byte[] bArr2 = new byte[size];
            zzfhVar.zza(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzfg zzfgVar = new zzfg(wrap, true);
            zzin.zzho().zzu(zzgb).zza(zzgb, zzfgVar, zzgdVar);
            zzgiVar.zza((zzgi<zzgs.zzd>) zzgVar.zzxb, zzgb);
            if (zzfgVar.zzdu() != Integer.MAX_VALUE) {
                throw zzhc.zzgq();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }
}
