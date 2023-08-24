package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import android.view.SurfaceHolder;
import android.view.TextureView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzin implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener, zzya, zznq, zzug, zzrg, zzgn, zzgj, zzkf, zzgz {
    public static final /* synthetic */ int zzb = 0;
    final /* synthetic */ zzir zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzin(zzir zzirVar, zzim zzimVar) {
        this.zza = zzirVar;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int r3, int r4) {
        zzir.zzN(this.zza, surfaceTexture);
        zzir.zzL(this.zza, r3, r4);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzir.zzO(this.zza, null);
        zzir.zzL(this.zza, 0, 0);
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int r2, int r3) {
        zzir.zzL(this.zza, r2, r3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int r2, int r3, int r4) {
        zzir.zzL(this.zza, r3, r4);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        zzir.zzL(this.zza, 0, 0);
    }

    @Override // com.google.android.gms.internal.ads.zzgz
    public final /* synthetic */ void zza(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzgz
    public final void zzb(boolean z) {
        zzir.zzQ(this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzc(Exception exc) {
        zzir.zzC(this.zza).zzy(exc);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzd(String str, long j, long j2) {
        zzir.zzC(this.zza).zzz(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zze(String str) {
        zzir.zzC(this.zza).zzA(str);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzf(zzgq zzgqVar) {
        zzir.zzC(this.zza).zzB(zzgqVar);
        zzir.zzF(this.zza, null);
        zzir.zzE(this.zza, null);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzg(zzgq zzgqVar) {
        zzir.zzE(this.zza, zzgqVar);
        zzir.zzC(this.zza).zzC(zzgqVar);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzh(zzaf zzafVar, zzgr zzgrVar) {
        zzir.zzF(this.zza, zzafVar);
        zzir.zzC(this.zza).zzD(zzafVar, zzgrVar);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzi(long j) {
        zzir.zzC(this.zza).zzE(j);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzj(Exception exc) {
        zzir.zzC(this.zza).zzF(exc);
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzk(int r8, long j, long j2) {
        zzir.zzC(this.zza).zzG(r8, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzl(int r2, long j) {
        zzir.zzC(this.zza).zzH(r2, j);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzm(Object obj, long j) {
        zzir.zzC(this.zza).zzI(obj, j);
        zzir zzirVar = this.zza;
        if (zzir.zzD(zzirVar) == obj) {
            zzdt zzz = zzir.zzz(zzirVar);
            zzz.zzd(26, new zzdq() { // from class: com.google.android.gms.internal.ads.zzih
                @Override // com.google.android.gms.internal.ads.zzdq
                public final void zza(Object obj2) {
                    zzcd zzcdVar = (zzcd) obj2;
                }
            });
            zzz.zzc();
        }
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final void zzn(final boolean z) {
        zzir zzirVar = this.zza;
        if (zzir.zzaf(zzirVar) == z) {
            return;
        }
        zzir.zzH(zzirVar, z);
        zzdt zzz = zzir.zzz(this.zza);
        zzz.zzd(23, new zzdq() { // from class: com.google.android.gms.internal.ads.zzik
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                boolean z2 = z;
                int r1 = zzin.zzb;
                ((zzcd) obj).zzq(z2);
            }
        });
        zzz.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzo(Exception exc) {
        zzir.zzC(this.zza).zzJ(exc);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzp(String str, long j, long j2) {
        zzir.zzC(this.zza).zzK(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzq(String str) {
        zzir.zzC(this.zza).zzL(str);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzr(zzgq zzgqVar) {
        zzir.zzC(this.zza).zzM(zzgqVar);
        zzir.zzJ(this.zza, null);
        zzir.zzI(this.zza, null);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzs(zzgq zzgqVar) {
        zzir.zzI(this.zza, zzgqVar);
        zzir.zzC(this.zza).zzN(zzgqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzt(long j, int r4) {
        zzir.zzC(this.zza).zzO(j, r4);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzu(zzaf zzafVar, zzgr zzgrVar) {
        zzir.zzJ(this.zza, zzafVar);
        zzir.zzC(this.zza).zzP(zzafVar, zzgrVar);
    }

    @Override // com.google.android.gms.internal.ads.zzya
    public final void zzv(final zzda zzdaVar) {
        zzir.zzK(this.zza, zzdaVar);
        zzdt zzz = zzir.zzz(this.zza);
        zzz.zzd(25, new zzdq() { // from class: com.google.android.gms.internal.ads.zzil
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                zzda zzdaVar2 = zzda.this;
                int r1 = zzin.zzb;
                ((zzcd) obj).zzu(zzdaVar2);
            }
        });
        zzz.zzc();
    }
}
