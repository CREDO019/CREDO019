package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzagm extends zzagk {
    private zzagl zza;
    private int zzb;
    private boolean zzc;
    private zzaar zzd;
    private zzaap zze;

    @Override // com.google.android.gms.internal.ads.zzagk
    protected final long zza(zzed zzedVar) {
        if ((zzedVar.zzH()[0] & 1) == 1) {
            return -1L;
        }
        byte b = zzedVar.zzH()[0];
        zzagl zzaglVar = this.zza;
        zzdd.zzb(zzaglVar);
        int r0 = !zzaglVar.zzd[(b >> 1) & (255 >>> (8 - zzaglVar.zze))].zza ? zzaglVar.zza.zze : zzaglVar.zza.zzf;
        long j = this.zzc ? (this.zzb + r0) / 4 : 0;
        if (zzedVar.zzb() < zzedVar.zzd() + 4) {
            byte[] copyOf = Arrays.copyOf(zzedVar.zzH(), zzedVar.zzd() + 4);
            zzedVar.zzD(copyOf, copyOf.length);
        } else {
            zzedVar.zzE(zzedVar.zzd() + 4);
        }
        byte[] zzH = zzedVar.zzH();
        zzH[zzedVar.zzd() - 4] = (byte) (j & 255);
        zzH[zzedVar.zzd() - 3] = (byte) ((j >>> 8) & 255);
        zzH[zzedVar.zzd() - 2] = (byte) ((j >>> 16) & 255);
        zzH[zzedVar.zzd() - 1] = (byte) ((j >>> 24) & 255);
        this.zzc = true;
        this.zzb = r0;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzb(boolean z) {
        super.zzb(z);
        if (z) {
            this.zza = null;
            this.zzd = null;
            this.zze = null;
        }
        this.zzb = 0;
        this.zzc = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzi(long j) {
        super.zzi(j);
        this.zzc = j != 0;
        zzaar zzaarVar = this.zzd;
        this.zzb = zzaarVar != null ? zzaarVar.zze : 0;
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    protected final boolean zzc(zzed zzedVar, long j, zzagh zzaghVar) throws IOException {
        zzagl zzaglVar;
        int r5;
        int r7;
        int r15;
        int r23;
        int r232;
        if (this.zza == null) {
            zzaar zzaarVar = this.zzd;
            if (zzaarVar == null) {
                zzaas.zzd(1, zzedVar, false);
                int zzh = zzedVar.zzh();
                int zzk = zzedVar.zzk();
                int zzh2 = zzedVar.zzh();
                int zzg = zzedVar.zzg();
                int r16 = zzg <= 0 ? -1 : zzg;
                int zzg2 = zzedVar.zzg();
                int r17 = zzg2 <= 0 ? -1 : zzg2;
                int zzg3 = zzedVar.zzg();
                int r18 = zzg3 <= 0 ? -1 : zzg3;
                int zzk2 = zzedVar.zzk();
                this.zzd = new zzaar(zzh, zzk, zzh2, r16, r17, r18, (int) Math.pow(2.0d, zzk2 & 15), (int) Math.pow(2.0d, (zzk2 & PsExtractor.VIDEO_STREAM_MASK) >> 4), 1 == (zzedVar.zzk() & 1), Arrays.copyOf(zzedVar.zzH(), zzedVar.zzd()));
            } else {
                zzaap zzaapVar = this.zze;
                if (zzaapVar == null) {
                    this.zze = zzaas.zzc(zzedVar, true, true);
                } else {
                    byte[] bArr = new byte[zzedVar.zzd()];
                    System.arraycopy(zzedVar.zzH(), 0, bArr, 0, zzedVar.zzd());
                    int r10 = zzaarVar.zza;
                    int r11 = 5;
                    zzaas.zzd(5, zzedVar, false);
                    int zzk3 = zzedVar.zzk() + 1;
                    zzaao zzaaoVar = new zzaao(zzedVar.zzH());
                    zzaaoVar.zzc(zzedVar.zzc() * 8);
                    int r1 = 0;
                    while (r1 < zzk3) {
                        if (zzaaoVar.zzb(24) != 5653314) {
                            throw zzbu.zza("expected code book to start with [0x56, 0x43, 0x42] at " + zzaaoVar.zza(), null);
                        }
                        int zzb = zzaaoVar.zzb(16);
                        int zzb2 = zzaaoVar.zzb(24);
                        long[] jArr = new long[zzb2];
                        long j2 = 0;
                        if (!zzaaoVar.zzd()) {
                            boolean zzd = zzaaoVar.zzd();
                            int r4 = 0;
                            while (r4 < zzb2) {
                                if (zzd) {
                                    if (zzaaoVar.zzd()) {
                                        r232 = zzk3;
                                        jArr[r4] = zzaaoVar.zzb(r11) + 1;
                                    } else {
                                        r232 = zzk3;
                                        jArr[r4] = 0;
                                    }
                                } else {
                                    r232 = zzk3;
                                    jArr[r4] = zzaaoVar.zzb(5) + 1;
                                }
                                r4++;
                                zzk3 = r232;
                                r11 = 5;
                            }
                            r23 = zzk3;
                        } else {
                            r23 = zzk3;
                            int zzb3 = zzaaoVar.zzb(5) + 1;
                            int r52 = 0;
                            while (r52 < zzb2) {
                                int zzb4 = zzaaoVar.zzb(zzaas.zza(zzb2 - r52));
                                int r12 = 0;
                                while (r12 < zzb4 && r52 < zzb2) {
                                    jArr[r52] = zzb3;
                                    r52++;
                                    r12++;
                                    zzaapVar = zzaapVar;
                                    bArr = bArr;
                                }
                                zzb3++;
                                zzaapVar = zzaapVar;
                                bArr = bArr;
                            }
                        }
                        zzaap zzaapVar2 = zzaapVar;
                        byte[] bArr2 = bArr;
                        int zzb5 = zzaaoVar.zzb(4);
                        if (zzb5 > 2) {
                            throw zzbu.zza("lookup type greater than 2 not decodable: " + zzb5, null);
                        }
                        if (zzb5 != 1) {
                            if (zzb5 == 2) {
                                zzb5 = 2;
                            } else {
                                r1++;
                                zzaapVar = zzaapVar2;
                                zzk3 = r23;
                                bArr = bArr2;
                                r11 = 5;
                            }
                        }
                        zzaaoVar.zzc(32);
                        zzaaoVar.zzc(32);
                        int zzb6 = zzaaoVar.zzb(4) + 1;
                        zzaaoVar.zzc(1);
                        if (zzb5 != 1) {
                            j2 = zzb2 * zzb;
                        } else if (zzb != 0) {
                            j2 = (long) Math.floor(Math.pow(zzb2, 1.0d / zzb));
                        }
                        zzaaoVar.zzc((int) (zzb6 * j2));
                        r1++;
                        zzaapVar = zzaapVar2;
                        zzk3 = r23;
                        bArr = bArr2;
                        r11 = 5;
                    }
                    zzaap zzaapVar3 = zzaapVar;
                    byte[] bArr3 = bArr;
                    int r13 = 6;
                    int zzb7 = zzaaoVar.zzb(6) + 1;
                    for (int r53 = 0; r53 < zzb7; r53++) {
                        if (zzaaoVar.zzb(16) != 0) {
                            throw zzbu.zza("placeholder of time domain transforms not zeroed out", null);
                        }
                    }
                    int r54 = 1;
                    int zzb8 = zzaaoVar.zzb(6) + 1;
                    int r72 = 0;
                    while (true) {
                        int r8 = 3;
                        if (r72 < zzb8) {
                            int zzb9 = zzaaoVar.zzb(16);
                            if (zzb9 == 0) {
                                int r14 = 8;
                                zzaaoVar.zzc(8);
                                zzaaoVar.zzc(16);
                                zzaaoVar.zzc(16);
                                zzaaoVar.zzc(6);
                                zzaaoVar.zzc(8);
                                int zzb10 = zzaaoVar.zzb(4) + 1;
                                int r55 = 0;
                                while (r55 < zzb10) {
                                    zzaaoVar.zzc(r14);
                                    r55++;
                                    r14 = 8;
                                }
                            } else if (zzb9 != r54) {
                                throw zzbu.zza("floor type greater than 1 not decodable: " + zzb9, null);
                            } else {
                                int zzb11 = zzaaoVar.zzb(5);
                                int[] r56 = new int[zzb11];
                                int r122 = -1;
                                for (int r112 = 0; r112 < zzb11; r112++) {
                                    int zzb12 = zzaaoVar.zzb(4);
                                    r56[r112] = zzb12;
                                    if (zzb12 > r122) {
                                        r122 = zzb12;
                                    }
                                }
                                int r123 = r122 + 1;
                                int[] r113 = new int[r123];
                                int r142 = 0;
                                while (r142 < r123) {
                                    r113[r142] = zzaaoVar.zzb(r8) + 1;
                                    int zzb13 = zzaaoVar.zzb(2);
                                    if (zzb13 > 0) {
                                        r15 = 8;
                                        zzaaoVar.zzc(8);
                                    } else {
                                        r15 = 8;
                                    }
                                    int r82 = 0;
                                    for (int r19 = 1; r82 < (r19 << zzb13); r19 = 1) {
                                        zzaaoVar.zzc(r15);
                                        r82++;
                                        r15 = 8;
                                    }
                                    r142++;
                                    r8 = 3;
                                }
                                zzaaoVar.zzc(2);
                                int zzb14 = zzaaoVar.zzb(4);
                                int r124 = 0;
                                int r143 = 0;
                                for (int r110 = 0; r110 < zzb11; r110++) {
                                    r124 += r113[r56[r110]];
                                    while (r143 < r124) {
                                        zzaaoVar.zzc(zzb14);
                                        r143++;
                                    }
                                }
                            }
                            r72++;
                            r13 = 6;
                            r54 = 1;
                        } else {
                            int r57 = 1;
                            int zzb15 = zzaaoVar.zzb(r13) + 1;
                            int r73 = 0;
                            while (r73 < zzb15) {
                                if (zzaaoVar.zzb(16) <= 2) {
                                    zzaaoVar.zzc(24);
                                    zzaaoVar.zzc(24);
                                    zzaaoVar.zzc(24);
                                    int zzb16 = zzaaoVar.zzb(r13) + r57;
                                    int r111 = 8;
                                    zzaaoVar.zzc(8);
                                    int[] r58 = new int[zzb16];
                                    for (int r114 = 0; r114 < zzb16; r114++) {
                                        r58[r114] = ((zzaaoVar.zzd() ? zzaaoVar.zzb(5) : 0) * 8) + zzaaoVar.zzb(3);
                                    }
                                    int r115 = 0;
                                    while (r115 < zzb16) {
                                        int r144 = 0;
                                        while (r144 < r111) {
                                            if ((r58[r115] & (1 << r144)) != 0) {
                                                zzaaoVar.zzc(r111);
                                            }
                                            r144++;
                                            r111 = 8;
                                        }
                                        r115++;
                                        r111 = 8;
                                    }
                                    r73++;
                                    r13 = 6;
                                    r57 = 1;
                                } else {
                                    throw zzbu.zza("residueType greater than 2 is not decodable", null);
                                }
                            }
                            int zzb17 = zzaaoVar.zzb(r13) + 1;
                            for (int r116 = 0; r116 < zzb17; r116++) {
                                int zzb18 = zzaaoVar.zzb(16);
                                if (zzb18 != 0) {
                                    Log.e("VorbisUtil", "mapping type other than 0 not supported: " + zzb18);
                                } else {
                                    if (zzaaoVar.zzd()) {
                                        r5 = 1;
                                        r7 = zzaaoVar.zzb(4) + 1;
                                    } else {
                                        r5 = 1;
                                        r7 = 1;
                                    }
                                    if (zzaaoVar.zzd()) {
                                        int zzb19 = zzaaoVar.zzb(8) + r5;
                                        for (int r59 = 0; r59 < zzb19; r59++) {
                                            int r83 = r10 - 1;
                                            zzaaoVar.zzc(zzaas.zza(r83));
                                            zzaaoVar.zzc(zzaas.zza(r83));
                                        }
                                    }
                                    if (zzaaoVar.zzb(2) != 0) {
                                        throw zzbu.zza("to reserved bits must be zero after mapping coupling steps", null);
                                    }
                                    if (r7 > 1) {
                                        for (int r84 = 0; r84 < r10; r84++) {
                                            zzaaoVar.zzc(4);
                                        }
                                    }
                                    for (int r85 = 0; r85 < r7; r85++) {
                                        zzaaoVar.zzc(8);
                                        zzaaoVar.zzc(8);
                                        zzaaoVar.zzc(8);
                                    }
                                }
                            }
                            int zzb20 = zzaaoVar.zzb(6) + 1;
                            zzaaq[] zzaaqVarArr = new zzaaq[zzb20];
                            for (int r42 = 0; r42 < zzb20; r42++) {
                                zzaaqVarArr[r42] = new zzaaq(zzaaoVar.zzd(), zzaaoVar.zzb(16), zzaaoVar.zzb(16), zzaaoVar.zzb(8));
                            }
                            if (!zzaaoVar.zzd()) {
                                throw zzbu.zza("framing bit after modes not set as expected", null);
                            }
                            zzaglVar = new zzagl(zzaarVar, zzaapVar3, bArr3, zzaaqVarArr, zzaas.zza(zzb20 - 1));
                        }
                    }
                }
            }
            zzaglVar = null;
            this.zza = zzaglVar;
            if (zzaglVar == null) {
                return true;
            }
            zzaar zzaarVar2 = zzaglVar.zza;
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzaarVar2.zzg);
            arrayList.add(zzaglVar.zzc);
            zzbq zzb21 = zzaas.zzb(zzfuv.zzn(zzaglVar.zzb.zzb));
            zzad zzadVar = new zzad();
            zzadVar.zzS(MimeTypes.AUDIO_VORBIS);
            zzadVar.zzv(zzaarVar2.zzd);
            zzadVar.zzO(zzaarVar2.zzc);
            zzadVar.zzw(zzaarVar2.zza);
            zzadVar.zzT(zzaarVar2.zzb);
            zzadVar.zzI(arrayList);
            zzadVar.zzM(zzb21);
            zzaghVar.zza = zzadVar.zzY();
            return true;
        }
        Objects.requireNonNull(zzaghVar.zza);
        return false;
    }
}
