package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfct {
    public final int zza;
    public final int zzb;
    public final boolean zzc;

    public zzfct(int r1, int r2, boolean z) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List zza(JsonReader jsonReader) throws IllegalStateException, IOException, NumberFormatException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            int r1 = 0;
            int r2 = 0;
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if ("width".equals(nextName)) {
                    r1 = jsonReader.nextInt();
                } else if ("height".equals(nextName)) {
                    r2 = jsonReader.nextInt();
                } else if ("is_fluid_height".equals(nextName)) {
                    z = jsonReader.nextBoolean();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            arrayList.add(new zzfct(r1, r2, z));
        }
        jsonReader.endArray();
        return arrayList;
    }
}
