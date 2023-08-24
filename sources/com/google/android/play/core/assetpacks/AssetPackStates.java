package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class AssetPackStates {
    /* renamed from: a */
    public static AssetPackStates m1036a(long j, Map<String, AssetPackState> map) {
        return new C2389bi(j, map);
    }

    /* renamed from: a */
    public static AssetPackStates m1035a(Bundle bundle, C2406bz c2406bz) {
        return m1033a(bundle, c2406bz, new ArrayList());
    }

    /* renamed from: a */
    public static AssetPackStates m1034a(Bundle bundle, C2406bz c2406bz, InterfaceC2379az interfaceC2379az) {
        return m1032a(bundle, c2406bz, new ArrayList(), interfaceC2379az);
    }

    /* renamed from: a */
    public static AssetPackStates m1033a(Bundle bundle, C2406bz c2406bz, List<String> list) {
        return m1032a(bundle, c2406bz, list, C2381ba.f470a);
    }

    /* renamed from: a */
    private static AssetPackStates m1032a(Bundle bundle, C2406bz c2406bz, List<String> list, InterfaceC2379az interfaceC2379az) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        HashMap hashMap = new HashMap();
        int size = stringArrayList.size();
        for (int r5 = 0; r5 < size; r5++) {
            String str = stringArrayList.get(r5);
            hashMap.put(str, AssetPackState.m1038a(bundle, str, c2406bz, interfaceC2379az));
        }
        int size2 = list.size();
        for (int r4 = 0; r4 < size2; r4++) {
            String str2 = list.get(r4);
            hashMap.put(str2, AssetPackState.m1037a(str2, 4, 0, 0L, 0L, 0.0d));
        }
        return m1036a(bundle.getLong("total_bytes_to_download"), hashMap);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
