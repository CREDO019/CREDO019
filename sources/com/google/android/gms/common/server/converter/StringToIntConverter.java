package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.FieldConverter<String, Integer> {
    public static final Parcelable.Creator<StringToIntConverter> CREATOR = new zad();
    final int zaa;
    private final HashMap zab;
    private final SparseArray zac;

    public StringToIntConverter() {
        this.zaa = 1;
        this.zab = new HashMap();
        this.zac = new SparseArray();
    }

    public StringToIntConverter add(String str, int r4) {
        this.zab.put(str, Integer.valueOf(r4));
        this.zac.put(r4, str);
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r7) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zac(str, ((Integer) this.zab.get(str)).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zaa() {
        return 7;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final int zab() {
        return 0;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* bridge */ /* synthetic */ Object zac(Object obj) {
        Integer num = (Integer) this.zab.get((String) obj);
        return num == null ? (Integer) this.zab.get("gms_unknown") : num;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter
    public final /* bridge */ /* synthetic */ Object zad(Object obj) {
        String str = (String) this.zac.get(((Integer) obj).intValue());
        return (str == null && this.zab.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StringToIntConverter(int r4, ArrayList arrayList) {
        this.zaa = r4;
        this.zab = new HashMap();
        this.zac = new SparseArray();
        int size = arrayList.size();
        for (int r0 = 0; r0 < size; r0++) {
            zac zacVar = (zac) arrayList.get(r0);
            add(zacVar.zab, zacVar.zac);
        }
    }
}
