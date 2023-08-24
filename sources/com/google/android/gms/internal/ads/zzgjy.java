package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjy extends zzgon implements zzgpy {
    private static final zzgjy zzb;
    private int zze;
    private zzgow zzf = zzaJ();

    static {
        zzgjy zzgjyVar = new zzgjy();
        zzb = zzgjyVar;
        zzgon.zzaP(zzgjy.class, zzgjyVar);
    }

    private zzgjy() {
    }

    public static zzgjv zza() {
        return (zzgjv) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzgjy zzgjyVar, zzgjx zzgjxVar) {
        zzgjxVar.getClass();
        zzgow zzgowVar = zzgjyVar.zzf;
        if (!zzgowVar.zzc()) {
            zzgjyVar.zzf = zzgon.zzaK(zzgowVar);
        }
        zzgjyVar.zzf.add(zzgjxVar);
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
                    return new zzgjv(null);
                }
                return new zzgjy();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzgjx.class});
        }
        return (byte) 1;
    }
}
