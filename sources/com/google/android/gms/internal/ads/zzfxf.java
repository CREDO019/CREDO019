package com.google.android.gms.internal.ads;

import com.facebook.hermes.intl.Constants;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfxf<V> extends zzfzq implements zzfyx<V> {
    private static final Logger zzaV;
    private static final zza zzaW;
    private static final Object zzaZ;
    static final boolean zzd;
    @CheckForNull
    private volatile zzd listeners;
    @CheckForNull
    private volatile Object value;
    @CheckForNull
    private volatile zzk waiters;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public abstract class zza {
        /* synthetic */ zza(C21371 c21371) {
        }

        abstract zzd zza(zzfxf zzfxfVar, zzd zzdVar);

        abstract zzk zzb(zzfxf zzfxfVar, zzk zzkVar);

        abstract void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2);

        abstract void zzd(zzk zzkVar, Thread thread);

        abstract boolean zze(zzfxf zzfxfVar, @CheckForNull zzd zzdVar, zzd zzdVar2);

        abstract boolean zzf(zzfxf zzfxfVar, @CheckForNull Object obj, Object obj2);

        abstract boolean zzg(zzfxf zzfxfVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zzfxf.zzd) {
                zzb = null;
                zza = null;
                return;
            }
            zzb = new zzb(false, null);
            zza = new zzb(true, null);
        }

        zzb(boolean z, @CheckForNull Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.ads.zzfxf.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            Objects.requireNonNull(th);
            this.zzb = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public final class zzd {
        static final zzd zza = new zzd();
        @CheckForNull
        zzd next;
        @CheckForNull
        final Runnable zzb;
        @CheckForNull
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzk, Thread> zza;
        final AtomicReferenceFieldUpdater<zzk, zzk> zzb;
        final AtomicReferenceFieldUpdater<zzfxf, zzk> zzc;
        final AtomicReferenceFieldUpdater<zzfxf, zzd> zzd;
        final AtomicReferenceFieldUpdater<zzfxf, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super(null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzd zza(zzfxf zzfxfVar, zzd zzdVar) {
            return this.zzd.getAndSet(zzfxfVar, zzdVar);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzk zzb(zzfxf zzfxfVar, zzk zzkVar) {
            return this.zzc.getAndSet(zzfxfVar, zzkVar);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            this.zzb.lazySet(zzkVar, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzd(zzk zzkVar, Thread thread) {
            this.zza.lazySet(zzkVar, thread);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zze(zzfxf zzfxfVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            return zzfxg.zza(this.zzd, zzfxfVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzf(zzfxf zzfxfVar, @CheckForNull Object obj, Object obj2) {
            return zzfxg.zza(this.zze, zzfxfVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzg(zzfxf zzfxfVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            return zzfxg.zza(this.zzc, zzfxfVar, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public final class zzf<V> implements Runnable {
        final zzfxf<V> zza;
        final zzfyx<? extends V> zzb;

        zzf(zzfxf zzfxfVar, zzfyx zzfyxVar) {
            this.zza = zzfxfVar;
            this.zzb = zzfyxVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (((zzfxf) this.zza).value != this) {
                return;
            }
            if (zzfxf.zzaW.zzf(this.zza, this, zzfxf.zzf(this.zzb))) {
                zzfxf.zzy(this.zza);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    final class zzg extends zza {
        private zzg() {
            super(null);
        }

        /* synthetic */ zzg(C21371 c21371) {
            super(null);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzd zza(zzfxf zzfxfVar, zzd zzdVar) {
            zzd zzdVar2;
            synchronized (zzfxfVar) {
                zzdVar2 = zzfxfVar.listeners;
                if (zzdVar2 != zzdVar) {
                    zzfxfVar.listeners = zzdVar;
                }
            }
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzk zzb(zzfxf zzfxfVar, zzk zzkVar) {
            zzk zzkVar2;
            synchronized (zzfxfVar) {
                zzkVar2 = zzfxfVar.waiters;
                if (zzkVar2 != zzkVar) {
                    zzfxfVar.waiters = zzkVar;
                }
            }
            return zzkVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            zzkVar.next = zzkVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzd(zzk zzkVar, Thread thread) {
            zzkVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zze(zzfxf zzfxfVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            synchronized (zzfxfVar) {
                if (zzfxfVar.listeners == zzdVar) {
                    zzfxfVar.listeners = zzdVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzf(zzfxf zzfxfVar, @CheckForNull Object obj, Object obj2) {
            synchronized (zzfxfVar) {
                if (zzfxfVar.value == obj) {
                    zzfxfVar.value = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzg(zzfxf zzfxfVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            synchronized (zzfxfVar) {
                if (zzfxfVar.waiters == zzkVar) {
                    zzfxfVar.waiters = zzkVar2;
                    return true;
                }
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public interface zzh<V> extends zzfyx<V> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public abstract class zzi<V> extends zzfxf<V> implements zzh<V> {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    final class zzj extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.android.gms.internal.ads.zzfxf.zzj.1
                        public static final Unsafe zza() throws Exception {
                            Field[] declaredFields;
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }

                        @Override // java.security.PrivilegedExceptionAction
                        public final /* bridge */ /* synthetic */ Unsafe run() throws Exception {
                            return zza();
                        }
                    });
                }
                try {
                    zzc = unsafe.objectFieldOffset(zzfxf.class.getDeclaredField("waiters"));
                    zzb = unsafe.objectFieldOffset(zzfxf.class.getDeclaredField("listeners"));
                    zzd = unsafe.objectFieldOffset(zzfxf.class.getDeclaredField("value"));
                    zze = unsafe.objectFieldOffset(zzk.class.getDeclaredField("thread"));
                    zzf = unsafe.objectFieldOffset(zzk.class.getDeclaredField("next"));
                    zza = unsafe;
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (RuntimeException e2) {
                    throw e2;
                }
            } catch (PrivilegedActionException e3) {
                throw new RuntimeException("Could not initialize intrinsics", e3.getCause());
            }
        }

        private zzj() {
            super(null);
        }

        /* synthetic */ zzj(C21371 c21371) {
            super(null);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzd zza(zzfxf zzfxfVar, zzd zzdVar) {
            zzd zzdVar2;
            do {
                zzdVar2 = zzfxfVar.listeners;
                if (zzdVar == zzdVar2) {
                    return zzdVar2;
                }
            } while (!zze(zzfxfVar, zzdVar2, zzdVar));
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final zzk zzb(zzfxf zzfxfVar, zzk zzkVar) {
            zzk zzkVar2;
            do {
                zzkVar2 = zzfxfVar.waiters;
                if (zzkVar == zzkVar2) {
                    return zzkVar2;
                }
            } while (!zzg(zzfxfVar, zzkVar2, zzkVar));
            return zzkVar2;
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzc(zzk zzkVar, @CheckForNull zzk zzkVar2) {
            zza.putObject(zzkVar, zzf, zzkVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final void zzd(zzk zzkVar, Thread thread) {
            zza.putObject(zzkVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zze(zzfxf zzfxfVar, @CheckForNull zzd zzdVar, zzd zzdVar2) {
            return zzfxh.zza(zza, zzfxfVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzf(zzfxf zzfxfVar, @CheckForNull Object obj, Object obj2) {
            return zzfxh.zza(zza, zzfxfVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.ads.zzfxf.zza
        final boolean zzg(zzfxf zzfxfVar, @CheckForNull zzk zzkVar, @CheckForNull zzk zzkVar2) {
            return zzfxh.zza(zza, zzfxfVar, zzc, zzkVar, zzkVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
    /* loaded from: classes2.dex */
    public final class zzk {
        static final zzk zza = new zzk(false);
        @CheckForNull
        volatile zzk next;
        @CheckForNull
        volatile Thread thread;

        zzk() {
            zzfxf.zzaW.zzd(this, Thread.currentThread());
        }

        zzk(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zzgVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", Constants.CASEFIRST_FALSE));
        } catch (SecurityException unused) {
            z = false;
        }
        zzd = z;
        zzaV = Logger.getLogger(zzfxf.class.getName());
        try {
            zzgVar = new zzj(null);
            th2 = null;
            th = null;
        } catch (Error | RuntimeException e) {
            try {
                th2 = e;
                zzgVar = new zze(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(zzfxf.class, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(zzfxf.class, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(zzfxf.class, Object.class, "value"));
                th = null;
            } catch (Error | RuntimeException e2) {
                th = e2;
                th2 = e;
                zzgVar = new zzg(null);
            }
        }
        zzaW = zzgVar;
        if (th != null) {
            Logger logger = zzaV;
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
        zzaZ = new Object();
    }

    private final void zzA(zzk zzkVar) {
        zzkVar.thread = null;
        while (true) {
            zzk zzkVar2 = this.waiters;
            if (zzkVar2 != zzk.zza) {
                zzk zzkVar3 = null;
                while (zzkVar2 != null) {
                    zzk zzkVar4 = zzkVar2.next;
                    if (zzkVar2.thread != null) {
                        zzkVar3 = zzkVar2;
                    } else if (zzkVar3 != null) {
                        zzkVar3.next = zzkVar4;
                        if (zzkVar3.thread == null) {
                            break;
                        }
                    } else if (!zzaW.zzg(this, zzkVar2, zzkVar4)) {
                        break;
                    }
                    zzkVar2 = zzkVar4;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzB(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        } else {
            if (obj == zzaZ) {
                return null;
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object zzf(zzfyx zzfyxVar) {
        Throwable zzp;
        if (zzfyxVar instanceof zzh) {
            Object obj = ((zzfxf) zzfyxVar).value;
            if (obj instanceof zzb) {
                zzb zzbVar = (zzb) obj;
                if (zzbVar.zzc) {
                    Throwable th = zzbVar.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            obj.getClass();
            return obj;
        } else if (!(zzfyxVar instanceof zzfzq) || (zzp = ((zzfzq) zzfyxVar).zzp()) == null) {
            boolean isCancelled = zzfyxVar.isCancelled();
            if ((!zzd) & isCancelled) {
                zzb zzbVar2 = zzb.zzb;
                zzbVar2.getClass();
                return zzbVar2;
            }
            try {
                Object zzg2 = zzg(zzfyxVar);
                if (!isCancelled) {
                    return zzg2 == null ? zzaZ : zzg2;
                }
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + zzfyxVar));
            } catch (Error e) {
                e = e;
                return new zzc(e);
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    Objects.toString(zzfyxVar);
                    return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(zzfyxVar)), e2));
                }
                return new zzb(false, e2);
            } catch (RuntimeException e3) {
                e = e3;
                return new zzc(e);
            } catch (ExecutionException e4) {
                if (isCancelled) {
                    Objects.toString(zzfyxVar);
                    return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzfyxVar)), e4));
                }
                return new zzc(e4.getCause());
            }
        } else {
            return new zzc(zzp);
        }
    }

    private static Object zzg(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzv(StringBuilder sb) {
        try {
            Object zzg2 = zzg(this);
            sb.append("SUCCESS, result=[");
            if (zzg2 == null) {
                sb.append("null");
            } else if (zzg2 == this) {
                sb.append("this future");
            } else {
                sb.append(zzg2.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzg2)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        }
    }

    private final void zzw(StringBuilder sb) {
        String concat;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzx(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                concat = zzfsu.zza(zza());
            } catch (RuntimeException | StackOverflowError e) {
                Class<?> cls = e.getClass();
                Objects.toString(cls);
                concat = "Exception thrown from implementation: ".concat(String.valueOf(cls));
            }
            if (concat != null) {
                sb.append(", info=[");
                sb.append(concat);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzv(sb);
        }
    }

    private final void zzx(StringBuilder sb, @CheckForNull Object obj) {
        try {
            if (obj == this) {
                sb.append("this future");
            } else {
                sb.append(obj);
            }
        } catch (RuntimeException | StackOverflowError e) {
            sb.append("Exception thrown from implementation: ");
            sb.append(e.getClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzy(zzfxf zzfxfVar) {
        zzd zzdVar = null;
        while (true) {
            for (zzk zzb2 = zzaW.zzb(zzfxfVar, zzk.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzfxfVar.zzb();
            zzd zzdVar2 = zzdVar;
            zzd zza2 = zzaW.zza(zzfxfVar, zzd.zza);
            zzd zzdVar3 = zzdVar2;
            while (zza2 != null) {
                zzd zzdVar4 = zza2.next;
                zza2.next = zzdVar3;
                zzdVar3 = zza2;
                zza2 = zzdVar4;
            }
            while (zzdVar3 != null) {
                zzdVar = zzdVar3.next;
                Runnable runnable = zzdVar3.zzb;
                runnable.getClass();
                if (runnable instanceof zzf) {
                    zzf zzfVar = (zzf) runnable;
                    zzfxfVar = zzfVar.zza;
                    if (zzfxfVar.value == zzfVar) {
                        if (zzaW.zzf(zzfxfVar, zzfVar, zzf(zzfVar.zzb))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = zzdVar3.zzc;
                    executor.getClass();
                    zzz(runnable, executor);
                }
                zzdVar3 = zzdVar;
            }
            return;
        }
    }

    private static void zzz(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzaV;
            Level level = Level.SEVERE;
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        zzb zzbVar;
        Object obj = this.value;
        if ((obj == null) || (obj instanceof zzf)) {
            if (zzd) {
                zzbVar = new zzb(z, new CancellationException("Future.cancel() was called."));
            } else {
                if (z) {
                    zzbVar = zzb.zza;
                } else {
                    zzbVar = zzb.zzb;
                }
                zzbVar.getClass();
            }
            boolean z2 = false;
            zzfxf<V> zzfxfVar = this;
            while (true) {
                if (zzaW.zzf(zzfxfVar, obj, zzbVar)) {
                    if (z) {
                        zzfxfVar.zzr();
                    }
                    zzy(zzfxfVar);
                    if (!(obj instanceof zzf)) {
                        break;
                    }
                    zzfyx<? extends V> zzfyxVar = ((zzf) obj).zzb;
                    if (zzfyxVar instanceof zzh) {
                        zzfxfVar = (zzfxf) zzfyxVar;
                        obj = zzfxfVar.value;
                        if (!(obj == null) && !(obj instanceof zzf)) {
                            break;
                        }
                        z2 = true;
                    } else {
                        zzfyxVar.cancel(z);
                        break;
                    }
                } else {
                    obj = zzfxfVar.value;
                    if (!(obj instanceof zzf)) {
                        return z2;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzB(obj2);
            }
            zzk zzkVar = this.waiters;
            if (zzkVar != zzk.zza) {
                zzk zzkVar2 = new zzk();
                do {
                    zza zzaVar = zzaW;
                    zzaVar.zzc(zzkVar2, zzkVar);
                    if (zzaVar.zzg(this, zzkVar, zzkVar2)) {
                        do {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                zzA(zzkVar2);
                                throw new InterruptedException();
                            }
                            obj = this.value;
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzB(obj);
                    }
                    zzkVar = this.waiters;
                } while (zzkVar != zzk.zza);
                Object obj3 = this.value;
                obj3.getClass();
                return zzB(obj3);
            }
            Object obj32 = this.value;
            obj32.getClass();
            return zzB(obj32);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.value instanceof zzb;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof zzf)) & (obj != null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzv(sb);
        } else {
            zzw(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public String zza() {
        if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
        return null;
    }

    protected void zzb() {
    }

    @Override // com.google.android.gms.internal.ads.zzfyx
    public void zzc(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzfsf.zzc(runnable, "Runnable was null.");
        zzfsf.zzc(executor, "Executor was null.");
        if (isDone() || (zzdVar = this.listeners) == zzd.zza) {
            zzz(runnable, executor);
        }
        zzd zzdVar2 = new zzd(runnable, executor);
        do {
            zzdVar2.next = zzdVar;
            if (zzaW.zze(this, zzdVar, zzdVar2)) {
                return;
            }
            zzdVar = this.listeners;
        } while (zzdVar != zzd.zza);
        zzz(runnable, executor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zzd(Object obj) {
        if (obj == null) {
            obj = zzaZ;
        }
        if (zzaW.zzf(this, null, obj)) {
            zzy(this);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zze(Throwable th) {
        Objects.requireNonNull(th);
        if (zzaW.zzf(this, null, new zzc(th))) {
            zzy(this);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfzq
    @CheckForNull
    public final Throwable zzp() {
        if (this instanceof zzh) {
            Object obj = this.value;
            if (obj instanceof zzc) {
                return ((zzc) obj).zzb;
            }
            return null;
        }
        return null;
    }

    protected void zzr() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzs(@CheckForNull Future future) {
        if ((future != null) && isCancelled()) {
            future.cancel(zzu());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzu() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzt(zzfyx zzfyxVar) {
        zzc zzcVar;
        Objects.requireNonNull(zzfyxVar);
        Object obj = this.value;
        if (obj == null) {
            if (zzfyxVar.isDone()) {
                if (zzaW.zzf(this, null, zzf(zzfyxVar))) {
                    zzy(this);
                    return true;
                }
                return false;
            }
            zzf zzfVar = new zzf(this, zzfyxVar);
            if (zzaW.zzf(this, null, zzfVar)) {
                try {
                    zzfyxVar.zzc(zzfVar, zzfyc.INSTANCE);
                } catch (Error | RuntimeException e) {
                    try {
                        zzcVar = new zzc(e);
                    } catch (Error | RuntimeException unused) {
                        zzcVar = zzc.zza;
                    }
                    zzaW.zzf(this, zzfVar, zzcVar);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzb) {
            zzfyxVar.cancel(((zzb) obj).zzc);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) & (!(obj instanceof zzf))) {
                return zzB(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                zzk zzkVar = this.waiters;
                if (zzkVar != zzk.zza) {
                    zzk zzkVar2 = new zzk();
                    do {
                        zza zzaVar = zzaW;
                        zzaVar.zzc(zzkVar2, zzkVar);
                        if (zzaVar.zzg(this, zzkVar, zzkVar2)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (Thread.interrupted()) {
                                    zzA(zzkVar2);
                                    throw new InterruptedException();
                                }
                                Object obj2 = this.value;
                                if (!((obj2 != null) & (!(obj2 instanceof zzf)))) {
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    return zzB(obj2);
                                }
                            } while (nanos >= 1000);
                            zzA(zzkVar2);
                        } else {
                            zzkVar = this.waiters;
                        }
                    } while (zzkVar != zzk.zza);
                    Object obj3 = this.value;
                    obj3.getClass();
                    return zzB(obj3);
                }
                Object obj32 = this.value;
                obj32.getClass();
                return zzB(obj32);
            }
            while (nanos > 0) {
                Object obj4 = this.value;
                if (!((obj4 != null) & (!(obj4 instanceof zzf)))) {
                    if (!Thread.interrupted()) {
                        nanos = nanoTime - System.nanoTime();
                    } else {
                        throw new InterruptedException();
                    }
                } else {
                    return zzB(obj4);
                }
            }
            String zzfxfVar = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j + " " + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j2 = -nanos;
                long convert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(convert);
                int r3 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
                if (r3 != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (r3 > 0) {
                    String str2 = concat + convert + " " + lowerCase;
                    if (z) {
                        str2 = str2.concat(",");
                    }
                    concat = str2.concat(" ");
                }
                if (z) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + zzfxfVar);
        }
        throw new InterruptedException();
    }
}
