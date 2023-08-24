package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

/* loaded from: classes2.dex */
public final class zzgt {

    /* loaded from: classes2.dex */
    public static final class zza extends zzcg<zza, C5822zza> implements zzdq {
        private static volatile zzdz<zza> zzbg;
        private static final zza zzbil;

        /* renamed from: com.google.android.gms.internal.clearcut.zzgt$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C5822zza extends zzcg.zza<zza, C5822zza> implements zzdq {
            private C5822zza() {
                super(zza.zzbil);
            }

            /* synthetic */ C5822zza(zzgu zzguVar) {
                this();
            }
        }

        /* loaded from: classes2.dex */
        public enum zzb implements zzcj {
            NO_RESTRICTION(0),
            SIDEWINDER_DEVICE(1),
            LATCHSKY_DEVICE(2);
            
            private static final zzck<zzb> zzbq = new zzgv();
            private final int value;

            zzb(int r3) {
                this.value = r3;
            }

            public static zzb zzbe(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            return null;
                        }
                        return LATCHSKY_DEVICE;
                    }
                    return SIDEWINDER_DEVICE;
                }
                return NO_RESTRICTION;
            }

            public static zzck<zzb> zzd() {
                return zzbq;
            }

            @Override // com.google.android.gms.internal.clearcut.zzcj
            public final int zzc() {
                return this.value;
            }
        }

        static {
            zza zzaVar = new zza();
            zzbil = zzaVar;
            zzcg.zza(zza.class, zzaVar);
        }

        private zza() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r1v13, types: [com.google.android.gms.internal.clearcut.zzcg$zzb, com.google.android.gms.internal.clearcut.zzdz<com.google.android.gms.internal.clearcut.zzgt$zza>] */
        @Override // com.google.android.gms.internal.clearcut.zzcg
        public final Object zza(int r1, Object obj, Object obj2) {
            zzdz<zza> zzdzVar;
            switch (zzgu.zzba[r1 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C5822zza(null);
                case 3:
                    return zza(zzbil, "\u0001\u0000", (Object[]) null);
                case 4:
                    return zzbil;
                case 5:
                    zzdz<zza> zzdzVar2 = zzbg;
                    zzdz<zza> zzdzVar3 = zzdzVar2;
                    if (zzdzVar2 == null) {
                        synchronized (zza.class) {
                            zzdz<zza> zzdzVar4 = zzbg;
                            zzdzVar = zzdzVar4;
                            if (zzdzVar4 == null) {
                                ?? zzbVar = new zzcg.zzb(zzbil);
                                zzbg = zzbVar;
                                zzdzVar = zzbVar;
                            }
                        }
                        zzdzVar3 = zzdzVar;
                    }
                    return zzdzVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
