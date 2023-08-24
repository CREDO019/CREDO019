package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzadg extends zzada {
    public static final Parcelable.Creator<zzadg> CREATOR = new zzadf();
    public final String zza;
    public final byte[] zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzadg(Parcel parcel) {
        super(PrivFrame.f232ID);
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = (byte[]) zzel.zzH(parcel.createByteArray());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzadg zzadgVar = (zzadg) obj;
            if (zzel.zzT(this.zza, zzadgVar.zza) && Arrays.equals(this.zzb, zzadgVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.zza;
        return (((str != null ? str.hashCode() : 0) + 527) * 31) + Arrays.hashCode(this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzada
    public final String toString() {
        String str = this.zzf;
        String str2 = this.zza;
        return str + ": owner=" + str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zza);
        parcel.writeByteArray(this.zzb);
    }

    public zzadg(String str, byte[] bArr) {
        super(PrivFrame.f232ID);
        this.zza = str;
        this.zzb = bArr;
    }
}
