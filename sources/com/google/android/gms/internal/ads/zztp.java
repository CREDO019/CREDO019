package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zztp {
    private final zzed zza = new zzed(32);
    private zzto zzb;
    private zzto zzc;
    private zzto zzd;
    private long zze;
    private final zzwf zzf;

    public zztp(zzwf zzwfVar, byte[] bArr) {
        this.zzf = zzwfVar;
        zzto zztoVar = new zzto(0L, 65536);
        this.zzb = zztoVar;
        this.zzc = zztoVar;
        this.zzd = zztoVar;
    }

    private final int zzi(int r7) {
        zzto zztoVar = this.zzd;
        if (zztoVar.zzc == null) {
            zzvy zzb = this.zzf.zzb();
            zzto zztoVar2 = new zzto(this.zzd.zzb, 65536);
            zztoVar.zzc = zzb;
            zztoVar.zzd = zztoVar2;
        }
        return Math.min(r7, (int) (this.zzd.zzb - this.zze));
    }

    private static zzto zzj(zzto zztoVar, long j) {
        while (j >= zztoVar.zzb) {
            zztoVar = zztoVar.zzd;
        }
        return zztoVar;
    }

    private static zzto zzk(zzto zztoVar, long j, ByteBuffer byteBuffer, int r7) {
        zzto zzj = zzj(zztoVar, j);
        while (r7 > 0) {
            int min = Math.min(r7, (int) (zzj.zzb - j));
            byteBuffer.put(zzj.zzc.zza, zzj.zza(j), min);
            r7 -= min;
            j += min;
            if (j == zzj.zzb) {
                zzj = zzj.zzd;
            }
        }
        return zzj;
    }

    private static zzto zzl(zzto zztoVar, long j, byte[] bArr, int r9) {
        zzto zzj = zzj(zztoVar, j);
        int r0 = r9;
        while (r0 > 0) {
            int min = Math.min(r0, (int) (zzj.zzb - j));
            System.arraycopy(zzj.zzc.zza, zzj.zza(j), bArr, r9 - r0, min);
            r0 -= min;
            j += min;
            if (j == zzj.zzb) {
                zzj = zzj.zzd;
            }
        }
        return zzj;
    }

    private static zzto zzm(zzto zztoVar, zzgg zzggVar, zztr zztrVar, zzed zzedVar) {
        zzto zztoVar2;
        int r11;
        if (zzggVar.zzk()) {
            long j = zztrVar.zzb;
            zzedVar.zzC(1);
            zzto zzl = zzl(zztoVar, j, zzedVar.zzH(), 1);
            long j2 = j + 1;
            byte b = zzedVar.zzH()[0];
            int r9 = b & 128;
            int r7 = b & Byte.MAX_VALUE;
            zzgd zzgdVar = zzggVar.zza;
            byte[] bArr = zzgdVar.zza;
            if (bArr == null) {
                zzgdVar.zza = new byte[16];
            } else {
                Arrays.fill(bArr, (byte) 0);
            }
            zztoVar2 = zzl(zzl, j2, zzgdVar.zza, r7);
            long j3 = j2 + r7;
            if (r9 != 0) {
                zzedVar.zzC(2);
                zztoVar2 = zzl(zztoVar2, j3, zzedVar.zzH(), 2);
                j3 += 2;
                r11 = zzedVar.zzo();
            } else {
                r11 = 1;
            }
            int[] r5 = zzgdVar.zzd;
            if (r5 == null || r5.length < r11) {
                r5 = new int[r11];
            }
            int[] r12 = r5;
            int[] r52 = zzgdVar.zze;
            if (r52 == null || r52.length < r11) {
                r52 = new int[r11];
            }
            int[] r13 = r52;
            if (r9 != 0) {
                int r53 = r11 * 6;
                zzedVar.zzC(r53);
                zztoVar2 = zzl(zztoVar2, j3, zzedVar.zzH(), r53);
                j3 += r53;
                zzedVar.zzF(0);
                for (int r8 = 0; r8 < r11; r8++) {
                    r12[r8] = zzedVar.zzo();
                    r13[r8] = zzedVar.zzn();
                }
            } else {
                r12[0] = 0;
                r13[0] = zztrVar.zza - ((int) (j3 - zztrVar.zzb));
            }
            zzaal zzaalVar = zztrVar.zzc;
            int r72 = zzel.zza;
            zzgdVar.zzc(r11, r12, r13, zzaalVar.zzb, zzgdVar.zza, zzaalVar.zza, zzaalVar.zzc, zzaalVar.zzd);
            long j4 = zztrVar.zzb;
            int r4 = (int) (j3 - j4);
            zztrVar.zzb = j4 + r4;
            zztrVar.zza -= r4;
        } else {
            zztoVar2 = zztoVar;
        }
        if (zzggVar.zze()) {
            zzedVar.zzC(4);
            zzto zzl2 = zzl(zztoVar2, zztrVar.zzb, zzedVar.zzH(), 4);
            int zzn = zzedVar.zzn();
            zztrVar.zzb += 4;
            zztrVar.zza -= 4;
            zzggVar.zzi(zzn);
            zzto zzk = zzk(zzl2, zztrVar.zzb, zzggVar.zzb, zzn);
            zztrVar.zzb += zzn;
            int r42 = zztrVar.zza - zzn;
            zztrVar.zza = r42;
            ByteBuffer byteBuffer = zzggVar.zze;
            if (byteBuffer == null || byteBuffer.capacity() < r42) {
                zzggVar.zze = ByteBuffer.allocate(r42);
            } else {
                zzggVar.zze.clear();
            }
            return zzk(zzk, zztrVar.zzb, zzggVar.zze, zztrVar.zza);
        }
        zzggVar.zzi(zztrVar.zza);
        return zzk(zztoVar2, zztrVar.zzb, zzggVar.zzb, zztrVar.zza);
    }

    private final void zzn(int r6) {
        long j = this.zze + r6;
        this.zze = j;
        zzto zztoVar = this.zzd;
        if (j == zztoVar.zzb) {
            this.zzd = zztoVar.zzd;
        }
    }

    public final int zza(zzr zzrVar, int r6, boolean z) throws IOException {
        int zzi = zzi(r6);
        zzto zztoVar = this.zzd;
        int zza = zzrVar.zza(zztoVar.zzc.zza, zztoVar.zza(this.zze), zzi);
        if (zza != -1) {
            zzn(zza);
            return zza;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public final long zzb() {
        return this.zze;
    }

    public final void zzc(long j) {
        zzto zztoVar;
        if (j != -1) {
            while (true) {
                zztoVar = this.zzb;
                if (j < zztoVar.zzb) {
                    break;
                }
                this.zzf.zzc(zztoVar.zzc);
                this.zzb = this.zzb.zzb();
            }
            if (this.zzc.zza < zztoVar.zza) {
                this.zzc = zztoVar;
            }
        }
    }

    public final void zzd(zzgg zzggVar, zztr zztrVar) {
        zzm(this.zzc, zzggVar, zztrVar, this.zza);
    }

    public final void zze(zzgg zzggVar, zztr zztrVar) {
        this.zzc = zzm(this.zzc, zzggVar, zztrVar, this.zza);
    }

    public final void zzf() {
        zzto zztoVar = this.zzb;
        if (zztoVar.zzc != null) {
            this.zzf.zzd(zztoVar);
            zztoVar.zzb();
        }
        this.zzb.zze(0L, 65536);
        zzto zztoVar2 = this.zzb;
        this.zzc = zztoVar2;
        this.zzd = zztoVar2;
        this.zze = 0L;
        this.zzf.zzg();
    }

    public final void zzg() {
        this.zzc = this.zzb;
    }

    public final void zzh(zzed zzedVar, int r7) {
        while (r7 > 0) {
            int zzi = zzi(r7);
            zzto zztoVar = this.zzd;
            zzedVar.zzB(zztoVar.zzc.zza, zztoVar.zza(this.zze), zzi);
            r7 -= zzi;
            zzn(zzi);
        }
    }
}
