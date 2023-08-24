package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ax */
/* loaded from: classes3.dex */
public final class C2634ax {

    /* renamed from: a */
    private final Context f981a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2634ax(Context context) {
        this.f981a = context;
    }

    /* renamed from: b */
    private final SharedPreferences m536b() {
        return this.f981a.getSharedPreferences("playcore_split_install_internal", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized Set<String> m538a() {
        Set<String> stringSet;
        try {
            stringSet = m536b().getStringSet("deferred_uninstall_module_list", new HashSet());
            if (stringSet == null) {
                stringSet = new HashSet<>();
            }
        } catch (Exception unused) {
            return new HashSet();
        }
        return stringSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m537a(Collection<String> collection) {
        Set<String> m538a = m538a();
        boolean z = false;
        for (String str : collection) {
            z |= m538a.add(str);
        }
        if (z) {
            try {
                m536b().edit().putStringSet("deferred_uninstall_module_list", m538a).apply();
            } catch (Exception unused) {
            }
        }
    }
}
