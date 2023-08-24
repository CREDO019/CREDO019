package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzac> CREATOR = new zzab();
    private final float zzdo;
    public final String zzec;
    public final zzaj[] zzeh;
    public final zzw zzei;
    private final zzw zzej;
    private final zzw zzek;
    public final String zzel;
    private final int zzem;
    public final boolean zzen;
    public final int zzeo;
    public final int zzep;

    public zzac(zzaj[] zzajVarArr, zzw zzwVar, zzw zzwVar2, zzw zzwVar3, String str, float f, String str2, int r8, boolean z, int r10, int r11) {
        this.zzeh = zzajVarArr;
        this.zzei = zzwVar;
        this.zzej = zzwVar2;
        this.zzek = zzwVar3;
        this.zzel = str;
        this.zzdo = f;
        this.zzec = str2;
        this.zzem = r8;
        this.zzen = z;
        this.zzeo = r10;
        this.zzep = r11;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzeh, r6, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzei, r6, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzej, r6, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzek, r6, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzel, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzdo);
        SafeParcelWriter.writeString(parcel, 8, this.zzec, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzem);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzen);
        SafeParcelWriter.writeInt(parcel, 11, this.zzeo);
        SafeParcelWriter.writeInt(parcel, 12, this.zzep);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
