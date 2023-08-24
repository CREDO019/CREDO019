package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ChapterFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzacp extends zzada {
    public static final Parcelable.Creator<zzacp> CREATOR = new zzaco();
    public final String zza;
    public final int zzb;
    public final int zzc;
    public final long zzd;
    public final long zze;
    private final zzada[] zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzacp(Parcel parcel) {
        super(ChapterFrame.f225ID);
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = parcel.readInt();
        this.zzc = parcel.readInt();
        this.zzd = parcel.readLong();
        this.zze = parcel.readLong();
        int readInt = parcel.readInt();
        this.zzg = new zzada[readInt];
        for (int r12 = 0; r12 < readInt; r12++) {
            this.zzg[r12] = (zzada) parcel.readParcelable(zzada.class.getClassLoader());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzada, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzacp zzacpVar = (zzacp) obj;
            if (this.zzb == zzacpVar.zzb && this.zzc == zzacpVar.zzc && this.zzd == zzacpVar.zzd && this.zze == zzacpVar.zze && zzel.zzT(this.zza, zzacpVar.zza) && Arrays.equals(this.zzg, zzacpVar.zzg)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = (((((((this.zzb + 527) * 31) + this.zzc) * 31) + ((int) this.zzd)) * 31) + ((int) this.zze)) * 31;
        String str = this.zza;
        return r0 + (str != null ? str.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        parcel.writeString(this.zza);
        parcel.writeInt(this.zzb);
        parcel.writeInt(this.zzc);
        parcel.writeLong(this.zzd);
        parcel.writeLong(this.zze);
        parcel.writeInt(this.zzg.length);
        for (zzada zzadaVar : this.zzg) {
            parcel.writeParcelable(zzadaVar, 0);
        }
    }

    public zzacp(String str, int r3, int r4, long j, long j2, zzada[] zzadaVarArr) {
        super(ChapterFrame.f225ID);
        this.zza = str;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = j;
        this.zze = j2;
        this.zzg = zzadaVarArr;
    }
}
