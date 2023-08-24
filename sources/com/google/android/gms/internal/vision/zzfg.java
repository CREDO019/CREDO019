package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzfg extends zzfe {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzru;
    private final int zzrv;
    private int zzrw;

    public zzfg(ByteBuffer byteBuffer, boolean z) {
        super(null);
        this.zzru = true;
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzrv = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzdt() {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzdu() throws IOException {
        if (zzdt()) {
            return Integer.MAX_VALUE;
        }
        int zzek = zzek();
        this.tag = zzek;
        if (zzek == this.zzrw) {
            return Integer.MAX_VALUE;
        }
        return zzek >>> 3;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final boolean zzdv() throws IOException {
        int r0;
        int r2;
        if (zzdt() || (r0 = this.tag) == (r2 = this.zzrw)) {
            return false;
        }
        int r3 = r0 & 7;
        if (r3 == 0) {
            int r02 = this.limit;
            int r22 = this.pos;
            if (r02 - r22 >= 10) {
                byte[] bArr = this.buffer;
                int r5 = 0;
                while (r5 < 10) {
                    int r6 = r22 + 1;
                    if (bArr[r22] >= 0) {
                        this.pos = r6;
                        break;
                    }
                    r5++;
                    r22 = r6;
                }
            }
            for (int r1 = 0; r1 < 10; r1++) {
                if (readByte() >= 0) {
                    return true;
                }
            }
            throw zzhc.zzgo();
        } else if (r3 == 1) {
            zzah(8);
            return true;
        } else if (r3 == 2) {
            zzah(zzek());
            return true;
        } else if (r3 == 3) {
            this.zzrw = ((r0 >>> 3) << 3) | 4;
            while (zzdu() != Integer.MAX_VALUE && zzdv()) {
            }
            if (this.tag != this.zzrw) {
                throw zzhc.zzgs();
            }
            this.zzrw = r2;
            return true;
        } else {
            if (r3 == 5) {
                zzah(4);
                return true;
            }
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final double readDouble() throws IOException {
        zzaj(1);
        return Double.longBitsToDouble(zzeo());
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final float readFloat() throws IOException {
        zzaj(5);
        return Float.intBitsToFloat(zzen());
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdw() throws IOException {
        zzaj(0);
        return zzel();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdx() throws IOException {
        zzaj(0);
        return zzel();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzdy() throws IOException {
        zzaj(0);
        return zzek();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdz() throws IOException {
        zzaj(1);
        return zzeo();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzea() throws IOException {
        zzaj(5);
        return zzen();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final boolean zzeb() throws IOException {
        zzaj(0);
        return zzek() != 0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final String readString() throws IOException {
        return zzj(false);
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final String zzec() throws IOException {
        return zzj(true);
    }

    private final String zzj(boolean z) throws IOException {
        zzaj(2);
        int zzek = zzek();
        if (zzek == 0) {
            return "";
        }
        zzai(zzek);
        if (z) {
            byte[] bArr = this.buffer;
            int r1 = this.pos;
            if (!zzjs.zzf(bArr, r1, r1 + zzek)) {
                throw zzhc.zzgt();
            }
        }
        String str = new String(this.buffer, this.pos, zzek, zzgt.UTF_8);
        this.pos += zzek;
        return str;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> T zza(Class<T> cls, zzgd zzgdVar) throws IOException {
        zzaj(2);
        return (T) zzb(zzin.zzho().zzf(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> T zza(zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        zzaj(2);
        return (T) zzb(zzirVar, zzgdVar);
    }

    private final <T> T zzb(zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int zzek = zzek();
        zzai(zzek);
        int r1 = this.limit;
        int r2 = this.pos + zzek;
        this.limit = r2;
        try {
            T newInstance = zzirVar.newInstance();
            zzirVar.zza(newInstance, this, zzgdVar);
            zzirVar.zzg(newInstance);
            if (this.pos == r2) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.limit = r1;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> T zzb(Class<T> cls, zzgd zzgdVar) throws IOException {
        zzaj(3);
        return (T) zzd(zzin.zzho().zzf(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> T zzc(zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        zzaj(3);
        return (T) zzd(zzirVar, zzgdVar);
    }

    private final <T> T zzd(zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int r0 = this.zzrw;
        this.zzrw = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzirVar.newInstance();
            zzirVar.zza(newInstance, this, zzgdVar);
            zzirVar.zzg(newInstance);
            if (this.tag == this.zzrw) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.zzrw = r0;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final zzfh zzed() throws IOException {
        zzfh zza;
        zzaj(2);
        int zzek = zzek();
        if (zzek == 0) {
            return zzfh.zzrx;
        }
        zzai(zzek);
        if (this.zzru) {
            zza = zzfh.zzb(this.buffer, this.pos, zzek);
        } else {
            zza = zzfh.zza(this.buffer, this.pos, zzek);
        }
        this.pos += zzek;
        return zza;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzee() throws IOException {
        zzaj(0);
        return zzek();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzef() throws IOException {
        zzaj(0);
        return zzek();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzeg() throws IOException {
        zzaj(5);
        return zzen();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzeh() throws IOException {
        zzaj(1);
        return zzeo();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzei() throws IOException {
        zzaj(0);
        return zzft.zzau(zzek());
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzej() throws IOException {
        zzaj(0);
        return zzft.zzr(zzel());
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zza(List<Double> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzgb) {
            zzgb zzgbVar = (zzgb) list;
            int r52 = this.tag & 7;
            if (r52 == 1) {
                do {
                    zzgbVar.zzc(readDouble());
                    if (zzdt()) {
                        return;
                    }
                    r5 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r5;
                return;
            } else if (r52 == 2) {
                int zzek = zzek();
                zzak(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzgbVar.zzc(Double.longBitsToDouble(zzeq()));
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 1) {
            do {
                list.add(Double.valueOf(readDouble()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = zzek();
            zzak(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Double.valueOf(Double.longBitsToDouble(zzeq())));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzb(List<Float> list) throws IOException {
        int r0;
        int r02;
        if (list instanceof zzgo) {
            zzgo zzgoVar = (zzgo) list;
            int r03 = this.tag & 7;
            if (r03 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzgoVar.zzu(Float.intBitsToFloat(zzep()));
                }
                return;
            } else if (r03 == 5) {
                do {
                    zzgoVar.zzu(readFloat());
                    if (zzdt()) {
                        return;
                    }
                    r02 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r02;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r04 = this.tag & 7;
        if (r04 == 2) {
            int zzek2 = zzek();
            zzal(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Float.valueOf(Float.intBitsToFloat(zzep())));
            }
        } else if (r04 == 5) {
            do {
                list.add(Float.valueOf(readFloat()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzc(List<Long> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r52 = this.tag & 7;
            if (r52 == 0) {
                do {
                    zzhqVar.zzac(zzdw());
                    if (zzdt()) {
                        return;
                    }
                    r5 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r5;
                return;
            } else if (r52 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzhqVar.zzac(zzel());
                }
                zzam(zzek);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 0) {
            do {
                list.add(Long.valueOf(zzdw()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = this.pos + zzek();
            while (this.pos < zzek2) {
                list.add(Long.valueOf(zzel()));
            }
            zzam(zzek2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzd(List<Long> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r52 = this.tag & 7;
            if (r52 == 0) {
                do {
                    zzhqVar.zzac(zzdx());
                    if (zzdt()) {
                        return;
                    }
                    r5 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r5;
                return;
            } else if (r52 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzhqVar.zzac(zzel());
                }
                zzam(zzek);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 0) {
            do {
                list.add(Long.valueOf(zzdx()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = this.pos + zzek();
            while (this.pos < zzek2) {
                list.add(Long.valueOf(zzel()));
            }
            zzam(zzek2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zze(List<Integer> list) throws IOException {
        int r0;
        int r4;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r42 = this.tag & 7;
            if (r42 == 0) {
                do {
                    zzguVar.zzbl(zzdy());
                    if (zzdt()) {
                        return;
                    }
                    r4 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r4;
                return;
            } else if (r42 == 2) {
                int zzek = this.pos + zzek();
                while (this.pos < zzek) {
                    zzguVar.zzbl(zzek());
                }
                zzam(zzek);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 0) {
            do {
                list.add(Integer.valueOf(zzdy()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = this.pos + zzek();
            while (this.pos < zzek2) {
                list.add(Integer.valueOf(zzek()));
            }
            zzam(zzek2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzf(List<Long> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r52 = this.tag & 7;
            if (r52 == 1) {
                do {
                    zzhqVar.zzac(zzdz());
                    if (zzdt()) {
                        return;
                    }
                    r5 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r5;
                return;
            } else if (r52 == 2) {
                int zzek = zzek();
                zzak(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzhqVar.zzac(zzeq());
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 1) {
            do {
                list.add(Long.valueOf(zzdz()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = zzek();
            zzak(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Long.valueOf(zzeq()));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzg(List<Integer> list) throws IOException {
        int r0;
        int r02;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r03 = this.tag & 7;
            if (r03 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzguVar.zzbl(zzep());
                }
                return;
            } else if (r03 == 5) {
                do {
                    zzguVar.zzbl(zzea());
                    if (zzdt()) {
                        return;
                    }
                    r02 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r02;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r04 = this.tag & 7;
        if (r04 == 2) {
            int zzek2 = zzek();
            zzal(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Integer.valueOf(zzep()));
            }
        } else if (r04 == 5) {
            do {
                list.add(Integer.valueOf(zzea()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzh(List<Boolean> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int r52 = this.tag & 7;
            if (r52 != 0) {
                if (r52 == 2) {
                    int zzek = this.pos + zzek();
                    while (this.pos < zzek) {
                        zzffVar.addBoolean(zzek() != 0);
                    }
                    zzam(zzek);
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                zzffVar.addBoolean(zzeb());
                if (zzdt()) {
                    return;
                }
                r5 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r5;
            return;
        }
        int r02 = this.tag & 7;
        if (r02 != 0) {
            if (r02 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Boolean.valueOf(zzek() != 0));
                }
                zzam(zzek2);
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Boolean.valueOf(zzeb()));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzi(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int r0;
        int r4;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        }
        if ((list instanceof zzhj) && !z) {
            zzhj zzhjVar = (zzhj) list;
            do {
                zzhjVar.zzc(zzed());
                if (zzdt()) {
                    return;
                }
                r4 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r4;
            return;
        }
        do {
            list.add(zzj(z));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> void zza(List<T> list, zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int r1;
        int r0 = this.tag;
        if ((r0 & 7) != 2) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzb(zzirVar, zzgdVar));
            if (zzdt()) {
                return;
            }
            r1 = this.pos;
        } while (zzek() == r0);
        this.pos = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> void zzb(List<T> list, zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int r1;
        int r0 = this.tag;
        if ((r0 & 7) != 3) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzd(zzirVar, zzgdVar));
            if (zzdt()) {
                return;
            }
            r1 = this.pos;
        } while (zzek() == r0);
        this.pos = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzj(List<zzfh> list) throws IOException {
        int r0;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzed());
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzk(List<Integer> list) throws IOException {
        int r0;
        int r4;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r42 = this.tag & 7;
            if (r42 != 0) {
                if (r42 == 2) {
                    int zzek = this.pos + zzek();
                    while (this.pos < zzek) {
                        zzguVar.zzbl(zzek());
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                zzguVar.zzbl(zzee());
                if (zzdt()) {
                    return;
                }
                r4 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r4;
            return;
        }
        int r02 = this.tag & 7;
        if (r02 != 0) {
            if (r02 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzek()));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(zzee()));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzl(List<Integer> list) throws IOException {
        int r0;
        int r4;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r42 = this.tag & 7;
            if (r42 != 0) {
                if (r42 == 2) {
                    int zzek = this.pos + zzek();
                    while (this.pos < zzek) {
                        zzguVar.zzbl(zzek());
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                zzguVar.zzbl(zzef());
                if (zzdt()) {
                    return;
                }
                r4 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r4;
            return;
        }
        int r02 = this.tag & 7;
        if (r02 != 0) {
            if (r02 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzek()));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(zzef()));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzm(List<Integer> list) throws IOException {
        int r0;
        int r02;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r03 = this.tag & 7;
            if (r03 == 2) {
                int zzek = zzek();
                zzal(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzguVar.zzbl(zzep());
                }
                return;
            } else if (r03 == 5) {
                do {
                    zzguVar.zzbl(zzeg());
                    if (zzdt()) {
                        return;
                    }
                    r02 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r02;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r04 = this.tag & 7;
        if (r04 == 2) {
            int zzek2 = zzek();
            zzal(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Integer.valueOf(zzep()));
            }
        } else if (r04 == 5) {
            do {
                list.add(Integer.valueOf(zzeg()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzn(List<Long> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r52 = this.tag & 7;
            if (r52 == 1) {
                do {
                    zzhqVar.zzac(zzeh());
                    if (zzdt()) {
                        return;
                    }
                    r5 = this.pos;
                } while (zzek() == this.tag);
                this.pos = r5;
                return;
            } else if (r52 == 2) {
                int zzek = zzek();
                zzak(zzek);
                int r1 = this.pos + zzek;
                while (this.pos < r1) {
                    zzhqVar.zzac(zzeq());
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r02 = this.tag & 7;
        if (r02 == 1) {
            do {
                list.add(Long.valueOf(zzeh()));
                if (zzdt()) {
                    return;
                }
                r0 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r0;
        } else if (r02 == 2) {
            int zzek2 = zzek();
            zzak(zzek2);
            int r12 = this.pos + zzek2;
            while (this.pos < r12) {
                list.add(Long.valueOf(zzeq()));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzo(List<Integer> list) throws IOException {
        int r0;
        int r4;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r42 = this.tag & 7;
            if (r42 != 0) {
                if (r42 == 2) {
                    int zzek = this.pos + zzek();
                    while (this.pos < zzek) {
                        zzguVar.zzbl(zzft.zzau(zzek()));
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                zzguVar.zzbl(zzei());
                if (zzdt()) {
                    return;
                }
                r4 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r4;
            return;
        }
        int r02 = this.tag & 7;
        if (r02 != 0) {
            if (r02 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Integer.valueOf(zzft.zzau(zzek())));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(zzei()));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzp(List<Long> list) throws IOException {
        int r0;
        int r5;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r52 = this.tag & 7;
            if (r52 != 0) {
                if (r52 == 2) {
                    int zzek = this.pos + zzek();
                    while (this.pos < zzek) {
                        zzhqVar.zzac(zzft.zzr(zzel()));
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                zzhqVar.zzac(zzej());
                if (zzdt()) {
                    return;
                }
                r5 = this.pos;
            } while (zzek() == this.tag);
            this.pos = r5;
            return;
        }
        int r02 = this.tag & 7;
        if (r02 != 0) {
            if (r02 == 2) {
                int zzek2 = this.pos + zzek();
                while (this.pos < zzek2) {
                    list.add(Long.valueOf(zzft.zzr(zzel())));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Long.valueOf(zzej()));
            if (zzdt()) {
                return;
            }
            r0 = this.pos;
        } while (zzek() == this.tag);
        this.pos = r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    public final <K, V> void zza(Map<K, V> map, zzht<K, V> zzhtVar, zzgd zzgdVar) throws IOException {
        zzaj(2);
        int zzek = zzek();
        zzai(zzek);
        int r2 = this.limit;
        this.limit = this.pos + zzek;
        try {
            Object obj = zzhtVar.zzyn;
            Object obj2 = zzhtVar.zzgc;
            while (true) {
                int zzdu = zzdu();
                if (zzdu == Integer.MAX_VALUE) {
                    map.put(obj, obj2);
                    return;
                } else if (zzdu == 1) {
                    obj = zza(zzhtVar.zzym, (Class<?>) null, (zzgd) null);
                } else if (zzdu == 2) {
                    obj2 = zza(zzhtVar.zzyo, zzhtVar.zzgc.getClass(), zzgdVar);
                } else {
                    try {
                        if (!zzdv()) {
                            throw new zzhc("Unable to parse map entry.");
                            break;
                        }
                    } catch (zzhb unused) {
                        if (!zzdv()) {
                            throw new zzhc("Unable to parse map entry.");
                        }
                    }
                }
            }
        } finally {
            this.limit = r2;
        }
    }

    private final Object zza(zzka zzkaVar, Class<?> cls, zzgd zzgdVar) throws IOException {
        switch (zzfd.zzrr[zzkaVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzeb());
            case 2:
                return zzed();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzef());
            case 5:
                return Integer.valueOf(zzea());
            case 6:
                return Long.valueOf(zzdz());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzdy());
            case 9:
                return Long.valueOf(zzdx());
            case 10:
                return zza(cls, zzgdVar);
            case 11:
                return Integer.valueOf(zzeg());
            case 12:
                return Long.valueOf(zzeh());
            case 13:
                return Integer.valueOf(zzei());
            case 14:
                return Long.valueOf(zzej());
            case 15:
                return zzj(true);
            case 16:
                return Integer.valueOf(zzee());
            case 17:
                return Long.valueOf(zzdw());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzek() throws IOException {
        int r0;
        int r02 = this.pos;
        int r1 = this.limit;
        if (r1 == r02) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        int r3 = r02 + 1;
        byte b = bArr[r02];
        if (b >= 0) {
            this.pos = r3;
            return b;
        } else if (r1 - r3 < 9) {
            return (int) zzem();
        } else {
            int r12 = r3 + 1;
            int r03 = b ^ (bArr[r3] << 7);
            if (r03 < 0) {
                r0 = r03 ^ (-128);
            } else {
                int r32 = r12 + 1;
                int r04 = r03 ^ (bArr[r12] << Ascii.f1129SO);
                if (r04 >= 0) {
                    r0 = r04 ^ 16256;
                } else {
                    r12 = r32 + 1;
                    int r05 = r04 ^ (bArr[r32] << Ascii.NAK);
                    if (r05 < 0) {
                        r0 = r05 ^ (-2080896);
                    } else {
                        r32 = r12 + 1;
                        byte b2 = bArr[r12];
                        r0 = (r05 ^ (b2 << Ascii.f1122FS)) ^ 266354560;
                        if (b2 < 0) {
                            r12 = r32 + 1;
                            if (bArr[r32] < 0) {
                                r32 = r12 + 1;
                                if (bArr[r12] < 0) {
                                    r12 = r32 + 1;
                                    if (bArr[r32] < 0) {
                                        r32 = r12 + 1;
                                        if (bArr[r12] < 0) {
                                            r12 = r32 + 1;
                                            if (bArr[r32] < 0) {
                                                throw zzhc.zzgo();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                r12 = r32;
            }
            this.pos = r12;
            return r0;
        }
    }

    private final long zzel() throws IOException {
        long j;
        long j2;
        long j3;
        int r0;
        int r02 = this.pos;
        int r1 = this.limit;
        if (r1 == r02) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        int r3 = r02 + 1;
        byte b = bArr[r02];
        if (b >= 0) {
            this.pos = r3;
            return b;
        } else if (r1 - r3 < 9) {
            return zzem();
        } else {
            int r12 = r3 + 1;
            int r03 = b ^ (bArr[r3] << 7);
            if (r03 >= 0) {
                int r32 = r12 + 1;
                int r04 = r03 ^ (bArr[r12] << Ascii.f1129SO);
                if (r04 >= 0) {
                    r12 = r32;
                    j = r04 ^ 16256;
                } else {
                    r12 = r32 + 1;
                    int r05 = r04 ^ (bArr[r32] << Ascii.NAK);
                    if (r05 < 0) {
                        r0 = r05 ^ (-2080896);
                    } else {
                        long j4 = r05;
                        int r06 = r12 + 1;
                        long j5 = j4 ^ (bArr[r12] << 28);
                        if (j5 >= 0) {
                            j3 = 266354560;
                        } else {
                            r12 = r06 + 1;
                            long j6 = j5 ^ (bArr[r06] << 35);
                            if (j6 < 0) {
                                j2 = -34093383808L;
                            } else {
                                r06 = r12 + 1;
                                j5 = j6 ^ (bArr[r12] << 42);
                                if (j5 >= 0) {
                                    j3 = 4363953127296L;
                                } else {
                                    r12 = r06 + 1;
                                    j6 = j5 ^ (bArr[r06] << 49);
                                    if (j6 < 0) {
                                        j2 = -558586000294016L;
                                    } else {
                                        int r07 = r12 + 1;
                                        long j7 = (j6 ^ (bArr[r12] << 56)) ^ 71499008037633920L;
                                        if (j7 < 0) {
                                            r12 = r07 + 1;
                                            if (bArr[r07] < 0) {
                                                throw zzhc.zzgo();
                                            }
                                        } else {
                                            r12 = r07;
                                        }
                                        j = j7;
                                    }
                                }
                            }
                            j = j6 ^ j2;
                        }
                        j = j5 ^ j3;
                        r12 = r06;
                    }
                }
                this.pos = r12;
                return j;
            }
            r0 = r03 ^ (-128);
            j = r0;
            this.pos = r12;
            return j;
        }
    }

    private final long zzem() throws IOException {
        long j = 0;
        for (int r2 = 0; r2 < 64; r2 += 7) {
            byte readByte = readByte();
            j |= (readByte & Byte.MAX_VALUE) << r2;
            if ((readByte & 128) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    private final byte readByte() throws IOException {
        int r0 = this.pos;
        if (r0 == this.limit) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        this.pos = r0 + 1;
        return bArr[r0];
    }

    private final int zzen() throws IOException {
        zzai(4);
        return zzep();
    }

    private final long zzeo() throws IOException {
        zzai(8);
        return zzeq();
    }

    private final int zzep() {
        int r0 = this.pos;
        byte[] bArr = this.buffer;
        this.pos = r0 + 4;
        return ((bArr[r0 + 3] & 255) << 24) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16);
    }

    private final long zzeq() {
        int r0 = this.pos;
        byte[] bArr = this.buffer;
        this.pos = r0 + 8;
        return ((bArr[r0 + 7] & 255) << 56) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48);
    }

    private final void zzah(int r2) throws IOException {
        zzai(r2);
        this.pos += r2;
    }

    private final void zzai(int r3) throws IOException {
        if (r3 < 0 || r3 > this.limit - this.pos) {
            throw zzhc.zzgm();
        }
    }

    private final void zzaj(int r2) throws IOException {
        if ((this.tag & 7) != r2) {
            throw zzhc.zzgr();
        }
    }

    private final void zzak(int r1) throws IOException {
        zzai(r1);
        if ((r1 & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzal(int r1) throws IOException {
        zzai(r1);
        if ((r1 & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzam(int r2) throws IOException {
        if (this.pos != r2) {
            throw zzhc.zzgm();
        }
    }
}
