package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzea {

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzb extends zzgs<zzb, zza> implements zzie {
        private static volatile zzil<zzb> zzbd;
        private static final zzha<Integer, zzeo> zzmn = new zzeb();
        private static final zzb zzmo;
        private zzgx zzmm = zzgg();

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzb, zza> implements zzie {
            private zza() {
                super(zzb.zzmo);
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzb>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzb> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzmo, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"zzmm", zzeo.zzah()});
                case 4:
                    return zzmo;
                case 5:
                    zzil<zzb> zzilVar2 = zzbd;
                    zzil<zzb> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzb.class) {
                            zzil<zzb> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzmo);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzeb, com.google.android.gms.internal.vision.zzha<java.lang.Integer, com.google.android.gms.internal.vision.zzeo>] */
        static {
            zzb zzbVar = new zzb();
            zzmo = zzbVar;
            zzgs.zza(zzb.class, zzbVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzc extends zzgs<zzc, zza> implements zzie {
        private static volatile zzil<zzc> zzbd;
        private static final zzc zzms;
        private int zzbf;
        private int zzmp = 1;
        private int zzmq = 1;
        private String zzmr = "";

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzc, zza> implements zzie {
            private zza() {
                super(zzc.zzms);
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzc>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzc> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzms, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\b\u0002", new Object[]{"zzbf", "zzmp", zzeo.zzah(), "zzmq", zzes.zzah(), "zzmr"});
                case 4:
                    return zzms;
                case 5:
                    zzil<zzc> zzilVar2 = zzbd;
                    zzil<zzc> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzc.class) {
                            zzil<zzc> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzms);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzms = zzcVar;
            zzgs.zza(zzc.class, zzcVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zze extends zzgs<zze, zzb> implements zzie {
        private static volatile zzil<zze> zzbd;
        private static final zze zznd;
        private int zzbf;
        private boolean zzmw;
        private int zzmx;
        private long zzmy;
        private long zzmz;
        private long zzna;
        private boolean zznc;
        private String zzmv = "";
        private String zznb = "";

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zza implements zzgw {
            REASON_UNKNOWN(0),
            REASON_MISSING(1),
            REASON_UPGRADE(2),
            REASON_INVALID(3);
            
            private static final zzgv<zza> zzgy = new zzed();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zza zzs(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            if (r1 != 3) {
                                return null;
                            }
                            return REASON_INVALID;
                        }
                        return REASON_UPGRADE;
                    }
                    return REASON_MISSING;
                }
                return REASON_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzec.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zza(int r3) {
                this.value = r3;
            }
        }

        private zze() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zzb extends zzgs.zza<zze, zzb> implements zzie {
            private zzb() {
                super(zze.zznd);
            }

            /* synthetic */ zzb(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zze>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zze> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zzb(null);
                case 3:
                    return zza(zznd, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\b\u0000\u0002\u0007\u0001\u0003\f\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\b\u0006\b\u0007\u0007", new Object[]{"zzbf", "zzmv", "zzmw", "zzmx", zza.zzah(), "zzmy", "zzmz", "zzna", "zznb", "zznc"});
                case 4:
                    return zznd;
                case 5:
                    zzil<zze> zzilVar2 = zzbd;
                    zzil<zze> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zze.class) {
                            zzil<zze> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zznd);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zze zzeVar = new zze();
            zznd = zzeVar;
            zzgs.zza(zze.class, zzeVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzl extends zzgs<zzl, zza> implements zzie {
        private static volatile zzil<zzl> zzbd;
        private static final zzl zzpq;
        private int zzbf;
        private long zzno;
        private long zznp;

        private zzl() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzl, zza> implements zzie {
            private zza() {
                super(zzl.zzpq);
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzl>, com.google.android.gms.internal.vision.zzgs$zzc] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzl> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001", new Object[]{"zzbf", "zzno", "zznp"});
                case 4:
                    return zzpq;
                case 5:
                    zzil<zzl> zzilVar2 = zzbd;
                    zzil<zzl> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzl.class) {
                            zzil<zzl> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpq);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzl zzlVar = new zzl();
            zzpq = zzlVar;
            zzgs.zza(zzl.class, zzlVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zza extends zzgs<zza, C5826zza> implements zzie {
        private static volatile zzil<zza> zzbd;
        private static final zza zzml;
        private int zzbf;
        private String zzmj = "";
        private String zzmk = "";

        private zza() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* renamed from: com.google.android.gms.internal.vision.zzea$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public static final class C5826zza extends zzgs.zza<zza, C5826zza> implements zzie {
            private C5826zza() {
                super(zza.zzml);
            }

            public final C5826zza zzl(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zza) this.zzwb).zzn(str);
                return this;
            }

            public final C5826zza zzm(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zza) this.zzwb).zzo(str);
                return this;
            }

            /* synthetic */ C5826zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zzmj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zzbf |= 2;
            this.zzmk = str;
        }

        public static C5826zza zzcj() {
            return zzml.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zza>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zza> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C5826zza(null);
                case 3:
                    return zza(zzml, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzbf", "zzmj", "zzmk"});
                case 4:
                    return zzml;
                case 5:
                    zzil<zza> zzilVar2 = zzbd;
                    zzil<zza> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zza.class) {
                            zzil<zza> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzml);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzml = zzaVar;
            zzgs.zza(zza.class, zzaVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzd extends zzgs<zzd, zza> implements zzie {
        private static volatile zzil<zzd> zzbd;
        private static final zzd zzmu;
        private zzgz<zzm> zzmt = zzgh();

        private zzd() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzd, zza> implements zzie {
            private zza() {
                super(zzd.zzmu);
            }

            public final zza zzb(zzm zzmVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzd) this.zzwb).zza(zzmVar);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzm zzmVar) {
            zzmVar.getClass();
            if (!this.zzmt.zzdo()) {
                this.zzmt = zzgs.zza(this.zzmt);
            }
            this.zzmt.add(zzmVar);
        }

        public static zza zzcn() {
            return zzmu.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzd>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzd> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzmu, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzmt", zzm.class});
                case 4:
                    return zzmu;
                case 5:
                    zzil<zzd> zzilVar2 = zzbd;
                    zzil<zzd> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzd.class) {
                            zzil<zzd> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzmu);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzmu = zzdVar;
            zzgs.zza(zzd.class, zzdVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzf extends zzgs<zzf, zza> implements zzie {
        private static volatile zzil<zzf> zzbd;
        private static final zzf zznr;
        private int zzbf;
        private int zznm;
        private long zzno;
        private long zznp;
        private String zznj = "";
        private String zznk = "";
        private zzgz<String> zznl = zzgs.zzgh();
        private String zznn = "";
        private zzgz<zzn> zznq = zzgh();

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zzb implements zzgw {
            RESULT_UNKNOWN(0),
            RESULT_SUCCESS(1),
            RESULT_FAIL(2),
            RESULT_SKIPPED(3);
            
            private static final zzgv<zzb> zzgy = new zzee();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zzb zzt(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            if (r1 != 3) {
                                return null;
                            }
                            return RESULT_SKIPPED;
                        }
                        return RESULT_FAIL;
                    }
                    return RESULT_SUCCESS;
                }
                return RESULT_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzef.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zzb(int r3) {
                this.value = r3;
            }
        }

        private zzf() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzf, zza> implements zzie {
            private zza() {
                super(zzf.zznr);
            }

            public final zza zzp(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzf) this.zzwb).setName(str);
                return this;
            }

            public final zza zzq(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzf) this.zzwb).zzr(str);
                return this;
            }

            public final zza zzd(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzf) this.zzwb).zzf(j);
                return this;
            }

            public final zza zze(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzf) this.zzwb).zzg(j);
                return this;
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzf) this.zzwb).zzd(iterable);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setName(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzr(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(long j) {
            this.zzbf |= 16;
            this.zzno = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzbf |= 32;
            this.zznp = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends zzn> iterable) {
            if (!this.zznq.zzdo()) {
                this.zznq = zzgs.zza(this.zznq);
            }
            zzet.zza(iterable, this.zznq);
        }

        public static zza zzcq() {
            return zznr.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzf>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzf> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zznr, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0002\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001a\u0004\f\u0002\u0005\b\u0003\u0006\u0002\u0004\u0007\u0002\u0005\b\u001b", new Object[]{"zzbf", "zznj", "zznk", "zznl", "zznm", zzb.zzah(), "zznn", "zzno", "zznp", "zznq", zzn.class});
                case 4:
                    return zznr;
                case 5:
                    zzil<zzf> zzilVar2 = zzbd;
                    zzil<zzf> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzf.class) {
                            zzil<zzf> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zznr);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzf zzfVar = new zzf();
            zznr = zzfVar;
            zzgs.zza(zzf.class, zzfVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzg extends zzgs<zzg, zzb> implements zzie {
        private static volatile zzil<zzg> zzbd;
        private static final zzg zzob;
        private int zzbf;
        private float zzjw;
        private boolean zzka;
        private int zznx;
        private int zzny;
        private int zznz;
        private boolean zzoa;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zza implements zzgw {
            CLASSIFICATION_UNKNOWN(0),
            CLASSIFICATION_NONE(1),
            CLASSIFICATION_ALL(2);
            
            private static final zzgv<zza> zzgy = new zzeh();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zza zzu(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            return null;
                        }
                        return CLASSIFICATION_ALL;
                    }
                    return CLASSIFICATION_NONE;
                }
                return CLASSIFICATION_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzeg.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zza(int r3) {
                this.value = r3;
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zzc implements zzgw {
            LANDMARK_UNKNOWN(0),
            LANDMARK_NONE(1),
            LANDMARK_ALL(2),
            LANDMARK_CONTOUR(3);
            
            private static final zzgv<zzc> zzgy = new zzei();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zzc zzv(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            if (r1 != 3) {
                                return null;
                            }
                            return LANDMARK_CONTOUR;
                        }
                        return LANDMARK_ALL;
                    }
                    return LANDMARK_NONE;
                }
                return LANDMARK_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzej.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zzc(int r3) {
                this.value = r3;
            }
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zzd implements zzgw {
            MODE_UNKNOWN(0),
            MODE_ACCURATE(1),
            MODE_FAST(2),
            MODE_SELFIE(3);
            
            private static final zzgv<zzd> zzgy = new zzel();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zzd zzw(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            if (r1 != 3) {
                                return null;
                            }
                            return MODE_SELFIE;
                        }
                        return MODE_FAST;
                    }
                    return MODE_ACCURATE;
                }
                return MODE_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzek.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zzd(int r3) {
                this.value = r3;
            }
        }

        private zzg() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zzb extends zzgs.zza<zzg, zzb> implements zzie {
            private zzb() {
                super(zzg.zzob);
            }

            public final zzb zzb(zzd zzdVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zza(zzdVar);
                return this;
            }

            public final zzb zzb(zzc zzcVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zza(zzcVar);
                return this;
            }

            public final zzb zzb(zza zzaVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zza(zzaVar);
                return this;
            }

            public final zzb zzh(boolean z) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zza(z);
                return this;
            }

            public final zzb zzi(boolean z) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zzg(z);
                return this;
            }

            public final zzb zzf(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzg) this.zzwb).zzd(f);
                return this;
            }

            /* synthetic */ zzb(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzd zzdVar) {
            this.zznx = zzdVar.zzag();
            this.zzbf |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzc zzcVar) {
            this.zzny = zzcVar.zzag();
            this.zzbf |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zza zzaVar) {
            this.zznz = zzaVar.zzag();
            this.zzbf |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzbf |= 8;
            this.zzka = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(boolean z) {
            this.zzbf |= 16;
            this.zzoa = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(float f) {
            this.zzbf |= 32;
            this.zzjw = f;
        }

        public static zzb zzcs() {
            return zzob.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzg>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzg> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zzb(null);
                case 3:
                    return zza(zzob, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002\u0004\u0007\u0003\u0005\u0007\u0004\u0006\u0001\u0005", new Object[]{"zzbf", "zznx", zzd.zzah(), "zzny", zzc.zzah(), "zznz", zza.zzah(), "zzka", "zzoa", "zzjw"});
                case 4:
                    return zzob;
                case 5:
                    zzil<zzg> zzilVar2 = zzbd;
                    zzil<zzg> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzg.class) {
                            zzil<zzg> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzob);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzob = zzgVar;
            zzgs.zza(zzg.class, zzgVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzh extends zzgs<zzh, zza> implements zzie {
        private static volatile zzil<zzh> zzbd;
        private static final zzh zzow;
        private int zzbf;
        private float zzoq;
        private float zzor;
        private float zzos;
        private float zzot;
        private float zzou;
        private float zzov;

        private zzh() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzh, zza> implements zzie {
            private zza() {
                super(zzh.zzow);
            }

            public final zza zzg(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzm(f);
                return this;
            }

            public final zza zzh(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzn(f);
                return this;
            }

            public final zza zzi(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzo(f);
                return this;
            }

            public final zza zzj(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzp(f);
                return this;
            }

            public final zza zzk(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzq(f);
                return this;
            }

            public final zza zzl(float f) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzh) this.zzwb).zzr(f);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(float f) {
            this.zzbf |= 1;
            this.zzoq = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(float f) {
            this.zzbf |= 2;
            this.zzor = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(float f) {
            this.zzbf |= 4;
            this.zzos = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(float f) {
            this.zzbf |= 8;
            this.zzot = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq(float f) {
            this.zzbf |= 16;
            this.zzou = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzr(float f) {
            this.zzbf |= 32;
            this.zzov = f;
        }

        public static zza zzcu() {
            return zzow.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzh>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzh> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzow, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0001\u0000\u0002\u0001\u0001\u0003\u0001\u0002\u0004\u0001\u0003\u0005\u0001\u0004\u0006\u0001\u0005", new Object[]{"zzbf", "zzoq", "zzor", "zzos", "zzot", "zzou", "zzov"});
                case 4:
                    return zzow;
                case 5:
                    zzil<zzh> zzilVar2 = zzbd;
                    zzil<zzh> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzh.class) {
                            zzil<zzh> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzow);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzow = zzhVar;
            zzgs.zza(zzh.class, zzhVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzi extends zzgs<zzi, zza> implements zzie {
        private static volatile zzil<zzi> zzbd;
        private static final zzi zzpa;
        private int zzbf;
        private zzj zzox;
        private zzl zzoy;
        private zzgz<zzf> zzoz = zzgh();

        private zzi() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzi, zza> implements zzie {
            private zza() {
                super(zzi.zzpa);
            }

            public final zza zza(zzj zzjVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzi) this.zzwb).zzb(zzjVar);
                return this;
            }

            public final zza zza(zzf.zza zzaVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzi) this.zzwb).zza((zzf) ((zzgs) zzaVar.zzgc()));
                return this;
            }

            public final zza zze(Iterable<? extends zzf> iterable) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzi) this.zzwb).zzf(iterable);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzj zzjVar) {
            zzjVar.getClass();
            this.zzox = zzjVar;
            this.zzbf |= 1;
        }

        private final void zzcw() {
            if (this.zzoz.zzdo()) {
                return;
            }
            this.zzoz = zzgs.zza(this.zzoz);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzf zzfVar) {
            zzfVar.getClass();
            zzcw();
            this.zzoz.add(zzfVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(Iterable<? extends zzf> iterable) {
            zzcw();
            zzet.zza(iterable, this.zzoz);
        }

        public static zza zzcx() {
            return zzpa.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzi>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzi> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpa, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\t\u0000\u0002\t\u0001\u0003\u001b", new Object[]{"zzbf", "zzox", "zzoy", "zzoz", zzf.class});
                case 4:
                    return zzpa;
                case 5:
                    zzil<zzi> zzilVar2 = zzbd;
                    zzil<zzi> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzi.class) {
                            zzil<zzi> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpa);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzi zziVar = new zzi();
            zzpa = zziVar;
            zzgs.zza(zzi.class, zziVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzj extends zzgs<zzj, zza> implements zzie {
        private static volatile zzil<zzj> zzbd;
        private static final zzj zzpf;
        private int zzbf;
        private int zzmp;
        private long zzpb;
        private long zzpc;
        private long zzpd;
        private long zzpe;

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public enum zzb implements zzgw {
            FORMAT_UNKNOWN(0),
            FORMAT_LUMINANCE(1),
            FORMAT_RGB8(2),
            FORMAT_MONOCHROME(3);
            
            private static final zzgv<zzb> zzgy = new zzem();
            private final int value;

            @Override // com.google.android.gms.internal.vision.zzgw
            public final int zzag() {
                return this.value;
            }

            public static zzb zzx(int r1) {
                if (r1 != 0) {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            if (r1 != 3) {
                                return null;
                            }
                            return FORMAT_MONOCHROME;
                        }
                        return FORMAT_RGB8;
                    }
                    return FORMAT_LUMINANCE;
                }
                return FORMAT_UNKNOWN;
            }

            public static zzgy zzah() {
                return zzen.zzhb;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            zzb(int r3) {
                this.value = r3;
            }
        }

        private zzj() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzj, zza> implements zzie {
            private zza() {
                super(zzj.zzpf);
            }

            public final zza zzh(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzj) this.zzwb).zzl(j);
                return this;
            }

            public final zza zzi(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzj) this.zzwb).zzm(j);
                return this;
            }

            public final zza zzj(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzj) this.zzwb).zzn(j);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzj) this.zzwb).zzo(j);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(long j) {
            this.zzbf |= 2;
            this.zzpb = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(long j) {
            this.zzbf |= 4;
            this.zzpc = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(long j) {
            this.zzbf |= 8;
            this.zzpd = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(long j) {
            this.zzbf |= 16;
            this.zzpe = j;
        }

        public static zza zzcz() {
            return zzpf.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzj>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzj> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpf, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0004\u0005\u0002\u0003", new Object[]{"zzbf", "zzmp", zzb.zzah(), "zzpb", "zzpc", "zzpe", "zzpd"});
                case 4:
                    return zzpf;
                case 5:
                    zzil<zzj> zzilVar2 = zzbd;
                    zzil<zzj> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzj.class) {
                            zzil<zzj> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpf);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzpf = zzjVar;
            zzgs.zza(zzj.class, zzjVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzk extends zzgs<zzk, zza> implements zzie {
        private static volatile zzil<zzk> zzbd;
        private static final zzk zzpp;
        private int zzbf;
        private long zzpl;
        private zza zzpm;
        private zzg zzpn;
        private zzb zzpo;
        private String zznj = "";
        private String zznb = "";

        private zzk() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzk, zza> implements zzie {
            private zza() {
                super(zzk.zzpp);
            }

            public final zza zzt(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzk) this.zzwb).setName(str);
                return this;
            }

            public final zza zzq(long j) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzk) this.zzwb).zzp(j);
                return this;
            }

            public final zza zzb(zza zzaVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzk) this.zzwb).zza(zzaVar);
                return this;
            }

            public final zza zzu(String str) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzk) this.zzwb).zzs(str);
                return this;
            }

            public final zza zza(zzg.zzb zzbVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzk) this.zzwb).zza((zzg) ((zzgs) zzbVar.zzgc()));
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setName(String str) {
            str.getClass();
            this.zzbf |= 1;
            this.zznj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(long j) {
            this.zzbf |= 2;
            this.zzpl = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zza zzaVar) {
            zzaVar.getClass();
            this.zzpm = zzaVar;
            this.zzbf |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzs(String str) {
            str.getClass();
            this.zzbf |= 8;
            this.zznb = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzg zzgVar) {
            zzgVar.getClass();
            this.zzpn = zzgVar;
            this.zzbf |= 16;
        }

        public static zza zzdb() {
            return zzpp.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzk>, com.google.android.gms.internal.vision.zzgs$zzc] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzk> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpp, "\u0001\u0006\u0000\u0001\u0001\u0011\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\t\u0002\u0006\b\u0003\u0010\t\u0004\u0011\t\u0005", new Object[]{"zzbf", "zznj", "zzpl", "zzpm", "zznb", "zzpn", "zzpo"});
                case 4:
                    return zzpp;
                case 5:
                    zzil<zzk> zzilVar2 = zzbd;
                    zzil<zzk> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzk.class) {
                            zzil<zzk> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpp);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzpp = zzkVar;
            zzgs.zza(zzk.class, zzkVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzm extends zzgs<zzm, zza> implements zzie {
        private static volatile zzil<zzm> zzbd;
        private static final zzm zzpt;
        private int zzbf;
        private int zzpr;
        private int zzps;

        private zzm() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzm, zza> implements zzie {
            private zza() {
                super(zzm.zzpt);
            }

            public final zza zzy(int r2) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzm) this.zzwb).setX(r2);
                return this;
            }

            public final zza zzz(int r2) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzm) this.zzwb).setY(r2);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setX(int r2) {
            this.zzbf |= 1;
            this.zzpr = r2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setY(int r2) {
            this.zzbf |= 2;
            this.zzps = r2;
        }

        public static zza zzde() {
            return zzpt.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzm>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzm> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpt, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"zzbf", "zzpr", "zzps"});
                case 4:
                    return zzpt;
                case 5:
                    zzil<zzm> zzilVar2 = zzbd;
                    zzil<zzm> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzm.class) {
                            zzil<zzm> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpt);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzm zzmVar = new zzm();
            zzpt = zzmVar;
            zzgs.zza(zzm.class, zzmVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzn extends zzgs<zzn, zza> implements zzie {
        private static volatile zzil<zzn> zzbd;
        private static final zzn zzpy;
        private int zzbf;
        private zzd zzpu;
        private int zzpv;
        private zzh zzpw;
        private zzc zzpx;

        private zzn() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzn, zza> implements zzie {
            private zza() {
                super(zzn.zzpy);
            }

            public final zza zza(zzd.zza zzaVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzn) this.zzwb).zza((zzd) ((zzgs) zzaVar.zzgc()));
                return this;
            }

            public final zza zzaa(int r2) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzn) this.zzwb).setId(r2);
                return this;
            }

            public final zza zzb(zzh zzhVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzn) this.zzwb).zza(zzhVar);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzd zzdVar) {
            zzdVar.getClass();
            this.zzpu = zzdVar;
            this.zzbf |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setId(int r2) {
            this.zzbf |= 2;
            this.zzpv = r2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzh zzhVar) {
            zzhVar.getClass();
            this.zzpw = zzhVar;
            this.zzbf |= 4;
        }

        public static zza zzdg() {
            return zzpy.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzn>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzn> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzpy, "\u0001\u0004\u0000\u0001\u0001\u0011\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\u0004\u0001\u0010\t\u0002\u0011\t\u0003", new Object[]{"zzbf", "zzpu", "zzpv", "zzpw", "zzpx"});
                case 4:
                    return zzpy;
                case 5:
                    zzil<zzn> zzilVar2 = zzbd;
                    zzil<zzn> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzn.class) {
                            zzil<zzn> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzpy);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzn zznVar = new zzn();
            zzpy = zznVar;
            zzgs.zza(zzn.class, zznVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzo extends zzgs<zzo, zza> implements zzie {
        private static volatile zzil<zzo> zzbd;
        private static final zzo zzqd;
        private int zzbf;
        private zze zzpz;
        private zzk zzqa;
        private zzi zzqb;
        private int zzqc;

        private zzo() {
        }

        /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
        /* loaded from: classes3.dex */
        public static final class zza extends zzgs.zza<zzo, zza> implements zzie {
            private zza() {
                super(zzo.zzqd);
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzo) this.zzwb).zza((zzk) ((zzgs) zzaVar.zzgc()));
                return this;
            }

            public final zza zzb(zzi zziVar) {
                if (this.zzwc) {
                    zzfy();
                    this.zzwc = false;
                }
                ((zzo) this.zzwb).zza(zziVar);
                return this;
            }

            /* synthetic */ zza(zzdz zzdzVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzk zzkVar) {
            zzkVar.getClass();
            this.zzqa = zzkVar;
            this.zzbf |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzi zziVar) {
            zziVar.getClass();
            this.zzqb = zziVar;
            this.zzbf |= 4;
        }

        public static zza zzdi() {
            return zzqd.zzge();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.vision.zzgs$zzc, com.google.android.gms.internal.vision.zzil<com.google.android.gms.internal.vision.zzea$zzo>] */
        @Override // com.google.android.gms.internal.vision.zzgs
        public final Object zza(int r2, Object obj, Object obj2) {
            zzil<zzo> zzilVar;
            switch (zzdz.zzbe[r2 - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzqd, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\u0004\u0003", new Object[]{"zzbf", "zzpz", "zzqa", "zzqb", "zzqc"});
                case 4:
                    return zzqd;
                case 5:
                    zzil<zzo> zzilVar2 = zzbd;
                    zzil<zzo> zzilVar3 = zzilVar2;
                    if (zzilVar2 == null) {
                        synchronized (zzo.class) {
                            zzil<zzo> zzilVar4 = zzbd;
                            zzilVar = zzilVar4;
                            if (zzilVar4 == null) {
                                ?? zzcVar = new zzgs.zzc(zzqd);
                                zzbd = zzcVar;
                                zzilVar = zzcVar;
                            }
                        }
                        zzilVar3 = zzilVar;
                    }
                    return zzilVar3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzo zzoVar = new zzo();
            zzqd = zzoVar;
            zzgs.zza(zzo.class, zzoVar);
        }
    }
}
