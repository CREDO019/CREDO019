package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public abstract class zzjj extends zzir {
    private static final Logger zzb = Logger.getLogger(zzjj.class.getName());
    private static final boolean zzc = zzmv.zzx();
    zzjk zza;

    private zzjj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjj(zzji zzjiVar) {
    }

    public static int zzA(int r1) {
        if ((r1 & (-128)) == 0) {
            return 1;
        }
        if ((r1 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & r1) == 0) {
            return 3;
        }
        return (r1 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzB(long j) {
        int r0;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            r0 = 6;
        } else {
            r0 = 2;
        }
        if (((-2097152) & j) != 0) {
            r0 += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? r0 + 1 : r0;
    }

    public static zzjj zzC(byte[] bArr) {
        return new zzjg(bArr, 0, bArr.length);
    }

    public static int zzt(zzjb zzjbVar) {
        int zzd = zzjbVar.zzd();
        return zzA(zzd) + zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzu(int r2, zzlj zzljVar, zzlu zzluVar) {
        int zzA = zzA(r2 << 3);
        int r22 = zzA + zzA;
        zzil zzilVar = (zzil) zzljVar;
        int zzbu = zzilVar.zzbu();
        if (zzbu == -1) {
            zzbu = zzluVar.zza(zzilVar);
            zzilVar.zzbx(zzbu);
        }
        return r22 + zzbu;
    }

    public static int zzv(int r0) {
        if (r0 >= 0) {
            return zzA(r0);
        }
        return 10;
    }

    public static int zzw(zzkp zzkpVar) {
        int zza = zzkpVar.zza();
        return zzA(zza) + zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(zzlj zzljVar, zzlu zzluVar) {
        zzil zzilVar = (zzil) zzljVar;
        int zzbu = zzilVar.zzbu();
        if (zzbu == -1) {
            zzbu = zzluVar.zza(zzilVar);
            zzilVar.zzbx(zzbu);
        }
        return zzA(zzbu) + zzbu;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzna.zzc(str);
        } catch (zzmz unused) {
            length = str.getBytes(zzkk.zzb).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int r0) {
        return zzA(r0 << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzE(String str, zzmz zzmzVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzmzVar);
        byte[] bytes = str.getBytes(zzkk.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int r1, boolean z) throws IOException;

    public abstract void zze(int r1, zzjb zzjbVar) throws IOException;

    public abstract void zzf(int r1, int r2) throws IOException;

    public abstract void zzg(int r1) throws IOException;

    public abstract void zzh(int r1, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int r1, int r2) throws IOException;

    public abstract void zzk(int r1) throws IOException;

    public abstract void zzl(byte[] bArr, int r2, int r3) throws IOException;

    public abstract void zzm(int r1, String str) throws IOException;

    public abstract void zzo(int r1, int r2) throws IOException;

    public abstract void zzp(int r1, int r2) throws IOException;

    public abstract void zzq(int r1) throws IOException;

    public abstract void zzr(int r1, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
