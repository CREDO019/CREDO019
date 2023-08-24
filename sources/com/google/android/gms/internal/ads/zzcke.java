package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
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
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcke extends zzcin implements zzazx, zzaxv, zzbbh, zzatp, zzasf {
    public static final /* synthetic */ int zzc = 0;
    private final Context zzd;
    private final zzcjt zze;
    private final zzasx zzf;
    private final zzasx zzg;
    private final zzayz zzh;
    private final zzciv zzi;
    private zzasi zzj;
    private ByteBuffer zzk;
    private boolean zzl;
    private final WeakReference zzm;
    private zzcim zzn;
    private int zzo;
    private int zzp;
    private long zzq;
    private final String zzr;
    private final int zzs;
    private final ArrayList zzu;
    private volatile zzcjs zzv;
    private final Object zzt = new Object();
    private final Set zzw = new HashSet();

    public zzcke(Context context, zzciv zzcivVar, zzciw zzciwVar) {
        this.zzd = context;
        this.zzi = zzcivVar;
        this.zzm = new WeakReference(zzciwVar);
        zzcjt zzcjtVar = new zzcjt();
        this.zze = zzcjtVar;
        zzbaw zzbawVar = new zzbaw(context, zzaws.zza, 0L, com.google.android.gms.ads.internal.util.zzs.zza, this, -1);
        this.zzf = zzbawVar;
        zzaue zzaueVar = new zzaue(zzaws.zza, null, true, com.google.android.gms.ads.internal.util.zzs.zza, this);
        this.zzg = zzaueVar;
        zzayv zzayvVar = new zzayv(null);
        this.zzh = zzayvVar;
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("ForkedExoPlayerAdapter initialize ".concat(toString()));
        }
        zza.incrementAndGet();
        zzasi zza = zzasj.zza(new zzasx[]{zzaueVar, zzbawVar}, zzayvVar, zzcjtVar);
        this.zzj = zza;
        zza.zze(this);
        this.zzo = 0;
        this.zzq = 0L;
        this.zzp = 0;
        this.zzu = new ArrayList();
        this.zzv = null;
        this.zzr = (zzciwVar == null || zzciwVar.zzt() == null) ? "" : zzciwVar.zzt();
        this.zzs = zzciwVar != null ? zzciwVar.zzh() : 0;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzn)).booleanValue()) {
            this.zzj.zzg();
        }
        if (zzciwVar != null && zzciwVar.zzg() > 0) {
            this.zzj.zzp(zzciwVar.zzg());
        }
        if (zzciwVar != null && zzciwVar.zzf() > 0) {
            this.zzj.zzo(zzciwVar.zzf());
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzp)).booleanValue()) {
            this.zzj.zzi();
            this.zzj.zzh(((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzq)).intValue());
        }
    }

    private final boolean zzY() {
        return this.zzv != null && this.zzv.zzk();
    }

    public final void finalize() throws Throwable {
        zza.decrementAndGet();
        if (com.google.android.gms.ads.internal.util.zze.zzc()) {
            com.google.android.gms.ads.internal.util.zze.zza("ForkedExoPlayerAdapter finalize ".concat(toString()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzA() {
        if (zzY()) {
            return 0L;
        }
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzB() {
        if (!zzY()) {
            synchronized (this.zzt) {
                while (!this.zzu.isEmpty()) {
                    long j = this.zzq;
                    Map zze = ((zzazr) this.zzu.remove(0)).zze();
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
                    this.zzq = j + j2;
                }
            }
            return this.zzq;
        }
        return this.zzv.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzC(Uri[] uriArr, String str) {
        zzD(uriArr, str, ByteBuffer.allocate(0), false);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzE() {
        zzasi zzasiVar = this.zzj;
        if (zzasiVar != null) {
            zzasiVar.zzl(this);
            this.zzj.zzk();
            this.zzj = null;
            zzb.decrementAndGet();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzF(long j) {
        this.zzj.zzm(j);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzG(int r2) {
        this.zze.zzf(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzH(int r2) {
        this.zze.zzg(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzI(zzcim zzcimVar) {
        this.zzn = zzcimVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzJ(int r2) {
        this.zze.zzh(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzK(int r2) {
        this.zze.zzi(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzL(boolean z) {
        this.zzj.zzq(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzM(boolean z) {
        if (this.zzj != null) {
            for (int r0 = 0; r0 < 2; r0++) {
                this.zzh.zze(r0, !z);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzN(int r3) {
        for (WeakReference weakReference : this.zzw) {
            zzcjq zzcjqVar = (zzcjq) weakReference.get();
            if (zzcjqVar != null) {
                zzcjqVar.zzh(r3);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzO(Surface surface, boolean z) {
        zzasi zzasiVar = this.zzj;
        if (zzasiVar == null) {
            return;
        }
        zzash zzashVar = new zzash(this.zzf, 1, surface);
        if (z) {
            zzasiVar.zzf(zzashVar);
        } else {
            zzasiVar.zzn(zzashVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzP(float f, boolean z) {
        if (this.zzj == null) {
            return;
        }
        this.zzj.zzn(new zzash(this.zzg, 2, Float.valueOf(f)));
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzQ() {
        this.zzj.zzr();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final boolean zzR() {
        return this.zzj != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzazi zzS(String str, boolean z) {
        zzcke zzckeVar = true != z ? null : this;
        zzciv zzcivVar = this.zzi;
        zzcjq zzcjqVar = new zzcjq(str, zzckeVar, zzcivVar.zzd, zzcivVar.zzf, zzcivVar.zzi);
        this.zzw.add(new WeakReference(zzcjqVar));
        return zzcjqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzazi zzT(String str, boolean z) {
        zzcke zzckeVar = true != z ? null : this;
        zzciv zzcivVar = this.zzi;
        return new zzazm(str, null, zzckeVar, zzcivVar.zzd, zzcivVar.zzf, true, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzazi zzU(zzazh zzazhVar) {
        return new zzcjs(this.zzd, zzazhVar.zza(), this.zzr, this.zzs, this, new zzcka(this), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzV(boolean z, long j) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            zzcimVar.zzi(z, j);
        }
    }

    public final void zzW(zzazi zzaziVar, int r2) {
        this.zzo += r2;
    }

    @Override // com.google.android.gms.internal.ads.zzazx
    /* renamed from: zzX */
    public final void zzk(zzazi zzaziVar, zzazk zzazkVar) {
        if (zzaziVar instanceof zzazr) {
            synchronized (this.zzt) {
                this.zzu.add((zzazr) zzaziVar);
            }
        } else if (zzaziVar instanceof zzcjs) {
            this.zzv = (zzcjs) zzaziVar;
            final zzciw zzciwVar = (zzciw) this.zzm.get();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() && zzciwVar != null && this.zzv.zzh()) {
                final HashMap hashMap = new HashMap();
                hashMap.put("gcacheHit", String.valueOf(this.zzv.zzj()));
                hashMap.put("gcacheDownloaded", String.valueOf(this.zzv.zzi()));
                com.google.android.gms.ads.internal.util.zzs.zza.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzckb
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzciw zzciwVar2 = zzciw.this;
                        Map map = hashMap;
                        int r2 = zzcke.zzc;
                        zzciwVar2.zzd("onGcacheInfoEvent", map);
                    }
                });
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zza(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zzb(zzasw zzaswVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zzc(zzase zzaseVar) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            zzcimVar.zzk("onPlayerError", zzaseVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zzd(boolean z, int r2) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            zzcimVar.zzm(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zze() {
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zzf(zzatd zzatdVar, Object obj) {
    }

    @Override // com.google.android.gms.internal.ads.zzasf
    public final void zzg(zzayp zzaypVar, zzazb zzazbVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzatp
    public final void zzh(zzass zzassVar) {
        zzciw zzciwVar = (zzciw) this.zzm.get();
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() || zzciwVar == null || zzassVar == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("audioMime", zzassVar.zze);
        hashMap.put("audioSampleMime", zzassVar.zzf);
        hashMap.put("audioCodec", zzassVar.zzc);
        zzciwVar.zzd("onMetadataEvent", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzaxv
    public final void zzi(IOException iOException) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            if (this.zzi.zzl) {
                zzcimVar.zzl("onLoadException", iOException);
            } else {
                zzcimVar.zzk("onLoadError", iOException);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazx
    public final /* synthetic */ void zzj(Object obj, int r2) {
        this.zzo += r2;
    }

    @Override // com.google.android.gms.internal.ads.zzbbh
    public final void zzl(int r1, long j) {
        this.zzp += r1;
    }

    @Override // com.google.android.gms.internal.ads.zzbbh
    public final void zzm(Surface surface) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            zzcimVar.zzv();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbh
    public final void zzn(zzass zzassVar) {
        zzciw zzciwVar = (zzciw) this.zzm.get();
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue() || zzciwVar == null || zzassVar == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("frameRate", String.valueOf(zzassVar.zzl));
        hashMap.put("bitRate", String.valueOf(zzassVar.zzb));
        int r2 = zzassVar.zzj;
        int r3 = zzassVar.zzk;
        hashMap.put("resolution", r2 + "x" + r3);
        hashMap.put("videoMime", zzassVar.zze);
        hashMap.put("videoSampleMime", zzassVar.zzf);
        hashMap.put("videoCodec", zzassVar.zzc);
        zzciwVar.zzd("onMetadataEvent", hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzbbh
    public final void zzo(int r1, int r2, int r3, float f) {
        zzcim zzcimVar = this.zzn;
        if (zzcimVar != null) {
            zzcimVar.zzC(r1, r2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0046, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(com.google.android.gms.internal.ads.zzbiy.zzbB)).booleanValue() == false) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final com.google.android.gms.internal.ads.zzaya zzp(android.net.Uri r11, final java.lang.String r12) {
        /*
            r10 = this;
            com.google.android.gms.internal.ads.zzaxw r9 = new com.google.android.gms.internal.ads.zzaxw
            boolean r0 = r10.zzl
            if (r0 == 0) goto L23
            java.nio.ByteBuffer r0 = r10.zzk
            int r0 = r0.limit()
            if (r0 <= 0) goto L23
            java.nio.ByteBuffer r12 = r10.zzk
            int r12 = r12.limit()
            byte[] r12 = new byte[r12]
            java.nio.ByteBuffer r0 = r10.zzk
            r0.get(r12)
            com.google.android.gms.internal.ads.zzcju r0 = new com.google.android.gms.internal.ads.zzcju
            r0.<init>()
        L20:
            r2 = r0
            goto L94
        L23:
            com.google.android.gms.internal.ads.zzbiq r0 = com.google.android.gms.internal.ads.zzbiy.zzbK
            com.google.android.gms.internal.ads.zzbiw r1 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r0 = r1.zzb(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            if (r0 == 0) goto L48
            com.google.android.gms.internal.ads.zzbiq r0 = com.google.android.gms.internal.ads.zzbiy.zzbB
            com.google.android.gms.internal.ads.zzbiw r2 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r0 = r2.zzb(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L50
        L48:
            com.google.android.gms.internal.ads.zzciv r0 = r10.zzi
            boolean r0 = r0.zzj
            if (r0 != 0) goto L4f
            goto L50
        L4f:
            r1 = 0
        L50:
            com.google.android.gms.internal.ads.zzciv r0 = r10.zzi
            boolean r2 = r0.zzo
            if (r2 == 0) goto L5c
            com.google.android.gms.internal.ads.zzcjv r0 = new com.google.android.gms.internal.ads.zzcjv
            r0.<init>()
            goto L6b
        L5c:
            int r0 = r0.zzi
            if (r0 <= 0) goto L66
            com.google.android.gms.internal.ads.zzcjw r0 = new com.google.android.gms.internal.ads.zzcjw
            r0.<init>()
            goto L6b
        L66:
            com.google.android.gms.internal.ads.zzcjx r0 = new com.google.android.gms.internal.ads.zzcjx
            r0.<init>()
        L6b:
            com.google.android.gms.internal.ads.zzciv r12 = r10.zzi
            boolean r12 = r12.zzj
            if (r12 == 0) goto L77
            com.google.android.gms.internal.ads.zzcjy r12 = new com.google.android.gms.internal.ads.zzcjy
            r12.<init>()
            r0 = r12
        L77:
            java.nio.ByteBuffer r12 = r10.zzk
            if (r12 == 0) goto L20
            int r12 = r12.limit()
            if (r12 <= 0) goto L20
            java.nio.ByteBuffer r12 = r10.zzk
            int r12 = r12.limit()
            byte[] r12 = new byte[r12]
            java.nio.ByteBuffer r1 = r10.zzk
            r1.get(r12)
            com.google.android.gms.internal.ads.zzcjz r1 = new com.google.android.gms.internal.ads.zzcjz
            r1.<init>()
            r2 = r1
        L94:
            com.google.android.gms.internal.ads.zzbiq r12 = com.google.android.gms.internal.ads.zzbiy.zzm
            com.google.android.gms.internal.ads.zzbiw r0 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r12 = r0.zzb(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto La9
            com.google.android.gms.internal.ads.zzckc r12 = new com.google.android.gms.internal.ads.zzaux() { // from class: com.google.android.gms.internal.ads.zzckc
                static {
                    /*
                        com.google.android.gms.internal.ads.zzckc r0 = new com.google.android.gms.internal.ads.zzckc
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:com.google.android.gms.internal.ads.zzckc) com.google.android.gms.internal.ads.zzckc.zza com.google.android.gms.internal.ads.zzckc
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckc.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckc.<init>():void");
                }

                @Override // com.google.android.gms.internal.ads.zzaux
                public final com.google.android.gms.internal.ads.zzauv[] zza() {
                    /*
                        r4 = this;
                        int r0 = com.google.android.gms.internal.ads.zzcke.zzc
                        r0 = 3
                        com.google.android.gms.internal.ads.zzauv[] r0 = new com.google.android.gms.internal.ads.zzauv[r0]
                        com.google.android.gms.internal.ads.zzawi r1 = new com.google.android.gms.internal.ads.zzawi
                        r1.<init>()
                        r2 = 0
                        r0[r2] = r1
                        com.google.android.gms.internal.ads.zzavn r1 = new com.google.android.gms.internal.ads.zzavn
                        r1.<init>(r2)
                        r3 = 1
                        r0[r3] = r1
                        com.google.android.gms.internal.ads.zzawe r1 = new com.google.android.gms.internal.ads.zzawe
                        r3 = 0
                        r1.<init>(r2, r3, r3)
                        r2 = 2
                        r0[r2] = r1
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckc.zza():com.google.android.gms.internal.ads.zzauv[]");
                }
            }
            goto Lab
        La9:
            com.google.android.gms.internal.ads.zzckd r12 = new com.google.android.gms.internal.ads.zzaux() { // from class: com.google.android.gms.internal.ads.zzckd
                static {
                    /*
                        com.google.android.gms.internal.ads.zzckd r0 = new com.google.android.gms.internal.ads.zzckd
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:com.google.android.gms.internal.ads.zzckd) com.google.android.gms.internal.ads.zzckd.zza com.google.android.gms.internal.ads.zzckd
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckd.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckd.<init>():void");
                }

                @Override // com.google.android.gms.internal.ads.zzaux
                public final com.google.android.gms.internal.ads.zzauv[] zza() {
                    /*
                        r3 = this;
                        int r0 = com.google.android.gms.internal.ads.zzcke.zzc
                        r0 = 2
                        com.google.android.gms.internal.ads.zzauv[] r0 = new com.google.android.gms.internal.ads.zzauv[r0]
                        com.google.android.gms.internal.ads.zzawi r1 = new com.google.android.gms.internal.ads.zzawi
                        r1.<init>()
                        r2 = 0
                        r0[r2] = r1
                        com.google.android.gms.internal.ads.zzavn r1 = new com.google.android.gms.internal.ads.zzavn
                        r1.<init>(r2)
                        r2 = 1
                        r0[r2] = r1
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzckd.zza():com.google.android.gms.internal.ads.zzauv[]");
                }
            }
        Lab:
            r3 = r12
            com.google.android.gms.internal.ads.zzciv r12 = r10.zzi
            int r4 = r12.zzk
            com.google.android.gms.internal.ads.zzfph r5 = com.google.android.gms.ads.internal.util.zzs.zza
            r7 = 0
            com.google.android.gms.internal.ads.zzciv r12 = r10.zzi
            int r8 = r12.zzg
            r0 = r9
            r1 = r11
            r6 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcke.zzp(android.net.Uri, java.lang.String):com.google.android.gms.internal.ads.zzaya");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzazi zzq(String str, boolean z) {
        zzcke zzckeVar = true != z ? null : this;
        zzciv zzcivVar = this.zzi;
        return new zzcki(str, zzckeVar, zzcivVar.zzd, zzcivVar.zzf, zzcivVar.zzp, zzcivVar.zzq);
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final int zzr() {
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final int zzt() {
        return this.zzj.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzv() {
        return this.zzj.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzw() {
        return this.zzo;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzx() {
        if (zzY() && this.zzv.zzj()) {
            return Math.min(this.zzo, this.zzv.zze());
        }
        return 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzy() {
        return this.zzj.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final long zzz() {
        return this.zzj.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzcin
    public final void zzD(Uri[] uriArr, String str, ByteBuffer byteBuffer, boolean z) {
        zzaya zzayeVar;
        if (this.zzj == null) {
            return;
        }
        this.zzk = byteBuffer;
        this.zzl = z;
        int length = uriArr.length;
        if (length == 1) {
            zzayeVar = zzp(uriArr[0], str);
        } else {
            zzaya[] zzayaVarArr = new zzaya[length];
            for (int r5 = 0; r5 < uriArr.length; r5++) {
                zzayaVarArr[r5] = zzp(uriArr[r5], str);
            }
            zzayeVar = new zzaye(zzayaVarArr);
        }
        this.zzj.zzj(zzayeVar);
        zzb.incrementAndGet();
    }
}
