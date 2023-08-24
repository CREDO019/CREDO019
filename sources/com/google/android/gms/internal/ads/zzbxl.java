package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbxl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbxl> CREATOR = new zzbxm();
    public final int zza;
    public final int zzb;
    public final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxl(int r1, int r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zzbxl zza(VersionInfo versionInfo) {
        return new zzbxl(versionInfo.getMajorVersion(), versionInfo.getMinorVersion(), versionInfo.getMicroVersion());
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzbxl)) {
            zzbxl zzbxlVar = (zzbxl) obj;
            if (zzbxlVar.zzc == this.zzc && zzbxlVar.zzb == this.zzb && zzbxlVar.zza == this.zza) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new int[]{this.zza, this.zzb, this.zzc});
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = this.zzb;
        int r2 = this.zzc;
        return r0 + "." + r1 + "." + r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r4) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
