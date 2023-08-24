package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzayj implements zzave {
    private zzayh zze;
    private zzayh zzf;
    private zzass zzg;
    private zzass zzh;
    private long zzi;
    private zzayi zzk;
    private final zzazl zzl;
    private final zzayg zza = new zzayg();
    private final zzayf zzb = new zzayf();
    private final zzbag zzc = new zzbag(32);
    private final AtomicInteger zzd = new AtomicInteger();
    private int zzj = 65536;

    public zzayj(zzazl zzazlVar, byte[] bArr) {
        this.zzl = zzazlVar;
        zzayh zzayhVar = new zzayh(0L, 65536);
        this.zze = zzayhVar;
        this.zzf = zzayhVar;
    }

    private final int zzo(int r7) {
        if (this.zzj == 65536) {
            this.zzj = 0;
            zzayh zzayhVar = this.zzf;
            if (zzayhVar.zzc) {
                this.zzf = zzayhVar.zze;
            }
            zzayh zzayhVar2 = this.zzf;
            zzazf zzb = this.zzl.zzb();
            zzayh zzayhVar3 = new zzayh(this.zzf.zzb, 65536);
            zzayhVar2.zzd = zzb;
            zzayhVar2.zze = zzayhVar3;
            zzayhVar2.zzc = true;
        }
        return Math.min(r7, 65536 - this.zzj);
    }

    private final void zzp() {
        this.zza.zzg();
        zzayh zzayhVar = this.zze;
        if (zzayhVar.zzc) {
            zzayh zzayhVar2 = this.zzf;
            int r3 = (zzayhVar2.zzc ? 1 : 0) + (((int) (zzayhVar2.zza - zzayhVar.zza)) / 65536);
            zzazf[] zzazfVarArr = new zzazf[r3];
            for (int r4 = 0; r4 < r3; r4++) {
                zzazfVarArr[r4] = zzayhVar.zzd;
                zzayhVar.zzd = null;
                zzayhVar = zzayhVar.zze;
            }
            this.zzl.zzd(zzazfVarArr);
        }
        zzayh zzayhVar3 = new zzayh(0L, 65536);
        this.zze = zzayhVar3;
        this.zzf = zzayhVar3;
        this.zzi = 0L;
        this.zzj = 65536;
        this.zzl.zzg();
    }

    private final void zzq(long j) {
        while (true) {
            zzayh zzayhVar = this.zze;
            if (j < zzayhVar.zzb) {
                return;
            }
            this.zzl.zzc(zzayhVar.zzd);
            zzayh zzayhVar2 = this.zze;
            zzayhVar2.zzd = null;
            this.zze = zzayhVar2.zze;
        }
    }

    private final void zzr() {
        if (this.zzd.compareAndSet(1, 0)) {
            return;
        }
        zzp();
    }

    private final void zzs(long j, byte[] bArr, int r10) {
        zzq(j);
        int r0 = 0;
        while (r0 < r10) {
            int r2 = (int) (j - this.zze.zza);
            int min = Math.min(r10 - r0, 65536 - r2);
            zzazf zzazfVar = this.zze.zzd;
            System.arraycopy(zzazfVar.zza, r2, bArr, r0, min);
            j += min;
            r0 += min;
            if (j == this.zze.zzb) {
                this.zzl.zzc(zzazfVar);
                zzayh zzayhVar = this.zze;
                zzayhVar.zzd = null;
                this.zze = zzayhVar.zze;
            }
        }
    }

    private final boolean zzt() {
        return this.zzd.compareAndSet(0, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzave
    public final void zza(zzass zzassVar) {
        zzass zzassVar2 = zzassVar == null ? null : zzassVar;
        boolean zzk = this.zza.zzk(zzassVar2);
        this.zzh = zzassVar;
        zzayi zzayiVar = this.zzk;
        if (zzayiVar == null || !zzk) {
            return;
        }
        zzayiVar.zzv(zzassVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzave
    public final void zzb(zzbag zzbagVar, int r7) {
        if (zzt()) {
            while (r7 > 0) {
                int zzo = zzo(r7);
                zzbagVar.zzq(this.zzf.zzd.zza, this.zzj, zzo);
                this.zzj += zzo;
                this.zzi += zzo;
                r7 -= zzo;
            }
            zzr();
            return;
        }
        zzbagVar.zzw(r7);
    }

    @Override // com.google.android.gms.internal.ads.zzave
    public final void zzc(long j, int r15, int r16, int r17, zzavd zzavdVar) {
        if (zzt()) {
            try {
                this.zza.zzh(j, r15, this.zzi - r16, r16, zzavdVar);
                return;
            } finally {
                zzr();
            }
        }
        this.zza.zzi(j);
    }

    @Override // com.google.android.gms.internal.ads.zzave
    public final int zzd(zzauu zzauuVar, int r4, boolean z) throws IOException, InterruptedException {
        if (!zzt()) {
            int zzb = zzauuVar.zzb(r4);
            if (zzb != -1) {
                return zzb;
            }
            throw new EOFException();
        }
        try {
            int zza = zzauuVar.zza(this.zzf.zzd.zza, this.zzj, zzo(r4));
            if (zza != -1) {
                this.zzj += zza;
                this.zzi += zza;
                return zza;
            }
            throw new EOFException();
        } finally {
            zzr();
        }
    }

    public final int zze() {
        return this.zza.zza();
    }

    public final int zzf(zzast zzastVar, zzaun zzaunVar, boolean z, boolean z2, long j) {
        int r11;
        int zzb = this.zza.zzb(zzastVar, zzaunVar, z, z2, this.zzg, this.zzb);
        if (zzb == -5) {
            this.zzg = zzastVar.zza;
            return -5;
        } else if (zzb != -4) {
            return -3;
        } else {
            if (!zzaunVar.zzf()) {
                if (zzaunVar.zzc < j) {
                    zzaunVar.zza(Integer.MIN_VALUE);
                }
                if (zzaunVar.zzi()) {
                    zzayf zzayfVar = this.zzb;
                    long j2 = zzayfVar.zzb;
                    this.zzc.zzs(1);
                    zzs(j2, this.zzc.zza, 1);
                    long j3 = j2 + 1;
                    byte b = this.zzc.zza[0];
                    int r9 = b & 128;
                    int r5 = b & Byte.MAX_VALUE;
                    zzaul zzaulVar = zzaunVar.zza;
                    if (zzaulVar.zza == null) {
                        zzaulVar.zza = new byte[16];
                    }
                    zzs(j3, zzaulVar.zza, r5);
                    long j4 = j3 + r5;
                    if (r9 != 0) {
                        this.zzc.zzs(2);
                        zzs(j4, this.zzc.zza, 2);
                        j4 += 2;
                        r11 = this.zzc.zzj();
                    } else {
                        r11 = 1;
                    }
                    zzaul zzaulVar2 = zzaunVar.zza;
                    int[] r6 = zzaulVar2.zzd;
                    if (r6 == null || r6.length < r11) {
                        r6 = new int[r11];
                    }
                    int[] r12 = r6;
                    int[] r52 = zzaulVar2.zze;
                    if (r52 == null || r52.length < r11) {
                        r52 = new int[r11];
                    }
                    int[] r13 = r52;
                    if (r9 != 0) {
                        int r53 = r11 * 6;
                        this.zzc.zzs(r53);
                        zzs(j4, this.zzc.zza, r53);
                        j4 += r53;
                        this.zzc.zzv(0);
                        for (int r7 = 0; r7 < r11; r7++) {
                            r12[r7] = this.zzc.zzj();
                            r13[r7] = this.zzc.zzi();
                        }
                    } else {
                        r12[0] = 0;
                        r13[0] = zzayfVar.zza - ((int) (j4 - zzayfVar.zzb));
                    }
                    zzavd zzavdVar = zzayfVar.zzd;
                    zzaul zzaulVar3 = zzaunVar.zza;
                    byte[] bArr = zzavdVar.zzb;
                    byte[] bArr2 = zzaulVar3.zza;
                    int r54 = zzavdVar.zza;
                    zzaulVar3.zzb(r11, r12, r13, bArr, bArr2, 1);
                    long j5 = zzayfVar.zzb;
                    int r4 = (int) (j4 - j5);
                    zzayfVar.zzb = j5 + r4;
                    zzayfVar.zza -= r4;
                }
                zzaunVar.zzh(this.zzb.zza);
                zzayf zzayfVar2 = this.zzb;
                long j6 = zzayfVar2.zzb;
                ByteBuffer byteBuffer = zzaunVar.zzb;
                int r1 = zzayfVar2.zza;
                zzq(j6);
                while (r1 > 0) {
                    int r72 = (int) (j6 - this.zze.zza);
                    int min = Math.min(r1, 65536 - r72);
                    zzazf zzazfVar = this.zze.zzd;
                    byteBuffer.put(zzazfVar.zza, r72, min);
                    j6 += min;
                    r1 -= min;
                    if (j6 == this.zze.zzb) {
                        this.zzl.zzc(zzazfVar);
                        zzayh zzayhVar = this.zze;
                        zzayhVar.zzd = null;
                        this.zze = zzayhVar.zze;
                    }
                }
                zzq(this.zzb.zzc);
            }
            return -4;
        }
    }

    public final long zzg() {
        return this.zza.zzc();
    }

    public final zzass zzh() {
        return this.zza.zzf();
    }

    public final void zzi() {
        if (this.zzd.getAndSet(2) == 0) {
            zzp();
        }
    }

    public final void zzj(boolean z) {
        int andSet = this.zzd.getAndSet(true != z ? 2 : 0);
        zzp();
        this.zza.zzj();
        if (andSet == 2) {
            this.zzg = null;
        }
    }

    public final void zzk(zzayi zzayiVar) {
        this.zzk = zzayiVar;
    }

    public final void zzl() {
        long zzd = this.zza.zzd();
        if (zzd != -1) {
            zzq(zzd);
        }
    }

    public final boolean zzm() {
        return this.zza.zzl();
    }

    public final boolean zzn(long j, boolean z) {
        long zze = this.zza.zze(j, z);
        if (zze == -1) {
            return false;
        }
        zzq(zze);
        return true;
    }
}
