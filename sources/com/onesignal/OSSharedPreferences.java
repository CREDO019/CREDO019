package com.onesignal;

import java.util.Set;

/* loaded from: classes3.dex */
public interface OSSharedPreferences {
    boolean getBool(String str, String str2, boolean z);

    int getInt(String str, String str2, int r3);

    long getLong(String str, String str2, long j);

    Object getObject(String str, String str2, Object obj);

    String getOutcomesV2KeyName();

    String getPreferencesName();

    String getString(String str, String str2, String str3);

    Set<String> getStringSet(String str, String str2, Set<String> set);

    void saveBool(String str, String str2, boolean z);

    void saveInt(String str, String str2, int r3);

    void saveLong(String str, String str2, long j);

    void saveObject(String str, String str2, Object obj);

    void saveString(String str, String str2, String str3);

    void saveStringSet(String str, String str2, Set<String> set);
}
