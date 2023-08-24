package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzgoj;
import com.google.android.gms.internal.ads.zzgon;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgon<MessageType extends zzgon<MessageType, BuilderType>, BuilderType extends zzgoj<MessageType, BuilderType>> extends zzgmo<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzgri zzc = zzgri.zzc();
    protected int zzd = -1;

    private static zzgon zza(zzgon zzgonVar) throws zzgoz {
        if (zzgonVar == null || zzgonVar.zzaR()) {
            return zzgonVar;
        }
        zzgoz zza = new zzgrg(zzgonVar).zza();
        zza.zzh(zzgonVar);
        throw zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgon zzaA(Class cls) {
        Map map = zzb;
        zzgon zzgonVar = (zzgon) map.get(cls);
        if (zzgonVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzgonVar = (zzgon) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzgonVar == null) {
            zzgonVar = (zzgon) ((zzgon) zzgrr.zzg(cls)).zzb(6, null, null);
            if (zzgonVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzgonVar);
        }
        return zzgonVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgon zzaB(zzgon zzgonVar, zzgnf zzgnfVar) throws zzgoz {
        zzgnz zza = zzgnz.zza();
        zzgnn zzl = zzgnfVar.zzl();
        zzgon zzgonVar2 = (zzgon) zzgonVar.zzb(4, null, null);
        try {
            zzgqq zzb2 = zzgqf.zza().zzb(zzgonVar2.getClass());
            zzb2.zzh(zzgonVar2, zzgno.zzq(zzl), zza);
            zzb2.zzf(zzgonVar2);
            try {
                zzl.zzz(0);
                zza(zzgonVar2);
                zza(zzgonVar2);
                return zzgonVar2;
            } catch (zzgoz e) {
                e.zzh(zzgonVar2);
                throw e;
            }
        } catch (zzgoz e2) {
            e = e2;
            if (e.zzl()) {
                e = new zzgoz(e);
            }
            e.zzh(zzgonVar2);
            throw e;
        } catch (zzgrg e3) {
            zzgoz zza2 = e3.zza();
            zza2.zzh(zzgonVar2);
            throw zza2;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzgoz) {
                throw ((zzgoz) e4.getCause());
            }
            zzgoz zzgozVar = new zzgoz(e4);
            zzgozVar.zzh(zzgonVar2);
            throw zzgozVar;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzgoz) {
                throw ((zzgoz) e5.getCause());
            }
            throw e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgon zzaC(zzgon zzgonVar, byte[] bArr) throws zzgoz {
        zzgon zzc = zzc(zzgonVar, bArr, 0, bArr.length, zzgnz.zza());
        zza(zzc);
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgon zzaD(zzgon zzgonVar, zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        zzgnn zzl = zzgnfVar.zzl();
        zzgon zzgonVar2 = (zzgon) zzgonVar.zzb(4, null, null);
        try {
            zzgqq zzb2 = zzgqf.zza().zzb(zzgonVar2.getClass());
            zzb2.zzh(zzgonVar2, zzgno.zzq(zzl), zzgnzVar);
            zzb2.zzf(zzgonVar2);
            try {
                zzl.zzz(0);
                zza(zzgonVar2);
                return zzgonVar2;
            } catch (zzgoz e) {
                e.zzh(zzgonVar2);
                throw e;
            }
        } catch (zzgoz e2) {
            e = e2;
            if (e.zzl()) {
                e = new zzgoz(e);
            }
            e.zzh(zzgonVar2);
            throw e;
        } catch (zzgrg e3) {
            zzgoz zza = e3.zza();
            zza.zzh(zzgonVar2);
            throw zza;
        } catch (IOException e4) {
            if (e4.getCause() instanceof zzgoz) {
                throw ((zzgoz) e4.getCause());
            }
            zzgoz zzgozVar = new zzgoz(e4);
            zzgozVar.zzh(zzgonVar2);
            throw zzgozVar;
        } catch (RuntimeException e5) {
            if (e5.getCause() instanceof zzgoz) {
                throw ((zzgoz) e5.getCause());
            }
            throw e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgon zzaE(zzgon zzgonVar, InputStream inputStream, zzgnz zzgnzVar) throws zzgoz {
        zzgnn zzH = zzgnn.zzH(inputStream, 4096);
        zzgon zzgonVar2 = (zzgon) zzgonVar.zzb(4, null, null);
        try {
            zzgqq zzb2 = zzgqf.zza().zzb(zzgonVar2.getClass());
            zzb2.zzh(zzgonVar2, zzgno.zzq(zzH), zzgnzVar);
            zzb2.zzf(zzgonVar2);
            zza(zzgonVar2);
            return zzgonVar2;
        } catch (zzgoz e) {
            e = e;
            if (e.zzl()) {
                e = new zzgoz(e);
            }
            e.zzh(zzgonVar2);
            throw e;
        } catch (zzgrg e2) {
            zzgoz zza = e2.zza();
            zza.zzh(zzgonVar2);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzgoz) {
                throw ((zzgoz) e3.getCause());
            }
            zzgoz zzgozVar = new zzgoz(e3);
            zzgozVar.zzh(zzgonVar2);
            throw zzgozVar;
        } catch (RuntimeException e4) {
            if (e4.getCause() instanceof zzgoz) {
                throw ((zzgoz) e4.getCause());
            }
            throw e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgon zzaF(zzgon zzgonVar, byte[] bArr, zzgnz zzgnzVar) throws zzgoz {
        zzgon zzc = zzc(zzgonVar, bArr, 0, bArr.length, zzgnzVar);
        zza(zzc);
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgos zzaG() {
        return zzgoo.zzf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgos zzaH(zzgos zzgosVar) {
        int size = zzgosVar.size();
        return zzgosVar.zzg(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgov zzaI() {
        return zzgpm.zzf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgow zzaJ() {
        return zzgqg.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzgow zzaK(zzgow zzgowVar) {
        int size = zzgowVar.size();
        return zzgowVar.zzd(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzaN(Method method, Object obj, Object... objArr) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzaO(zzgpx zzgpxVar, String str, Object[] objArr) {
        return new zzgqh(zzgpxVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzaP(Class cls, zzgon zzgonVar) {
        zzb.put(cls, zzgonVar);
    }

    private static zzgon zzc(zzgon zzgonVar, byte[] bArr, int r8, int r9, zzgnz zzgnzVar) throws zzgoz {
        zzgon zzgonVar2 = (zzgon) zzgonVar.zzb(4, null, null);
        try {
            zzgqq zzb2 = zzgqf.zza().zzb(zzgonVar2.getClass());
            zzb2.zzi(zzgonVar2, bArr, 0, r9, new zzgmr(zzgnzVar));
            zzb2.zzf(zzgonVar2);
            if (zzgonVar2.zza == 0) {
                return zzgonVar2;
            }
            throw new RuntimeException();
        } catch (zzgoz e) {
            e = e;
            if (e.zzl()) {
                e = new zzgoz(e);
            }
            e.zzh(zzgonVar2);
            throw e;
        } catch (zzgrg e2) {
            zzgoz zza = e2.zza();
            zza.zzh(zzgonVar2);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzgoz) {
                throw ((zzgoz) e3.getCause());
            }
            zzgoz zzgozVar = new zzgoz(e3);
            zzgozVar.zzh(zzgonVar2);
            throw zzgozVar;
        } catch (IndexOutOfBoundsException unused) {
            zzgoz zzj = zzgoz.zzj();
            zzj.zzh(zzgonVar2);
            throw zzj;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzgqf.zza().zzb(getClass()).zzj(this, (zzgon) obj);
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza;
        if (r0 != 0) {
            return r0;
        }
        int zzb2 = zzgqf.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzgpz.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final /* synthetic */ zzgpw zzaL() {
        return (zzgoj) zzb(5, null, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final /* synthetic */ zzgpw zzaM() {
        zzgoj zzgojVar = (zzgoj) zzb(5, null, null);
        zzgojVar.zzaj(this);
        return zzgojVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final void zzaQ(zzgnu zzgnuVar) throws IOException {
        zzgqf.zza().zzb(getClass()).zzn(this, zzgnv.zza(zzgnuVar));
    }

    public final boolean zzaR() {
        Boolean bool = Boolean.TRUE;
        byte byteValue = ((Byte) zzb(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzgqf.zza().zzb(getClass()).zzk(this);
        zzb(2, true != zzk ? null : this, null);
        return zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgmo
    public final int zzar() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgmo
    public final void zzau(int r1) {
        this.zzd = r1;
    }

    @Override // com.google.android.gms.internal.ads.zzgpx
    public final int zzax() {
        int r0 = this.zzd;
        if (r0 == -1) {
            int zza = zzgqf.zza().zzb(getClass()).zza(this);
            this.zzd = zza;
            return zza;
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzgoj zzay() {
        return (zzgoj) zzb(5, null, null);
    }

    public final zzgoj zzaz() {
        zzgoj zzgojVar = (zzgoj) zzb(5, null, null);
        zzgojVar.zzaj(this);
        return zzgojVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzb(int r1, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.ads.zzgpy
    public final /* synthetic */ zzgpx zzbh() {
        return (zzgon) zzb(6, null, null);
    }
}
