package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdwi implements zzdft, zzdem, zzddb, zzdds, com.google.android.gms.ads.internal.client.zza, zzdie {
    private final zzbel zza;
    private boolean zzb = false;

    public zzdwi(zzbel zzbelVar, @Nullable zzfan zzfanVar) {
        this.zza = zzbelVar;
        zzbelVar.zzc(2);
        if (zzfanVar != null) {
            zzbelVar.zzc(1101);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final synchronized void onAdClicked() {
        if (this.zzb) {
            this.zza.zzc(8);
            return;
        }
        this.zza.zzc(7);
        this.zzb = true;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        switch (zzeVar.zza) {
            case 1:
                this.zza.zzc(101);
                return;
            case 2:
                this.zza.zzc(102);
                return;
            case 3:
                this.zza.zzc(5);
                return;
            case 4:
                this.zza.zzc(103);
                return;
            case 5:
                this.zza.zzc(104);
                return;
            case 6:
                this.zza.zzc(105);
                return;
            case 7:
                this.zza.zzc(106);
                return;
            default:
                this.zza.zzc(4);
                return;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(final zzfde zzfdeVar) {
        this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdwe
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                zzfde zzfdeVar2 = zzfde.this;
                zzbew zzbewVar = (zzbew) zzbgaVar.zza().zzaz();
                zzbfo zzbfoVar = (zzbfo) zzbgaVar.zza().zzd().zzaz();
                zzbfoVar.zza(zzfdeVar2.zzb.zzb.zzb);
                zzbewVar.zzb(zzbfoVar);
                zzbgaVar.zze(zzbewVar);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzd() {
        this.zza.zzc(1109);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zze(final zzbfg zzbfgVar) {
        this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdwh
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                zzbgaVar.zzf(zzbfg.this);
            }
        });
        this.zza.zzc(1103);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzf(final zzbfg zzbfgVar) {
        this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdwg
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                zzbgaVar.zzf(zzbfg.this);
            }
        });
        this.zza.zzc(1102);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzh(boolean z) {
        this.zza.zzc(true != z ? 1108 : 1107);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzi(final zzbfg zzbfgVar) {
        this.zza.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdwf
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                zzbgaVar.zzf(zzbfg.this);
            }
        });
        this.zza.zzc(1104);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzk(boolean z) {
        this.zza.zzc(true != z ? 1106 : 1105);
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final synchronized void zzl() {
        this.zza.zzc(6);
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        this.zza.zzc(3);
    }
}
