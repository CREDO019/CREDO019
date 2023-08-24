package com.google.android.play.core.assetpacks;

import android.os.ParcelFileDescriptor;
import com.google.android.play.core.tasks.Task;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.w */
/* loaded from: classes3.dex */
public interface InterfaceC2478w {
    /* renamed from: a */
    Task<AssetPackStates> mo828a(List<String> list, InterfaceC2379az interfaceC2379az, Map<String, Long> map);

    /* renamed from: a */
    Task<AssetPackStates> mo827a(List<String> list, List<String> list2, Map<String, Long> map);

    /* renamed from: a */
    Task<List<String>> mo826a(Map<String, Long> map);

    /* renamed from: a */
    void mo834a();

    /* renamed from: a */
    void mo833a(int r1);

    /* renamed from: a */
    void mo832a(int r1, String str);

    /* renamed from: a */
    void mo831a(int r1, String str, String str2, int r4);

    /* renamed from: a */
    void mo830a(String str);

    /* renamed from: a */
    void mo829a(List<String> list);

    /* renamed from: b */
    Task<ParcelFileDescriptor> mo825b(int r1, String str, String str2, int r4);
}
