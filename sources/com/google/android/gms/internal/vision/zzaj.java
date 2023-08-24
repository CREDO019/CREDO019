package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzam();
    private final float zzdo;
    public final String zzec;
    public final zzw zzei;
    private final zzw zzej;
    public final String zzel;
    private final zzag[] zzer;
    private final boolean zzes;

    public zzaj(zzag[] zzagVarArr, zzw zzwVar, zzw zzwVar2, String str, float f, String str2, boolean z) {
        this.zzer = zzagVarArr;
        this.zzei = zzwVar;
        this.zzej = zzwVar2;
        this.zzel = str;
        this.zzdo = f;
        this.zzec = str2;
        this.zzes = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r6) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzer, r6, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzei, r6, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzej, r6, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzel, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzdo);
        SafeParcelWriter.writeString(parcel, 7, this.zzec, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzes);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
