package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import com.google.android.gms.internal.vision.zzgs.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzgs<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzet<MessageType, BuilderType> {
    private static Map<Object, zzgs<?, ?>> zzwf = new ConcurrentHashMap();
    protected zzjm zzwd = zzjm.zzig();
    private int zzwe = -1;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class zzc<T extends zzgs<T, ?>> extends zzey<T> {
        private final T zzwa;

        public zzc(T t) {
            this.zzwa = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public enum zzf {
        public static final int zzwl = 1;
        public static final int zzwm = 2;
        public static final int zzwn = 3;
        public static final int zzwo = 4;
        public static final int zzwp = 5;
        public static final int zzwq = 6;
        public static final int zzwr = 7;
        public static final int zzwt = 1;
        public static final int zzwu = 2;
        public static final int zzww = 1;
        public static final int zzwx = 2;
        private static final /* synthetic */ int[] zzws = {1, 2, 3, 4, 5, 6, 7};
        private static final /* synthetic */ int[] zzwv = {1, 2};
        private static final /* synthetic */ int[] zzwy = {1, 2};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m1084x126d66cb() {
            return (int[]) zzws.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zza(int r1, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static abstract class zzb<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzie {
        protected zzb(MessageType messagetype) {
            super(messagetype);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.vision.zzgs.zza
        public void zzfy() {
            super.zzfy();
            ((zze) this.zzwb).zzwk = (zzgi) ((zze) this.zzwb).zzwk.clone();
        }

        @Override // com.google.android.gms.internal.vision.zzgs.zza
        public /* synthetic */ zzgs zzfz() {
            return (zze) zzgb();
        }

        @Override // com.google.android.gms.internal.vision.zzgs.zza, com.google.android.gms.internal.vision.zzib
        public /* synthetic */ zzic zzgb() {
            if (this.zzwc) {
                return (zze) this.zzwb;
            }
            ((zze) this.zzwb).zzwk.zzdp();
            return (zze) super.zzgb();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static abstract class zze<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgs<MessageType, BuilderType> implements zzie {
        protected zzgi<zzd> zzwk = zzgi.zzfn();

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzgi<zzd> zzgk() {
            if (this.zzwk.isImmutable()) {
                this.zzwk = (zzgi) this.zzwk.clone();
            }
            return this.zzwk;
        }

        /* JADX WARN: Type inference failed for: r1v8, types: [Type, java.util.List, java.util.ArrayList] */
        public final <Type> Type zzc(zzge<MessageType, Type> zzgeVar) {
            zzg zza = zzgs.zza(zzgeVar);
            if (zza.zzwz != ((zzgs) zzgd())) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
            Type type = (Type) this.zzwk.zza((zzgi<zzd>) zza.zzxb);
            if (type == null) {
                return zza.zzgc;
            }
            if (zza.zzxb.zzwi) {
                if (zza.zzxb.zzwh.zzip() == zzkd.ENUM) {
                    ?? r1 = (Type) new ArrayList();
                    for (Object obj : (List) type) {
                        r1.add(zza.zzi(obj));
                    }
                    return r1;
                }
                return type;
            }
            return (Type) zza.zzi(type);
        }
    }

    public String toString() {
        return zzid.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzri != 0) {
            return this.zzri;
        }
        this.zzri = zzin.zzho().zzu(this).hashCode(this);
        return this.zzri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static final class zzd implements zzgk<zzd> {
        final zzka zzwh;
        final zzgv<?> zzwg = null;
        final int number = 202056002;
        final boolean zzwi = true;
        final boolean zzwj = false;

        zzd(zzgv<?> zzgvVar, int r2, zzka zzkaVar, boolean z, boolean z2) {
            this.zzwh = zzkaVar;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final int zzag() {
            return this.number;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzka zzfs() {
            return this.zzwh;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzkd zzft() {
            return this.zzwh.zzip();
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final boolean zzfu() {
            return this.zzwi;
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final boolean zzfv() {
            return this.zzwj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzib zza(zzib zzibVar, zzic zzicVar) {
            return ((zza) zzibVar).zza((zza) ((zzgs) zzicVar));
        }

        @Override // com.google.android.gms.internal.vision.zzgk
        public final zzih zza(zzih zzihVar, zzih zzihVar2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            return this.number - ((zzd) obj).number;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static abstract class zza<MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzew<MessageType, BuilderType> {
        private final MessageType zzwa;
        protected MessageType zzwb;
        protected boolean zzwc = false;

        /* JADX INFO: Access modifiers changed from: protected */
        public zza(MessageType messagetype) {
            this.zzwa = messagetype;
            this.zzwb = (MessageType) messagetype.zza(zzf.zzwo, null, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void zzfy() {
            MessageType messagetype = (MessageType) this.zzwb.zza(zzf.zzwo, null, null);
            zza(messagetype, this.zzwb);
            this.zzwb = messagetype;
        }

        @Override // com.google.android.gms.internal.vision.zzie
        public final boolean isInitialized() {
            return zzgs.zza(this.zzwb, false);
        }

        @Override // com.google.android.gms.internal.vision.zzib
        /* renamed from: zzfz */
        public MessageType zzgb() {
            if (this.zzwc) {
                return this.zzwb;
            }
            MessageType messagetype = this.zzwb;
            zzin.zzho().zzu(messagetype).zzg(messagetype);
            this.zzwc = true;
            return this.zzwb;
        }

        @Override // com.google.android.gms.internal.vision.zzib
        /* renamed from: zzga */
        public final MessageType zzgc() {
            MessageType messagetype = (MessageType) zzgb();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjk(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzwc) {
                zzfy();
                this.zzwc = false;
            }
            zza(this.zzwb, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzin.zzho().zzu(messagetype).zzd(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int r10, int r11, zzgd zzgdVar) throws zzhc {
            if (this.zzwc) {
                zzfy();
                this.zzwc = false;
            }
            try {
                zzin.zzho().zzu(this.zzwb).zza(this.zzwb, bArr, 0, r11 + 0, new zzfb(zzgdVar));
                return this;
            } catch (zzhc e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw zzhc.zzgm();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.internal.vision.zzew
        /* renamed from: zzb */
        public final BuilderType zza(zzft zzftVar, zzgd zzgdVar) throws IOException {
            if (this.zzwc) {
                zzfy();
                this.zzwc = false;
            }
            try {
                zzin.zzho().zzu(this.zzwb).zza(this.zzwb, zzfy.zza(zzftVar), zzgdVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzew
        protected final /* synthetic */ zzew zza(zzet zzetVar) {
            return zza((zza<MessageType, BuilderType>) ((zzgs) zzetVar));
        }

        @Override // com.google.android.gms.internal.vision.zzew
        public final /* synthetic */ zzew zza(byte[] bArr, int r2, int r3, zzgd zzgdVar) throws zzhc {
            return zzb(bArr, 0, r3, zzgdVar);
        }

        @Override // com.google.android.gms.internal.vision.zzew
        public final /* synthetic */ zzew zzdn() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.vision.zzie
        public final /* synthetic */ zzic zzgd() {
            return this.zzwa;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzew
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzwa.zza(zzf.zzwp, null, null);
            zzaVar.zza((zza) ((zzgs) zzgb()));
            return zzaVar;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static class zzg<ContainingType extends zzic, Type> extends zzge<ContainingType, Type> {
        final Type zzgc;
        final ContainingType zzwz;
        final zzic zzxa;
        final zzd zzxb;

        zzg(ContainingType containingtype, Type type, zzic zzicVar, zzd zzdVar, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (zzdVar.zzwh == zzka.zzabw && zzicVar == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.zzwz = containingtype;
            this.zzgc = type;
            this.zzxa = zzicVar;
            this.zzxb = zzdVar;
        }

        final Object zzi(Object obj) {
            return this.zzxb.zzwh.zzip() == zzkd.ENUM ? this.zzxb.zzwg.zzg(((Integer) obj).intValue()) : obj;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzgs) zza(zzf.zzwq, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzin.zzho().zzu(this).equals(this, (zzgs) obj);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <MessageType extends zzgs<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzge() {
        return (BuilderType) zza(zzf.zzwp, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.vision.zzie
    public final boolean isInitialized() {
        Boolean bool = Boolean.TRUE;
        return zza(this, true);
    }

    @Override // com.google.android.gms.internal.vision.zzet
    final int zzdl() {
        return this.zzwe;
    }

    @Override // com.google.android.gms.internal.vision.zzet
    final void zzad(int r1) {
        this.zzwe = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final void zzb(zzga zzgaVar) throws IOException {
        zzin.zzho().zzu(this).zza(this, zzgc.zza(zzgaVar));
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final int zzgf() {
        if (this.zzwe == -1) {
            this.zzwe = zzin.zzho().zzu(this).zzr(this);
        }
        return this.zzwe;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzgs<?, ?>> T zzd(Class<T> cls) {
        zzgs<?, ?> zzgsVar = zzwf.get(cls);
        if (zzgsVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzgsVar = zzwf.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzgsVar == null) {
            zzgsVar = (T) ((zzgs) zzjp.zzh(cls)).zza(zzf.zzwq, (Object) null, (Object) null);
            if (zzgsVar == null) {
                throw new IllegalStateException();
            }
            zzwf.put(cls, zzgsVar);
        }
        return (T) zzgsVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzgs<?, ?>> void zza(Class<T> cls, T t) {
        zzwf.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(zzic zzicVar, String str, Object[] objArr) {
        return new zzip(zzicVar, str, objArr);
    }

    public static <ContainingType extends zzic, Type> zzg<ContainingType, Type> zza(ContainingType containingtype, zzic zzicVar, zzgv<?> zzgvVar, int r12, zzka zzkaVar, boolean z, Class cls) {
        return new zzg<>(containingtype, Collections.emptyList(), zzicVar, new zzd(null, 202056002, zzkaVar, true, false), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>, T> zzg<MessageType, T> zza(zzge<MessageType, T> zzgeVar) {
        return (zzg) zzgeVar;
    }

    protected static final <T extends zzgs<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zzwl, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzt = zzin.zzho().zzu(t).zzt(t);
        if (z) {
            t.zza(zzf.zzwm, zzt ? t : null, null);
        }
        return zzt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzgx, com.google.android.gms.internal.vision.zzgu] */
    public static zzgx zzgg() {
        return zzgu.zzgl();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzgz<E> zzgh() {
        return zziq.zzhr();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzgz<E> zza(zzgz<E> zzgzVar) {
        int size = zzgzVar.size();
        return zzgzVar.zzag(size == 0 ? 10 : size << 1);
    }

    private static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr, int r8, int r9, zzgd zzgdVar) throws zzhc {
        T t2 = (T) t.zza(zzf.zzwo, null, null);
        try {
            zzir zzu = zzin.zzho().zzu(t2);
            zzu.zza(t2, bArr, 0, r9, new zzfb(zzgdVar));
            zzu.zzg(t2);
            if (t2.zzri == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzhc) {
                throw ((zzhc) e.getCause());
            }
            throw new zzhc(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzhc.zzgm().zzg(t2);
        }
    }

    private static <T extends zzgs<T, ?>> T zzb(T t) throws zzhc {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzhc(new zzjk(t).getMessage()).zzg(t);
    }

    protected static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr) throws zzhc {
        return (T) zzb(zza(t, bArr, 0, bArr.length, zzgd.zzfl()));
    }

    protected static <T extends zzgs<T, ?>> T zza(T t, byte[] bArr, zzgd zzgdVar) throws zzhc {
        return (T) zzb(zza(t, bArr, 0, bArr.length, zzgdVar));
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final /* synthetic */ zzib zzgi() {
        zza zzaVar = (zza) zza(zzf.zzwp, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.vision.zzic
    public final /* synthetic */ zzib zzgj() {
        return (zza) zza(zzf.zzwp, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.vision.zzie
    public final /* synthetic */ zzic zzgd() {
        return (zzgs) zza(zzf.zzwq, (Object) null, (Object) null);
    }
}
