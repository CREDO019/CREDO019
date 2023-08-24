package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes3.dex */
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Parcelable.Creator<zzi> CREATOR = new zzk();
    private static final Comparator<zzi> zzai = new zzj();
    public final String name;
    private final long zzab;
    private final boolean zzac;
    private final double zzad;
    private final String zzae;
    private final byte[] zzaf;
    private final int zzag;
    public final int zzah;

    public zzi(String str, long j, boolean z, double d, String str2, byte[] bArr, int r9, int r10) {
        this.name = str;
        this.zzab = j;
        this.zzac = z;
        this.zzad = d;
        this.zzae = str2;
        this.zzaf = bArr;
        this.zzag = r9;
        this.zzah = r10;
    }

    private static int compare(int r0, int r1) {
        if (r0 < r1) {
            return -1;
        }
        return r0 == r1 ? 0 : 1;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzi zziVar) {
        zzi zziVar2 = zziVar;
        int compareTo = this.name.compareTo(zziVar2.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzag, zziVar2.zzag);
        if (compare != 0) {
            return compare;
        }
        int r0 = this.zzag;
        if (r0 == 1) {
            int r9 = (this.zzab > zziVar2.zzab ? 1 : (this.zzab == zziVar2.zzab ? 0 : -1));
            if (r9 < 0) {
                return -1;
            }
            return r9 == 0 ? 0 : 1;
        } else if (r0 == 2) {
            boolean z = this.zzac;
            if (z == zziVar2.zzac) {
                return 0;
            }
            return z ? 1 : -1;
        } else if (r0 != 3) {
            if (r0 == 4) {
                String str = this.zzae;
                String str2 = zziVar2.zzae;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            } else if (r0 != 5) {
                int r02 = this.zzag;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(r02);
                throw new AssertionError(sb.toString());
            } else {
                byte[] bArr = this.zzaf;
                byte[] bArr2 = zziVar2.zzaf;
                if (bArr == bArr2) {
                    return 0;
                }
                if (bArr == null) {
                    return -1;
                }
                if (bArr2 == null) {
                    return 1;
                }
                for (int r2 = 0; r2 < Math.min(this.zzaf.length, zziVar2.zzaf.length); r2++) {
                    int r03 = this.zzaf[r2] - zziVar2.zzaf[r2];
                    if (r03 != 0) {
                        return r03;
                    }
                }
                return compare(this.zzaf.length, zziVar2.zzaf.length);
            }
        } else {
            return Double.compare(this.zzad, zziVar2.zzad);
        }
    }

    public final boolean equals(Object obj) {
        int r0;
        if (obj instanceof zzi) {
            zzi zziVar = (zzi) obj;
            if (zzn.equals(this.name, zziVar.name) && (r0 = this.zzag) == zziVar.zzag && this.zzah == zziVar.zzah) {
                if (r0 != 1) {
                    if (r0 == 2) {
                        return this.zzac == zziVar.zzac;
                    } else if (r0 == 3) {
                        return this.zzad == zziVar.zzad;
                    } else if (r0 != 4) {
                        if (r0 == 5) {
                            return Arrays.equals(this.zzaf, zziVar.zzaf);
                        }
                        int r02 = this.zzag;
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Invalid enum value: ");
                        sb.append(r02);
                        throw new AssertionError(sb.toString());
                    } else {
                        return zzn.equals(this.zzae, zziVar.zzae);
                    }
                } else if (this.zzab == zziVar.zzab) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        int r2 = this.zzag;
        if (r2 == 1) {
            sb.append(this.zzab);
        } else if (r2 == 2) {
            sb.append(this.zzac);
        } else if (r2 != 3) {
            if (r2 == 4) {
                sb.append("'");
                str = this.zzae;
            } else if (r2 != 5) {
                String str2 = this.name;
                int r3 = this.zzag;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 27);
                sb2.append("Invalid type: ");
                sb2.append(str2);
                sb2.append(", ");
                sb2.append(r3);
                throw new AssertionError(sb2.toString());
            } else if (this.zzaf == null) {
                sb.append("null");
            } else {
                sb.append("'");
                str = Base64.encodeToString(this.zzaf, 3);
            }
            sb.append(str);
            sb.append("'");
        } else {
            sb.append(this.zzad);
        }
        sb.append(", ");
        sb.append(this.zzag);
        sb.append(", ");
        sb.append(this.zzah);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzab);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzac);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzad);
        SafeParcelWriter.writeString(parcel, 6, this.zzae, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzaf, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzag);
        SafeParcelWriter.writeInt(parcel, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
