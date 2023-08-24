package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzght extends zzgon implements zzgpy {
    private static final zzght zzb;
    private zzgic zze;
    private zzghn zzf;
    private int zzg;

    static {
        zzght zzghtVar = new zzght();
        zzb = zzghtVar;
        zzgon.zzaP(zzght.class, zzghtVar);
    }

    private zzght() {
    }

    public static zzghs zzc() {
        return (zzghs) zzb.zzay();
    }

    public static zzght zze() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzght zzghtVar, zzgic zzgicVar) {
        zzgicVar.getClass();
        zzghtVar.zze = zzgicVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzght zzghtVar, zzghn zzghnVar) {
        zzghnVar.getClass();
        zzghtVar.zzf = zzghnVar;
    }

    public final zzghn zza() {
        zzghn zzghnVar = this.zzf;
        return zzghnVar == null ? zzghn.zzd() : zzghnVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r3, Object obj, Object obj2) {
        int r32 = r3 - 1;
        if (r32 != 0) {
            if (r32 != 2) {
                if (r32 != 3) {
                    if (r32 != 4) {
                        if (r32 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzghs(null);
                }
                return new zzght();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgic zzf() {
        zzgic zzgicVar = this.zze;
        return zzgicVar == null ? zzgic.zzd() : zzgicVar;
    }

    public final int zzi() {
        int r0 = this.zzg;
        int r1 = 3;
        if (r0 == 0) {
            r1 = 2;
        } else if (r0 != 1) {
            r1 = r0 != 2 ? r0 != 3 ? 0 : 5 : 4;
        }
        if (r1 == 0) {
            return 1;
        }
        return r1;
    }
}
