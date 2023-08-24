package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ChapterTocFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacr extends zzada {
    public static final Parcelable.Creator<zzacr> CREATOR = new zzacq();
    public final String zza;
    public final boolean zzb;
    public final boolean zzc;
    public final String[] zzd;
    private final zzada[] zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacr(Parcel parcel) {
        super(ChapterTocFrame.f226ID);
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = parcel.readByte() != 0;
        this.zzc = parcel.readByte() != 0;
        this.zzd = (String[]) zzel.zzH(parcel.createStringArray());
        int readInt = parcel.readInt();
        this.zze = new zzada[readInt];
        for (int r2 = 0; r2 < readInt; r2++) {
            this.zze[r2] = (zzada) parcel.readParcelable(zzada.class.getClassLoader());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzacr zzacrVar = (zzacr) obj;
            if (this.zzb == zzacrVar.zzb && this.zzc == zzacrVar.zzc && zzel.zzT(this.zza, zzacrVar.zza) && Arrays.equals(this.zzd, zzacrVar.zzd) && Arrays.equals(this.zze, zzacrVar.zze)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = ((((this.zzb ? 1 : 0) + 527) * 31) + (this.zzc ? 1 : 0)) * 31;
        String str = this.zza;
        return r0 + (str != null ? str.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        parcel.writeString(this.zza);
        parcel.writeByte(this.zzb ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.zzc ? (byte) 1 : (byte) 0);
        parcel.writeStringArray(this.zzd);
        parcel.writeInt(this.zze.length);
        for (zzada zzadaVar : this.zze) {
            parcel.writeParcelable(zzadaVar, 0);
        }
    }

    public zzacr(String str, boolean z, boolean z2, String[] strArr, zzada[] zzadaVarArr) {
        super(ChapterTocFrame.f226ID);
        this.zza = str;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = strArr;
        this.zze = zzadaVarArr;
    }
}
