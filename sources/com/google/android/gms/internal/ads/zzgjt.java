package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjt extends zzgon implements zzgpy {
    private static final zzgjt zzb;
    private int zze;
    private zzgow zzf = zzaJ();

    static {
        zzgjt zzgjtVar = new zzgjt();
        zzb = zzgjtVar;
        zzgon.zzaP(zzgjt.class, zzgjtVar);
    }

    private zzgjt() {
    }

    public static zzgjq zzd() {
        return (zzgjq) zzb.zzay();
    }

    public static zzgjt zzf(InputStream inputStream, zzgnz zzgnzVar) throws IOException {
        return (zzgjt) zzgon.zzaE(zzb, inputStream, zzgnzVar);
    }

    public static zzgjt zzg(byte[] bArr, zzgnz zzgnzVar) throws zzgoz {
        return (zzgjt) zzgon.zzaF(zzb, bArr, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzgjt zzgjtVar, zzgjs zzgjsVar) {
        zzgjsVar.getClass();
        zzgow zzgowVar = zzgjtVar.zzf;
        if (!zzgowVar.zzc()) {
            zzgjtVar.zzf = zzgon.zzaK(zzgowVar);
        }
        zzgjtVar.zzf.add(zzgjsVar);
    }

    public final int zza() {
        return this.zzf.size();
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
                    return new zzgjq(null);
                }
                return new zzgjt();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzgjs.class});
        }
        return (byte) 1;
    }

    public final int zzc() {
        return this.zze;
    }

    public final List zzh() {
        return this.zzf;
    }
}
