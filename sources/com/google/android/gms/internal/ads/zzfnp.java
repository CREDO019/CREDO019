package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfnp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfnp> CREATOR = new zzfnq();
    public final int zza;
    private zzamx zzb = null;
    private byte[] zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfnp(int r1, byte[] bArr) {
        this.zza = r1;
        this.zzc = bArr;
        zzb();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r5) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        byte[] bArr = this.zzc;
        if (bArr == null) {
            bArr = this.zzb.zzaw();
        }
        SafeParcelWriter.writeByteArray(parcel, 2, bArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzamx zza() {
        if (this.zzb == null) {
            try {
                this.zzb = zzamx.zze(this.zzc, zzgnz.zza());
                this.zzc = null;
            } catch (zzgoz | NullPointerException e) {
                throw new IllegalStateException(e);
            }
        }
        zzb();
        return this.zzb;
    }

    private final void zzb() {
        zzamx zzamxVar = this.zzb;
        if (zzamxVar != null || this.zzc == null) {
            if (zzamxVar == null || this.zzc != null) {
                if (zzamxVar == null || this.zzc == null) {
                    if (zzamxVar != null || this.zzc != null) {
                        throw new IllegalStateException("Impossible");
                    }
                    throw new IllegalStateException("Invalid internal representation - empty");
                }
                throw new IllegalStateException("Invalid internal representation - full");
            }
        }
    }
}
