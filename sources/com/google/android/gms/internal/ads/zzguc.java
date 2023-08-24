package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzguc implements Iterator, Closeable, zzalo {
    private static final zzaln zza = new zzgub("eof ");
    private static final zzguj zzb = zzguj.zzb(zzguc.class);
    protected zzalk zzc;
    protected zzgud zzd;
    zzaln zze = null;
    long zzf = 0;
    long zzg = 0;
    private final List zzh = new ArrayList();

    public void close() throws IOException {
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        zzaln zzalnVar = this.zze;
        if (zzalnVar == zza) {
            return false;
        }
        if (zzalnVar != null) {
            return true;
        }
        try {
            this.zze = next();
            return true;
        } catch (NoSuchElementException unused) {
            this.zze = zza;
            return false;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        for (int r1 = 0; r1 < this.zzh.size(); r1++) {
            if (r1 > 0) {
                sb.append(";");
            }
            sb.append(((zzaln) this.zzh.get(r1)).toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.Iterator
    /* renamed from: zzd */
    public final zzaln next() {
        zzaln zzb2;
        zzaln zzalnVar = this.zze;
        if (zzalnVar == null || zzalnVar == zza) {
            zzgud zzgudVar = this.zzd;
            if (zzgudVar == null || this.zzf >= this.zzg) {
                this.zze = zza;
                throw new NoSuchElementException();
            }
            try {
                synchronized (zzgudVar) {
                    this.zzd.zze(this.zzf);
                    zzb2 = this.zzc.zzb(this.zzd, this);
                    this.zzf = this.zzd.zzb();
                }
                return zzb2;
            } catch (EOFException unused) {
                throw new NoSuchElementException();
            } catch (IOException unused2) {
                throw new NoSuchElementException();
            }
        }
        this.zze = null;
        return zzalnVar;
    }

    public final List zze() {
        return (this.zzd == null || this.zze == zza) ? this.zzh : new zzgui(this.zzh, this);
    }

    public final void zzf(zzgud zzgudVar, long j, zzalk zzalkVar) throws IOException {
        this.zzd = zzgudVar;
        this.zzf = zzgudVar.zzb();
        zzgudVar.zze(zzgudVar.zzb() + j);
        this.zzg = zzgudVar.zzb();
        this.zzc = zzalkVar;
    }
}
