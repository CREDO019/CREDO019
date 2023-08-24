package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgpp implements zzgqr {
    private static final zzgpv zza = new zzgpn();
    private final zzgpv zzb;

    public zzgpp() {
        zzgpv zzgpvVar;
        zzgpv[] zzgpvVarArr = new zzgpv[2];
        zzgpvVarArr[0] = zzgoi.zza();
        try {
            zzgpvVar = (zzgpv) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzgpvVar = zza;
        }
        zzgpvVarArr[1] = zzgpvVar;
        zzgpo zzgpoVar = new zzgpo(zzgpvVarArr);
        zzgox.zzf(zzgpoVar, "messageInfoFactory");
        this.zzb = zzgpoVar;
    }

    private static boolean zzb(zzgpu zzgpuVar) {
        return zzgpuVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.ads.zzgqr
    public final zzgqq zza(Class cls) {
        zzgqs.zzG(cls);
        zzgpu zzb = this.zzb.zzb(cls);
        if (!zzb.zzb()) {
            if (zzgon.class.isAssignableFrom(cls)) {
                if (zzb(zzb)) {
                    return zzgqa.zzl(cls, zzb, zzgqd.zzb(), zzgpl.zze(), zzgqs.zzB(), zzgoc.zzb(), zzgpt.zzb());
                }
                return zzgqa.zzl(cls, zzb, zzgqd.zzb(), zzgpl.zze(), zzgqs.zzB(), null, zzgpt.zzb());
            } else if (zzb(zzb)) {
                return zzgqa.zzl(cls, zzb, zzgqd.zza(), zzgpl.zzd(), zzgqs.zzz(), zzgoc.zza(), zzgpt.zza());
            } else {
                return zzgqa.zzl(cls, zzb, zzgqd.zza(), zzgpl.zzd(), zzgqs.zzA(), null, zzgpt.zza());
            }
        } else if (zzgon.class.isAssignableFrom(cls)) {
            return zzgqb.zzc(zzgqs.zzB(), zzgoc.zzb(), zzb.zza());
        } else {
            return zzgqb.zzc(zzgqs.zzz(), zzgoc.zza(), zzb.zza());
        }
    }
}
