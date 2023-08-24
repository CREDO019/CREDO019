package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public class zzach implements zzbp {
    public static final Parcelable.Creator<zzach> CREATOR = new zzacg();
    public final String zza;
    public final String zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzach(Parcel parcel) {
        String readString = parcel.readString();
        int r1 = zzel.zza;
        this.zza = readString;
        this.zzb = parcel.readString();
    }

    public zzach(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzach zzachVar = (zzach) obj;
            if (this.zza.equals(zzachVar.zza) && this.zzb.equals(zzachVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() + 527) * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return "VC: " + str + "=" + str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r2) {
        parcel.writeString(this.zza);
        parcel.writeString(this.zzb);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.ads.zzbp
    public final void zza(zzbk zzbkVar) {
        char c;
        String str = this.zza;
        switch (str.hashCode()) {
            case 62359119:
                if (str.equals("ALBUM")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 79833656:
                if (str.equals("TITLE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 428414940:
                if (str.equals("DESCRIPTION")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1746739798:
                if (str.equals("ALBUMARTIST")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1939198791:
                if (str.equals("ARTIST")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            zzbkVar.zzr(this.zzb);
        } else if (c == 1) {
            zzbkVar.zze(this.zzb);
        } else if (c == 2) {
            zzbkVar.zzd(this.zzb);
        } else if (c == 3) {
            zzbkVar.zzc(this.zzb);
        } else if (c != 4) {
        } else {
            zzbkVar.zzi(this.zzb);
        }
    }
}
