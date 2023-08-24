package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzoy extends zzqo implements zzjg {
    private final Context zzb;
    private final zznp zzc;
    private final zznw zzd;
    private int zze;
    private boolean zzf;
    private zzaf zzg;
    private long zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private zzjx zzl;

    public zzoy(Context context, zzqi zzqiVar, zzqq zzqqVar, boolean z, Handler handler, zznq zznqVar, zznw zznwVar) {
        super(1, zzqiVar, zzqqVar, false, 44100.0f);
        this.zzb = context.getApplicationContext();
        this.zzd = zznwVar;
        this.zzc = new zznp(handler, zznqVar);
        zznwVar.zzn(new zzox(this, null));
    }

    private final int zzaw(zzql zzqlVar, zzaf zzafVar) {
        if (!"OMX.google.raw.decoder".equals(zzqlVar.zza) || zzel.zza >= 24 || (zzel.zza == 23 && zzel.zzX(this.zzb))) {
            return zzafVar.zzn;
        }
        return -1;
    }

    private static List zzax(zzqq zzqqVar, zzaf zzafVar, boolean z, zznw zznwVar) throws zzqx {
        zzql zzd;
        String str = zzafVar.zzm;
        if (str == null) {
            return zzfuv.zzo();
        }
        if (!zznwVar.zzv(zzafVar) || (zzd = zzrd.zzd()) == null) {
            List zzf = zzrd.zzf(str, false, false);
            String zze = zzrd.zze(zzafVar);
            if (zze == null) {
                return zzfuv.zzm(zzf);
            }
            List zzf2 = zzrd.zzf(zze, false, false);
            zzfus zzi = zzfuv.zzi();
            zzi.zzf(zzf);
            zzi.zzf(zzf2);
            return zzi.zzg();
        }
        return zzfuv.zzp(zzd);
    }

    private final void zzay() {
        long zzb = this.zzd.zzb(zzM());
        if (zzb != Long.MIN_VALUE) {
            if (!this.zzj) {
                zzb = Math.max(this.zzh, zzb);
            }
            this.zzh = zzb;
            this.zzj = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzjz
    public final String zzK() {
        return "MediaCodecAudioRenderer";
    }

    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzjy
    public final boolean zzM() {
        return super.zzM() && this.zzd.zzu();
    }

    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzjy
    public final boolean zzN() {
        return this.zzd.zzt() || super.zzN();
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final float zzP(float f, zzaf zzafVar, zzaf[] zzafVarArr) {
        int r2 = -1;
        for (zzaf zzafVar2 : zzafVarArr) {
            int r3 = zzafVar2.zzA;
            if (r3 != -1) {
                r2 = Math.max(r2, r3);
            }
        }
        if (r2 == -1) {
            return -1.0f;
        }
        return r2 * f;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final int zzQ(zzqq zzqqVar, zzaf zzafVar) throws zzqx {
        boolean z;
        if (zzbt.zzg(zzafVar.zzm)) {
            int r0 = zzel.zza >= 21 ? 32 : 0;
            int r2 = zzafVar.zzF;
            boolean zzav = zzav(zzafVar);
            if (zzav && this.zzd.zzv(zzafVar) && (r2 == 0 || zzrd.zzd() != null)) {
                return r0 | 140;
            }
            if ((!MimeTypes.AUDIO_RAW.equals(zzafVar.zzm) || this.zzd.zzv(zzafVar)) && this.zzd.zzv(zzel.zzF(2, zzafVar.zzz, zzafVar.zzA))) {
                List zzax = zzax(zzqqVar, zzafVar, false, this.zzd);
                if (zzax.isEmpty()) {
                    return TsExtractor.TS_STREAM_TYPE_AC3;
                }
                if (zzav) {
                    zzql zzqlVar = (zzql) zzax.get(0);
                    boolean zzd = zzqlVar.zzd(zzafVar);
                    if (!zzd) {
                        for (int r6 = 1; r6 < zzax.size(); r6++) {
                            zzql zzqlVar2 = (zzql) zzax.get(r6);
                            if (zzqlVar2.zzd(zzafVar)) {
                                zzqlVar = zzqlVar2;
                                z = false;
                                zzd = true;
                                break;
                            }
                        }
                    }
                    z = true;
                    int r62 = true != zzd ? 3 : 4;
                    int r7 = 8;
                    if (zzd && zzqlVar.zze(zzafVar)) {
                        r7 = 16;
                    }
                    return r62 | r7 | r0 | (true != zzqlVar.zzg ? 0 : 64) | (true != z ? 0 : 128);
                }
                return TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
            }
            return TsExtractor.TS_STREAM_TYPE_AC3;
        }
        return 128;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final zzgr zzR(zzql zzqlVar, zzaf zzafVar, zzaf zzafVar2) {
        int r6;
        int r7;
        zzgr zzb = zzqlVar.zzb(zzafVar, zzafVar2);
        int r1 = zzb.zze;
        if (zzaw(zzqlVar, zzafVar2) > this.zze) {
            r1 |= 64;
        }
        String str = zzqlVar.zza;
        if (r1 != 0) {
            r7 = r1;
            r6 = 0;
        } else {
            r6 = zzb.zzd;
            r7 = 0;
        }
        return new zzgr(str, zzafVar, zzafVar2, r6, r7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo
    public final zzgr zzS(zzje zzjeVar) throws zzgy {
        zzgr zzS = super.zzS(zzjeVar);
        this.zzc.zzg(zzjeVar.zza, zzS);
        return zzS;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final zzqh zzV(zzql zzqlVar, zzaf zzafVar, MediaCrypto mediaCrypto, float f) {
        zzaf[] zzJ = zzJ();
        int zzaw = zzaw(zzqlVar, zzafVar);
        if (zzJ.length != 1) {
            for (zzaf zzafVar2 : zzJ) {
                if (zzqlVar.zzb(zzafVar, zzafVar2).zzd != 0) {
                    zzaw = Math.max(zzaw, zzaw(zzqlVar, zzafVar2));
                }
            }
        }
        this.zze = zzaw;
        this.zzf = zzel.zza < 24 && "OMX.SEC.aac.dec".equals(zzqlVar.zza) && "samsung".equals(zzel.zzc) && (zzel.zzb.startsWith("zeroflte") || zzel.zzb.startsWith("herolte") || zzel.zzb.startsWith("heroqlte"));
        String str = zzqlVar.zzc;
        int r0 = this.zze;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("channel-count", zzafVar.zzz);
        mediaFormat.setInteger("sample-rate", zzafVar.zzA);
        zzdw.zzb(mediaFormat, zzafVar.zzo);
        zzdw.zza(mediaFormat, "max-input-size", r0);
        if (zzel.zza >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f && (zzel.zza != 23 || (!"ZTE B2017G".equals(zzel.zzd) && !"AXON 7 mini".equals(zzel.zzd)))) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (zzel.zza <= 28 && MimeTypes.AUDIO_AC4.equals(zzafVar.zzm)) {
            mediaFormat.setInteger("ac4-is-sync", 1);
        }
        if (zzel.zza >= 24 && this.zzd.zza(zzel.zzF(4, zzafVar.zzz, zzafVar.zzA)) == 2) {
            mediaFormat.setInteger("pcm-encoding", 4);
        }
        if (zzel.zza >= 32) {
            mediaFormat.setInteger("max-output-channel-count", 99);
        }
        this.zzg = (!MimeTypes.AUDIO_RAW.equals(zzqlVar.zzb) || MimeTypes.AUDIO_RAW.equals(zzafVar.zzm)) ? null : zzafVar;
        return zzqh.zza(zzqlVar, mediaFormat, zzafVar, null);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final List zzW(zzqq zzqqVar, zzaf zzafVar, boolean z) throws zzqx {
        return zzrd.zzg(zzax(zzqqVar, zzafVar, false, this.zzd), zzafVar);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzX(Exception exc) {
        zzdu.zza("MediaCodecAudioRenderer", "Audio codec error", exc);
        this.zzc.zza(exc);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzY(String str, zzqh zzqhVar, long j, long j2) {
        this.zzc.zzc(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzZ(String str) {
        this.zzc.zzd(str);
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final long zza() {
        if (zzbe() == 2) {
            zzay();
        }
        return this.zzh;
    }

    public final void zzab() {
        this.zzj = true;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzac() {
        this.zzd.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzad(zzgg zzggVar) {
        if (!this.zzi || zzggVar.zzf()) {
            return;
        }
        if (Math.abs(zzggVar.zzd - this.zzh) > 500000) {
            this.zzh = zzggVar.zzd;
        }
        this.zzi = false;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final boolean zzag(zzaf zzafVar) {
        return this.zzd.zzv(zzafVar);
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final zzby zzc() {
        return this.zzd.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final void zzg(zzby zzbyVar) {
        this.zzd.zzo(zzbyVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzjy
    public final zzjg zzi() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzju
    public final void zzp(int r2, Object obj) throws zzgy {
        if (r2 == 2) {
            this.zzd.zzr(((Float) obj).floatValue());
        } else if (r2 == 3) {
            this.zzd.zzk((zzk) obj);
        } else if (r2 != 6) {
            switch (r2) {
                case 9:
                    this.zzd.zzq(((Boolean) obj).booleanValue());
                    return;
                case 10:
                    this.zzd.zzl(((Integer) obj).intValue());
                    return;
                case 11:
                    this.zzl = (zzjx) obj;
                    return;
                default:
                    return;
            }
        } else {
            this.zzd.zzm((zzl) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzs() {
        this.zzk = true;
        try {
            this.zzd.zze();
            try {
                super.zzs();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.zzs();
                throw th;
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzt(boolean z, boolean z2) throws zzgy {
        super.zzt(z, z2);
        this.zzc.zzf(this.zza);
        zzk();
        this.zzd.zzp(zzl());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzu(long j, boolean z) throws zzgy {
        super.zzu(j, z);
        this.zzd.zze();
        this.zzh = j;
        this.zzi = true;
        this.zzj = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzv() {
        try {
            super.zzv();
            if (this.zzk) {
                this.zzk = false;
                this.zzd.zzj();
            }
        } catch (Throwable th) {
            if (this.zzk) {
                this.zzk = false;
                this.zzd.zzj();
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgp
    protected final void zzw() {
        this.zzd.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzgp
    protected final void zzx() {
        zzay();
        this.zzd.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzae() throws zzgy {
        try {
            this.zzd.zzi();
        } catch (zznv e) {
            throw zzbg(e, e.zzc, e.zzb, PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final boolean zzaf(long j, long j2, zzqj zzqjVar, ByteBuffer byteBuffer, int r7, int r8, int r9, long j3, boolean z, boolean z2, zzaf zzafVar) throws zzgy {
        Objects.requireNonNull(byteBuffer);
        if (this.zzg != null && (r8 & 2) != 0) {
            Objects.requireNonNull(zzqjVar);
            zzqjVar.zzn(r7, false);
            return true;
        } else if (z) {
            if (zzqjVar != null) {
                zzqjVar.zzn(r7, false);
            }
            this.zza.zzf += r9;
            this.zzd.zzf();
            return true;
        } else {
            try {
                if (this.zzd.zzs(byteBuffer, j3, r9)) {
                    if (zzqjVar != null) {
                        zzqjVar.zzn(r7, false);
                    }
                    this.zza.zze += r9;
                    return true;
                }
                return false;
            } catch (zzns e) {
                throw zzbg(e, e.zzc, e.zzb, PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED);
            } catch (zznv e2) {
                throw zzbg(e2, zzafVar, e2.zzb, PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzaa(zzaf zzafVar, MediaFormat mediaFormat) throws zzgy {
        int zzn;
        int r0;
        zzaf zzafVar2 = this.zzg;
        int[] r2 = null;
        if (zzafVar2 != null) {
            zzafVar = zzafVar2;
        } else if (zzaj() != null) {
            if (MimeTypes.AUDIO_RAW.equals(zzafVar.zzm)) {
                zzn = zzafVar.zzB;
            } else if (zzel.zza < 24 || !mediaFormat.containsKey("pcm-encoding")) {
                zzn = mediaFormat.containsKey("v-bits-per-sample") ? zzel.zzn(mediaFormat.getInteger("v-bits-per-sample")) : 2;
            } else {
                zzn = mediaFormat.getInteger("pcm-encoding");
            }
            zzad zzadVar = new zzad();
            zzadVar.zzS(MimeTypes.AUDIO_RAW);
            zzadVar.zzN(zzn);
            zzadVar.zzC(zzafVar.zzC);
            zzadVar.zzD(zzafVar.zzD);
            zzadVar.zzw(mediaFormat.getInteger("channel-count"));
            zzadVar.zzT(mediaFormat.getInteger("sample-rate"));
            zzaf zzY = zzadVar.zzY();
            if (this.zzf && zzY.zzz == 6 && (r0 = zzafVar.zzz) < 6) {
                r2 = new int[r0];
                for (int r02 = 0; r02 < zzafVar.zzz; r02++) {
                    r2[r02] = r02;
                }
            }
            zzafVar = zzY;
        }
        try {
            this.zzd.zzd(zzafVar, 0, r2);
        } catch (zznr e) {
            throw zzbg(e, e.zza, false, PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED);
        }
    }
}
