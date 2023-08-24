package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import java.lang.reflect.Method;
import java.util.Objects;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzoa {
    private long zzA;
    private long zzB;
    private long zzC;
    private boolean zzD;
    private long zzE;
    private long zzF;
    private final zznz zza;
    private final long[] zzb;
    private AudioTrack zzc;
    private int zzd;
    private int zze;
    private zzny zzf;
    private int zzg;
    private boolean zzh;
    private long zzi;
    private float zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private Method zzn;
    private long zzo;
    private boolean zzp;
    private boolean zzq;
    private long zzr;
    private long zzs;
    private long zzt;
    private long zzu;
    private int zzv;
    private int zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    public zzoa(zznz zznzVar) {
        this.zza = zznzVar;
        int r4 = zzel.zza;
        try {
            Class[] clsArr = null;
            this.zzn = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException unused) {
        }
        this.zzb = new long[10];
    }

    private final long zzl(long j) {
        return (j * 1000000) / this.zzg;
    }

    private final long zzm() {
        AudioTrack audioTrack = this.zzc;
        Objects.requireNonNull(audioTrack);
        if (this.zzx != C1856C.TIME_UNSET) {
            return Math.min(this.zzA, this.zzz + ((((SystemClock.elapsedRealtime() * 1000) - this.zzx) * this.zzg) / 1000000));
        }
        int playState = audioTrack.getPlayState();
        long j = 0;
        if (playState == 1) {
            return 0L;
        }
        long playbackHeadPosition = audioTrack.getPlaybackHeadPosition() & BodyPartID.bodyIdMax;
        if (this.zzh) {
            if (playState == 2) {
                if (playbackHeadPosition == 0) {
                    this.zzu = this.zzs;
                }
                playState = 2;
            }
            playbackHeadPosition += this.zzu;
        }
        if (zzel.zza <= 29) {
            if (playbackHeadPosition != 0) {
                j = playbackHeadPosition;
            } else if (this.zzs > 0 && playState == 3) {
                if (this.zzy == C1856C.TIME_UNSET) {
                    this.zzy = SystemClock.elapsedRealtime();
                }
                return this.zzs;
            }
            this.zzy = C1856C.TIME_UNSET;
            playbackHeadPosition = j;
        }
        if (this.zzs > playbackHeadPosition) {
            this.zzt++;
        }
        this.zzs = playbackHeadPosition;
        return playbackHeadPosition + (this.zzt << 32);
    }

    private final void zzn() {
        this.zzl = 0L;
        this.zzw = 0;
        this.zzv = 0;
        this.zzm = 0L;
        this.zzC = 0L;
        this.zzF = 0L;
        this.zzk = false;
    }

    public final int zza(long j) {
        return this.zze - ((int) (j - (zzm() * this.zzd)));
    }

    public final long zzb(boolean z) {
        long j;
        zznt zzntVar;
        zznt zzntVar2;
        zznp zznpVar;
        Method method;
        AudioTrack audioTrack;
        long zzE;
        long zzF;
        long zzE2;
        long zzF2;
        zzoa zzoaVar = this;
        AudioTrack audioTrack2 = zzoaVar.zzc;
        Objects.requireNonNull(audioTrack2);
        if (audioTrack2.getPlayState() == 3) {
            long zzl = zzoaVar.zzl(zzm());
            if (zzl != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - zzoaVar.zzm >= 30000) {
                    long[] jArr = zzoaVar.zzb;
                    int r11 = zzoaVar.zzv;
                    jArr[r11] = zzl - nanoTime;
                    zzoaVar.zzv = (r11 + 1) % 10;
                    int r112 = zzoaVar.zzw;
                    if (r112 < 10) {
                        zzoaVar.zzw = r112 + 1;
                    }
                    zzoaVar.zzm = nanoTime;
                    zzoaVar.zzl = 0L;
                    int r10 = 0;
                    while (true) {
                        int r113 = zzoaVar.zzw;
                        if (r10 >= r113) {
                            break;
                        }
                        zzoaVar.zzl += zzoaVar.zzb[r10] / r113;
                        r10++;
                    }
                }
                if (!zzoaVar.zzh) {
                    zzny zznyVar = zzoaVar.zzf;
                    Objects.requireNonNull(zznyVar);
                    if (zznyVar.zzg(nanoTime)) {
                        long zzb = zznyVar.zzb();
                        long zza = zznyVar.zza();
                        if (Math.abs(zzb - nanoTime) > 5000000) {
                            zzon zzonVar = (zzon) zzoaVar.zza;
                            zzE2 = zzonVar.zza.zzE();
                            zzF2 = zzonVar.zza.zzF();
                            Log.w("DefaultAudioSink", "Spurious audio timestamp (system clock mismatch): " + zza + ", " + zzb + ", " + nanoTime + ", " + zzl + ", " + zzE2 + ", " + zzF2);
                            zznyVar.zzd();
                        } else if (Math.abs(zzoaVar.zzl(zza) - zzl) > 5000000) {
                            zzon zzonVar2 = (zzon) zzoaVar.zza;
                            zzE = zzonVar2.zza.zzE();
                            zzF = zzonVar2.zza.zzF();
                            Log.w("DefaultAudioSink", "Spurious audio timestamp (frame position mismatch): " + zza + ", " + zzb + ", " + nanoTime + ", " + zzl + ", " + zzE + ", " + zzF);
                            zznyVar.zzd();
                        } else {
                            zznyVar.zzc();
                        }
                        zzoaVar = this;
                    }
                    if (zzoaVar.zzq && (method = zzoaVar.zzn) != null && nanoTime - zzoaVar.zzr >= 500000) {
                        try {
                            Objects.requireNonNull(zzoaVar.zzc);
                            int r2 = zzel.zza;
                            long intValue = (((Integer) method.invoke(audioTrack, new Object[0])).intValue() * 1000) - zzoaVar.zzi;
                            zzoaVar.zzo = intValue;
                            long max = Math.max(intValue, 0L);
                            zzoaVar.zzo = max;
                            if (max > 5000000) {
                                Log.w("DefaultAudioSink", "Ignoring impossibly large audio latency: " + max);
                                zzoaVar.zzo = 0L;
                            }
                        } catch (Exception unused) {
                            zzoaVar.zzn = null;
                        }
                        zzoaVar.zzr = nanoTime;
                    }
                }
            }
        }
        long nanoTime2 = System.nanoTime() / 1000;
        zzny zznyVar2 = zzoaVar.zzf;
        Objects.requireNonNull(zznyVar2);
        boolean zzf = zznyVar2.zzf();
        if (zzf) {
            j = zzoaVar.zzl(zznyVar2.zza()) + zzel.zzs(nanoTime2 - zznyVar2.zzb(), zzoaVar.zzj);
        } else {
            if (zzoaVar.zzw == 0) {
                j = zzoaVar.zzl(zzm());
            } else {
                j = zzoaVar.zzl + nanoTime2;
            }
            if (!z) {
                j = Math.max(0L, j - zzoaVar.zzo);
            }
        }
        if (zzoaVar.zzD != zzf) {
            zzoaVar.zzF = zzoaVar.zzC;
            zzoaVar.zzE = zzoaVar.zzB;
        }
        long j2 = nanoTime2 - zzoaVar.zzF;
        if (j2 < 1000000) {
            long j3 = (j2 * 1000) / 1000000;
            j = ((j * j3) + ((1000 - j3) * (zzoaVar.zzE + zzel.zzs(j2, zzoaVar.zzj)))) / 1000;
        }
        if (!zzoaVar.zzk) {
            long j4 = zzoaVar.zzB;
            if (j > j4) {
                zzoaVar.zzk = true;
                long currentTimeMillis = System.currentTimeMillis() - zzel.zzz(zzel.zzu(zzel.zzz(j - j4), zzoaVar.zzj));
                zzos zzosVar = ((zzon) zzoaVar.zza).zza;
                zzntVar = zzosVar.zzn;
                if (zzntVar != null) {
                    zzntVar2 = zzosVar.zzn;
                    zznpVar = ((zzox) zzntVar2).zza.zzc;
                    zznpVar.zzr(currentTimeMillis);
                }
            }
        }
        zzoaVar.zzC = nanoTime2;
        zzoaVar.zzB = j;
        zzoaVar.zzD = zzf;
        return j;
    }

    public final void zzc(long j) {
        this.zzz = zzm();
        this.zzx = SystemClock.elapsedRealtime() * 1000;
        this.zzA = j;
    }

    public final void zzd() {
        zzn();
        this.zzc = null;
        this.zzf = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zze(android.media.AudioTrack r4, boolean r5, int r6, int r7, int r8) {
        /*
            r3 = this;
            r3.zzc = r4
            r3.zzd = r7
            r3.zze = r8
            com.google.android.gms.internal.ads.zzny r0 = new com.google.android.gms.internal.ads.zzny
            r0.<init>(r4)
            r3.zzf = r0
            int r4 = r4.getSampleRate()
            r3.zzg = r4
            r4 = 6
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L25
            int r5 = com.google.android.gms.internal.ads.zzel.zza
            r2 = 23
            if (r5 >= r2) goto L25
            r5 = 5
            if (r6 == r5) goto L26
            if (r6 != r4) goto L25
            r6 = 6
            goto L26
        L25:
            r0 = 0
        L26:
            r3.zzh = r0
            boolean r4 = com.google.android.gms.internal.ads.zzel.zzV(r6)
            r3.zzq = r4
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L3c
            int r8 = r8 / r7
            long r7 = (long) r8
            long r7 = r3.zzl(r7)
            goto L3d
        L3c:
            r7 = r5
        L3d:
            r3.zzi = r7
            r7 = 0
            r3.zzs = r7
            r3.zzt = r7
            r3.zzu = r7
            r3.zzp = r1
            r3.zzx = r5
            r3.zzy = r5
            r3.zzr = r7
            r3.zzo = r7
            r4 = 1065353216(0x3f800000, float:1.0)
            r3.zzj = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzoa.zze(android.media.AudioTrack, boolean, int, int, int):void");
    }

    public final boolean zzg(long j) {
        if (j <= zzm()) {
            if (this.zzh) {
                AudioTrack audioTrack = this.zzc;
                Objects.requireNonNull(audioTrack);
                return audioTrack.getPlayState() == 2 && zzm() == 0;
            }
            return false;
        }
        return true;
    }

    public final boolean zzh() {
        AudioTrack audioTrack = this.zzc;
        Objects.requireNonNull(audioTrack);
        return audioTrack.getPlayState() == 3;
    }

    public final boolean zzi(long j) {
        return this.zzy != C1856C.TIME_UNSET && j > 0 && SystemClock.elapsedRealtime() - this.zzy >= 200;
    }

    public final boolean zzj(long j) {
        zznt zzntVar;
        long j2;
        zznt zzntVar2;
        zznp zznpVar;
        AudioTrack audioTrack = this.zzc;
        Objects.requireNonNull(audioTrack);
        int playState = audioTrack.getPlayState();
        if (this.zzh) {
            if (playState == 2) {
                this.zzp = false;
                return false;
            } else if (playState == 1) {
                if (zzm() == 0) {
                    return false;
                }
                playState = 1;
            }
        }
        boolean z = this.zzp;
        boolean zzg = zzg(j);
        this.zzp = zzg;
        if (z && !zzg && playState != 1) {
            zznz zznzVar = this.zza;
            int r4 = this.zze;
            long zzz = zzel.zzz(this.zzi);
            zzon zzonVar = (zzon) zznzVar;
            zzntVar = zzonVar.zza.zzn;
            if (zzntVar != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                zzos zzosVar = zzonVar.zza;
                j2 = zzosVar.zzS;
                zzntVar2 = zzosVar.zzn;
                zznpVar = ((zzox) zzntVar2).zza.zzc;
                zznpVar.zzt(r4, zzz, elapsedRealtime - j2);
            }
        }
        return true;
    }

    public final boolean zzk() {
        zzn();
        if (this.zzx == C1856C.TIME_UNSET) {
            zzny zznyVar = this.zzf;
            Objects.requireNonNull(zznyVar);
            zznyVar.zze();
            return true;
        }
        return false;
    }

    public final void zzf() {
        zzny zznyVar = this.zzf;
        Objects.requireNonNull(zznyVar);
        zznyVar.zze();
    }
}
