package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfuz extends zzfuo {
    @CheckForNull
    Object[] zzd;
    private int zze;

    public zzfuz() {
        super(4);
    }

    @Override // com.google.android.gms.internal.ads.zzfuo, com.google.android.gms.internal.ads.zzfup
    public final /* bridge */ /* synthetic */ zzfup zzb(Object obj) {
        zze(obj);
        return this;
    }

    public final zzfuz zzf(Iterable iterable) {
        if (this.zzd != null) {
            for (Object obj : iterable) {
                zze(obj);
            }
        } else {
            super.zzc(iterable);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuz(int r1) {
        super(r1);
        this.zzd = new Object[zzfva.zzh(r1)];
    }

    public final zzfva zzg() {
        zzfva zzp;
        boolean zzq;
        int r0 = this.zzb;
        if (r0 != 0) {
            if (r0 == 1) {
                Object obj = this.zza[0];
                obj.getClass();
                return new zzfwr(obj);
            }
            if (this.zzd == null || zzfva.zzh(r0) != this.zzd.length) {
                zzp = zzfva.zzp(this.zzb, this.zza);
                this.zzb = zzp.size();
            } else {
                int r02 = this.zzb;
                Object[] objArr = this.zza;
                zzq = zzfva.zzq(r02, objArr.length);
                if (zzq) {
                    objArr = Arrays.copyOf(objArr, r02);
                }
                int r5 = this.zze;
                Object[] objArr2 = this.zzd;
                zzp = new zzfwk(objArr, r5, objArr2, objArr2.length - 1, this.zzb);
            }
            this.zzc = true;
            this.zzd = null;
            return zzp;
        }
        return zzfwk.zza;
    }

    public final zzfuz zze(Object obj) {
        Objects.requireNonNull(obj);
        if (this.zzd != null) {
            int zzh = zzfva.zzh(this.zzb);
            Object[] objArr = this.zzd;
            int length = objArr.length;
            if (zzh <= length) {
                objArr.getClass();
                int r2 = length - 1;
                int hashCode = obj.hashCode();
                int zza = zzfun.zza(hashCode);
                while (true) {
                    int r1 = zza & r2;
                    Object[] objArr2 = this.zzd;
                    Object obj2 = objArr2[r1];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zza = r1 + 1;
                    } else {
                        objArr2[r1] = obj;
                        this.zze += hashCode;
                        super.zza(obj);
                        break;
                    }
                }
                return this;
            }
        }
        this.zzd = null;
        super.zza(obj);
        return this;
    }
}
