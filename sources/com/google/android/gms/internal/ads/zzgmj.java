package com.google.android.gms.internal.ads;

import java.security.InvalidKeyException;
import java.util.Arrays;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmj {
    public static byte[] zza(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] copyOf = Arrays.copyOf(bArr, 32);
        copyOf[0] = (byte) (copyOf[0] & 248);
        int r7 = copyOf[31] & Byte.MAX_VALUE;
        copyOf[31] = (byte) r7;
        copyOf[31] = (byte) (r7 | 64);
        int r72 = zzglg.zzb;
        if (bArr2.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        byte[] copyOf2 = Arrays.copyOf(bArr2, 32);
        copyOf2[31] = (byte) (copyOf2[31] & Byte.MAX_VALUE);
        for (int r6 = 0; r6 < 7; r6++) {
            if (zzgle.zzb(zzglg.zza[r6], copyOf2)) {
                throw new InvalidKeyException("Banned public key: ".concat(zzglz.zza(zzglg.zza[r6])));
            }
        }
        long[] zzk = zzgly.zzk(copyOf2);
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        System.arraycopy(zzk, 0, jArr2, 0, 10);
        int r11 = 0;
        for (int r5 = 32; r11 < r5; r5 = 32) {
            int r52 = copyOf[(32 - r11) - 1] & 255;
            int r73 = 0;
            while (r73 < 8) {
                int r10 = (r52 >> (7 - r73)) & 1;
                zzglg.zza(jArr4, jArr2, r10);
                zzglg.zza(jArr5, jArr3, r10);
                byte[] bArr3 = copyOf;
                long[] copyOf3 = Arrays.copyOf(jArr4, 10);
                int r16 = r52;
                long[] jArr10 = new long[19];
                long[] jArr11 = jArr;
                long[] jArr12 = new long[19];
                int r19 = r11;
                long[] jArr13 = new long[19];
                int r20 = r73;
                long[] jArr14 = new long[19];
                long[] jArr15 = new long[19];
                long[] jArr16 = jArr9;
                long[] jArr17 = new long[19];
                long[] jArr18 = new long[19];
                zzgly.zzi(jArr4, jArr4, jArr5);
                zzgly.zzh(jArr5, copyOf3, jArr5);
                long[] copyOf4 = Arrays.copyOf(jArr2, 10);
                zzgly.zzi(jArr2, jArr2, jArr3);
                zzgly.zzh(jArr3, copyOf4, jArr3);
                zzgly.zzb(jArr14, jArr2, jArr5);
                zzgly.zzb(jArr15, jArr4, jArr3);
                zzgly.zze(jArr14);
                zzgly.zzd(jArr14);
                zzgly.zze(jArr15);
                zzgly.zzd(jArr15);
                long[] jArr19 = jArr2;
                System.arraycopy(jArr14, 0, copyOf4, 0, 10);
                zzgly.zzi(jArr14, jArr14, jArr15);
                zzgly.zzh(jArr15, copyOf4, jArr15);
                zzgly.zzg(jArr18, jArr14);
                zzgly.zzg(jArr17, jArr15);
                zzgly.zzb(jArr15, jArr17, zzk);
                zzgly.zze(jArr15);
                zzgly.zzd(jArr15);
                System.arraycopy(jArr18, 0, jArr6, 0, 10);
                System.arraycopy(jArr15, 0, jArr7, 0, 10);
                zzgly.zzg(jArr12, jArr4);
                zzgly.zzg(jArr13, jArr5);
                zzgly.zzb(jArr8, jArr12, jArr13);
                zzgly.zze(jArr8);
                zzgly.zzd(jArr8);
                zzgly.zzh(jArr13, jArr12, jArr13);
                Arrays.fill(jArr10, 10, 18, 0L);
                zzgly.zzf(jArr10, jArr13, 121665L);
                zzgly.zzd(jArr10);
                zzgly.zzi(jArr10, jArr10, jArr12);
                zzgly.zzb(jArr16, jArr13, jArr10);
                zzgly.zze(jArr16);
                zzgly.zzd(jArr16);
                zzglg.zza(jArr8, jArr6, r10);
                zzglg.zza(jArr16, jArr7, r10);
                r73 = r20 + 1;
                jArr9 = jArr5;
                jArr2 = jArr6;
                r52 = r16;
                jArr = jArr11;
                r11 = r19;
                jArr6 = jArr19;
                jArr5 = jArr16;
                copyOf = bArr3;
                long[] jArr20 = jArr4;
                jArr4 = jArr8;
                jArr8 = jArr20;
                long[] jArr21 = jArr7;
                jArr7 = jArr3;
                jArr3 = jArr21;
            }
            r11++;
            copyOf = copyOf;
        }
        long[] jArr22 = jArr;
        long[] jArr23 = new long[10];
        long[] jArr24 = new long[10];
        long[] jArr25 = new long[10];
        long[] jArr26 = new long[10];
        long[] jArr27 = new long[10];
        long[] jArr28 = new long[10];
        long[] jArr29 = new long[10];
        long[] jArr30 = new long[10];
        long[] jArr31 = new long[10];
        long[] jArr32 = new long[10];
        long[] jArr33 = jArr2;
        long[] jArr34 = new long[10];
        zzgly.zzg(jArr24, jArr5);
        zzgly.zzg(jArr34, jArr24);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zza(jArr25, jArr32, jArr5);
        zzgly.zza(jArr26, jArr25, jArr24);
        zzgly.zzg(jArr32, jArr26);
        zzgly.zza(jArr27, jArr32, jArr25);
        zzgly.zzg(jArr32, jArr27);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zza(jArr28, jArr32, jArr27);
        zzgly.zzg(jArr32, jArr28);
        zzgly.zzg(jArr34, jArr32);
        for (int r2 = 2; r2 < 10; r2 += 2) {
            zzgly.zzg(jArr32, jArr34);
            zzgly.zzg(jArr34, jArr32);
        }
        zzgly.zza(jArr29, jArr34, jArr28);
        zzgly.zzg(jArr32, jArr29);
        zzgly.zzg(jArr34, jArr32);
        for (int r22 = 2; r22 < 20; r22 += 2) {
            zzgly.zzg(jArr32, jArr34);
            zzgly.zzg(jArr34, jArr32);
        }
        zzgly.zza(jArr32, jArr34, jArr29);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zzg(jArr32, jArr34);
        for (int r23 = 2; r23 < 10; r23 += 2) {
            zzgly.zzg(jArr34, jArr32);
            zzgly.zzg(jArr32, jArr34);
        }
        zzgly.zza(jArr30, jArr32, jArr28);
        zzgly.zzg(jArr32, jArr30);
        zzgly.zzg(jArr34, jArr32);
        for (int r24 = 2; r24 < 50; r24 += 2) {
            zzgly.zzg(jArr32, jArr34);
            zzgly.zzg(jArr34, jArr32);
        }
        zzgly.zza(jArr31, jArr34, jArr30);
        zzgly.zzg(jArr34, jArr31);
        zzgly.zzg(jArr32, jArr34);
        for (int r25 = 2; r25 < 100; r25 += 2) {
            zzgly.zzg(jArr34, jArr32);
            zzgly.zzg(jArr32, jArr34);
        }
        zzgly.zza(jArr34, jArr32, jArr31);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zzg(jArr34, jArr32);
        for (int r0 = 2; r0 < 50; r0 += 2) {
            zzgly.zzg(jArr32, jArr34);
            zzgly.zzg(jArr34, jArr32);
        }
        zzgly.zza(jArr32, jArr34, jArr30);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zzg(jArr32, jArr34);
        zzgly.zzg(jArr34, jArr32);
        zzgly.zza(jArr23, jArr34, jArr26);
        zzgly.zza(jArr22, jArr4, jArr23);
        long[] jArr35 = new long[10];
        long[] jArr36 = new long[10];
        long[] jArr37 = new long[11];
        long[] jArr38 = new long[11];
        long[] jArr39 = new long[11];
        zzgly.zza(jArr35, zzk, jArr22);
        zzgly.zzi(jArr36, zzk, jArr22);
        long[] jArr40 = new long[10];
        jArr40[0] = 486662;
        zzgly.zzi(jArr38, jArr36, jArr40);
        zzgly.zza(jArr38, jArr38, jArr3);
        zzgly.zzi(jArr38, jArr38, jArr33);
        zzgly.zza(jArr38, jArr38, jArr35);
        zzgly.zza(jArr38, jArr38, jArr33);
        zzgly.zzf(jArr37, jArr38, 4L);
        zzgly.zzd(jArr37);
        zzgly.zza(jArr38, jArr35, jArr3);
        zzgly.zzh(jArr38, jArr38, jArr3);
        zzgly.zza(jArr39, jArr36, jArr33);
        zzgly.zzi(jArr38, jArr38, jArr39);
        zzgly.zzg(jArr38, jArr38);
        if (!zzgle.zzb(zzgly.zzj(jArr37), zzgly.zzj(jArr38))) {
            throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: ".concat(zzglz.zza(bArr2)));
        }
        return zzgly.zzj(jArr22);
    }

    public static byte[] zzb() {
        byte[] zza = zzgmg.zza(32);
        zza[0] = (byte) (zza[0] | 7);
        int r2 = zza[31] & Utf8.REPLACEMENT_BYTE;
        zza[31] = (byte) r2;
        zza[31] = (byte) (r2 | 128);
        return zza;
    }

    public static byte[] zzc(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = 9;
        return zza(bArr, bArr2);
    }
}
