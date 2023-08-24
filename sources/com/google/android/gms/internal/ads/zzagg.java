package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;
import java.util.List;
import okio.Utf8;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzagg extends zzagk {
    private static final byte[] zza = {79, 112, 117, 115, 72, 101, 97, 100};
    private static final byte[] zzb = {79, 112, 117, 115, 84, 97, 103, 115};
    private boolean zzc;

    public static boolean zzd(zzed zzedVar) {
        return zzk(zzedVar, zza);
    }

    private static boolean zzk(zzed zzedVar, byte[] bArr) {
        if (zzedVar.zza() < 8) {
            return false;
        }
        int zzc = zzedVar.zzc();
        byte[] bArr2 = new byte[8];
        zzedVar.zzB(bArr2, 0, 8);
        zzedVar.zzF(zzc);
        return Arrays.equals(bArr2, bArr);
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    protected final long zza(zzed zzedVar) {
        byte[] zzH = zzedVar.zzH();
        int r0 = zzH[0] & 255;
        int r1 = r0 & 3;
        int r2 = 2;
        if (r1 == 0) {
            r2 = 1;
        } else if (r1 != 1 && r1 != 2) {
            r2 = zzH[1] & Utf8.REPLACEMENT_BYTE;
        }
        int r02 = r0 >> 3;
        int r12 = r02 & 3;
        return zzg(r2 * (r02 >= 16 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS << r12 : r02 >= 12 ? 10000 << (r12 & 1) : r12 == 3 ? 60000 : 10000 << r12));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzb(boolean z) {
        super.zzb(z);
        if (z) {
            this.zzc = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    protected final boolean zzc(zzed zzedVar, long j, zzagh zzaghVar) throws zzbu {
        if (zzk(zzedVar, zza)) {
            byte[] copyOf = Arrays.copyOf(zzedVar.zzH(), zzedVar.zzd());
            int r4 = copyOf[9] & 255;
            List zza2 = zzaad.zza(copyOf);
            if (zzaghVar.zza != null) {
                return true;
            }
            zzad zzadVar = new zzad();
            zzadVar.zzS(MimeTypes.AUDIO_OPUS);
            zzadVar.zzw(r4);
            zzadVar.zzT(OpusUtil.SAMPLE_RATE);
            zzadVar.zzI(zza2);
            zzaghVar.zza = zzadVar.zzY();
            return true;
        } else if (zzk(zzedVar, zzb)) {
            zzdd.zzb(zzaghVar.zza);
            if (this.zzc) {
                return true;
            }
            this.zzc = true;
            zzedVar.zzG(8);
            zzbq zzb2 = zzaas.zzb(zzfuv.zzn(zzaas.zzc(zzedVar, false, false).zzb));
            if (zzb2 == null) {
                return true;
            }
            zzad zzb3 = zzaghVar.zza.zzb();
            zzb3.zzM(zzb2.zzd(zzaghVar.zza.zzk));
            zzaghVar.zza = zzb3.zzY();
            return true;
        } else {
            zzdd.zzb(zzaghVar.zza);
            return false;
        }
    }
}
