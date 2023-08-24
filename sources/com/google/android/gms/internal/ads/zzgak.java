package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgak {
    private final zzgjl zza;

    private zzgak(zzgjl zzgjlVar) {
        this.zza = zzgjlVar;
    }

    public static zzgak zze(String str, byte[] bArr, int r4) {
        zzgjk zza = zzgjl.zza();
        zza.zza(str);
        zza.zzb(zzgnf.zzv(bArr));
        int r42 = r4 - 1;
        zza.zzc(r42 != 0 ? r42 != 1 ? 5 : 4 : 3);
        return new zzgak((zzgjl) zza.zzal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgjl zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zza.zzf();
    }

    public final byte[] zzc() {
        return this.zza.zze().zzE();
    }

    public final int zzd() {
        int zzi = this.zza.zzi() - 2;
        int r1 = 1;
        if (zzi != 1) {
            r1 = 2;
            if (zzi != 2) {
                r1 = 3;
                if (zzi != 3) {
                    if (zzi == 4) {
                        return 4;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
            }
        }
        return r1;
    }
}
