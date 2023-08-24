package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzfy implements zzis {
    private int tag;
    private int zzrw;
    private final zzft zzsp;
    private int zzsq = 0;

    public static zzfy zza(zzft zzftVar) {
        if (zzftVar.zzsi != null) {
            return zzftVar.zzsi;
        }
        return new zzfy(zzftVar);
    }

    private zzfy(zzft zzftVar) {
        zzft zzftVar2 = (zzft) zzgt.zza(zzftVar, "input");
        this.zzsp = zzftVar2;
        zzftVar2.zzsi = this;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzdu() throws IOException {
        int r0 = this.zzsq;
        if (r0 != 0) {
            this.tag = r0;
            this.zzsq = 0;
        } else {
            this.tag = this.zzsp.zzex();
        }
        int r02 = this.tag;
        if (r02 == 0 || r02 == this.zzrw) {
            return Integer.MAX_VALUE;
        }
        return r02 >>> 3;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final boolean zzdv() throws IOException {
        int r0;
        if (this.zzsp.zzdt() || (r0 = this.tag) == this.zzrw) {
            return false;
        }
        return this.zzsp.zzar(r0);
    }

    private final void zzaj(int r2) throws IOException {
        if ((this.tag & 7) != r2) {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final double readDouble() throws IOException {
        zzaj(1);
        return this.zzsp.readDouble();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final float readFloat() throws IOException {
        zzaj(5);
        return this.zzsp.readFloat();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdw() throws IOException {
        zzaj(0);
        return this.zzsp.zzdw();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdx() throws IOException {
        zzaj(0);
        return this.zzsp.zzdx();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzdy() throws IOException {
        zzaj(0);
        return this.zzsp.zzdy();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzdz() throws IOException {
        zzaj(1);
        return this.zzsp.zzdz();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzea() throws IOException {
        zzaj(5);
        return this.zzsp.zzea();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final boolean zzeb() throws IOException {
        zzaj(0);
        return this.zzsp.zzeb();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final String readString() throws IOException {
        zzaj(2);
        return this.zzsp.readString();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final String zzec() throws IOException {
        zzaj(2);
        return this.zzsp.zzec();
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

    private final <T> T zzb(zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int zzee = this.zzsp.zzee();
        if (this.zzsp.zzsf >= this.zzsp.zzsg) {
            throw new zzhc("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zzas = this.zzsp.zzas(zzee);
        T newInstance = zzirVar.newInstance();
        this.zzsp.zzsf++;
        zzirVar.zza(newInstance, this, zzgdVar);
        zzirVar.zzg(newInstance);
        this.zzsp.zzaq(0);
        zzft zzftVar = this.zzsp;
        zzftVar.zzsf--;
        this.zzsp.zzat(zzas);
        return newInstance;
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
        zzaj(2);
        return this.zzsp.zzed();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzee() throws IOException {
        zzaj(0);
        return this.zzsp.zzee();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzef() throws IOException {
        zzaj(0);
        return this.zzsp.zzef();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzeg() throws IOException {
        zzaj(5);
        return this.zzsp.zzeg();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzeh() throws IOException {
        zzaj(1);
        return this.zzsp.zzeh();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final int zzei() throws IOException {
        zzaj(0);
        return this.zzsp.zzei();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final long zzej() throws IOException {
        zzaj(0);
        return this.zzsp.zzej();
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zza(List<Double> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgb) {
            zzgb zzgbVar = (zzgb) list;
            int r5 = this.tag & 7;
            if (r5 == 1) {
                do {
                    zzgbVar.zzc(this.zzsp.readDouble());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzak(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzgbVar.zzc(this.zzsp.readDouble());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 1) {
            do {
                list.add(Double.valueOf(this.zzsp.readDouble()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzak(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Double.valueOf(this.zzsp.readDouble()));
            } while (this.zzsp.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzb(List<Float> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgo) {
            zzgo zzgoVar = (zzgo) list;
            int r5 = this.tag & 7;
            if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzal(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzgoVar.zzu(this.zzsp.readFloat());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else if (r5 == 5) {
                do {
                    zzgoVar.zzu(this.zzsp.readFloat());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzal(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Float.valueOf(this.zzsp.readFloat()));
            } while (this.zzsp.zzez() < zzez2);
        } else if (r0 == 5) {
            do {
                list.add(Float.valueOf(this.zzsp.readFloat()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzc(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r5 = this.tag & 7;
            if (r5 == 0) {
                do {
                    zzhqVar.zzac(this.zzsp.zzdw());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzhqVar.zzac(this.zzsp.zzdw());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzdw()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Long.valueOf(this.zzsp.zzdw()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzd(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r5 = this.tag & 7;
            if (r5 == 0) {
                do {
                    zzhqVar.zzac(this.zzsp.zzdx());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzhqVar.zzac(this.zzsp.zzdx());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzdx()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Long.valueOf(this.zzsp.zzdx()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zze(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r3 = this.tag & 7;
            if (r3 == 0) {
                do {
                    zzguVar.zzbl(this.zzsp.zzdy());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r3 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzguVar.zzbl(this.zzsp.zzdy());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzdy()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Integer.valueOf(this.zzsp.zzdy()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzf(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r5 = this.tag & 7;
            if (r5 == 1) {
                do {
                    zzhqVar.zzac(this.zzsp.zzdz());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzak(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzhqVar.zzac(this.zzsp.zzdz());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 1) {
            do {
                list.add(Long.valueOf(this.zzsp.zzdz()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzak(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Long.valueOf(this.zzsp.zzdz()));
            } while (this.zzsp.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzg(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r5 = this.tag & 7;
            if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzal(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzguVar.zzbl(this.zzsp.zzea());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else if (r5 == 5) {
                do {
                    zzguVar.zzbl(this.zzsp.zzea());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzal(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Integer.valueOf(this.zzsp.zzea()));
            } while (this.zzsp.zzez() < zzez2);
        } else if (r0 == 5) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzea()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzh(List<Boolean> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzff) {
            zzff zzffVar = (zzff) list;
            int r3 = this.tag & 7;
            if (r3 == 0) {
                do {
                    zzffVar.addBoolean(this.zzsp.zzeb());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r3 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzffVar.addBoolean(this.zzsp.zzeb());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzsp.zzeb()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Boolean.valueOf(this.zzsp.zzeb()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
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
        int zzex;
        int zzex2;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        }
        if ((list instanceof zzhj) && !z) {
            zzhj zzhjVar = (zzhj) list;
            do {
                zzhjVar.zzc(zzed());
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex2 = this.zzsp.zzex();
            } while (zzex2 == this.tag);
            this.zzsq = zzex2;
            return;
        }
        do {
            list.add(z ? zzec() : readString());
            if (this.zzsp.zzdt()) {
                return;
            }
            zzex = this.zzsp.zzex();
        } while (zzex == this.tag);
        this.zzsq = zzex;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> void zza(List<T> list, zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int zzex;
        int r0 = this.tag;
        if ((r0 & 7) != 2) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzb(zzirVar, zzgdVar));
            if (this.zzsp.zzdt() || this.zzsq != 0) {
                return;
            }
            zzex = this.zzsp.zzex();
        } while (zzex == r0);
        this.zzsq = zzex;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    public final <T> void zzb(List<T> list, zzir<T> zzirVar, zzgd zzgdVar) throws IOException {
        int zzex;
        int r0 = this.tag;
        if ((r0 & 7) != 3) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzd(zzirVar, zzgdVar));
            if (this.zzsp.zzdt() || this.zzsq != 0) {
                return;
            }
            zzex = this.zzsp.zzex();
        } while (zzex == r0);
        this.zzsq = zzex;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzj(List<zzfh> list) throws IOException {
        int zzex;
        if ((this.tag & 7) != 2) {
            throw zzhc.zzgr();
        }
        do {
            list.add(zzed());
            if (this.zzsp.zzdt()) {
                return;
            }
            zzex = this.zzsp.zzex();
        } while (zzex == this.tag);
        this.zzsq = zzex;
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzk(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r3 = this.tag & 7;
            if (r3 == 0) {
                do {
                    zzguVar.zzbl(this.zzsp.zzee());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r3 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzguVar.zzbl(this.zzsp.zzee());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzee()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Integer.valueOf(this.zzsp.zzee()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzl(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r3 = this.tag & 7;
            if (r3 == 0) {
                do {
                    zzguVar.zzbl(this.zzsp.zzef());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r3 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzguVar.zzbl(this.zzsp.zzef());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzef()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Integer.valueOf(this.zzsp.zzef()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzm(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r5 = this.tag & 7;
            if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzal(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzguVar.zzbl(this.zzsp.zzeg());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else if (r5 == 5) {
                do {
                    zzguVar.zzbl(this.zzsp.zzeg());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzal(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Integer.valueOf(this.zzsp.zzeg()));
            } while (this.zzsp.zzez() < zzez2);
        } else if (r0 == 5) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzeg()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzn(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r5 = this.tag & 7;
            if (r5 == 1) {
                do {
                    zzhqVar.zzac(this.zzsp.zzeh());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzee = this.zzsp.zzee();
                zzak(zzee);
                int zzez = this.zzsp.zzez() + zzee;
                do {
                    zzhqVar.zzac(this.zzsp.zzeh());
                } while (this.zzsp.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 1) {
            do {
                list.add(Long.valueOf(this.zzsp.zzeh()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzee2 = this.zzsp.zzee();
            zzak(zzee2);
            int zzez2 = this.zzsp.zzez() + zzee2;
            do {
                list.add(Long.valueOf(this.zzsp.zzeh()));
            } while (this.zzsp.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzo(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            int r3 = this.tag & 7;
            if (r3 == 0) {
                do {
                    zzguVar.zzbl(this.zzsp.zzei());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r3 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzguVar.zzbl(this.zzsp.zzei());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zzsp.zzei()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Integer.valueOf(this.zzsp.zzei()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzis
    public final void zzp(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            int r5 = this.tag & 7;
            if (r5 == 0) {
                do {
                    zzhqVar.zzac(this.zzsp.zzej());
                    if (this.zzsp.zzdt()) {
                        return;
                    }
                    zzex2 = this.zzsp.zzex();
                } while (zzex2 == this.tag);
                this.zzsq = zzex2;
                return;
            } else if (r5 == 2) {
                int zzez = this.zzsp.zzez() + this.zzsp.zzee();
                do {
                    zzhqVar.zzac(this.zzsp.zzej());
                } while (this.zzsp.zzez() < zzez);
                zzam(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int r0 = this.tag & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zzsp.zzej()));
                if (this.zzsp.zzdt()) {
                    return;
                }
                zzex = this.zzsp.zzex();
            } while (zzex == this.tag);
            this.zzsq = zzex;
        } else if (r0 == 2) {
            int zzez2 = this.zzsp.zzez() + this.zzsp.zzee();
            do {
                list.add(Long.valueOf(this.zzsp.zzej()));
            } while (this.zzsp.zzez() < zzez2);
            zzam(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    private static void zzak(int r0) throws IOException {
        if ((r0 & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0063, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzis
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.vision.zzht<K, V> r9, com.google.android.gms.internal.vision.zzgd r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzaj(r0)
            com.google.android.gms.internal.vision.zzft r1 = r7.zzsp
            int r1 = r1.zzee()
            com.google.android.gms.internal.vision.zzft r2 = r7.zzsp
            int r1 = r2.zzas(r1)
            K r2 = r9.zzyn
            V r3 = r9.zzgc
        L14:
            int r4 = r7.zzdu()     // Catch: java.lang.Throwable -> L64
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L5b
            com.google.android.gms.internal.vision.zzft r5 = r7.zzsp     // Catch: java.lang.Throwable -> L64
            boolean r5 = r5.zzdt()     // Catch: java.lang.Throwable -> L64
            if (r5 != 0) goto L5b
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L46
            if (r4 == r0) goto L39
            boolean r4 = r7.zzdv()     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            if (r4 == 0) goto L33
            goto L14
        L33:
            com.google.android.gms.internal.vision.zzhc r4 = new com.google.android.gms.internal.vision.zzhc     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            r4.<init>(r6)     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            throw r4     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
        L39:
            com.google.android.gms.internal.vision.zzka r4 = r9.zzyo     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            V r5 = r9.zzgc     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            java.lang.Class r5 = r5.getClass()     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            java.lang.Object r3 = r7.zza(r4, r5, r10)     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            goto L14
        L46:
            com.google.android.gms.internal.vision.zzka r4 = r9.zzym     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            r5 = 0
            java.lang.Object r2 = r7.zza(r4, r5, r5)     // Catch: com.google.android.gms.internal.vision.zzhb -> L4e java.lang.Throwable -> L64
            goto L14
        L4e:
            boolean r4 = r7.zzdv()     // Catch: java.lang.Throwable -> L64
            if (r4 == 0) goto L55
            goto L14
        L55:
            com.google.android.gms.internal.vision.zzhc r8 = new com.google.android.gms.internal.vision.zzhc     // Catch: java.lang.Throwable -> L64
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L64
            throw r8     // Catch: java.lang.Throwable -> L64
        L5b:
            r8.put(r2, r3)     // Catch: java.lang.Throwable -> L64
            com.google.android.gms.internal.vision.zzft r8 = r7.zzsp
            r8.zzat(r1)
            return
        L64:
            r8 = move-exception
            com.google.android.gms.internal.vision.zzft r9 = r7.zzsp
            r9.zzat(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfy.zza(java.util.Map, com.google.android.gms.internal.vision.zzht, com.google.android.gms.internal.vision.zzgd):void");
    }

    private final Object zza(zzka zzkaVar, Class<?> cls, zzgd zzgdVar) throws IOException {
        switch (zzfx.zzrr[zzkaVar.ordinal()]) {
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
                return zzec();
            case 16:
                return Integer.valueOf(zzee());
            case 17:
                return Long.valueOf(zzdw());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzal(int r0) throws IOException {
        if ((r0 & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    private final void zzam(int r2) throws IOException {
        if (this.zzsp.zzez() != r2) {
            throw zzhc.zzgm();
        }
    }
}
