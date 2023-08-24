package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.Surface;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcma extends zzcin implements zzfx, zzkp {
    public static final /* synthetic */ int zzc = 0;
    private final Context zzd;
    private final zzcll zze;
    private final zzvo zzf;
    private final zzciv zzg;
    private final WeakReference zzh;
    private final zztl zzi;
    private zzkd zzj;
    private ByteBuffer zzk;
    private boolean zzl;
    private zzcim zzm;
    private int zzn;
    private int zzo;
    private long zzp;
    private final String zzq;
    private final int zzr;
    private final ArrayList zzt;
    private volatile zzcln zzu;
    private final Object zzs = new Object();
    private final Set zzv = new HashSet();

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00f5, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(com.google.android.gms.internal.ads.zzbiy.zzbB)).booleanValue() == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00f9, code lost:
        if (r7.zzj == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00fb, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00fe, code lost:
        if (r7.zzo == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0100, code lost:
        r8 = new com.google.android.gms.internal.ads.zzclr(r5, r6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0108, code lost:
        if (r7.zzi <= 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x010a, code lost:
        r8 = new com.google.android.gms.internal.ads.zzcls(r5, r6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0110, code lost:
        r8 = new com.google.android.gms.internal.ads.zzclt(r5, r6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0117, code lost:
        if (r7.zzj == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0119, code lost:
        r7 = new com.google.android.gms.internal.ads.zzclu(r5, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0120, code lost:
        r7 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0121, code lost:
        r6 = r5.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0123, code lost:
        if (r6 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0129, code lost:
        if (r6.limit() <= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x012b, code lost:
        r6 = new byte[r5.zzk.limit()];
        r5.zzk.get(r6);
        r7 = new com.google.android.gms.internal.ads.zzclv(r7, r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzcma(android.content.Context r6, com.google.android.gms.internal.ads.zzciv r7, com.google.android.gms.internal.ads.zzciw r8) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcma.<init>(android.content.Context, com.google.android.gms.internal.ads.zzciv, com.google.android.gms.internal.ads.zzciw):void");
    }

    private final boolean zzZ() {
        return this.zzu != null && this.zzu.zzq();
    }

    public final void finalize() {
        zza.decrementAndGet();
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("OfficialSimpleExoPlayerAdapter finalize ".concat(toString()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzA() {
        if (zzZ()) {
            return 0L;
        }
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzB() {
        if (!zzZ()) {
            synchronized (this.zzs) {
                while (!this.zzt.isEmpty()) {
                    long j = this.zzp;
                    Map zze = ((zzfs) this.zzt.remove(0)).zze();
                    long j2 = 0;
                    if (zze != null) {
                        Iterator it = zze.entrySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Map.Entry entry = (Map.Entry) it.next();
                            if (entry != null) {
                                try {
                                    if (entry.getKey() != null && zzfrm.zzc("content-length", (CharSequence) entry.getKey()) && entry.getValue() != null && ((List) entry.getValue()).get(0) != null) {
                                        j2 = Long.parseLong((String) ((List) entry.getValue()).get(0));
                                        break;
                                    }
                                } catch (NumberFormatException unused) {
                                    continue;
                                }
                            }
                        }
                    }
                    this.zzp = j + j2;
                }
            }
            return this.zzp;
        }
        return this.zzu.zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzC(Uri[] uriArr, String str) {
        zzD(uriArr, str, ByteBuffer.allocate(0), false);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzE() {
        zzkd zzkdVar = this.zzj;
        if (zzkdVar != null) {
            zzkdVar.zzA(this);
            this.zzj.zzz();
            this.zzj = null;
            zzb.decrementAndGet();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzF(long j) {
        zzkd zzkdVar = this.zzj;
        zzkdVar.zzp(zzkdVar.zzf(), j);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzG(int r2) {
        this.zze.zzk(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzH(int r2) {
        this.zze.zzl(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzI(zzcim zzcimVar) {
        this.zzm = zzcimVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzJ(int r2) {
        this.zze.zzm(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzK(int r2) {
        this.zze.zzn(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzL(boolean z) {
        this.zzj.zzC(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzM(boolean z) {
        if (this.zzj == null) {
            return;
        }
        int r0 = 0;
        while (true) {
            this.zzj.zzt();
            if (r0 >= 2) {
                return;
            }
            zzvo zzvoVar = this.zzf;
            zzva zzc2 = zzvoVar.zzc().zzc();
            zzc2.zzo(r0, !z);
            zzvoVar.zzj(zzc2);
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzN(int r3) {
        for (WeakReference weakReference : this.zzv) {
            zzclk zzclkVar = (zzclk) weakReference.get();
            if (zzclkVar != null) {
                zzclkVar.zzm(r3);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzO(Surface surface, boolean z) {
        zzkd zzkdVar = this.zzj;
        if (zzkdVar == null) {
            return;
        }
        zzkdVar.zzE(surface);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzP(float f, boolean z) {
        zzkd zzkdVar = this.zzj;
        if (zzkdVar == null) {
            return;
        }
        zzkdVar.zzF(f);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzQ() {
        this.zzj.zzG();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final boolean zzR() {
        return this.zzj != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzev zzS(String str, boolean z) {
        zzcma zzcmaVar = true != z ? null : this;
        zzciv zzcivVar = this.zzg;
        return new zzcmd(str, zzcmaVar, zzcivVar.zzd, zzcivVar.zzf, zzcivVar.zzp, zzcivVar.zzq);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzev zzT(String str, boolean z) {
        zzcma zzcmaVar = true != z ? null : this;
        zzciv zzcivVar = this.zzg;
        zzclk zzclkVar = new zzclk(str, zzcmaVar, zzcivVar.zzd, zzcivVar.zzf, zzcivVar.zzi);
        this.zzv.add(new WeakReference(zzclkVar));
        return zzclkVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzev zzU(String str, boolean z) {
        zzfd zzfdVar = new zzfd();
        zzfdVar.zzf(str);
        zzfdVar.zze(true != z ? null : this);
        zzfdVar.zzc(this.zzg.zzd);
        zzfdVar.zzd(this.zzg.zzf);
        zzfdVar.zzb(true);
        return zzfdVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzev zzV(zzeu zzeuVar) {
        return new zzcln(this.zzd, zzeuVar.zza(), this.zzq, this.zzr, this, new zzclq(this), null);
    }

    final zzsi zzW(Uri uri) {
        zzaj zzajVar = new zzaj();
        zzajVar.zzb(uri);
        zzbg zzc2 = zzajVar.zzc();
        zztl zztlVar = this.zzi;
        zztlVar.zza(this.zzg.zzg);
        return zztlVar.zzb(zzc2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzX(boolean z, long j) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            zzcimVar.zzi(z, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzjy[] zzY(Handler handler, zzya zzyaVar, zznq zznqVar, zzug zzugVar, zzrg zzrgVar) {
        Context context = this.zzd;
        zzqq zzqqVar = zzqq.zzb;
        zznb zznbVar = zznb.zza;
        zzne[] zzneVarArr = new zzne[0];
        zzog zzogVar = new zzog();
        zznb zznbVar2 = zznb.zza;
        if (zznbVar == null) {
            Objects.requireNonNull(zznbVar2, "Both parameters are null");
            zznbVar = zznbVar2;
        }
        zzogVar.zzb(zznbVar);
        zzogVar.zzc(zzneVarArr);
        return new zzjy[]{new zzoy(context, zzqi.zza, zzqqVar, false, handler, zznqVar, zzogVar.zzd()), new zzxd(this.zzd, zzqi.zza, zzqq.zzb, 0L, false, handler, zzyaVar, -1, 30.0f)};
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final void zza(zzev zzevVar, zzfa zzfaVar, boolean z, int r4) {
        this.zzn += r4;
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final void zzb(zzev zzevVar, zzfa zzfaVar, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final void zzc(zzev zzevVar, zzfa zzfaVar, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final void zzd(zzev zzevVar, zzfa zzfaVar, boolean z) {
        if (zzevVar instanceof zzfs) {
            synchronized (this.zzs) {
                this.zzt.add((zzfs) zzevVar);
            }
        } else if (zzevVar instanceof zzcln) {
            this.zzu = (zzcln) zzevVar;
            final zzciw zzciwVar = (zzciw) this.zzh.get();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() && zzciwVar != null && this.zzu.zzn()) {
                final HashMap hashMap = new HashMap();
                hashMap.put("gcacheHit", String.valueOf(this.zzu.zzp()));
                hashMap.put("gcacheDownloaded", String.valueOf(this.zzu.zzo()));
                com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzclx
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzciw zzciwVar2 = zzciw.this;
                        Map map = hashMap;
                        int r2 = zzcma.zzc;
                        zzciwVar2.zzd("onGcacheInfoEvent", map);
                    }
                });
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zze(zzkn zzknVar, zzaf zzafVar, zzgr zzgrVar) {
        zzciw zzciwVar = (zzciw) this.zzh.get();
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() || zzciwVar == null || zzafVar == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("audioMime", zzafVar.zzl);
        hashMap.put("audioSampleMime", zzafVar.zzm);
        hashMap.put("audioCodec", zzafVar.zzj);
        zzciwVar.zzd("onMetadataEvent", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzf(zzkn zzknVar, int r2, long j, long j2) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzg(zzkn zzknVar, zzsc zzscVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzh(zzkn zzknVar, int r2, long j) {
        this.zzo += r2;
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzi(zzcg zzcgVar, zzko zzkoVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzj(zzkn zzknVar, zzrx zzrxVar, zzsc zzscVar, IOException iOException, boolean z) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            if (this.zzg.zzl) {
                zzcimVar.zzl("onLoadException", iOException);
            } else {
                zzcimVar.zzk("onLoadError", iOException);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzk(zzkn zzknVar, int r2) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            zzcimVar.zzm(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzl(zzkn zzknVar, zzbw zzbwVar) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            zzcimVar.zzk("onPlayerError", zzbwVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzm(zzkn zzknVar, zzcf zzcfVar, zzcf zzcfVar2, int r4) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzn(zzkn zzknVar, Object obj, long j) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            zzcimVar.zzv();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final /* synthetic */ void zzo(zzkn zzknVar, zzgq zzgqVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzp(zzkn zzknVar, zzaf zzafVar, zzgr zzgrVar) {
        zzciw zzciwVar = (zzciw) this.zzh.get();
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() || zzciwVar == null || zzafVar == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("frameRate", String.valueOf(zzafVar.zzt));
        hashMap.put("bitRate", String.valueOf(zzafVar.zzi));
        int r0 = zzafVar.zzr;
        int r1 = zzafVar.zzs;
        hashMap.put("resolution", r0 + "x" + r1);
        hashMap.put("videoMime", zzafVar.zzl);
        hashMap.put("videoSampleMime", zzafVar.zzm);
        hashMap.put("videoCodec", zzafVar.zzj);
        zzciwVar.zzd("onMetadataEvent", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzkp
    public final void zzq(zzkn zzknVar, zzda zzdaVar) {
        zzcim zzcimVar = this.zzm;
        if (zzcimVar != null) {
            zzcimVar.zzC(zzdaVar.zzc, zzdaVar.zzd);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final int zzr() {
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final int zzt() {
        return this.zzj.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzv() {
        return this.zzj.zzu();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzw() {
        return this.zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzx() {
        if (zzZ() && this.zzu.zzp()) {
            return Math.min(this.zzn, this.zzu.zzk());
        }
        return 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzy() {
        return this.zzj.zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzz() {
        return this.zzj.zzv();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzD(Uri[] uriArr, String str, ByteBuffer byteBuffer, boolean z) {
        zzsi zzsyVar;
        if (this.zzj == null) {
            return;
        }
        this.zzk = byteBuffer;
        this.zzl = z;
        int length = uriArr.length;
        if (length == 1) {
            zzsyVar = zzW(uriArr[0]);
        } else {
            zzsi[] zzsiVarArr = new zzsi[length];
            for (int r4 = 0; r4 < uriArr.length; r4++) {
                zzsiVarArr[r4] = zzW(uriArr[r4]);
            }
            zzsyVar = new zzsy(false, false, zzsiVarArr);
        }
        this.zzj.zzB(zzsyVar);
        this.zzj.zzy();
        zzb.incrementAndGet();
    }
}
