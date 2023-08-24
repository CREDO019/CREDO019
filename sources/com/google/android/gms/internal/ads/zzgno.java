package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgno implements zzgqi {
    private final zzgnn zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzgno(zzgnn zzgnnVar) {
        zzgox.zzf(zzgnnVar, "input");
        this.zza = zzgnnVar;
        zzgnnVar.zzc = this;
    }

    private final Object zzP(zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        int r0 = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            Object zze = zzgqqVar.zze();
            zzgqqVar.zzh(zze, this, zzgnzVar);
            zzgqqVar.zzf(zze);
            if (this.zzb == this.zzc) {
                return zze;
            }
            throw zzgoz.zzg();
        } finally {
            this.zzc = r0;
        }
    }

    private final Object zzQ(zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        zzgnn zzgnnVar;
        int zzn = this.zza.zzn();
        zzgnn zzgnnVar2 = this.zza;
        if (zzgnnVar2.zza >= zzgnnVar2.zzb) {
            throw new zzgoz("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zze = zzgnnVar2.zze(zzn);
        Object zze2 = zzgqqVar.zze();
        this.zza.zza++;
        zzgqqVar.zzh(zze2, this, zzgnzVar);
        zzgqqVar.zzf(zze2);
        this.zza.zzz(0);
        zzgnnVar.zza--;
        this.zza.zzA(zze);
        return zze2;
    }

    private final void zzR(int r2) throws IOException {
        if (this.zza.zzd() != r2) {
            throw zzgoz.zzj();
        }
    }

    private final void zzS(int r2) throws IOException {
        if ((this.zzb & 7) != r2) {
            throw zzgoz.zza();
        }
    }

    private static final void zzT(int r0) throws IOException {
        if ((r0 & 3) != 0) {
            throw zzgoz.zzg();
        }
    }

    private static final void zzU(int r0) throws IOException {
        if ((r0 & 7) != 0) {
            throw zzgoz.zzg();
        }
    }

    public static zzgno zzq(zzgnn zzgnnVar) {
        zzgno zzgnoVar = zzgnnVar.zzc;
        return zzgnoVar != null ? zzgnoVar : new zzgno(zzgnnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzA(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            int r5 = this.zzb & 7;
            if (r5 == 1) {
                do {
                    zzgpmVar.zzg(this.zza.zzo());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    zzgpmVar.zzg(this.zza.zzo());
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzo()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                list.add(Long.valueOf(this.zza.zzo()));
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzB(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzgog)) {
            int r0 = this.zzb & 7;
            if (r0 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (r0 != 5) {
                throw zzgoz.zza();
            } else {
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzgog zzgogVar = (zzgog) list;
        int r5 = this.zzb & 7;
        if (r5 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzgogVar.zze(this.zza.zzc());
            } while (this.zza.zzd() < zzd2);
        } else if (r5 != 5) {
            throw zzgoz.zza();
        } else {
            do {
                zzgogVar.zze(this.zza.zzc());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    @Deprecated
    public final void zzC(List list, zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        int zzm;
        int r0 = this.zzb;
        if ((r0 & 7) != 3) {
            throw zzgoz.zza();
        }
        do {
            list.add(zzP(zzgqqVar, zzgnzVar));
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == r0);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzD(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            int r3 = this.zzb & 7;
            if (r3 == 0) {
                do {
                    zzgooVar.zzh(this.zza.zzh());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r3 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgooVar.zzh(this.zza.zzh());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzE(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            int r5 = this.zzb & 7;
            if (r5 == 0) {
                do {
                    zzgpmVar.zzg(this.zza.zzp());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgpmVar.zzg(this.zza.zzp());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzp()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Long.valueOf(this.zza.zzp()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzF(List list, zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        int zzm;
        int r0 = this.zzb;
        if ((r0 & 7) != 2) {
            throw zzgoz.zza();
        }
        do {
            list.add(zzQ(zzgqqVar, zzgnzVar));
            if (this.zza.zzC() || this.zzd != 0) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == r0);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzG(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzgoo)) {
            int r0 = this.zzb & 7;
            if (r0 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (r0 != 5) {
                throw zzgoz.zza();
            } else {
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzgoo zzgooVar = (zzgoo) list;
        int r5 = this.zzb & 7;
        if (r5 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzgooVar.zzh(this.zza.zzk());
            } while (this.zza.zzd() < zzd2);
        } else if (r5 != 5) {
            throw zzgoz.zza();
        } else {
            do {
                zzgooVar.zzh(this.zza.zzk());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzH(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            int r5 = this.zzb & 7;
            if (r5 == 1) {
                do {
                    zzgpmVar.zzg(this.zza.zzt());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    zzgpmVar.zzg(this.zza.zzt());
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzt()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                list.add(Long.valueOf(this.zza.zzt()));
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzI(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            int r3 = this.zzb & 7;
            if (r3 == 0) {
                do {
                    zzgooVar.zzh(this.zza.zzl());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r3 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgooVar.zzh(this.zza.zzl());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzl()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Integer.valueOf(this.zza.zzl()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzJ(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            int r5 = this.zzb & 7;
            if (r5 == 0) {
                do {
                    zzgpmVar.zzg(this.zza.zzu());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgpmVar.zzg(this.zza.zzu());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzu()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Long.valueOf(this.zza.zzu()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzm;
        int zzm2;
        if ((this.zzb & 7) != 2) {
            throw zzgoz.zza();
        }
        if (!(list instanceof zzgpf) || z) {
            do {
                list.add(z ? zzu() : zzt());
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        zzgpf zzgpfVar = (zzgpf) list;
        do {
            zzgpfVar.zzi(zzp());
            if (this.zza.zzC()) {
                return;
            }
            zzm2 = this.zza.zzm();
        } while (zzm2 == this.zzb);
        this.zzd = zzm2;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzL(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            int r3 = this.zzb & 7;
            if (r3 == 0) {
                do {
                    zzgooVar.zzh(this.zza.zzn());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r3 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgooVar.zzh(this.zza.zzn());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzM(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            int r5 = this.zzb & 7;
            if (r5 == 0) {
                do {
                    zzgpmVar.zzg(this.zza.zzv());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgpmVar.zzg(this.zza.zzv());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzv()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Long.valueOf(this.zza.zzv()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzD();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final boolean zzO() throws IOException {
        int r0;
        if (this.zza.zzC() || (r0 = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(r0);
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final double zza() throws IOException {
        zzS(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final float zzb() throws IOException {
        zzS(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzc() throws IOException {
        int r0 = this.zzd;
        if (r0 != 0) {
            this.zzb = r0;
            this.zzd = 0;
        } else {
            r0 = this.zza.zzm();
            this.zzb = r0;
        }
        if (r0 == 0 || r0 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return r0 >>> 3;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zze() throws IOException {
        zzS(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzf() throws IOException {
        zzS(5);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzg() throws IOException {
        zzS(0);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzh() throws IOException {
        zzS(5);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzi() throws IOException {
        zzS(0);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final int zzj() throws IOException {
        zzS(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final long zzk() throws IOException {
        zzS(1);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final long zzl() throws IOException {
        zzS(0);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final long zzm() throws IOException {
        zzS(1);
        return this.zza.zzt();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final long zzn() throws IOException {
        zzS(0);
        return this.zza.zzu();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final long zzo() throws IOException {
        zzS(0);
        return this.zza.zzv();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final zzgnf zzp() throws IOException {
        zzS(2);
        return this.zza.zzw();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    @Deprecated
    public final Object zzr(zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        zzS(3);
        return zzP(zzgqqVar, zzgnzVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final Object zzs(zzgqq zzgqqVar, zzgnz zzgnzVar) throws IOException {
        zzS(2);
        return zzQ(zzgqqVar, zzgnzVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final String zzt() throws IOException {
        zzS(2);
        return this.zza.zzx();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final String zzu() throws IOException {
        zzS(2);
        return this.zza.zzy();
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzv(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgmt) {
            zzgmt zzgmtVar = (zzgmt) list;
            int r3 = this.zzb & 7;
            if (r3 == 0) {
                do {
                    zzgmtVar.zze(this.zza.zzD());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r3 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgmtVar.zze(this.zza.zzD());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Boolean.valueOf(this.zza.zzD()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Boolean.valueOf(this.zza.zzD()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzw(List list) throws IOException {
        int zzm;
        if ((this.zzb & 7) != 2) {
            throw zzgoz.zza();
        }
        do {
            list.add(zzp());
            if (this.zza.zzC()) {
                return;
            }
            zzm = this.zza.zzm();
        } while (zzm == this.zzb);
        this.zzd = zzm;
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzx(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgnw) {
            zzgnw zzgnwVar = (zzgnw) list;
            int r5 = this.zzb & 7;
            if (r5 == 1) {
                do {
                    zzgnwVar.zze(this.zza.zzb());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r5 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    zzgnwVar.zze(this.zza.zzb());
                } while (this.zza.zzd() < zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 1) {
            do {
                list.add(Double.valueOf(this.zza.zzb()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzn2 = this.zza.zzn();
            zzU(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                list.add(Double.valueOf(this.zza.zzb()));
            } while (this.zza.zzd() < zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzy(List list) throws IOException {
        int zzm;
        int zzm2;
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            int r3 = this.zzb & 7;
            if (r3 == 0) {
                do {
                    zzgooVar.zzh(this.zza.zzf());
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm2 = this.zza.zzm();
                } while (zzm2 == this.zzb);
                this.zzd = zzm2;
                return;
            } else if (r3 == 2) {
                int zzd = this.zza.zzd() + this.zza.zzn();
                do {
                    zzgooVar.zzh(this.zza.zzf());
                } while (this.zza.zzd() < zzd);
                zzR(zzd);
                return;
            } else {
                throw zzgoz.zza();
            }
        }
        int r0 = this.zzb & 7;
        if (r0 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
                if (this.zza.zzC()) {
                    return;
                }
                zzm = this.zza.zzm();
            } while (zzm == this.zzb);
            this.zzd = zzm;
        } else if (r0 == 2) {
            int zzd2 = this.zza.zzd() + this.zza.zzn();
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
            } while (this.zza.zzd() < zzd2);
            zzR(zzd2);
        } else {
            throw zzgoz.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqi
    public final void zzz(List list) throws IOException {
        int zzm;
        int zzm2;
        if (!(list instanceof zzgoo)) {
            int r0 = this.zzb & 7;
            if (r0 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd = this.zza.zzd() + zzn;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzd() < zzd);
                return;
            } else if (r0 != 5) {
                throw zzgoz.zza();
            } else {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    if (this.zza.zzC()) {
                        return;
                    }
                    zzm = this.zza.zzm();
                } while (zzm == this.zzb);
                this.zzd = zzm;
                return;
            }
        }
        zzgoo zzgooVar = (zzgoo) list;
        int r5 = this.zzb & 7;
        if (r5 == 2) {
            int zzn2 = this.zza.zzn();
            zzT(zzn2);
            int zzd2 = this.zza.zzd() + zzn2;
            do {
                zzgooVar.zzh(this.zza.zzg());
            } while (this.zza.zzd() < zzd2);
        } else if (r5 != 5) {
            throw zzgoz.zza();
        } else {
            do {
                zzgooVar.zzh(this.zza.zzg());
                if (this.zza.zzC()) {
                    return;
                }
                zzm2 = this.zza.zzm();
            } while (zzm2 == this.zzb);
            this.zzd = zzm2;
        }
    }
}
