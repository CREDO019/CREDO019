package com.google.android.gms.flags;

import android.os.RemoteException;

@Deprecated
/* loaded from: classes2.dex */
public abstract class Flag<T> {
    private final String mKey;
    private final int zze;
    private final T zzf;

    private Flag(int r1, String str, T t) {
        this.zze = r1;
        this.mKey = str;
        this.zzf = t;
        Singletons.flagRegistry().zza(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract T zza(zzc zzcVar);

    @Deprecated
    /* loaded from: classes2.dex */
    public static class BooleanFlag extends Flag<Boolean> {
        public BooleanFlag(int r2, String str, Boolean bool) {
            super(r2, str, bool);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.flags.Flag
        /* renamed from: zzb */
        public final Boolean zza(zzc zzcVar) {
            try {
                return Boolean.valueOf(zzcVar.getBooleanFlagValue(getKey(), zzb().booleanValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class IntegerFlag extends Flag<Integer> {
        public IntegerFlag(int r2, String str, Integer num) {
            super(r2, str, num);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.flags.Flag
        /* renamed from: zzc */
        public final Integer zza(zzc zzcVar) {
            try {
                return Integer.valueOf(zzcVar.getIntFlagValue(getKey(), zzb().intValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class LongFlag extends Flag<Long> {
        public LongFlag(int r2, String str, Long l) {
            super(r2, str, l);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.flags.Flag
        /* renamed from: zzd */
        public final Long zza(zzc zzcVar) {
            try {
                return Long.valueOf(zzcVar.getLongFlagValue(getKey(), zzb().longValue(), getSource()));
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class StringFlag extends Flag<String> {
        public StringFlag(int r2, String str, String str2) {
            super(r2, str, str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.flags.Flag
        /* renamed from: zze */
        public final String zza(zzc zzcVar) {
            try {
                return zzcVar.getStringFlagValue(getKey(), zzb(), getSource());
            } catch (RemoteException unused) {
                return zzb();
            }
        }
    }

    public final String getKey() {
        return this.mKey;
    }

    public final T zzb() {
        return this.zzf;
    }

    public T get() {
        return (T) Singletons.zzd().zzb(this);
    }

    @Deprecated
    public static BooleanFlag define(int r1, String str, Boolean bool) {
        return new BooleanFlag(r1, str, bool);
    }

    @Deprecated
    public static IntegerFlag define(int r1, String str, int r3) {
        return new IntegerFlag(r1, str, Integer.valueOf(r3));
    }

    @Deprecated
    public static LongFlag define(int r1, String str, long j) {
        return new LongFlag(r1, str, Long.valueOf(j));
    }

    @Deprecated
    public static StringFlag define(int r1, String str, String str2) {
        return new StringFlag(r1, str, str2);
    }

    @Deprecated
    public final int getSource() {
        return this.zze;
    }
}
