package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghn extends zzgon implements zzgpy {
    private static final zzghn zzb;
    private zzgjl zze;

    static {
        zzghn zzghnVar = new zzghn();
        zzb = zzghnVar;
        zzgon.zzaP(zzghn.class, zzghnVar);
    }

    private zzghn() {
    }

    public static zzghm zza() {
        return (zzghm) zzb.zzay();
    }

    public static zzghn zzd() {
        return zzb;
    }

    public static /* synthetic */ void zzf(zzghn zzghnVar, zzgjl zzgjlVar) {
        zzgjlVar.getClass();
        zzghnVar.zze = zzgjlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r1, Object obj, Object obj2) {
        int r12 = r1 - 1;
        if (r12 != 0) {
            if (r12 != 2) {
                if (r12 != 3) {
                    if (r12 != 4) {
                        if (r12 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzghm(null);
                }
                return new zzghn();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zze"});
        }
        return (byte) 1;
    }

    public final zzgjl zze() {
        zzgjl zzgjlVar = this.zze;
        return zzgjlVar == null ? zzgjl.zzd() : zzgjlVar;
    }
}
